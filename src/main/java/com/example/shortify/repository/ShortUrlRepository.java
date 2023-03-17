package com.example.shortify.repository;

import com.example.shortify.entity.ShortUrl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends CrudRepository<ShortUrl, Long> {
     Optional<ShortUrl> findByShortKey(String shortKey);
}


