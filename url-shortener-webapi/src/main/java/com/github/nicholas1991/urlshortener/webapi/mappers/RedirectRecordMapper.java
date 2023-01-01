package com.github.nicholas1991.urlshortener.webapi.mappers;

import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.RedirectRecord;
import com.github.nicholas1991.urlshortener.webapi.models.CreateRedirectRecordTaskDataModel;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RedirectRecordMapper {

  @Mapping(target = "id", source = "id")
  @Mapping(target = "directLink", expression = "java(taskData.getReferenceFrom() == null)")
  @Mapping(target = "queryString", source = "taskData.queryStrings")

  RedirectRecord create(ObjectId id, CreateRedirectRecordTaskDataModel taskData);
}
