package com.example.mongodbcrud;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.io.FileUtils;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


@Component
public class Runner implements CommandLineRunner {

    FlashcardRepository repository;

    public Runner(FlashcardRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Flashcard> entries = repository.findAll();
        entries.stream().forEach(e -> System.out.println(e.toString()));

        long count = repository.count();
        System.out.println(count);

        QFlashcard qflashcard = new QFlashcard("abc");
        BooleanExpression expression = qflashcard.uuid.eq("001599b0-60e0-43ca-81b7-782b173d900b");
        Iterable<Flashcard> oneItemlist = repository.findAll(expression);

        System.out.println();
        oneItemlist.forEach(e -> System.out.println(e.toString()));


        Flashcard flashcard = new Flashcard();
        flashcard.setUserName("Wodnik Szuwarek");
        flashcard.setFolder("Puszcza");
        flashcard.setPolish(Arrays.asList("Gdzie szumią wierzby"));
        flashcard.setForeign(Arrays.asList("W Puszczy"));
        String uuid = UUID.randomUUID().toString();
        flashcard.setUuid(uuid);
        flashcard.setHashtags(Arrays.asList("Testy"));


        File file = ResourceUtils.getFile("classpath:img/67f1da42-2eef-458a-9572-e4d1bfcad4d3.jpg");
        InputStream in = new FileInputStream(file);

        byte[] data = in.readAllBytes();
        Binary image = new Binary(BsonBinarySubType.BINARY, data);
        flashcard.setImage(image);

        repository.save(flashcard);
        //repository.insert(flashcard);

        {
            QFlashcard qflashcard2 = new QFlashcard("abc");
            BooleanExpression expression2 = qflashcard2.uuid.eq(uuid);
            Iterable<Flashcard> oneItemlist2 = repository.findAll(expression2);

            Iterator<Flashcard> iterator = oneItemlist2.iterator();

            while (iterator.hasNext()) {
                Flashcard fc = iterator.next();
                Binary binary = fc.getImage();
                byte[] data2 = binary.getData();

                File outFile = new File("c:/mongodb/67f1da42-2eef-458a-9572-e4d1bfcad4d3.jpg");
                FileUtils.writeByteArrayToFile(outFile, data2);
                break;
            }
        }
    }
}
