package com.github.nicholas1991.urlshortener.webapi.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LoggerConfigurations {

  @Bean
  @Scope(BeanDefinition.SCOPE_PROTOTYPE)
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
