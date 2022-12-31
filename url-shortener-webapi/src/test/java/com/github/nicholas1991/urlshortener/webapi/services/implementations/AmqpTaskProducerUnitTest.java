package com.github.nicholas1991.urlshortener.webapi.services.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nicholas1991.urlshortener.webapi.models.Task;
import com.github.nicholas1991.urlshortener.webapi.services.TaskProducer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AmqpTaskProducerUnitTest {

  private final AmqpTemplate amqpTemplate = mock(AmqpTemplate.class);

  private final ObjectMapper objectMapper = new ObjectMapper();

  private final Logger logger = LoggerFactory.getLogger(AmqpTaskProducer.class);

  private final TaskProducer taskProducer = new AmqpTaskProducer(amqpTemplate, objectMapper, logger);

  public static Stream<Arguments> testProduce() {
    return Stream.of(
      Arguments.of("TASK_NAME", Map.of("k1", "v1"))
    );
  }

  @MethodSource
  @ParameterizedTest
  public void testProduce(String taskName, Object data) {
    //// Act
    this.taskProducer.produce(taskName, data);

    //// Assert
    verify(this.amqpTemplate, times(1)).convertAndSend(anyString(), any(Task.class));
  }
}
