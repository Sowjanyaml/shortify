package com.example.shortify.controller;

import com.example.shortify.exception.UrlNotFoundException;
import com.example.shortify.service.ShortifyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShortifyController.class)
public class ShortifyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShortifyService shortifyService;

    @Test
    @DisplayName("should return HTTP 200 along with the corresponding long URL")
    public void getUrlShouldReturnFullUrl() throws Exception {
        given(shortifyService.getUrlByKey("abc")).willReturn("https://www.github.com");
        this.mockMvc.perform(get("/shortify/abc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("https://www.github.com")));
    }
    @Test
    @DisplayName("should return HTTP 404 along with the corresponding long URL")
    public void getUrlShouldReturnHTTP404() throws Exception {
        given(shortifyService.getUrlByKey("xyz")).willThrow(UrlNotFoundException.class);
        this.mockMvc.perform(get("/shortify/xyz"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertThat(result.getResponse().getErrorMessage()).isEqualTo("URL Not Found"));

    }
}