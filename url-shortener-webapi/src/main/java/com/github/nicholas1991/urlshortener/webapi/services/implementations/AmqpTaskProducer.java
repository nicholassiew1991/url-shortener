package com.github.nicholas1991.urlshortener.webapi.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nicholas1991.urlshortener.webapi.rabbitmq.contants.MessageQueueNameConstants;
import com.github.nicholas1991.urlshortener.webapi.models.Task;
import com.github.nicholas1991.urlshortener.webapi.services.TaskProducer;
import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class AmqpTaskProducer implements TaskProducer {

  private final AmqpTemplate amqpTemplate;
  
  private final ObjectMapper objectMapper;

  private final Logger logger;

  public AmqpTaskProducer(AmqpTemplate amqpTemplate,
                          ObjectMapper objectMapper,
                          Logger logger) {
    this.amqpTemplate = amqpTemplate;
    this.objectMapper = objectMapper;
    this.logger = logger;
  }

  @Override
  public void produce(String taskName, Object data) {
    try {
      this.logger.info("Producing a task - Name: {}", taskName);
      Task task = new Task(taskName, objectMapper.writeValueAsString(data));
      this.amqpTemplate.convertAndSend(MessageQueueNameConstants.TaskMessageQueue, task);
    } catch (JsonProcessingException ex) {
      throw new RuntimeException(ex);
    }
  }

}
