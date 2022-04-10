package com.nicholas.urlshortenerapi.repositories;

import com.nicholas.urlshortenerapi.repositories.entities.LinkEntity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkRepository extends MongoRepository<LinkEntity, String> {
}
