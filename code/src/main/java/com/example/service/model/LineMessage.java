package com.example.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "line_message")
public class LineMessage {
    @Indexed(unique = true)
    private String replyToken;

    private String lineId;
    private String message;
    private String username;
    private Date date;

    public LineMessage(@JsonProperty("replyToken") String replyToken,
                       @JsonProperty("lineId") String lineId, @JsonProperty("message") String message,
                       @JsonProperty("username") String username) {
        this.replyToken = replyToken;
        this.lineId = lineId;
        this.message = message;
        this.username = username;
        this.date = new Date();
    }
}