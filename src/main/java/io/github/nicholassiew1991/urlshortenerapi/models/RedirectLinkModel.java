package io.github.nicholassiew1991.urlshortenerapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedirectLinkModel {

  private String code;

  private String originalUrl;
}
