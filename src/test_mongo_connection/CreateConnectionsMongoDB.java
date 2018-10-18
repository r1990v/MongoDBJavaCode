package test_mongo_connection;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class CreateConnectionsMongoDB {

	public static void main(String[] args) {
		
		//Loop to create 100 connections and closing it to check BOTMAP Feature.
		for(int i=0;i<100;i++)
		{
		MongoClient mongoClient = new MongoClient("10.134.0.28",27017);
		MongoDatabase database = mongoClient.getDatabase("harmonyDB");
		for (String name : database.listCollectionNames()) {
		    System.out.println(name);
		}
		mongoClient.close();
		}
		
	
		
		//MongoCollection<Document> coll = database.getCollection("projects");


	}

}
