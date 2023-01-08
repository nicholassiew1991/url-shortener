package com.github.nicholas1991.urlshortener.webapi.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nicholas1991.urlshortener.webapi.tasks.constants.TaskNameConstants;
import com.github.nicholas1991.urlshortener.webapi.models.CreateRedirectRecordTaskDataModel;
import com.github.nicholas1991.urlshortener.webapi.services.LinkService;
import com.github.nicholas1991.urlshortener.webapi.services.TaskExecutor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service(TaskNameConstants.CREATE_REDIRECT_RECORD)
public class CreateRedirectRecordTaskExecutor implements TaskExecutor {

  private final LinkService linkService;

  private final ObjectMapper objectMapper;

  private final Logger logger;

  public CreateRedirectRecordTaskExecutor(LinkService linkService,
                                          ObjectMapper objectMapper,
                                          Logger logger) {
    this.linkService = linkService;
    this.objectMapper = objectMapper;
    this.logger = logger;
  }

  @Override
  public void execute(String data) {
    try {
      CreateRedirectRecordTaskDataModel dataModel = this.objectMapper.readValue(data, CreateRedirectRecordTaskDataModel.class);
      this.linkService.createRedirectRecord(dataModel);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}
