import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoTest {

    public static void main(String[] args) {

        String uri = "mongodb://course3:course3@localhost:27017/course3";

        //Step 1: Create a MongoClient
        MongoClient mongoClient = MongoClients.create(uri);

        //STEP 2: Select the database to work with
        //similar to "db" object in the shell
        MongoDatabase database = mongoClient.getDatabase("course3");

        //STEP 3 : Create the Collection
        database.createCollection("patients");

        //STEP 4: Get the Collection
        //similar to the "db.collectionName" object in the shell
        MongoCollection<Document> patients = database.getCollection("patients");

        //create a document
        Document patient1 = new Document()
                           .append("first_name","Sana")
                           .append("last_nam","Kumar")
                           .append("age",45);

        Document address2 = new Document()
                .append("street" , "649 First St")
                .append("city" , "Minneapolis")
                .append("state","AL");

        Document patient2 = new Document()
                .append("first_name","Murali")
                .append("last_nam","Mani")
                .append("age",32)
                .append("gender","male")
                .append("medicines", Arrays.asList("DoloCold","Ibuprofen","Advil"))
                .append("address" , address2);


        //insert a document
        //patients.insertOne(patient1);
        List<Document> listDocument = new ArrayList<>();
        listDocument.add(patient1);
        listDocument.add(patient2);

        patients.insertMany(listDocument);

        //use the id for subsequent operations
        ObjectId _id = patient1.getObjectId("_id");

        Document address = new Document()
                           .append("street" , "649 First St")
                           .append("city" , "Minneapolis")
                           .append("state","MN");

        //replace the document just inserted to include "gender" field
        //This translates to db.patients.update()
        patients.replaceOne(new Document("_id", _id), new Document()
                            .append("first_name", "Sana")
                            .append("last_name", "Kumar")
                            .append("age",45)
                            .append("gender","female")
                            .append("medicines", Arrays.asList("zzzQuil","Ibuprofen","Advil"))
                            .append("address" , address));

        //update the document to rename a field
        patients.updateOne(new Document("_id", _id), new Document()
                           .append("$rename", new Document("gender", "sex")));

        //upsert here
        patients.replaceOne(new Document("first_name", "Eduardo") , new Document("first_name" , "Edurado")
                            .append("last_name" , "Lopez").append("age",23).append("gender","male"),
                            new ReplaceOptions().upsert(true));

        //update the document to rename a field
        patients.updateOne(new Document("_id", _id), new Document()
                .append("$rename", new Document("sex", "gender")));


        //Use the delete method to remove  a document
        //patients.deleteOne(new Document("_id",_id));

        mongoClient.close();


    }
}
