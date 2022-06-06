package io.github.nicholassiew1991.urlshortenerapi.services.implementations;

import io.github.nicholassiew1991.urlshortenerapi.mappers.LinkMapper;
import io.github.nicholassiew1991.urlshortenerapi.mappers.LinkMapperImpl;
import io.github.nicholassiew1991.urlshortenerapi.models.Link;
import io.github.nicholassiew1991.urlshortenerapi.repositories.LinkRepository;
import io.github.nicholassiew1991.urlshortenerapi.repositories.entities.LinkEntity;
import io.github.nicholassiew1991.urlshortenerapi.services.LinkCodeGenerator;
import io.github.nicholassiew1991.urlshortenerapi.services.LinkService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class LinkServiceImplTest {

  @Mock
  private LinkRepository linkRepository;

  private LinkCodeGenerator linkCodeGenerator = new RandomLinkCodeGenerator();

  private LinkMapper linkMapper = new LinkMapperImpl();

  public static Stream<Arguments> testGetLink() {
    return Stream.of(
      Arguments.of(new LinkEntity("id", "code", "originalUrl", LocalDateTime.now()), false),
      Arguments.of(null, true)
    );
  }

  @ParameterizedTest
  @MethodSource
  public void testGetLink(LinkEntity queryResult, boolean isEmpty) {

    // Stub
    when(linkRepository.findById(anyString())).thenReturn(Optional.ofNullable(queryResult));

    // Test
    LinkService linkService = this.getService();
    Optional<String> link = linkService.getLink(linkCodeGenerator.generateLinkCode(5));

    // Assert
    assertEquals(isEmpty, link.isEmpty());
  }

  public static Stream<Arguments> testCreate() {
    return Stream.of(
      Arguments.of("https://www.google.com")
    );
  }

  @ParameterizedTest
  @MethodSource
  public void testCreate(String originalUrl) {

    // Stub
    // Stub return original insert value
    when(linkRepository.insert(Mockito.any(LinkEntity.class))).thenAnswer(x -> x.getArgument(0));

    // Test
    LinkService linkService = this.getService();
    Link link = linkService.create(originalUrl);

    // Assert
    assertEquals(originalUrl, link.getOriginalUrl());
    assertFalse(link.getCode().isBlank());
  }

  private LinkService getService() {
    return new LinkServiceImpl(
      linkRepository,
      linkCodeGenerator,
      linkMapper
    );
  }
}
