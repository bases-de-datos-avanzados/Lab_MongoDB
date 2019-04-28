package DB;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Users implements CONSTANTSDB {

	/**
	 * Insert a new document to the "usuario" collection
	 * 
	 * @param pId       Id (Cedula)
	 * @param pName     Name
	 * @param pLastName Last name
	 * @param pEmail    Email
	 * @param pPhones   Contact phone(s)
	 * @return -1 if document already exists, 0 if success
	 */
	public int insert(String pId, String pName, String pLastName, String pEmail, String pPhones) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> userCollection = db.getCollection("usuario");

		if (userCollection.find(Filters.eq("cedula", pId)).first() != null) {
			mongoClient.close();
			return -1;
		}

		Document user = new Document("cedula", pId);
		user.append("nombre", pName);
		user.append("apellido", pLastName);
		user.append("email", pEmail);
		user.append("telefonos", Arrays.asList(pPhones.split(",\\s*")));

		userCollection.insertOne(user);
		mongoClient.close();
		return 0;
	}

	/**
	 * Find a specific user in the data base by its id(cedula)
	 * 
	 * @param pId Id (Cedula)
	 * @return ArrayList of fields values
	 */
	public ArrayList<String> findUser(String pId) {

		ArrayList<String> fields = new ArrayList<String>();

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> userCollection = db.getCollection("usuario");

		Document user = userCollection.find(Filters.eq("cedula", pId)).first();
		if (user == null) {
			mongoClient.close();
			return null;
		}

		for (int i = 0; i < USER_FIELDS.length; i++) {
			fields.add(user.get(USER_FIELDS[i]).toString().replaceAll("\\[*\\]*", ""));
		}

		mongoClient.close();

		return fields;
	}

	/**
	 * Update the fields of a document in the collection "usuario"
	 * 
	 * @param pId       Id (cedula)
	 * @param pName     Name
	 * @param pLastName Last name
	 * @param pEmail    Email
	 * @param pPhones   Phones
	 * @return -1 if the document doesn't exists, 0 if success
	 */
	public int update(String pId, String pName, String pLastName, String pEmail, String pPhones) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		MongoCollection<Document> userCollection = db.getCollection("usuario");

		if (userCollection.find(Filters.eq("cedula", pId)).first() == null) {
			mongoClient.close();
			return -1;
		}

		Bson filter = new Document(USER_FIELDS[0], pId);
		Document update = new Document(USER_FIELDS[0], pId);
		update.append(USER_FIELDS[1], pName);
		update.append(USER_FIELDS[2], pLastName);
		update.append(USER_FIELDS[3], pEmail);
		update.append(USER_FIELDS[4], Arrays.asList(pPhones.split(",\\s*")));

		Bson updateOpDoc = new Document("$set", update);
		userCollection.updateOne(filter, updateOpDoc);
		mongoClient.close();

		return 0;
	}

}
