package ws.beauty.salon.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize; 
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType; 


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ws.beauty.salon.BeautySalonApplication.class)
public class RescheduleRequestControllerTestSP {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllTest() throws Exception {
        mvc.perform(get("http://localhost:8080/api/v1/reschedule_requests")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void getByIdTest() throws Exception {
        mvc.perform(get("http://localhost:8080/api/v1/reschedule_requests/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("pending")));
    }

    @Test
    public void getByIdNotFoundTest() throws Exception {
        mvc.perform(get("http://localhost:8080/api/v1/reschedule_requests/9999")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }
}
