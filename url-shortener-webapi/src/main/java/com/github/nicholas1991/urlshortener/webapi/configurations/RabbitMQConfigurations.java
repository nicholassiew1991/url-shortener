package com.github.nicholas1991.urlshortener.webapi.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nicholas1991.urlshortener.webapi.constants.MessageQueueNameConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigurations {

  @Bean
  public Queue taskMessageQueue() {
    return new Queue(MessageQueueNameConstants.TaskMessageQueue);
  }

  @Bean
  public MessageConverter messageConverter(ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }

}
