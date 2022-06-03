package io.github.nicholassiew1991.urlshortenerapi.services.implementations;

import io.github.nicholassiew1991.urlshortenerapi.mappers.LinkMapper;
import io.github.nicholassiew1991.urlshortenerapi.models.Link;
import io.github.nicholassiew1991.urlshortenerapi.repositories.LinkRepository;
import io.github.nicholassiew1991.urlshortenerapi.repositories.entities.LinkEntity;
import io.github.nicholassiew1991.urlshortenerapi.services.LinkCodeGenerator;
import io.github.nicholassiew1991.urlshortenerapi.services.LinkService;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class LinkServiceImpl implements LinkService {

  private final LinkRepository linkRepository;

  private final LinkCodeGenerator linkCodeGenerator;

  private final LinkMapper linkMapper;

  public LinkServiceImpl(
    LinkRepository linkRepository,
    LinkCodeGenerator linkCodeGenerator,
    LinkMapper linkMapper) {
    this.linkRepository = linkRepository;
    this.linkCodeGenerator = linkCodeGenerator;
    this.linkMapper = linkMapper;
  }

  @Override
  @Cacheable(cacheNames = "links", cacheManager = "cacheManager", key = "#a0", unless = "#result == null")
  public Optional<String> getLink(String code) {
    return this.linkRepository.findById(code).map(LinkEntity::getOriginalUrl);
  }

  @Override
  @Retry(name = "retryLinkCodeDuplicate")
  public Link create(String originalUrl) {
    String code = this.linkCodeGenerator.generateLinkCode(5);
    LinkEntity entity = new LinkEntity(code, code, originalUrl, LocalDateTime.now(ZoneOffset.UTC));
    entity = this.linkRepository.insert(entity);
    return this.linkMapper.createLinkFromLinkEntity(entity);
  }
}