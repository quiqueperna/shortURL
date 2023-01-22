package com.farmu.urlshortener.infraestructure.rest;

import com.farmu.urlshortener.application.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/shortener-url")
public class UrlController {

    private UrlService urlService;

    public UrlController (UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping ("/short-url")
    public ResponseEntity createShortUrl (@RequestBody UrlDTO urlDto) {
        try {
            return ResponseEntity.ok(this.urlService.createShortUrl(urlDto.getUrl()));
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().body("Malformed URL");
        }
    }

    @GetMapping("/short-url/{short-url}")
    public ResponseEntity getUrl (@PathVariable("short-url") String shortUrl) {
        return ResponseEntity.ok(this.urlService.getUrl(shortUrl));
    }
}