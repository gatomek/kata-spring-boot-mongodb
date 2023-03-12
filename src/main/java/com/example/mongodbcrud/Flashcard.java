package com.example.mongodbcrud;

import com.querydsl.core.annotations.QueryEntity;
import lombok.*;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document( "entries")
@QueryEntity
public class Flashcard {

    @MongoId
    private String id;

    @Field( "uuid")
    private String uuid;

    @Field( "username")
    private String userName;

    private String folder;
}
