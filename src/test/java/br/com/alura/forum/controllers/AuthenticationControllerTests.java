package br.com.alura.forum.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthenticationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn400IfPasswordAndEmailAreIncorrect() throws Exception {
        URI uri = new URI("/auth");
        String json = "{\"email\":\"invalido@email.com\",\"senha\":\"123346\"}";

        mockMvc
            .perform(MockMvcRequestBuilders.post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }
}
