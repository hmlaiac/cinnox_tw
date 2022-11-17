package com.example.service.repository;

import com.example.service.model.BotReply;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotReplyRepository extends MongoRepository<BotReply, String> {

}
