package com.github.nicholas1991.urlshortener.webapi.dataaccess.repositories;

import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.Link;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkRepository extends MongoRepository<Link, String> {
}
