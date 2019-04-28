package DB;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Rent {

	public int insert(String pPlate, String pClientId, int pDays, int pTotal) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> rentCollection = db.getCollection("renta");

		if (rentCollection.find(Filters.eq("placa", pPlate)).first() != null) {
			mongoClient.close();
			return -1;
		}

		Document rent = new Document("placa", pPlate);
		rent.append("cedula", pClientId);
		rent.append("dias", pDays);
		rent.append("total", pTotal);

		rentCollection.insertOne(rent);
		mongoClient.close();
		return 0;
	}

}
