package com.github.nicholas1991.urlshortener.webapi.services;

import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.Link;

public interface LinkService {

  Link create(String originalUrl);
}
