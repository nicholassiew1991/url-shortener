package com.github.nicholas1991.urlshortener.webapi.tasks.dispatchers;

import com.github.nicholas1991.urlshortener.webapi.tasks.models.Task;
import com.github.nicholas1991.urlshortener.webapi.tasks.executors.TaskExecutor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;


@ExtendWith(MockitoExtension.class)
public class TaskDispatcherUnitTest {

  private final BeanFactory beanFactory = mock(BeanFactory.class);

  private final Logger logger = LoggerFactory.getLogger(TaskDispatcherImpl.class);

  private final TaskDispatcher dispatcher = new TaskDispatcherImpl(beanFactory, logger);

  public static Stream<Arguments> testHandleReceiveTaskMessage() {
    return Stream.of(
      Arguments.of("VALID_TASK_NAME", "{}", 1),
      Arguments.of("INVALID_TASK_NAME", "{}", 0)
    );
  }

  @MethodSource
  @ParameterizedTest
  public void testHandleReceiveTaskMessage(String taskName, String taskData, int expectedTaskExecutedTimes) {
    //// Stub
    TaskExecutor taskExecutor = mock(TaskExecutor.class);
    when(beanFactory.getBean(eq("VALID_TASK_NAME"), eq(TaskExecutor.class))).thenReturn(taskExecutor);
    when(beanFactory.getBean(not(eq("VALID_TASK_NAME")), eq(TaskExecutor.class))).thenThrow(new NoSuchBeanDefinitionException("NoSuchBeanDefinitionException"));

    //// Act
    this.dispatcher.dispatch(new Task(taskName, taskData));

    //// Verify
    verify(taskExecutor, times(expectedTaskExecutedTimes)).execute(anyString());
  }

}
