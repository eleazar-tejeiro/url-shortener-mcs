package com.uetg.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.uetg.urlshortener.dto.ShortUrlRequest;
import com.uetg.urlshortener.dto.ShortUrlResponse;
import com.uetg.urlshortener.service.UrlShortenerService;

/**
 * The UrlShortenerController class handles the API endpoints for URL shortening
 * operations.
 */
@RestController
@RequestMapping("/api/url")
public class UrlShortenerController {

  private final UrlShortenerService urlShortenerService;

  @Autowired
  public UrlShortenerController(UrlShortenerService urlShortenerService) {
    this.urlShortenerService = urlShortenerService;
  }

  @PostMapping("/shorten")
  public ResponseEntity<ShortUrlResponse> createShortUrl(@RequestBody ShortUrlRequest request) {
    ShortUrlResponse response = urlShortenerService.createShortUrl(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{key}")
  public RedirectView redirectToFullUrl(@PathVariable String key) {
    String fullUrl = urlShortenerService.getFullUrl(key);
    return new RedirectView(fullUrl);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }
}