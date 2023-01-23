package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.example.Singleton.mongoDB.mongoDBConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Updates.set;


public class App{


    public static void main(String[] args) {

        /* removing loggers and get only warnings */
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);

        MongoCollection<Document> mongoCollection = mongoDBConnection.getInstance().getMongoCollection();


        /** CRUD also mean by create, read, update, delete */

        /* delete function */
        deleteDocuments(mongoCollection);

        /* create functions */
        createDocuments(mongoCollection);

        /* update function */
        updateDocuments(mongoCollection);

        /* find function */
        findDocuments(mongoCollection);
    }


    private static void findDocuments(MongoCollection<Document> mongoCollection) {

        System.out.println();
        System.out.println("FIND FUNCTION INVOKED =====================================================================================");
        System.out.println();

        ArrayList<Document> lowCaloriesCookies = mongoCollection.find(Filters.gte("calories", 500)).into(new ArrayList<>());
        for (Document lowCaloriesCookie : lowCaloriesCookies) {
            System.out.println(lowCaloriesCookie.toJson());
        }

        System.out.println();
        System.out.println("FIND FUNCTION CLOSED =====================================================================================");
        System.out.println();

    }

    private static void updateDocuments(MongoCollection<Document> mongoCollection) {

        System.out.println();
        System.out.println("UPDATE FUNCTION INVOKED =====================================================================================");
        System.out.println();

        Random random = new Random();
        mongoCollection.updateMany(new Document(), set("calories", random.nextInt(1000)));

        List<Document> cookiesList = mongoCollection.find().into(new ArrayList<>());
        cookiesList.forEach(c -> {
            Object id = c.get("_id");
            Document filter = new Document("_id", id);
            Bson update = set("calories", random.nextInt(1000));
            FindOneAndUpdateOptions findOneAndUpdateOptions = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document cookie = mongoCollection.findOneAndUpdate(filter, update, findOneAndUpdateOptions);
            System.out.println(cookie.toJson());
        });

        System.out.println();
        System.out.println("UPDATE FUNCTION CLOSED =====================================================================================");
        System.out.println();

    }

    private static void deleteDocuments(MongoCollection<Document> mongoCollection) {

        System.out.println();
        System.out.println("DELETE FUNCTION INVOKED =====================================================================================");
        System.out.println();

        mongoCollection.deleteMany(new Document());

        System.out.println();
        System.out.println("DELETE FUNCTION CLOSED =====================================================================================");
        System.out.println();

    }


    /* creating new database called xmas and collection called cookies, details chocolate cookies */
    private static void createDocuments(MongoCollection<Document> mongoCollection) {

        System.out.println();
        System.out.println("CREATE FUNCTION INVOKED =====================================================================================");
        System.out.println();

        /* adding more cookies using insertMany() method */
        List<Document> cookiesList = new ArrayList<>();

        List<String> ingredients = List.of("flour", "eggs", "butter", "sugar", "red food coloring");

        for (int i = 0; i < 10; i++) {
            cookiesList.add(new Document("cookies_id", i).append("color", "pink").append("array", ingredients));
        }

        mongoCollection.insertMany(cookiesList);

        System.out.println();
        System.out.println("CREATE FUNCTION CLOSED =====================================================================================");
        System.out.println();

    }

    /* getting all databases on project twitch */
    private static void printDatabases(MongoClient mongoClient) {
        ArrayList<Document> documents = mongoClient.listDatabases().into(new ArrayList<>());
        documents.forEach(document -> System.out.println(document.toJson()));
    }
}


