package com.github.nicholas1991.urlshortener.webapi.rabbitmq.listeners;

import com.github.nicholas1991.urlshortener.webapi.rabbitmq.contants.MessageQueueNameConstants;
import com.github.nicholas1991.urlshortener.webapi.models.Task;
import com.github.nicholas1991.urlshortener.webapi.tasks.dispatchers.TaskDispatcher;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = MessageQueueNameConstants.TaskMessageQueue)
public class TaskMessageQueueListener {

  private final TaskDispatcher taskDispatcher;

  public TaskMessageQueueListener(TaskDispatcher taskDispatcher) {
    this.taskDispatcher = taskDispatcher;
  }

  @RabbitHandler
  public void handleReceiveTaskMessage(Task task) {
    this.taskDispatcher.dispatch(task);
  }

}
