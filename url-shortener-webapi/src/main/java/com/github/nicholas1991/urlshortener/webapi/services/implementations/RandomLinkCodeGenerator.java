package com.github.nicholas1991.urlshortener.webapi.services.implementations;

import com.github.nicholas1991.urlshortener.webapi.services.LinkCodeGenerator;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
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
  public String generate(int length) {

    StringBuilder sb = new StringBuilder();
    Random random = new Random();

    while (sb.length() < length) {
      int index = random.nextInt(characters.length);
      sb.append(characters[index]);
    }

    return sb.toString();
  }

}
