package com.example.shortify.mapper;

import com.example.shortify.dto.ShortUrlDto;
import com.example.shortify.entity.ShortUrl;

public class DtoToEntityMapper {

    public static ShortUrlDto convertToShortUrlDto(ShortUrl shortenUrl) {
        return ShortUrlDto.builder()
                .shortKey(shortenUrl.getShortKey())
                .createdDate(shortenUrl.getCreatedDate())
                .expiryDate(shortenUrl.getExpiryDate())
                .build();
    }
}