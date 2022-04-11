package com.nicholas.urlshortenerapi.mappers;

import com.nicholas.urlshortenerapi.models.Link;
import com.nicholas.urlshortenerapi.repositories.entities.LinkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LinkMapper {

  @Mapping(target = "shortUrl", expression = "java(mapShortUrl(entity, domain))")
  Link createLinkFromLinkEntity(LinkEntity entity, String domain);

  default String mapShortUrl(LinkEntity entity, String domain) {
    return String.format("%s/%s", domain, entity.getCode());
  }
}
