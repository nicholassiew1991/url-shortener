package io.github.nicholassiew1991.urlshortenerapi.services.implementations;

import io.github.nicholassiew1991.urlshortenerapi.services.LinkCodeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RandomLinkCodeGeneratorTest {

  private final LinkCodeGenerator linkCodeGenerator = new RandomLinkCodeGenerator();

  @Test
  public void testGenerateLinkCode() {
    int length = 5;
    String code = this.linkCodeGenerator.generateLinkCode(length);
    Assertions.assertEquals(length, code.length());
  }
}
