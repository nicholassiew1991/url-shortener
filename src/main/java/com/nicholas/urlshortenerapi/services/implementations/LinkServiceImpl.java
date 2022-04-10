package com.nicholas.urlshortenerapi.services.implementations;

import com.nicholas.urlshortenerapi.configurations.AppConfigurations;
import com.nicholas.urlshortenerapi.models.Link;
import com.nicholas.urlshortenerapi.repositories.LinkRepository;
import com.nicholas.urlshortenerapi.repositories.entities.LinkEntity;
import com.nicholas.urlshortenerapi.services.LinkCodeGenerator;
import com.nicholas.urlshortenerapi.services.LinkService;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
  public Optional<Link> getLink(String code) {
    return this.linkRepository.findById(code).map(this::getLink);
  }

  @Override
  public Link create(String originalUrl) {
    String code = this.linkCodeGenerator.generateLinkCode(5);
    LinkEntity entity = new LinkEntity(code, code, originalUrl);
    return this.getLink(entity);
  }

  private Link getLink(LinkEntity entity) {
    return new Link(
      entity.getCode(),
      String.format("%s/%s", appConfigurations.getDomain(), entity.getCode()),
      entity.getOriginalUrl()
    );
  }
}