package com.github.nicholas1991.urlshortener.webapi.mappers;

import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.Link;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper(
  imports = {
    LocalDateTime.class,
    ZoneOffset.class
  }
)
public interface LinkMapper {

  @Mapping(target = "id", source = "code")
  @Mapping(target = "code", source = "code")
  @Mapping(target = "createdDateTime", expression = "java(LocalDateTime.now(ZoneOffset.UTC))")
  Link create(String code, String originalUrl);
}
