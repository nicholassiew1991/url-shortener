package io.github.nicholassiew1991.urlshortenerapi.controllers.api;

import io.github.nicholassiew1991.urlshortenerapi.models.Link;
import io.github.nicholassiew1991.urlshortenerapi.services.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/links")
public class LinksController {

  private final LinkService linkService;

  public LinksController(LinkService linkService) {
    this.linkService = linkService;
  }

  @PostMapping("")
  public ResponseEntity<?> createLink(@RequestBody Map<String, Object> map, HttpServletRequest httpServletRequest) {
    String url = map.get("url").toString();
    Link link = this.linkService.create(url);
    return new ResponseEntity<>(link, HttpStatus.CREATED);
  }
}
