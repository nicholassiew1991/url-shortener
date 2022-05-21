package io.github.nicholassiew1991.urlshortenerapi.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LoggerConfigurations {

  /**
   * Create a Logger instance by injection
   * @param injectionPoint InjectionPoint
   * @return Logger
   */
  @Bean
  @Scope("prototype")
  public Logger logger(final InjectionPoint injectionPoint) {

    if (injectionPoint.getMethodParameter() != null) {
      return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
    }
    else if (injectionPoint.getField() != null) {
      return LoggerFactory.getLogger(injectionPoint.getField().getDeclaringClass());
    }

    throw new IllegalArgumentException();
  }
}
