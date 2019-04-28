package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DB.Vehicles;

@SuppressWarnings("serial")
public class VehiclePanel extends JPanel implements ActionListener, CONTANTSGUI {

	private JButton _create;

	private JPanel _row1, _row2, _row3, _row4;

	private JTextField _plateTx, _capacityTx, _brandTx, _styleTx, _modelTx, _colorTx, _ccTx, _yearTx, _extrasTx,
			_passengersTx, _rentPriceTx;

	private JComboBox<String> _states, _fuels, _transmissions;

	private Vehicles _vehicles;

	/**
	 * Class constructor
	 * 
	 * @param pVehicles Class that manage connection to the DB
	 */
	public VehiclePanel(Vehicles pVehicles) {
		_vehicles = pVehicles;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// this.setPreferredSize(new Dimension(800, 500));
		initElements();
		addRow1();
		addRow2();
		addRow3();
		addRow4();
		this.add(Box.createRigidArea(new Dimension(0, 350)));
	}

	/**
	 * Initialize all the objects
	 */
	private void initElements() {
		_create = new JButton("Crear");
		_create.addActionListener(this);
		_row1 = new JPanel();
		_row2 = new JPanel();
		_row3 = new JPanel();
		_row4 = new JPanel();

		_plateTx = new JTextField(8);
		_capacityTx = new JTextField(2);
		_brandTx = new JTextField(10);
		_styleTx = new JTextField(10);
		_modelTx = new JTextField(10);
		_colorTx = new JTextField(10);
		_ccTx = new JTextField(4);
		_yearTx = new JTextField(4);
		_extrasTx = new JTextField(30);
		_passengersTx = new JTextField(2);
		_rentPriceTx = new JTextField(6);
		_states = new JComboBox<String>(STATES);
		_fuels = new JComboBox<String>(FUELS);
		_transmissions = new JComboBox<String>(TRANSMISSION);
	}

	/**
	 * Add the elements of the first row to the BoxLayout
	 */
	private void addRow1() {
		_row1.add(new JLabel(PLATE_LB));
		_row1.add(_plateTx);
		_row1.add(new JLabel(CAPATICY_LB));
		_row1.add(_capacityTx);
		_row1.add(new JLabel(BRAND_LB));
		_row1.add(_brandTx);
		_row1.add(new JLabel(STYLE_LB));
		_row1.add(_styleTx);
		_row1.add(new JLabel(MODEL_LB));
		_row1.add(_modelTx);
		this.add(_row1);
	}

	/**
	 * Add the elements of the second row to the BoxLayout
	 */
	private void addRow2() {
		_row2.add(new JLabel(COLOR_LB));
		_row2.add(_colorTx);
		_row2.add(new JLabel(CC_LB));
		_row2.add(_ccTx);
		_row2.add(new JLabel(YEAR_LB));
		_row2.add(_yearTx);
		_row2.add(new JLabel(EXTRAS_LB));
		_row2.add(_extrasTx);
		this.add(_row2);
	}

	/**
	 * Add the elements of the third row to the BoxLayout
	 */
	private void addRow3() {
		_row3.add(new JLabel(PASSENGERS_LB));
		_row3.add(_passengersTx);
		_row3.add(new JLabel(RENT_PRICE_LB));
		_row3.add(_rentPriceTx);
		_row3.add(new JLabel(FUEL_LB));
		_row3.add(_fuels);
		_row3.add(new JLabel(TRANSMISSION_LB));
		_row3.add(_transmissions);
		_row3.add(new JLabel(STATUS_LB));
		_row3.add(_states);
		this.add(_row3);
	}

	/**
	 * Add the elements of fourth row to the BoxLayout
	 */
	private void addRow4() {
		_row4.add(_create);
		this.add(_row4);
	}

	/**
	 * Wipes the text fields
	 */
	public void flush() {
		_plateTx.setText("");
		_capacityTx.setText("");
		_brandTx.setText("");
		_styleTx.setText("");
		_modelTx.setText("");
		_colorTx.setText("");
		_ccTx.setText("");
		_yearTx.setText("");
		_extrasTx.setText("");
		_passengersTx.setText("");
		_rentPriceTx.setText("");
	}

	/**
	 * Enable all the text fields and combo boxes so they can be edited
	 */
	public void enableElements() {
		_plateTx.setEditable(true);
		_capacityTx.setEditable(true);
		_brandTx.setEditable(true);
		_styleTx.setEditable(true);
		_modelTx.setEditable(true);
		_colorTx.setEditable(true);
		_ccTx.setEditable(true);
		_yearTx.setEditable(true);
		_extrasTx.setEditable(true);
		_passengersTx.setEditable(true);
		_rentPriceTx.setEditable(true);
		_states.setEnabled(true);
		_fuels.setEnabled(true);
		_transmissions.setEnabled(true);
	}

	/**
	 * Disable all the text fields and combo boxes so they can't be edited
	 */
	public void disableElements() {
		_plateTx.setEditable(false);
		_capacityTx.setEditable(false);
		_brandTx.setEditable(false);
		_styleTx.setEditable(false);
		_modelTx.setEditable(false);
		_colorTx.setEditable(false);
		_ccTx.setEditable(false);
		_yearTx.setEditable(false);
		_extrasTx.setEditable(false);
		_passengersTx.setEditable(false);
		_rentPriceTx.setEditable(false);
		_states.setEnabled(false);
		_fuels.setEnabled(false);
		_transmissions.setEnabled(false);
	}

	/**
	 * Display the info of a vehicle in the text fields and combo boxes
	 * 
	 * @param pFields Info of the vehicle
	 */
	public void displayVehicle(ArrayList<String> pFields) {
		_create.setVisible(false);
		_plateTx.setText(pFields.get(IDX_PLATE));
		_capacityTx.setText(pFields.get(IDX_CAPACITY));
		_brandTx.setText(pFields.get(IDX_BRAND));
		_styleTx.setText(pFields.get(IDX_STYLE));
		_modelTx.setText(pFields.get(IDX_MODEL));
		_colorTx.setText(pFields.get(IDX_COLOR));
		_ccTx.setText(pFields.get(IDX_CC));
		_fuels.setSelectedItem(pFields.get(IDX_FUEL));
		_transmissions.setSelectedItem(pFields.get(IDX_TRANSMISSION));
		_yearTx.setText(pFields.get(IDX_YEAR));
		_extrasTx.setText(pFields.get(IDX_EXTRAS));
		_passengersTx.setText(pFields.get(IDX_PASSENGERS));
		_rentPriceTx.setText(pFields.get(IDX_RENT_PRICE));
		_states.setSelectedItem(pFields.get(IDX_STATE));
	}

	/**
	 * Set the visibility of the "Create" button to true
	 */
	public void showCreateButton() {
		_create.setVisible(true);
	}

	/**
	 * Extract the text in all text fields to update a vehicle in the DB
	 * 
	 * @return Result of the update operation
	 */
	public int updateVehicle() {
		ArrayList<String> values = extractValues();
		int result = _vehicles.update(values);
		return result;
	}

	/**
	 * Extract all the values in the text fields and combo boxes
	 * 
	 * @return ArrayList<String> with all field values
	 */
	private ArrayList<String> extractValues() {
		ArrayList<String> values = new ArrayList<String>();

		values.add(_plateTx.getText().trim());
		values.add(_capacityTx.getText().trim());
		values.add(_brandTx.getText().trim());
		values.add(_styleTx.getText().trim());
		values.add(_modelTx.getText().trim());
		values.add(_colorTx.getText().trim());
		values.add(_ccTx.getText().trim());
		values.add(_fuels.getSelectedItem().toString());
		values.add(_transmissions.getSelectedItem().toString());
		values.add(_yearTx.getText().trim());
		values.add(_extrasTx.getText().trim());
		values.add(_passengersTx.getText().trim());
		values.add(_rentPriceTx.getText().trim());
		values.add(_states.getSelectedItem().toString());

		return values;
	}

	@Override
	public void actionPerformed(ActionEvent pEvent) {
		if (pEvent.getSource() == _create) {
			ArrayList<String> values = this.extractValues();
			int result = _vehicles.insert(values);
			if (result != 0) // REPLACE ME!!
				System.out.println("Vehículo ya esta registrado");
		}

	}

}
