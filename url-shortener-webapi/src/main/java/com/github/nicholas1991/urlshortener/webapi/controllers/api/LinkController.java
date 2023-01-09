package com.github.nicholas1991.urlshortener.webapi.controllers.api;

import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.Link;
import com.github.nicholas1991.urlshortener.webapi.links.models.CreateShortUrlRequestModel;
import com.github.nicholas1991.urlshortener.webapi.links.services.LinkService;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/link")
public class LinkController {

  private final LinkService linkService;

  private final Logger logger;

  public LinkController(LinkService linkService, Logger logger) {
    this.linkService = linkService;
    this.logger = logger;
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody CreateShortUrlRequestModel body) {
    Link link = this.linkService.create(body.url());
    return ResponseEntity.ok(link);
  }
}
