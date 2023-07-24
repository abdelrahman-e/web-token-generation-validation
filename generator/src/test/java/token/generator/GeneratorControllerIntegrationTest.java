package token.generator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class GeneratorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetGeneratedTokenWithValidDigits() throws Exception {

        mockMvc.perform(post("/token/generate?availableDigits=1,2,3"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetGeneratedTokenWithInvalidDigits() throws Exception {
        mockMvc.perform(post("/token/generate?availableDigits=-1,10"))
                .andExpect(status().isBadRequest());
    }
}
