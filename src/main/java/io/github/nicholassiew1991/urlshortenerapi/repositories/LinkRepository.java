package io.github.nicholassiew1991.urlshortenerapi.repositories;

import io.github.nicholassiew1991.urlshortenerapi.repositories.entities.LinkEntity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkRepository extends MongoRepository<LinkEntity, String> {
}
