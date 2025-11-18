package ws.beauty.salon.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.MediaType; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;  
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

import ws.beauty.salon.controller.ServicePriceHistoryController;
import ws.beauty.salon.dto.ServicePriceHistoryDTO;
import ws.beauty.salon.dto.ServicePriceHistoryResponse;
import ws.beauty.salon.service.ServicePriceHistoryService;


@WebMvcTest(controllers = ServicePriceHistoryController.class)
class ServicePriceHistoryControllerTest {
    @Autowired 
    private MockMvc mvc;
    @Autowired 
    private ObjectMapper mapper;
    @Autowired 
    private ServicePriceHistoryService service;

    private static final String BASE_URL = "/api/service_price-history"; 

    @BeforeEach 
    void setup() { 
        reset(service); 
        mapper.registerModule(new JavaTimeModule());
    }

    @TestConfiguration
    static class Config {
        @Bean
        ServicePriceHistoryService servicePriceHistoryService() {
            return mock(ServicePriceHistoryService.class);
        }
        @Bean
        ObjectMapper objectMapper() {
            ObjectMapper mapper = new ObjectMapper();
            // Registrar módulo JavaTime para LocalDateTime**
            mapper.registerModule(new JavaTimeModule());
            return mapper;
        }
    }

    private ServicePriceHistoryResponse mockResponse(int id, double oldP, double newP) {
        return ServicePriceHistoryResponse.builder()
                .idPrice(1)
                .idService(1)
                .oldPrice(oldP)
                .newPrice(newP)
                .changedAt(LocalDateTime.now())
                .build();
    }

    private ServicePriceHistoryDTO mockRequest(double oldP, double newP) {
        return ServicePriceHistoryDTO.builder()
                .serviceId(1)
                .oldPrice(oldP)
                .newPrice(newP)
                .build();
    }

    @Test
    @DisplayName("GET all → 200 OK")
    void getAll_shouldReturnList() throws Exception {
        when(service.findAll()).thenReturn(List.of(mockResponse(1, 100, 120)));

        mvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].new_price").value(120));
    }

    @Test
    @DisplayName("GET by ID → 200 OK")
    void getById_shouldReturnItem() throws Exception {
        when(service.findById(1)).thenReturn(mockResponse(1, 100, 120));

        mvc.perform(get(BASE_URL + "/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.old_price").value(100));
    }

    @Test
    @DisplayName("POST válido → 201 Created")
    void create_shouldReturnCreated() throws Exception {
        when(service.create(any())).thenReturn(mockResponse(1, 100, 120));

        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockRequest(100, 120))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.new_price").value(120));
    }

    @Test
    @DisplayName("PUT válido → 200 OK")
    void update_shouldReturnUpdated() throws Exception {
        when(service.update(eq(1), any())).thenReturn(mockResponse(1, 90, 110));

        mvc.perform(put(BASE_URL + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockRequest(90, 110))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.new_price").value(110));
    }
}
