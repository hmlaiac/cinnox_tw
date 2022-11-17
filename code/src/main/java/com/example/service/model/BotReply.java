package com.example.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BotReply {
    private String messageId;
    private String message;

    public BotReply(@JsonProperty("messageId") String messageId, @JsonProperty("message") String message) {
        this.messageId = messageId;
        this.message = message;
    }
}
