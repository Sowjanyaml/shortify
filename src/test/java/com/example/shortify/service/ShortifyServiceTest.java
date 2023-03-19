package com.example.shortify.service;

import com.example.shortify.dto.LongUrlDto;
import com.example.shortify.entity.ShortUrl;
import com.example.shortify.exception.UrlInvalidException;
import com.example.shortify.exception.UrlNotFoundException;
import com.example.shortify.repository.ShortifyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

    @DisplayName("should save Long URL with key when Valid Url is passed")
    @Test
    void shortenUrlTest() throws UrlInvalidException {
        LongUrlDto longurl = LongUrlDto.builder()
                .longUrl("https://www.abc.com").build();
        ReflectionTestUtils.setField(shortifyService, "validityInDays", 14);
        when(shortifyRepository.save(Mockito.any(ShortUrl.class)))
                .thenReturn(ShortUrl.builder()
                        .longUrl(longurl.getLongUrl()).shortKey("8ad4840c")
                        .createdDate(LocalDateTime.now()).build());
        shortifyService.shortenUrl(longurl);
        verify(shortifyRepository, times(1)).save(Mockito.any(ShortUrl.class));
    }

    @DisplayName("should throw UrlInvalidException when invalid Url is passed")
    @Test
    void shortenUrlTest2(){
        LongUrlDto longurl = LongUrlDto.builder()
                .longUrl("hghjjhk").build();
        assertThrows(UrlInvalidException.class, () -> shortifyService.shortenUrl(longurl));

    }



}