package com.patrimoine;

import com.patrimoine.Patrimoine;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatrimoineControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        objectMapper = new ObjectMapper();
    }

    @Test
    void testPutPatrimoine() throws Exception {
        Patrimoine patrimoine = new Patrimoine("Violet", LocalDateTime.now());

        mockMvc.perform(put("/patrimoines/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patrimoine)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPatrimoine() throws Exception {
        mockMvc.perform(get("/patrimoines/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testPutPatrimoineContent() throws Exception {
        Patrimoine patrimoine = new Patrimoine("Rose", LocalDateTime.now());

        mockMvc.perform(put("/patrimoines/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patrimoine)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/patrimoines/2"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    Patrimoine retrievedPatrimoine = objectMapper.readValue(json, Patrimoine.class);
                });
    }

}
