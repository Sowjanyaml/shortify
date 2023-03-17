package com.example.shortify.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "shortUrl")
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String longUrl;
    @Column(nullable = false)
    private String shortKey;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    private LocalDateTime expiresDate;

}

