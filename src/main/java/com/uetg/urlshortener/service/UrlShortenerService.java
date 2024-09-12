package com.uetg.urlshortener.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.uetg.urlshortener.dto.ShortUrlRequest;
import com.uetg.urlshortener.dto.ShortUrlResponse;
import com.uetg.urlshortener.entity.ShortUrlEntity;
import com.uetg.urlshortener.repository.ShortUrlRepository;
import com.uetg.urlshortener.util.ShortUrlUtil;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {

  private final ShortUrlRepository repository;
  private final ShortUrlUtil util;

  public ShortUrlResponse createShortUrl(ShortUrlRequest request) {
    String fullUrl = request.getUrl();

    ShortUrlEntity existingShortUrl = repository.findByFullUrl(fullUrl);

    if (existingShortUrl != null) {
      return ShortUrlResponse.builder().key(existingShortUrl.getKey()).build();
    } else {
      String newKey = util.generateUniqueKey();
      ShortUrlEntity newEntity = ShortUrlEntity.builder()
          .key(newKey).fullUrl(fullUrl).clickCount(0L)
          .build();
      repository.save(newEntity);
      return ShortUrlResponse.builder().key(newKey).build();
    }
  }

  public RedirectView getFullUrl(String key) {
    ShortUrlEntity entityInDb = repository.findByKey(key);
    entityInDb.setClickCount(entityInDb.getClickCount() + 1);
    repository.save(entityInDb);
    return new RedirectView(entityInDb.getFullUrl());
  }
}
