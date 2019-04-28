import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;


public class test {

	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient();
		
		MongoDatabase db = mongoClient.getDatabase("RentACarDB");
		
		System.out.println(db.listCollectionNames().first());

	}

}
