package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        /* removing loggers and get only warnings */
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);


        String cnnectionString = "mongodb+srv://twitch:dinithi12345@twitchcluster.yqnkxgo.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(cnnectionString)) {
            Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);

            ArrayList<Document> documents = mongoClient.listDatabases().into(new ArrayList<>());
            documents.forEach(document -> System.out.println(document.toJson()));


//            MongoIterable<String> strings = mongoClient.listDatabaseNames();
//            MongoCursor<String> cursor = strings.cursor();
//            while (cursor.hasNext()){
//                System.out.println(cursor.hasNext());
//            }
        }
    }
}


