package com.example.mongodbcrud;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

@EnableMongoRepositories
public interface FlashcardRepository extends MongoRepository<Flashcard, String>, QuerydslPredicateExecutor<Flashcard> {

    @Query("{uuid : '?0'}")
    Flashcard findItemByUuid(String uuid);

    @Query(value="{username : '?0'}", fields="{'username' : 1, 'uuid': 1, 'folder': 1 }")
    List<Flashcard> findAll(String username);

    @Query(value="{}", fields="{'username' : 1, 'uuid': 1, 'folder': 1 }")
    List<Flashcard> findAll();

    public long count();
}
