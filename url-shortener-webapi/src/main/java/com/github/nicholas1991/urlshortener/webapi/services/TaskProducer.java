package com.github.nicholas1991.urlshortener.webapi.services;

public interface TaskProducer {

  void produce(String taskName, Object data);

}
