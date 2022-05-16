package io.github.nicholassiew1991.urlshortenerapi.configurations;

import io.github.nicholassiew1991.urlshortenerapi.properties.AppCorsProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurations implements WebMvcConfigurer {

  private final AppCorsProperties appCorsProperties;

  public WebConfigurations(AppCorsProperties appCorsProperties) {
    this.appCorsProperties = appCorsProperties;
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
      .allowedOrigins(appCorsProperties.getAllowedOrigins())
      .allowedMethods(appCorsProperties.getAllowedMethods());
  }
}
