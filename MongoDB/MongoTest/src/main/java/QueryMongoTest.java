import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class QueryMongoTest {

    public static void main(String[] args) {
        String uri = "mongodb://course3:course3@localhost:27017/course3";

        //Step 1: Create a MongoClient
        MongoClient mongoClient = MongoClients.create(uri);

        //STEP 2: Select the database to work with
        //similar to "db" object in the shell
        MongoDatabase database = mongoClient.getDatabase("course3");

//        //STEP 3 : Create the Collection
//        database.createCollection("patients");

        //STEP 4: Get the Collection
        //similar to the "db.collectionName" object in the shell
        MongoCollection<Document> patients = database.getCollection("patients");

        //db.patiens.find({last_name : 'Kumar'})
        Document patient = patients.find(new Document("last_name","Kumar")).first();

        System.err.println(patient);

        // find with logical operator
        // db.patients.find({$or : [{last_name : 'Kumar'} , {last_name : 'Mani'}]})
        patients.find(new Document("$or", Arrays.asList(new Document("last_name","Kumar") ,
                                   new Document("last_name","Mani"))))
                                   .iterator().forEachRemaining( doc -> System.err.println("Logical Query : " + doc));

        // db.patients.find({medicines : 'Advil'})
        patient = patients.find(new Document("medicines","Advil")).first();
        System.err.println("Array Find " +patient);

        //db.patients.find("address.state" : "AL"})
        patients.find(new Document("address.state" , "AL"))
                      .iterator()
                      .forEachRemaining(doc -> System.err.println("Sub Document: " + doc));

        //db.patients.find({gender:"male"}).sort({first_name:1})
        patients.find(new Document("gender","male")).sort(new Document("first_name" , 1))
                .iterator()
                .forEachRemaining(doc -> System.err.println("Sorted: " + doc));

        //db.patients.find({gender:"male"}).count();
        long count = patients.countDocuments(new Document("gender","male"));
        System.err.println("Count: " + count);

        //db.patients.find({gender: "female"}).limit(1)
        patient = patients.find(new Document("gender","female")).limit(1).first();
        System.err.println("Limit: " + patient);

        //db.patients.find({gender : "male"}).sort({first_name:1}).limit(2)
        patients.find(new Document("gender","male")).sort(new Document("first_name" ,1)).limit(2)
                      .iterator()
                      .forEachRemaining(doc -> System.err.println("Combination: " + doc));

        mongoClient.close();



    }
}
