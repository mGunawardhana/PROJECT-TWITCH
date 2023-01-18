package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import java.util.ArrayList;
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
            /*printDatabases(mongoClient);*/

            createDocuments(mongoClient);
        }
    }

    private static void createDocuments(MongoClient mongoClient) {

    }

    private static void printDatabases(MongoClient mongoClient) {
        ArrayList<Document> documents = mongoClient.listDatabases().into(new ArrayList<>());
        documents.forEach(document -> System.out.println(document.toJson()));
    }
}


