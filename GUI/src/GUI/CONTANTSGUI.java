package GUI;

public interface CONTANTSGUI {

	public static int WD_WIDTH = 800;
	public static int WD_HEIGHT = 600;
	public static int WD_VH_INFO_WIDTH = 700;
	public static int WD_VH_INFO_HEIGHT = 300;

	// ------- tabbedPanel
	public static String START_LB = "Inicio";
	public static String USERS_LB = "Usuarios";
	public static String VEHICLES_LB = "Vehículos";
	public static String RENTS_LB = "Renta";

	// ------- UserPanel
	public static int IDX_ID = 0;
	public static int IDX_NAME = 1;
	public static int IDX_LASTNAME = 2;
	public static int IDX_EMAIL = 3;
	public static int IDX_PHONE = 4;

	public static String ID_LB = "Cédula";

	// -------- VehiclePanel
	public static int IDX_PLATE = 0;
	public static int IDX_CAPACITY = 1;
	public static int IDX_BRAND = 2;
	public static int IDX_STYLE = 3;
	public static int IDX_MODEL = 4;
	public static int IDX_COLOR = 5;
	public static int IDX_CC = 6;
	public static int IDX_FUEL = 7;
	public static int IDX_TRANSMISSION = 8;
	public static int IDX_YEAR = 9;
	public static int IDX_EXTRAS = 10;
	public static int IDX_PASSENGERS = 11;
	public static int IDX_RENT_PRICE = 12;
	public static int IDX_STATE = 13;

	public static String[] VEHICLE_FIELDS = { "placa", "capacidad", "marca", "estilo", "modelo", "color", "cilindrada",
			"combustible", "transmision", "año", "extras", "pasajeros", "precio", "estado" };

	public static String PLATE_LB = "Placa";
	public static String CAPATICY_LB = "Capacidad";
	public static String BRAND_LB = "Marca";
	public static String STYLE_LB = "Estilo";
	public static String MODEL_LB = "Modelo";
	public static String COLOR_LB = "Color";
	public static String CC_LB = "Cilindrada";
	public static String FUEL_LB = "Combustible";
	public static String TRANSMISSION_LB = "Transmisión";
	public static String YEAR_LB = "Año";
	public static String EXTRAS_LB = "Extras";
	public static String PASSENGERS_LB = "Pasajeros";
	public static String RENT_PRICE_LB = "Precio/día";
	public static String STATUS_LB = "Estado";

	public static String[] STATES = { "Disponible", "Rentado" };
	public static String[] FUELS = { "Gasolina", "Diesel", "Híbrido", "Eléctrico" };
	public static String[] TRANSMISSION = { "Manual", "Automática" };
}
