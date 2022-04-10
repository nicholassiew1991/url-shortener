package com.nicholas.urlshortenerapi.services;

import com.nicholas.urlshortenerapi.models.Link;

import java.util.Optional;

public interface LinkService {

  Optional<Link> getLink(String code);

  Link create(String originalUrl);
}
