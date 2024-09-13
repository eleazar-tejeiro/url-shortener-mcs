package com.uetg.urlshortener.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.uetg.urlshortener.dto.ShortUrlRequest;
import com.uetg.urlshortener.dto.ShortUrlResponse;
import com.uetg.urlshortener.entity.ShortUrlEntity;
import com.uetg.urlshortener.repository.ShortUrlRepository;
import com.uetg.urlshortener.util.ShortUrlUtil;

/**
 * Service class for URL shortening functionality.
 */
@Service
@RequiredArgsConstructor
public class UrlShortenerService {

  private final ShortUrlRepository repository;
  private final ShortUrlUtil util;

  @Value("${app.base-url}")
  private String baseUrl;

  public ShortUrlResponse createShortUrl(ShortUrlRequest request) {
    String fullUrl = request.getUrl();

    ShortUrlEntity existingShortUrl = repository.findByFullUrl(fullUrl);

    if (existingShortUrl != null) {
      return buildShortUrlResponse(existingShortUrl.getKey());
    } else {
      String newKey = util.generateUniqueKey();
      ShortUrlEntity newEntity = ShortUrlEntity.builder()
          .key(newKey).fullUrl(fullUrl).clickCount(0L)
          .build();
      repository.save(newEntity);
      return buildShortUrlResponse(newKey);
    }
  }

  private ShortUrlResponse buildShortUrlResponse(String key) {
    return ShortUrlResponse.builder()
        .key(key)
        .shortUrl(baseUrl + "/api/url/" + key)
        .build();
  }

  public String getFullUrl(String key) {
    ShortUrlEntity entityInDb = repository.findByKey(key);
    if (entityInDb == null) {
      throw new IllegalArgumentException("URL not found for key: " + key);
    }
    entityInDb.setClickCount(entityInDb.getClickCount() + 1);
    repository.save(entityInDb);
    return entityInDb.getFullUrl();
  }
}