package com.uetg.urlshortener.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration class for short URL settings.
 */
@ConfigurationProperties(prefix = "short-url")
@Getter
@Setter
public class ShortUrlConfig {
  private String allowedCharacters;
  private int keyLength;
}