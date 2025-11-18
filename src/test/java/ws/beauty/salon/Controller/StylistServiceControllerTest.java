package ws.beauty.salon.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.List;
import org.springframework.http.MediaType; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import ws.beauty.salon.controller.StylistServiceController;
import ws.beauty.salon.dto.StylistServiceDTO;
import ws.beauty.salon.dto.StylistServiceResponse;
import ws.beauty.salon.service.StylistServiceService;

@WebMvcTest(controllers = StylistServiceController.class)
class StylistServiceControllerTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;
    @Autowired StylistServiceService service;

    private static final String BASE_URL = "/api/v1/stylist_services";

    @BeforeEach void setup() { reset(service); }

    @TestConfiguration
    static class Config {
        @Bean
        StylistServiceService stylistServiceService() {
            return mock(StylistServiceService.class);
        }
    }

    private StylistServiceResponse mockResponse(int stylistId, int serviceId) {
        return StylistServiceResponse.builder()
                .stylistId(stylistId)
                .serviceId(serviceId)
                .build();
    }

    private StylistServiceDTO mockRequest(int stylistId, int serviceId) {
        return StylistServiceDTO.builder()
                .stylistId(stylistId)
                .serviceId(serviceId)
                .build();
    }

    @Test
    @DisplayName("GET all → 200 OK")
    void getAll_shouldReturnList() throws Exception {
        when(service.findAll()).thenReturn(List.of(mockResponse(1, 1), mockResponse(2, 2)));

        mvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stylistId").value(1));
    }

    @Test
    @DisplayName("POST válido → 201 Created")
    void create_shouldReturnCreated() throws Exception {
        when(service.create(any())).thenReturn(mockResponse(3, 4));

        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockRequest(3, 4))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serviceId").value(4));
    }
}
