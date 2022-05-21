package io.github.nicholassiew1991.urlshortenerapi.controllers;

import io.github.nicholassiew1991.urlshortenerapi.models.Link;
import io.github.nicholassiew1991.urlshortenerapi.services.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

  private final LinkService linkService;

  public HomeController(LinkService linkService) {
    this.linkService = linkService;
  }

  @GetMapping("/{code}")
  public RedirectView redirect(@PathVariable String code, HttpServletRequest httpServletRequest) {

    String domain = String.format("%s://%s", httpServletRequest.getHeader("x-forwarded-scheme"), httpServletRequest.getHeader("x-forwarded-host"));
    Optional<Link> optionalLink = this.linkService.getLink(code, domain);

    if (optionalLink.isEmpty() == true) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return new RedirectView(optionalLink.get().getOriginalUrl());
  }

}
