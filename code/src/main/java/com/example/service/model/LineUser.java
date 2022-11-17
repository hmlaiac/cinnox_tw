package com.example.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.net.URI;
import java.util.List;

@Data
@Document(collection = "line_user")
public class LineUser {
    @Id
    @Field("userId")
    private String userId;

    private String displayName;
    private String pictureUrl;
    private String language;

    public LineUser(String userId, String displayName, String pictureUrl, String language) {
        this.userId = userId;
        this.displayName = displayName;
        this.pictureUrl = pictureUrl;
        this.language = language;
    }
}
