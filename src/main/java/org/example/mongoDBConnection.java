package org.example;

import com.mongodb.annotations.ThreadSafe;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.sql.Connection;

public class mongoDBConnection {

    private static mongoDBConnection mongoDBConnection=null;

    private final MongoCollection<Document> mongoCollection;

    public mongoDBConnection() {
        String cnnectionString = "mongodb+srv://twitch:dinithi12345@twitchcluster.yqnkxgo.mongodb.net/?retryWrites=true&w=majority";
        MongoClient mongoClient = MongoClients.create(cnnectionString);
        mongoCollection = mongoClient.getDatabase("xmas").getCollection("cookies");
    }

    public static mongoDBConnection getInstance(){
        if (mongoDBConnection==null) {
            mongoDBConnection=new mongoDBConnection();
        }
        return mongoDBConnection;
    }

    public MongoCollection<Document> getMongoCollection() {
        return mongoCollection;
    }
}
