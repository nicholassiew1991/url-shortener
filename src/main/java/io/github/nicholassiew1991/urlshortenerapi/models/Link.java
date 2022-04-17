package io.github.nicholassiew1991.urlshortenerapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Link {

  private String code;

  private String shortUrl;

  private String originalUrl;
}
