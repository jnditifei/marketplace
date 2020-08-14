package com.marketplace.product.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "comment")
public class CommentEntity {

    @Id
    private String id;

    private String title;

    private String message;
}
