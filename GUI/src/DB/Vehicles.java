package DB;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Vehicles implements CONSTANTSDB {

	/**
	 * Insert a new document to the collection "vehiculo" of the data base
	 * 
	 * @param pFields Fields of the document
	 * @return -1 if document already exists, 0 if success
	 */
	public int insert(ArrayList<String> pFields) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> vehicleCollection = db.getCollection("vehiculo");

		if (vehicleCollection.find(Filters.eq("placa", pFields.get(PLATE_IDX))).first() != null) {
			mongoClient.close();
			return -1;
		}

		Document vehicle = new Document(VEHICLE_FIELDS[PLATE_IDX], pFields.get(PLATE_IDX));
		vehicle.append(VEHICLE_FIELDS[CAPACITY_IDX], Integer.parseInt(pFields.get(CAPACITY_IDX)));
		vehicle.append(VEHICLE_FIELDS[BRAND_IDX], pFields.get(BRAND_IDX));
		vehicle.append(VEHICLE_FIELDS[STYLE_IDX], pFields.get(STYLE_IDX));
		vehicle.append(VEHICLE_FIELDS[MODEL_IDX], pFields.get(MODEL_IDX));
		vehicle.append(VEHICLE_FIELDS[COLOR_IDX], pFields.get(COLOR_IDX));
		vehicle.append(VEHICLE_FIELDS[CC_IDX], Integer.parseInt(pFields.get(CC_IDX)));
		vehicle.append(VEHICLE_FIELDS[FUEL_IDX], pFields.get(FUEL_IDX));
		vehicle.append(VEHICLE_FIELDS[TRANSMISSION_IDX], pFields.get(TRANSMISSION_IDX));
		vehicle.append(VEHICLE_FIELDS[YEAR_IDX], Integer.parseInt(pFields.get(YEAR_IDX)));
		vehicle.append(VEHICLE_FIELDS[EXTRAS_IDX], Arrays.asList(pFields.get(EXTRAS_IDX).split(",\\s*")));
		vehicle.append(VEHICLE_FIELDS[PASSENGERS_IDX], Integer.parseInt(pFields.get(PASSENGERS_IDX)));
		vehicle.append(VEHICLE_FIELDS[RENT_PRICE_IDX],
				Integer.parseInt(pFields.get(RENT_PRICE_IDX).replaceAll(" ", "")));
		vehicle.append(VEHICLE_FIELDS[STATE_IDX], pFields.get(STATE_IDX));

		vehicleCollection.insertOne(vehicle);
		mongoClient.close();
		return 0;
	}

	/**
	 * Find a specific vehicle in the data base by its plate
	 * 
	 * @param pPlate Plate of the vehicle
	 * @return ArrayList with field values, null if not found
	 */
	public ArrayList<String> findVehicle(String pPlate) {

		ArrayList<String> fields = new ArrayList<String>();

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> userCollection = db.getCollection("vehiculo");

		Document vehicle = userCollection.find(Filters.eq(VEHICLE_FIELDS[PLATE_IDX], pPlate)).first();
		if (vehicle == null) {
			mongoClient.close();
			return null;
		}

		for (int i = 0; i < VEHICLE_FIELDS.length; i++) {
			fields.add(vehicle.get(VEHICLE_FIELDS[i]).toString().replaceAll("\\[*\\]*", ""));
		}

		mongoClient.close();

		return fields;
	}

	public int update(ArrayList<String> pFields) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> vehicleCollection = db.getCollection("vehiculo");

		if (vehicleCollection.find(Filters.eq(VEHICLE_FIELDS[PLATE_IDX], pFields.get(PLATE_IDX))).first() == null) {
			mongoClient.close();
			return -1;
		}

		Bson filter = new Document(VEHICLE_FIELDS[PLATE_IDX], pFields.get(PLATE_IDX));
		Document update = new Document(VEHICLE_FIELDS[PLATE_IDX], pFields.get(PLATE_IDX));
		update.append(VEHICLE_FIELDS[CAPACITY_IDX], Integer.parseInt(pFields.get(CAPACITY_IDX)));
		update.append(VEHICLE_FIELDS[BRAND_IDX], pFields.get(BRAND_IDX));
		update.append(VEHICLE_FIELDS[STYLE_IDX], pFields.get(STYLE_IDX));
		update.append(VEHICLE_FIELDS[MODEL_IDX], pFields.get(MODEL_IDX));
		update.append(VEHICLE_FIELDS[COLOR_IDX], pFields.get(COLOR_IDX));
		update.append(VEHICLE_FIELDS[CC_IDX], Integer.parseInt(pFields.get(CC_IDX)));
		update.append(VEHICLE_FIELDS[FUEL_IDX], pFields.get(FUEL_IDX));
		update.append(VEHICLE_FIELDS[TRANSMISSION_IDX], pFields.get(TRANSMISSION_IDX));
		update.append(VEHICLE_FIELDS[YEAR_IDX], Integer.parseInt(pFields.get(YEAR_IDX)));
		update.append(VEHICLE_FIELDS[EXTRAS_IDX], Arrays.asList(pFields.get(EXTRAS_IDX).split(",\\s*")));
		update.append(VEHICLE_FIELDS[PASSENGERS_IDX], Integer.parseInt(pFields.get(PASSENGERS_IDX)));
		update.append(VEHICLE_FIELDS[RENT_PRICE_IDX],
				Integer.parseInt(pFields.get(RENT_PRICE_IDX).replaceAll(" ", "")));
		update.append(VEHICLE_FIELDS[STATE_IDX], pFields.get(STATE_IDX));

		Bson updateOpDoc = new Document("$set", update);
		vehicleCollection.updateOne(filter, updateOpDoc);
		mongoClient.close();
		return 0;
	}

}
