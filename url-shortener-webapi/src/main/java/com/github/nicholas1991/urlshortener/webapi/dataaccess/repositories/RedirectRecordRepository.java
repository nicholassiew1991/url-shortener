package com.github.nicholas1991.urlshortener.webapi.dataaccess.repositories;

import com.github.nicholas1991.urlshortener.webapi.dataaccess.entities.RedirectRecord;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RedirectRecordRepository extends MongoRepository<RedirectRecord, ObjectId> {
}
