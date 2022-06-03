package io.github.nicholassiew1991.urlshortenerapi.services;

import io.github.nicholassiew1991.urlshortenerapi.models.Link;

import java.util.Optional;

public interface LinkService {

  Optional<String> getLink(String code);

  Link create(String originalUrl);
}
