package com.example.shortify.controller;

import com.example.shortify.dto.LongUrlDto;
import com.example.shortify.dto.ShortUrlDto;
import com.example.shortify.exception.UrlInvalidException;
import com.example.shortify.exception.UrlNotFoundException;
import com.example.shortify.service.ShortifyService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/shortify")
public class ShortifyController {
    private final ShortifyService shortifyService;

    public ShortifyController(ShortifyService shortifyService) {
        this.shortifyService = shortifyService;
    }

    @PostMapping()
    public ShortUrlDto shortenUrl(@RequestBody LongUrlDto url) throws UrlInvalidException {
        return shortifyService.shortenUrl(url);
    }

    @GetMapping(value = "/{key}")
    public String getUrl(@PathVariable String key) throws UrlNotFoundException {
        return shortifyService.getUrlByKey(key);
    }
}
