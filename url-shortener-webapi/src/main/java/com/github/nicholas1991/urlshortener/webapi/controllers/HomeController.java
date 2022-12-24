package com.github.nicholas1991.urlshortener.webapi.controllers;

import com.github.nicholas1991.urlshortener.webapi.services.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class HomeController {

  private final LinkService linkService;

  public HomeController(LinkService linkService) {
    this.linkService = linkService;
  }

  @RequestMapping("/{code:[a-zA-Z0-9]+}")
  public RedirectView redirect(@PathVariable String code) {
    String url = this.linkService.getOriginalUrl(code)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return new RedirectView(url);
  }
}
