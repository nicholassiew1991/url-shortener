package com.github.nicholas1991.urlshortener.webapi.rabbitmq.listeners;

import com.github.nicholas1991.urlshortener.webapi.constants.MessageQueueNameConstants;
import com.github.nicholas1991.urlshortener.webapi.models.Task;
import com.github.nicholas1991.urlshortener.webapi.services.TaskExecutor;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = MessageQueueNameConstants.TaskMessageQueue)
public class TaskMessageQueueListener {

  private final BeanFactory beanFactory;

  private final Logger logger;

  public TaskMessageQueueListener(BeanFactory beanFactory, Logger logger) {
    this.beanFactory = beanFactory;
    this.logger = logger;
  }

  @RabbitHandler
  public void handleReceiveTaskMessage(Task task) {
    try {
      this.logger.info("Received a task message to execute - TaskName: {}", task.name());
      TaskExecutor executor = this.beanFactory.getBean(task.name(), TaskExecutor.class);
      executor.execute(task.data());
    } catch (BeansException exception) {
      this.logger.warn("Unable to resolve TaskExecutor with bean name: {}", task.name());
    }
  }

}
