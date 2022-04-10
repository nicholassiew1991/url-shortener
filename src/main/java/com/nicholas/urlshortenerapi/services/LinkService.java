package com.nicholas.urlshortenerapi.services;

import com.nicholas.urlshortenerapi.models.Link;

public interface LinkService {

  Link create(String originalUrl);
}
