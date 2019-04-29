package DB;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
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

	/**
	 * Update the field of a document in the collection "vehiculo"
	 * 
	 * @param pFields ArrayList of values
	 * @return -1 if the document doen's exists, 0 if success
	 */
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

	/**
	 * Update one field of a specific document in the collection "vehiculo"
	 * 
	 * @param pPlate    Plate number
	 * @param pKey      Field name to update
	 * @param pKeyValue New value
	 * @return -1 if the vehicle doesn't exists, 0 if success
	 */
	public int updateOneField(String pPlate, String pKey, String pKeyValue) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> vehicleCollection = db.getCollection("vehiculo");

		if (vehicleCollection.find(Filters.eq(VEHICLE_FIELDS[PLATE_IDX], pPlate)).first() == null) {
			mongoClient.close();
			return -1;
		}

		Bson filter = new Document(VEHICLE_FIELDS[PLATE_IDX], pPlate);
		Document update = new Document(pKey, pKeyValue);

		Bson updateOpDoc = new Document("$set", update);
		vehicleCollection.updateOne(filter, updateOpDoc);
		mongoClient.close();
		return 0;
	}

	/**
	 * Obtains all the documents in the collection "vehiculo"
	 * 
	 * @return ArrayList with all documents fields
	 */
	public ArrayList<ArrayList<String>> getVehicles() {

		ArrayList<ArrayList<String>> vehicles = new ArrayList<ArrayList<String>>();

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> vehicleCollection = db.getCollection("vehiculo");

		for (Document doc : vehicleCollection.find()) {
			ArrayList<String> vehicle = new ArrayList<String>();
			for (int i = 1; i < VEHICLE_FIELDS.length + 1; i++)
				vehicle.add(doc.get(VEHICLE_FIELDS[i - 1]).toString().replaceAll("\\[*\\]*", ""));
			vehicles.add(vehicle);
		}

		mongoClient.close();
		return vehicles;
	}

	/**
	 * Obtains all the documents in the collection "vehiculo" that meets the
	 * condition pKey = pKeyValue
	 * 
	 * @param pKey      Field name
	 * @param pKeyValue Value of field
	 * @return ArrayList with the documents fields
	 */
	public ArrayList<ArrayList<String>> filterVehicles(String pKey, String pKeyValue) {
		ArrayList<ArrayList<String>> vehicles = new ArrayList<ArrayList<String>>();

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> vehicleCollection = db.getCollection("vehiculo");

		for (Document doc : vehicleCollection.find(Filters.eq(pKey, pKeyValue))) {
			ArrayList<String> vehicle = new ArrayList<String>();
			for (int i = 1; i < VEHICLE_FIELDS.length + 1; i++)
				vehicle.add(doc.get(VEHICLE_FIELDS[i - 1]).toString().replaceAll("\\[*\\]*", ""));
			vehicles.add(vehicle);
		}

		mongoClient.close();
		return vehicles;
	}

	/**
	 * Obtains all the different values (as String) of a specific field in the
	 * collection "vehiculo
	 * 
	 * @return ArrayList with the field value
	 */
	public ArrayList<String> getDistinctFields(String pKey) {
		ArrayList<String> fields = new ArrayList<String>();

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> vehicleCollection = db.getCollection("vehiculo");

		if (vehicleCollection.count() == 0) {
			mongoClient.close();
			return null;
		}

		for (String field : vehicleCollection.distinct(pKey, String.class)) {
			fields.add(field);
		}

		mongoClient.close();
		return fields;
	}

	/**
	 * Obtain the quantity of vehicles of a specific brand
	 * 
	 * @param pBrand Brand of vehicle
	 * @return Number of vehicles
	 */
	public int getNumberBrandVehicles(String pBrand) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> vehicleCollection = db.getCollection("vehiculo");

		Document query = new Document("marca", pBrand);
		return (int) vehicleCollection.count(query);
	}

	/**
	 * Obtains the plate numbers of the vehicles of a specific brand that has the
	 * minimum and maximum price of rent for day
	 * 
	 * @param pBrand Brand of the vehicle
	 * @return Plates of the vehicles
	 */
	public String[] getVehicleMinMixPrice(String pBrand) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> vehicleCollection = db.getCollection("vehiculo");

		Document match = new Document("$match", new Document("marca", pBrand));
		Document sort = new Document("$sort", new Document("precio", 1));
		Document project = new Document("$project", new Document("placa", "$placa").append("_id", 0));

		vehicleCollection.aggregate(Arrays.asList(match, sort, project));

		AggregateIterable<Document> result = vehicleCollection.aggregate(Arrays.asList(match, sort, project));

		String plateMin = result.first().get(VEHICLE_FIELDS[PLATE_IDX]).toString();

		String plateMax = "";
		for (Document doc : result)
			plateMax = doc.get(VEHICLE_FIELDS[PLATE_IDX]).toString();

		String[] minMax = { plateMin, plateMax };

		return minMax;
	}

	/**
	 * Obtains the average price of rent for day for all vehicles of a specific
	 * brand
	 * 
	 * @param pBrand Brand of vehicle
	 * @return Integer value of average
	 */
	public int getAVGPrice(String pBrand) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> vehicleCollection = db.getCollection("vehiculo");

		AggregateIterable<Document> result = vehicleCollection
				.aggregate(Arrays.asList(Aggregates.match(Filters.eq("marca", pBrand)),
						Aggregates.group("0", Accumulators.avg("promedio", "$precio"))));

		return result.first().getDouble("promedio").intValue();
	}

}