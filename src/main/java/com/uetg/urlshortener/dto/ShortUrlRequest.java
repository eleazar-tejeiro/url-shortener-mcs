package com.uetg.urlshortener.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a request to create a short URL.
 */
@Getter
@Setter
public class ShortUrlRequest {
  private String url;
}