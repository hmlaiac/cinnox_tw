package com.example.service.repository;

import com.example.service.model.LineMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineMessageRepository extends MongoRepository<LineMessage, String> {
}
