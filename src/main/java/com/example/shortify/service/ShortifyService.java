package com.example.shortify.service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

import com.example.shortify.dto.LongUrlDto;
import com.example.shortify.dto.ShortUrlDto;
import com.example.shortify.entity.ShortUrl;
import com.example.shortify.exception.UrlInvalidException;
import com.example.shortify.exception.UrlNotFoundException;
import com.example.shortify.mapper.EntityToDTOMapper;
import com.example.shortify.repository.ShortifyRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShortifyService {

    @Value("${validity.in-days}")
    private Integer validityInDays;
    private final ShortifyRepository shortifyRepository;

    public ShortifyService(ShortifyRepository shortifyRepository) {
        this.shortifyRepository = shortifyRepository;
    }

    public ShortUrlDto shortenUrl(LongUrlDto url) throws UrlInvalidException {
        try {
            validateURL(url.getLongUrl());
        } catch (Exception e) {
            throw new UrlInvalidException();
        }

        ShortUrl shortUrl;

        // generating murmur3 based hash hashForUrl as short URL
        String hashForUrl = Hashing.murmur3_32_fixed().hashString(url.getLongUrl(), Charset.defaultCharset()).toString();
        if (shortifyRepository.findByShortKey(hashForUrl).isPresent()) {
             shortUrl = shortifyRepository.findByShortKey(hashForUrl).get();
        } else {
             shortUrl = ShortUrl.
                    builder()
                    .longUrl(url.getLongUrl())
                    .shortKey(hashForUrl)
                    .createdDate(LocalDateTime.now())
                    .expiryDate(LocalDateTime.now().plusDays(validityInDays))
                    .build();
             shortUrl = shortifyRepository.save(shortUrl);
        }
        return EntityToDTOMapper.convertToShortUrlDto(shortUrl);
    }

    public String getUrlByKey(String key) throws UrlNotFoundException {
        ShortUrl byShortKey = shortifyRepository.findByShortKey(key).orElseThrow(() -> new UrlNotFoundException());
        return byShortKey.getLongUrl();
    }

    public void validateURL(String url) throws MalformedURLException, URISyntaxException {
        new URL(url).toURI();
    }

}
