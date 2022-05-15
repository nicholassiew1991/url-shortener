package io.github.nicholassiew1991.urlshortenerapi.configurations;

import io.github.nicholassiew1991.urlshortenerapi.properties.AppCorsProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfigurations implements WebMvcConfigurer {

  private AppCorsProperties appCorsProperties;

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
