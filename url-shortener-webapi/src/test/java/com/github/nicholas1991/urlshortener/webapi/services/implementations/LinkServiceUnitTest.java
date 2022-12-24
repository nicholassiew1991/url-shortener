package com.github.nicholas1991.urlshortener.webapi.services.implementations;

import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.Link;
import com.github.nicholas1991.urlshortener.webapi.dataaccess.repositories.LinkRepository;
import com.github.nicholas1991.urlshortener.webapi.mappers.LinkMapper;
import com.github.nicholas1991.urlshortener.webapi.mappers.LinkMapperImpl;
import com.github.nicholas1991.urlshortener.webapi.services.LinkCodeGenerator;
import com.github.nicholas1991.urlshortener.webapi.services.LinkService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LinkServiceUnitTest {

  private final LinkRepository linkRepository = mock(LinkRepository.class);

  private final LinkCodeGenerator linkCodeGenerator = new RandomLinkCodeGenerator();

  private final LinkMapper linkMapper = new LinkMapperImpl();

  private final Logger logger = LoggerFactory.getLogger(LinkServiceUnitTest.class);

  private final LinkService linkService = new LinkServiceImpl(
    this.linkRepository,
    this.linkCodeGenerator,
    this.linkMapper,
    this.logger
  );

  public static Stream<Arguments> testCreate() {
    return Stream.of(
      Arguments.of("https://www.google.com")
    );
  }

  @MethodSource
  @ParameterizedTest
  public void testCreate(String originalUrl) {
    //// Stub
    when(linkRepository.insert(Mockito.any(Link.class))).thenAnswer(x -> x.getArgument(0));

    //// Act
    Link link = this.linkService.create(originalUrl);

    //// Assert
    assertEquals(originalUrl, link.getOriginalUrl());
    assertNotNull(link.getCode());
    assertFalse(link.getCode().isEmpty());
  }
}
