package test_mongo_connection;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class GetMongoDbConnectionCount {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

//Get logger related to MongoDB driver.
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		// Update the logging level to get rid of unwanted information, mongoLogger
		// could be commented to get
		// detailed information related to any of the issues.
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("10.134.0.28", 27017);
		MongoDatabase database = mongoClient.getDatabase("harmonyDB");

		// Loop to keep checking the connection count every second.
		for (int i = 0; i < 1000; i++) {

			Document serverStatus = database.runCommand(new Document("serverStatus", 1));
			Map connections = (Map) serverStatus.get("connections");
			Integer current = (Integer) connections.get("current");
			current = current - 2;
			Integer available = (Integer) connections.get("available");
			available = available + 2;

			try {
				System.out.println("Current: " + current.toString() + ", Available: " + available.toString());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		mongoClient.close();
	}

}
