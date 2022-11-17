package com.example.service.repository;

import com.example.service.model.TestObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TestObjectRepo extends MongoRepository<TestObject, String> {
    Optional<TestObject> findTestObjectByEmail(String email);
}
