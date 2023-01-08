package com.github.nicholas1991.urlshortener.webapi.tasks.producers;

public interface TaskProducer {

  void produce(String taskName, Object data);

}
