package io.github.nicholassiew1991.urlshortenerapi.services.implementations;

import io.github.nicholassiew1991.urlshortenerapi.services.LinkCodeGenerator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomLinkCodeGenerator implements LinkCodeGenerator {

  private static final char[] characters = new char[] {
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
    'u', 'v', 'w', 'x', 'y', 'z',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
    'U', 'V', 'W', 'X', 'Y', 'Z',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
  };

  @Override
  public String generateLinkCode(int length) {

    StringBuilder sb = new StringBuilder();
    Random random = new Random();

    while (sb.length() < length) {
      int index = random.nextInt(characters.length);
      sb.append(characters[index]);
    }

    return sb.toString();
  }
}
