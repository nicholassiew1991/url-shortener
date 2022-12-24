package com.github.nicholas1991.urlshortener.webapi.services;

import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.Link;

import java.util.Optional;

public interface LinkService {

  Optional<String> getOriginalUrl(String code);

  Link create(String originalUrl);
}
