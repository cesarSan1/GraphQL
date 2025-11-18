package ws.beauty.salon.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType; 

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ws.beauty.salon.BeautySalonApplication.class)
public class StylistServiceControllerTestWOJson {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllTest() throws Exception {
        mvc.perform(get("http://localhost:8080/api/v1/stylist_services")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getByStylistIdTest() throws Exception {
        mvc.perform(get("http://localhost:8080/api/v1/stylist_services/stylist/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
