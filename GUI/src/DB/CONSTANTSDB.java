package DB;

public interface CONSTANTSDB {

	// ----------- User
	public static String[] USER_FIELDS = { "cedula", "nombre", "apellido", "email", "telefonos" };

	// ----------- Vehicle
	public static int PLATE_IDX = 0;
	public static int CAPACITY_IDX = 1;
	public static int BRAND_IDX = 2;
	public static int STYLE_IDX = 3;
	public static int MODEL_IDX = 4;
	public static int COLOR_IDX = 5;
	public static int CC_IDX = 6;
	public static int FUEL_IDX = 7;
	public static int TRANSMISSION_IDX = 8;
	public static int YEAR_IDX = 9;
	public static int EXTRAS_IDX = 10;
	public static int PASSENGERS_IDX = 11;
	public static int RENT_PRICE_IDX = 12;
	public static int STATE_IDX = 13;

	public static String[] VEHICLE_FIELDS = { "placa", "capacidad", "marca", "estilo", "modelo", "color", "cilindrada",
			"combustible", "transmision", "año", "extras", "pasajeros", "precio", "estado" };
}
