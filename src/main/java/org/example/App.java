package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
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


    /* creating new database called xmas and collection called cookies, details chocolate cookies */
    private static void createDocuments(MongoClient mongoClient) {
        MongoCollection<Document> cookies = mongoClient.getDatabase("xmas").getCollection("cookies");

        /* adding single data using insertOne() method */
        /*cookies.insertOne(new Document("name", "chocolate chips"));*/

        /* deleting previous cookies */
        /* cookies.deleteMany(new Document());*/

        /* deleting pink color cookies */
        cookies.deleteMany(new Document("color","pink"));

        /* adding more cookies using insertMany() method */
        List<Document> cookiesList = new ArrayList<>();

        List<String> ingredients = List.of("flour", "eggs", "butter", "sugar", "blue food coloring");

        for (int i = 0; i < 10; i++) {
            cookiesList.add(new Document("_id", i).append("color", "blue").append("array", ingredients));
        }

        cookies.insertMany(cookiesList);
    }

    /* getting all databases on project twitch */
    private static void printDatabases(MongoClient mongoClient) {
        ArrayList<Document> documents = mongoClient.listDatabases().into(new ArrayList<>());
        documents.forEach(document -> System.out.println(document.toJson()));
    }
}


