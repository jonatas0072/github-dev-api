package br.com.github.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(value = "classpath:application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
public class GithuControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private final String AUTH = "Authorization";

    private final String TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb25hdGFzZmVybmFuZG9hcmF1am9tYWNlZG9AZ21haWwuY29tIiwiZXhwIjoxNjA2ODc2NjQyLCJyb2xlIjpbIkFETUlOIl19.ZeFQ9VoEL0hXspaVbQtydma6czsCLDmhoBF7RpS7nwb8tqxoNBZNsuW99DgLT4wiJHT2voxg49_rCQHYX-F6Ow";

    @Test
    void findByName() throws Exception {
       this.mockMvc
                .perform(get("/api/github/find/jonatas0072")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE).header(AUTH, TOKEN)).andExpect(status().isOk());
    }

    @Test
    void findByTopics() throws Exception {
        this.mockMvc
                .perform(get("/api/github/find-language/java")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE).header(AUTH, TOKEN)).andExpect(status().isOk());
    }
}
