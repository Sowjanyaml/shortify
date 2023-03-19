package com.example.shortify.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortUrlDto implements Serializable {
    private String longUrl;
    private String shortKey;
    private LocalDateTime createdDate;
    private LocalDateTime expiryDate;
}
