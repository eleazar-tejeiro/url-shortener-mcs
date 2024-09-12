package com.uetg.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uetg.urlshortener.entity.ShortUrlEntity;

/**
 * The ShortUrlRepository interface represents a repository for managing
 * ShortUrlEntity objects.
 * It extends the JpaRepository interface, providing CRUD operations for the
 * ShortUrlEntity class.
 */
public interface ShortUrlRepository extends JpaRepository<ShortUrlEntity, Long> {
  ShortUrlEntity findByKey(String key);

  ShortUrlEntity findByFullUrl(String fullUrl);
}