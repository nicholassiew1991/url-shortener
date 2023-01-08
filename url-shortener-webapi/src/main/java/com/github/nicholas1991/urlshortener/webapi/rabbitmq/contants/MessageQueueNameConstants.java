package com.github.nicholas1991.urlshortener.webapi.rabbitmq.contants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageQueueNameConstants {

  public static final String TaskMessageQueue = "q.task_message";

}
