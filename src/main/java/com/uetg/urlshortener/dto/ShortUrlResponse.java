package com.uetg.urlshortener.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a response object for a short URL.
 */
@Getter
@Setter
@Builder
public class ShortUrlResponse {
  private String key;
}