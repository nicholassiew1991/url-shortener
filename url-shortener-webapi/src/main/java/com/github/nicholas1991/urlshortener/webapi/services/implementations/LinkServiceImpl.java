package com.github.nicholas1991.urlshortener.webapi.services.implementations;

import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.Link;
import com.github.nicholas1991.urlshortener.webapi.dataaccess.repositories.LinkRepository;
import com.github.nicholas1991.urlshortener.webapi.mappers.LinkMapper;
import com.github.nicholas1991.urlshortener.webapi.services.LinkCodeGenerator;
import com.github.nicholas1991.urlshortener.webapi.services.LinkService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinkServiceImpl implements LinkService {

  private final LinkRepository linkRepository;

  private final LinkCodeGenerator linkCodeGenerator;

  private final LinkMapper linkMapper;

  private final Logger logger;

  public LinkServiceImpl(LinkRepository linkRepository,
                         LinkCodeGenerator linkCodeGenerator,
                         LinkMapper linkMapper,
                         Logger logger) {
    this.linkRepository = linkRepository;
    this.linkCodeGenerator = linkCodeGenerator;
    this.linkMapper = linkMapper;
    this.logger = logger;
  }

  @Override
  public Optional<String> getOriginalUrl(String code) {
    this.logger.info("Get Original Url with Code: " + code);
    return code == null || code.isBlank() == true ? Optional.empty() : this.linkRepository.findById(code).map(Link::getOriginalUrl);
  }

  @Override
  public Link create(String originalUrl) {
    String code = this.linkCodeGenerator.generate(5);
    Link link = this.linkMapper.create(code, originalUrl);
    return this.linkRepository.insert(link);
  }

}
