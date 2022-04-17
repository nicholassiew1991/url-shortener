package io.github.nicholassiew1991.urlshortenerapi.mappers;

import io.github.nicholassiew1991.urlshortenerapi.repositories.entities.LinkEntity;
import io.github.nicholassiew1991.urlshortenerapi.models.Link;
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
