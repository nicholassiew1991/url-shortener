package com.nicholas.urlshortenerapi.controllers.api;

import com.nicholas.urlshortenerapi.models.Link;
import com.nicholas.urlshortenerapi.services.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/links")
public class LinksController {

  private final LinkService linkService;

  public LinksController(LinkService linkService) {
    this.linkService = linkService;
  }

  @PostMapping("")
  public ResponseEntity<?> createLink(@RequestBody Map<String, Object> map) {
    String url = map.get("url").toString();
    Link link = this.linkService.create(url);
    return new ResponseEntity<>(link, HttpStatus.CREATED);
  }
}
