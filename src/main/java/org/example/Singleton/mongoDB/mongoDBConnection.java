package org.example.Singleton.mongoDB;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class mongoDBConnection {

    private static mongoDBConnection mongoDBConnection = null;

    private final MongoCollection<Document> mongoCollection;

    public mongoDBConnection() {
        mongoCollection =
                MongoClients
                        .create("mongodb+srv://twitch:dinithi12345@twitchcluster.yqnkxgo.mongodb.net/?retryWrites=true&w=majority")
                        .getDatabase("xmas")
                        .getCollection("cookies");
    }

    public static mongoDBConnection getInstance() {
        if (mongoDBConnection == null) {
            mongoDBConnection = new mongoDBConnection();
        }
        return mongoDBConnection;
    }

    public MongoCollection<Document> getMongoCollection() {
        return mongoCollection;
    }
}
