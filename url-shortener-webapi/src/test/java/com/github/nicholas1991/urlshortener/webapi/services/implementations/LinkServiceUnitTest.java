package com.github.nicholas1991.urlshortener.webapi.services.implementations;

import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.Link;
import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.RedirectRecord;
import com.github.nicholas1991.urlshortener.webapi.dataaccess.repositories.LinkRepository;
import com.github.nicholas1991.urlshortener.webapi.dataaccess.repositories.RedirectRecordRepository;
import com.github.nicholas1991.urlshortener.webapi.links.services.implementations.RandomLinkCodeGenerator;
import com.github.nicholas1991.urlshortener.webapi.mappers.LinkMapper;
import com.github.nicholas1991.urlshortener.webapi.mappers.LinkMapperImpl;
import com.github.nicholas1991.urlshortener.webapi.mappers.RedirectRecordMapper;
import com.github.nicholas1991.urlshortener.webapi.mappers.RedirectRecordMapperImpl;
import com.github.nicholas1991.urlshortener.webapi.models.CreateRedirectRecordTaskDataModel;
import com.github.nicholas1991.urlshortener.webapi.links.services.LinkCodeGenerator;
import com.github.nicholas1991.urlshortener.webapi.services.LinkService;
import com.github.nicholas1991.urlshortener.webapi.tasks.producers.TaskProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LinkServiceUnitTest {

  private final LinkRepository linkRepository = mock(LinkRepository.class);

  private final RedirectRecordRepository redirectRecordRepository = mock(RedirectRecordRepository.class);

  private final LinkCodeGenerator linkCodeGenerator = new RandomLinkCodeGenerator();

  private final TaskProducer taskProducer = mock(TaskProducer.class);

  private final LinkMapper linkMapper = new LinkMapperImpl();

  private final RedirectRecordMapper redirectRecordMapper = new RedirectRecordMapperImpl();

  private final Logger logger = LoggerFactory.getLogger(LinkServiceUnitTest.class);

  private final LinkService linkService = new LinkServiceImpl(
    this.linkRepository,
    this.redirectRecordRepository,
    this.linkCodeGenerator,
    this.taskProducer,
    this.linkMapper,
    this.redirectRecordMapper,
    this.logger
  );

  public static Stream<Arguments> testGetOriginalUrl() {
    return Stream.of(
      Arguments.of(null, null, true),
      Arguments.of("", null, true),
      Arguments.of("code", null, true),
      Arguments.of("code", new Link("code", "code", "originalUrl", LocalDateTime.now(ZoneOffset.UTC)), false)
    );
  }

  @MethodSource
  @ParameterizedTest
  public void testGetOriginalUrl(String code, Link stubQueryResult, boolean expectedIsEmpty) {
    //// Stub
    when(linkRepository.findById(anyString())).thenReturn(Optional.ofNullable(stubQueryResult));

    //// Act
    Optional<String> optionalOriginalUrl = this.linkService.getOriginalUrl(code);

    //// Assert
    assertEquals(expectedIsEmpty, optionalOriginalUrl.isEmpty());
  }

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

  @Test
  public void testCreateRedirectRecordTask() {
    //// Act
    this.linkService.createRedirectRecordTask("CODE", Map.of(), Map.of());

    //// Verify
    verify(this.taskProducer, times(1)).produce(anyString(), any());
  }

  @Test
  public void testCreateRedirectRecord() {
    //// Act
    CreateRedirectRecordTaskDataModel data = CreateRedirectRecordTaskDataModel.of("CODE", Map.of(), null, null);
    this.linkService.createRedirectRecord(data);

    //// Verify
    verify(this.redirectRecordRepository, times(1)).insert(any(RedirectRecord.class));
  }
}
