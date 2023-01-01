package com.github.nicholas1991.urlshortener.webapi.services.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nicholas1991.urlshortener.webapi.services.LinkService;
import com.github.nicholas1991.urlshortener.webapi.services.TaskExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateRedirectRecordTaskExecutorUnitTest {

  private final LinkService linkService = mock(LinkService.class);

  private final ObjectMapper objectMapper = new ObjectMapper();

  private final Logger logger = LoggerFactory.getLogger(CreateRedirectRecordTaskExecutorUnitTest.class);

  private final TaskExecutor taskExecutor = new CreateRedirectRecordTaskExecutor(linkService, objectMapper, logger);

  @Test
  public void testExecute() {
    //// Act
    this.taskExecutor.execute("{\"code\":\"STUB_CODE\"}");
    //// Verify
    verify(this.linkService, times(1)).createRedirectRecord(any());
  }

}
