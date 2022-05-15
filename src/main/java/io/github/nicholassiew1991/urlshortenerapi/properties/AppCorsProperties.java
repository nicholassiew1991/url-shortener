package io.github.nicholassiew1991.urlshortenerapi.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "app.cors")
public class AppCorsProperties {

  private String allowedOrigins;

  private String allowedMethods;
}
