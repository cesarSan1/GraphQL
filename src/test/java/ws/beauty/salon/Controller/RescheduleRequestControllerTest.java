package ws.beauty.salon.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put; 
import static org.hamcrest.Matchers.hasSize; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.http.MediaType;
import jakarta.persistence.EntityNotFoundException;
import ws.beauty.salon.controller.RescheduleRequestController;
import ws.beauty.salon.dto.RescheduleRequestDTO;
import ws.beauty.salon.dto.RescheduleRequestResponse;
import ws.beauty.salon.service.RescheduleRequestService;

@WebMvcTest(controllers = RescheduleRequestController.class) 
class RescheduleRequestControllerTest {
    
    @Autowired 
    private MockMvc mvc;
    @Autowired 
    private ObjectMapper mapper;
    @Autowired 
    private RescheduleRequestService service;

    private static final String BASE_URL = "/api/reschedule-request";  

    @BeforeEach
    void setup() {
        reset(service);
        mapper.registerModule(new JavaTimeModule()); 
    }

    @TestConfiguration
    static class Config {
        @Bean
        RescheduleRequestService rescheduleRequestService() {
            return mock(RescheduleRequestService.class);
        }
        @Bean
        ObjectMapper objectMapper() {
            ObjectMapper mapper = new ObjectMapper();
            // Registrar módulo JavaTime para LocalDateTime**
            mapper.registerModule(new JavaTimeModule());
            return mapper;
        }
    }

    private RescheduleRequestResponse mockResponse(Integer id, String status) {
        return RescheduleRequestResponse.builder()
                .idRequest(id)
                .appointmentId(2)
                .clientId(3)
                .requestedDate("2025-11-30T13:00:00")
                .reason("Cambio por fuerza mayor")
                .status(status)
                .build();
    }
    private RescheduleRequestDTO mockRequest() {
        return RescheduleRequestDTO.builder()
                .appointmentId(1)
                .clientId(2)
                .requestedDate("2025-11-30T13:00:00")
                .status("pending") 
                .reason("Conflicto de horario")
                .build();
    }
    @Test
    @DisplayName("GET /reschedule-requests → 200 OK")
    void getAll_shouldReturnList() throws Exception {
        when(service.findAll()).thenReturn(List.of(mockResponse(1, "pending")));

        mvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].status").value("pending"));
    }
    @Test
    @DisplayName("GET /reschedule-requests/{id} existente → 200 OK")
    void getById_shouldReturnItem() throws Exception {
        when(service.findById(1)).thenReturn(mockResponse(1, "approved"));

        mvc.perform(get(BASE_URL + "/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("approved"));
    }
    /*@Test
    @DisplayName("GET /reschedule-request/{id} inexistente → 404")
    void getById_notFound() throws Exception {
        when(service.findById(2)).thenThrow(new EntityNotFoundException("Not found"));

        mvc.perform(get(BASE_URL + "/{id}", 2))
                .andExpect(status().isNotFound());
    }*/

    @Test
    @DisplayName("POST válido → 201 Created")
    void create_shouldReturnCreated() throws Exception {
        var request = mockRequest();
        var response = mockResponse(10, "pending");
        when(service.create(any())).thenReturn(response);

        mvc.perform(post(BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10));
    }
    @Test
    @DisplayName("POST inválido → 400 Bad Request")
    void create_invalidRequest() throws Exception {
        mvc.perform(post(BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("PUT válido → 200 OK")
    void update_shouldReturnUpdated() throws Exception {
        var request = mockRequest();
        var response = mockResponse(1, "approved");
        when(service.update(eq(1), any())).thenReturn(response);

        mvc.perform(put(BASE_URL + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("approved"));
        }

    @Test
    @DisplayName("PUT inexistente → 404 Not Found")
    void update_notFound() throws Exception {
        when(service.update(eq(3), any())).thenThrow(new EntityNotFoundException("Not found"));

        mvc.perform(put(BASE_URL + "/{id}", 99)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(mockRequest())))
                .andExpect(status().isNotFound());
    }
}

