package test.mongo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CountDocumentsInAMongoCollection {

	public static void main(String[] args) {

		// Get logger related to MongoDB driver.
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		// Update the logging level to get rid of unwanted information.
		mongoLogger.setLevel(Level.SEVERE);

		// Create a client which connects to mongoDB database by providing server_ip and
		// associated port.
		MongoClient mongoClient = new MongoClient("192.168.56.101", 27017);
		// Connect to a database named "vats" using mongoClient.
		MongoDatabase database = mongoClient.getDatabase("vats");
		// Set date time formatter so that we can display data in required format.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		// Create LocalDateTime and MongoCollection variables.
		LocalDateTime now;
		MongoCollection<Document> collection;

		// Number of times to count documents present in a collection.
		int numOfTimesToCheck = 50;
		int timeToWaitInMilliSec = 1000;

		// Loop to count the number of documents present in a collection within a
		// specific time.
		for (int Counter = 1; Counter <= numOfTimesToCheck; Counter++) {

			// Get the current time in
			now = LocalDateTime.now();

			// Get all the data related to a collection into a collection variable.
			collection = database.getCollection("table");

			// Print or display the current time and number of documents present in
			// specified collection.
			System.out.println(dtf.format(now) + "," + collection.count());

			try {

				Thread.sleep(timeToWaitInMilliSec);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		}

		// Close MongoDb Connection.
		mongoClient.close();
	}

}
