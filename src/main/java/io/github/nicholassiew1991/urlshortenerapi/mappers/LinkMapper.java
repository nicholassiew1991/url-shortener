package io.github.nicholassiew1991.urlshortenerapi.mappers;

import io.github.nicholassiew1991.urlshortenerapi.repositories.entities.LinkEntity;
import io.github.nicholassiew1991.urlshortenerapi.models.Link;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LinkMapper {

  Link createLinkFromLinkEntity(LinkEntity entity);
}
