package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DB.Vehicles;

@SuppressWarnings("serial")
public class AdvanceSearchPanel extends JPanel implements ActionListener, CONTANTSGUI {

	private JPanel _row1, _row2, _row3, _row4;

	private JButton _allVehiclesBT, _byBrandBT, _priceRangeBT, _byModelBT;
	private JButton _consultByBrandBT, _consultPriceRangeBT, _consultByModelBT;

	private JTextField _price1Tx, _price2Tx;
	private JComboBox<String> _brandsComboBox, _modelsComboBox, _modelsComboBox2;
	private DefaultComboBoxModel<String> _modelBrandsCBox, _modelModelsCBox;

	private Vehicles _vehiclesDB;

	/**
	 * Class constructor
	 * 
	 * @param pVehiclesDB Class that manage connection to the DB
	 */
	public AdvanceSearchPanel(Vehicles pVehiclesDB) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		_vehiclesDB = pVehiclesDB;
		initElements();
		addElements();
		addListeners();
	}

	/**
	 * Initialize all the objects
	 */
	private void initElements() {
		_row1 = new JPanel();
		_row2 = new JPanel();
		_row3 = new JPanel();
		_row4 = new JPanel();

		_allVehiclesBT = new JButton("Todos");
		_byBrandBT = new JButton("Marca");
		_priceRangeBT = new JButton("Precio");
		_byModelBT = new JButton("Modelo");

		_consultByBrandBT = new JButton("Consultar");
		_consultPriceRangeBT = new JButton("Consultar");
		_consultByModelBT = new JButton("Consultar");
		_modelBrandsCBox = new DefaultComboBoxModel<String>();
		_modelModelsCBox = new DefaultComboBoxModel<String>();
		_brandsComboBox = new JComboBox<String>(_modelBrandsCBox);
		_modelsComboBox = new JComboBox<String>(_modelModelsCBox);
		_modelsComboBox2 = new JComboBox<String>(_modelModelsCBox);
		_price1Tx = new JTextField(8);
		_price2Tx = new JTextField(8);

		_consultByBrandBT.setVisible(false);
		_consultPriceRangeBT.setVisible(false);
		_consultByModelBT.setVisible(false);
		_brandsComboBox.setVisible(false);
		_modelsComboBox.setVisible(false);
		_modelsComboBox2.setVisible(false);
		_price1Tx.setVisible(false);
		_price2Tx.setVisible(false);
	}

	/**
	 * Add all the elements to the corresponding panel
	 */
	private void addElements() {
		_row1.add(_allVehiclesBT);
		this.add(_row1);

		_row2.add(_byBrandBT);
		_row2.add(_brandsComboBox);
		_row2.add(_consultByBrandBT);
		this.add(_row2);

		_row3.add(_priceRangeBT);
		_row3.add(_modelsComboBox);
		_row3.add(_price1Tx);
		_row3.add(_price2Tx);
		_row3.add(_consultPriceRangeBT);
		this.add(_row3);

		_row4.add(_byModelBT);
		_row4.add(_modelsComboBox2);
		_row4.add(_consultByModelBT);
		this.add(_row4);
	}

	/**
	 * Add the current panel as listener to all the buttons
	 */
	private void addListeners() {
		_allVehiclesBT.addActionListener(this);
		_byBrandBT.addActionListener(this);
		_priceRangeBT.addActionListener(this);
		_byModelBT.addActionListener(this);

		_consultByBrandBT.addActionListener(this);
		_consultPriceRangeBT.addActionListener(this);
		_consultByModelBT.addActionListener(this);
	}

	/**
	 * Hide all the text fields and buttons of each search option
	 */
	private void hideSubElements() {
		_brandsComboBox.setVisible(false);
		_modelsComboBox.setVisible(false);
		_modelsComboBox2.setVisible(false);
		_consultByBrandBT.setVisible(false);
		_price1Tx.setVisible(false);
		_price2Tx.setVisible(false);
		_consultPriceRangeBT.setVisible(false);
		_consultByModelBT.setVisible(false);

	}

	/**
	 * Fill the combo box with all the brands of vehicles registered in the data
	 * base
	 */
	private void fillBrandComboBox() {
		ArrayList<String> brands = _vehiclesDB.getDistinctFields(VEHICLE_FIELDS[IDX_BRAND]);
		if (brands != null) {
			_modelBrandsCBox.removeAllElements();
			for (String brand : brands)
				_modelBrandsCBox.addElement(brand);
		} else {
			_modelBrandsCBox.removeAllElements();
			_modelBrandsCBox.addElement("No hay vehículos");
		}
	}

	/**
	 * Fill the combo box with all the models of vehicles registered in the data
	 * base
	 */
	private void fillModelComboBox() {
		ArrayList<String> models = _vehiclesDB.getDistinctFields(VEHICLE_FIELDS[IDX_MODEL]);
		if (models != null) {
			_modelModelsCBox.removeAllElements();
			for (String model : models)
				_modelModelsCBox.addElement(model);
		} else {
			_modelModelsCBox.removeAllElements();
			_modelModelsCBox.addElement("No hay vehículos");
		}
	}

	/**
	 * Call the class that manage the connection with the DB to consult for
	 * addtional data
	 * 
	 * @return ArraList with data
	 */
	private ArrayList<String> consultAddtionalInfo() {
		ArrayList<String> data = new ArrayList<String>();
		int numberVehicles = _vehiclesDB.getNumberBrandVehicles(_brandsComboBox.getSelectedItem().toString());
		String[] minMax = _vehiclesDB.getVehicleMinMixPrice(_brandsComboBox.getSelectedItem().toString());
		int average = _vehiclesDB.getAVGPrice(_brandsComboBox.getSelectedItem().toString());

		data.add(String.valueOf(numberVehicles));
		data.add(minMax[0]);
		data.add(minMax[1]);
		data.add(String.valueOf(average));

		return data;
	}

	@Override
	public void actionPerformed(ActionEvent pEvent) {

		if (pEvent.getSource() == _allVehiclesBT) {
			hideSubElements();
			ArrayList<ArrayList<String>> vehicles = _vehiclesDB.getVehicles();
			VehicleInfoDisplay info = new VehicleInfoDisplay(1, false, vehicles, null);
		}

		if (pEvent.getSource() == _byBrandBT) {
			hideSubElements();
			fillBrandComboBox();
			_brandsComboBox.setVisible(true);
			_consultByBrandBT.setVisible(true);

		}

		if (pEvent.getSource() == _priceRangeBT) {
			hideSubElements();
			fillModelComboBox();
			_modelsComboBox.setVisible(true);
			_price1Tx.setVisible(true);
			_price2Tx.setVisible(true);
			_consultPriceRangeBT.setVisible(true);
		}

		if (pEvent.getSource() == _byModelBT) {
			hideSubElements();
			fillModelComboBox();
			_modelsComboBox2.setVisible(true);
			_consultByModelBT.setVisible(true);
		}

		if (pEvent.getSource() == _consultByBrandBT) {
			if (_brandsComboBox.getComponentCount() != 0) {
				ArrayList<ArrayList<String>> vehicles = _vehiclesDB.filterVehicles(VEHICLE_FIELDS[IDX_BRAND],
						_brandsComboBox.getSelectedItem().toString());
				ArrayList<String> addtionalData = consultAddtionalInfo();
				VehicleInfoDisplay info = new VehicleInfoDisplay(1, true, vehicles, addtionalData);
			}
		}

		if (pEvent.getSource() == _consultByModelBT) {
			if (_modelsComboBox.getComponentCount() != 0) {
				ArrayList<ArrayList<String>> vehicles = _vehiclesDB.filterVehicles(VEHICLE_FIELDS[IDX_MODEL],
						_modelsComboBox.getSelectedItem().toString());
				VehicleInfoDisplay info = new VehicleInfoDisplay(2, false, vehicles, null);
			}
		}

	}

}
