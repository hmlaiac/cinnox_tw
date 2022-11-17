package com.example.service.repository;

import com.example.service.model.LineUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LineUserRepository extends MongoRepository<LineUser, String> {
    Optional<LineUser> findLineUserByUserId(String userId);
}
