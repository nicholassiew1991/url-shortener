package io.github.nicholassiew1991.urlshortenerapi.controllers;

import io.github.nicholassiew1991.urlshortenerapi.properties.AppCorsProperties;
import io.github.nicholassiew1991.urlshortenerapi.services.LinkService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = HomeController.class, properties = {"app.cors.allowed-origins=*", "app.cors.allowed-methods=*"})
@EnableConfigurationProperties({AppCorsProperties.class})
public class HomeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LinkService linkService;

  public static Stream<Arguments> testShortUrlRedirection() {
    return Stream.of(
      Arguments.of(null, 404),
      Arguments.of("https://www.google.com", 302)
    );
  }

  @ParameterizedTest
  @MethodSource
  public void testShortUrlRedirection(String originalUrl, int expectedStatus) throws Exception {

    when(linkService.getLink(anyString())).thenReturn(Optional.ofNullable(originalUrl));

    this.mockMvc.perform(get("/codes"))
      .andExpect(status().is(expectedStatus))
      .andReturn();
  }
}
