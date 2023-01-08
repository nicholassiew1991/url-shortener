package com.github.nicholas1991.urlshortener.webapi.tasks.dispatchers;

import com.github.nicholas1991.urlshortener.webapi.tasks.models.Task;
import com.github.nicholas1991.urlshortener.webapi.tasks.executors.TaskExecutor;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskDispatcherImpl implements TaskDispatcher {

  private final BeanFactory beanFactory;

  private final Logger logger;

  public TaskDispatcherImpl(BeanFactory beanFactory, Logger logger) {
    this.beanFactory = beanFactory;
    this.logger = logger;
  }

  @Override
  public void dispatch(Task task) {
    try {
      this.logger.info("Received a task message to execute - TaskName: {}", task.name());
      TaskExecutor executor = this.beanFactory.getBean(task.name(), TaskExecutor.class);
      executor.execute(task.data());
    } catch (BeansException exception) {
      this.logger.warn("Unable to resolve TaskExecutor with bean name: {}", task.name());
    }
  }

}
