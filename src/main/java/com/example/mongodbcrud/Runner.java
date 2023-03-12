package com.example.mongodbcrud;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Component
public class Runner implements CommandLineRunner {

    FlashcardRepository repository;
    public Runner( FlashcardRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Flashcard> entries = repository.findAll();
        entries.stream().forEach( e-> System.out.println( e.toString() ));

        long count = repository.count();
        System.out.println( count);

        QFlashcard qflashcard = new QFlashcard( "abc");
        BooleanExpression expression = qflashcard.uuid.eq( "001599b0-60e0-43ca-81b7-782b173d900b");
        Iterable<Flashcard> oneItemlist = repository.findAll( expression);

        System.out.println();
        oneItemlist.forEach( e -> System.out.println( e.toString()));


        Flashcard flashcard = new Flashcard();
        flashcard.setUserName( "Wodnik Szuwarek");
        flashcard.setFolder( "Puszcza");
        flashcard.setPolish(Arrays.asList( "Gdzie szumią wierzby" ));
        flashcard.setForeign( Arrays.asList("W Puszczy"));
        String uuid = UUID.randomUUID().toString();
        flashcard.setUuid( uuid);
        flashcard.setHashtags( Arrays.asList( "Testy"));

        repository.save( flashcard);
    }
}
