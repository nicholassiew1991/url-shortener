package com.github.nicholas1991.urlshortener.webapi.links.services;

import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.Link;
import com.github.nicholas1991.urlshortener.webapi.links.models.CreateRedirectRecordTaskDataModel;

import java.util.Map;
import java.util.Optional;

public interface LinkService {

  Optional<String> getOriginalUrl(String code);

  Link create(String originalUrl);

  void createRedirectRecordTask(String code, Map<String, String> headers, Map<String, String> queryString);

  void createRedirectRecord(CreateRedirectRecordTaskDataModel data);
}
