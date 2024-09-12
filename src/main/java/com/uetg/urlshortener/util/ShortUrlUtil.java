package com.uetg.urlshortener.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.uetg.urlshortener.config.ShortUrlConfig;
import java.util.Random;

/**
 * Utility class for generating unique short URLs.
 */
@Component
public class ShortUrlUtil {

  private final ShortUrlConfig config;

  @Autowired
  public ShortUrlUtil(ShortUrlConfig config) {
    this.config = config;
  }

  public String generateUniqueKey() {
    int keyLength = config.getKeyLength();
    String allowedCharacters = config.getAllowedCharacters();

    StringBuilder keyBuilder = new StringBuilder();
    Random random = new Random();

    for (int i = 0; i < keyLength; i++) {
      int randomIndex = random.nextInt(allowedCharacters.length());
      keyBuilder.append(allowedCharacters.charAt(randomIndex));
    }

    return keyBuilder.toString();
  }
}