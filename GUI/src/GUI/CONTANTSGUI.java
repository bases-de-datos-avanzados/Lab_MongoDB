package GUI;

public interface CONTANTSGUI {

	public static int WD_WIDTH = 800;
	public static int WD_HEIGHT = 600;

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
