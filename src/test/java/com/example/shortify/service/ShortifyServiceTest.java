package com.example.shortify.service;

import com.example.shortify.entity.ShortUrl;
import com.example.shortify.exception.UrlNotFoundException;
import com.example.shortify.repository.ShortifyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShortifyServiceTest {
    @Mock
    private ShortifyRepository shortifyRepository;

    @InjectMocks
    private ShortifyService shortifyService;

    @DisplayName("should return URL for the key")
    @Test
    void getUrlByKeyTest() throws UrlNotFoundException {
        String testURL = "https://www.abc.com";
        when(shortifyRepository.findByShortKey("abc"))
                .thenReturn(Optional.of(ShortUrl.builder().longUrl(testURL).build()));
        String actualResult = shortifyService.getUrlByKey("abc");
        Assertions.assertThat(actualResult).isEqualTo(testURL);
    }

    @DisplayName("should throw UrlNotFoundException")
    @Test
    void getUrlByKeyTest2() {
        when(shortifyRepository.findByShortKey("abc"))
                .thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> shortifyService.getUrlByKey("abc")).isInstanceOf(UrlNotFoundException.class);
    }

}