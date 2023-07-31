package com.shortener.urlshortner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class UrlShortnerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShortenUrl() throws Exception {
        String originalUrl = "{\"full_url\": \"http://www.example.com/long-url\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/shortenurl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(originalUrl))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
