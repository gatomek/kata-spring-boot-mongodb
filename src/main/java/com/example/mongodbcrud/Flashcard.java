package com.example.mongodbcrud;

import com.querydsl.core.annotations.QueryEntity;
import lombok.*;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "entries")
@QueryEntity
public class Flashcard {

    @MongoId
    private String id;

    @Field( "uuid")
    private String uuid;

    @Field( "username")
    private String userName;

    @Field( "folder")
    private String folder;

    @Field( "polish")
    private List<String> polish;

    @Field( "foreign")
    private List<String> foreign;

    @Field( "hashtags")
    private List<String> hashtags;

    @Field( "image")
    private Binary image;
}
