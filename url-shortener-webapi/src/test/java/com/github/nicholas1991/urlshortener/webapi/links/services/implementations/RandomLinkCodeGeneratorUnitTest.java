package com.github.nicholas1991.urlshortener.webapi.links.services.implementations;

import com.github.nicholas1991.urlshortener.webapi.links.services.LinkCodeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomLinkCodeGeneratorUnitTest {

  private final LinkCodeGenerator linkCodeGenerator = new RandomLinkCodeGenerator();

  @Test
  public void testGenerate() {
    int length = 5;
    String code = this.linkCodeGenerator.generate(length);
    assertEquals(length, code.length());
  }

}
