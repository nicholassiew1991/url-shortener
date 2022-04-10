package com.nicholas.urlshortenerapi.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppConfigurations {

  @Value("${app.domain}")
  private String domain;
}
