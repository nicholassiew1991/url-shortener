package com.nicholas.urlshortenerapi.services.implementations;

import com.nicholas.urlshortenerapi.configurations.AppConfigurations;
import com.nicholas.urlshortenerapi.models.Link;
import com.nicholas.urlshortenerapi.repositories.LinkRepository;
import com.nicholas.urlshortenerapi.repositories.entities.LinkEntity;
import com.nicholas.urlshortenerapi.services.LinkCodeGenerator;
import com.nicholas.urlshortenerapi.services.LinkService;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService {

  private final LinkRepository linkRepository;

  private final LinkCodeGenerator linkCodeGenerator;

  private final AppConfigurations appConfigurations;

  public LinkServiceImpl(
    LinkRepository linkRepository,
    LinkCodeGenerator linkCodeGenerator,
    AppConfigurations appConfigurations) {
    this.linkRepository = linkRepository;
    this.linkCodeGenerator = linkCodeGenerator;
    this.appConfigurations = appConfigurations;
  }

  @Override
  public Link create(String originalUrl) {
    String code = this.linkCodeGenerator.generateLinkCode(5);
    String shortUrl = String.format("%s/%s", appConfigurations.getDomain(), code);
    LinkEntity entity = new LinkEntity(code, code, originalUrl);
    return new Link(entity.getCode(), shortUrl, entity.getOriginalUrl());
  }
}