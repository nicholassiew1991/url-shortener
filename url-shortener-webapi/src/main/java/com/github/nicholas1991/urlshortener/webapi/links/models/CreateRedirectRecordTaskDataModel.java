package com.github.nicholas1991.urlshortener.webapi.links.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRedirectRecordTaskDataModel {

  private String code;

  private Map<String, String> queryStrings;

  private String referenceFrom;

  private String userAgent;

  private LocalDateTime redirectDateTime;

  public static CreateRedirectRecordTaskDataModel of(String code, Map<String, String> queryStrings, String referenceFrom, String userAgent) {
    return new CreateRedirectRecordTaskDataModel(code, queryStrings, referenceFrom, userAgent, LocalDateTime.now(ZoneOffset.UTC));
  }
}
