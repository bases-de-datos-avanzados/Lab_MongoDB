package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DB.Rent;
import DB.Users;
import DB.Vehicles;

@SuppressWarnings("serial")
public class MainRentPanel extends JPanel implements ActionListener, CONTANTSGUI {

	private JPanel _row1, _row2, _row3, _row4, _row5, _row6;

	private JTextField _idTx, _rentalDaysTx;
	private JLabel _clientNameLB, _totalCalculatedLB;
	private JButton _consultBT, _infoVhBT, _checkUserBT, _rentBT;
	private JComboBox<String> _plates;
	private DefaultComboBoxModel<String> _modelComboBox;

	private Vehicles _vehiclesDB;
	private Users _usersDB;
	private Rent _rentDB;

	private ArrayList<String> _currentVehicle;
	private ArrayList<ArrayList<String>> _availableVehicles;
	private ArrayList<String> _currentClient;

	/**
	 * Class constructor
	 */
	public MainRentPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initElements();
		addElements();
	}

	/**
	 * Initialize all the objects
	 */
	private void initElements() {
		_vehiclesDB = new Vehicles();
		_usersDB = new Users();
		_rentDB = new Rent();
		_currentVehicle = new ArrayList<String>();
		_availableVehicles = new ArrayList<ArrayList<String>>();
		_currentClient = new ArrayList<String>();
		_row1 = new JPanel();
		_row2 = new JPanel();
		_row2.setVisible(false);
		_row3 = new JPanel();
		_row3.setVisible(false);
		_row4 = new JPanel();
		_row4.setVisible(false);
		_row5 = new JPanel();
		_row5.setVisible(false);
		_row6 = new JPanel();
		_row6.setVisible(false);

		_idTx = new JTextField(9);
		_consultBT = new JButton("Consultar vehículos disponibles");
		_infoVhBT = new JButton("Detalles");
		_checkUserBT = new JButton("Verificar");
		_clientNameLB = new JLabel("");
		_rentBT = new JButton("Rentar");
		_modelComboBox = new DefaultComboBoxModel<String>();
		_plates = new JComboBox<String>(_modelComboBox);
		_rentalDaysTx = new JTextField(3);
		_totalCalculatedLB = new JLabel("     ");

		_consultBT.addActionListener(this);
		_infoVhBT.addActionListener(this);
		_checkUserBT.addActionListener(this);
		_rentBT.addActionListener(this);
		_rentalDaysTx.addActionListener(this);
	}

	/**
	 * Add all the elements to the corresponding JPanel
	 */
	private void addElements() {
		_row1.add(_consultBT);
		this.add(_row1);

		_row2.add(new JLabel("Placa:"));
		_row2.add(_plates);
		_row2.add(_infoVhBT);
		this.add(_row2);

		_row3.add(new JLabel("Cédula"));
		_row3.add(_idTx);
		_row3.add(_checkUserBT);
		this.add(_row3);

		_row4.add(new JLabel("Cliente:"));
		_row4.add(_clientNameLB);
		this.add(_row4);

		_row5.add(new JLabel("Días de renta:"));
		_row5.add(_rentalDaysTx);
		_row5.add(new JLabel("Total:"));
		_row5.add(_totalCalculatedLB);
		this.add(_row5);

		_row6.add(_rentBT);
		this.add(_row6);
	}

	/**
	 * Fill the combo box with the plate number of all available vehicles
	 */
	private void fillPlatesComboBox() {
		if (_availableVehicles.isEmpty())
			return;
		_modelComboBox.removeAllElements();
		for (ArrayList<String> vehicle : _availableVehicles)
			_modelComboBox.addElement(vehicle.get(IDX_PLATE));
	}

	/**
	 * Update the current selection of vehicle
	 */
	private void updateCurrentVehicle() {
		for (ArrayList<String> vehicle : _availableVehicles)
			if (vehicle.get(IDX_PLATE) == _plates.getSelectedItem().toString())
				_currentVehicle = vehicle;
	}

	/**
	 * Calculate and set the total amount to pay for rent in the corresponding
	 * jlabel
	 */
	private void setTotalAmount() {
		int days = Integer.parseInt(_rentalDaysTx.getText().trim());
		_totalCalculatedLB.setText(Integer.toString((Integer.parseInt(_currentVehicle.get(IDX_RENT_PRICE)) * days)));
	}

	@Override
	public void actionPerformed(ActionEvent pEvent) {
		if (pEvent.getSource() == _consultBT) {
			_availableVehicles = _vehiclesDB.filterVehicles(VEHICLE_FIELDS[IDX_STATE], STATES[0]);
			fillPlatesComboBox();
			if (!_availableVehicles.isEmpty()) {
				_row2.setVisible(true);
				_row3.setVisible(true);
			}
		}

		if (pEvent.getSource() == _plates) {
			for (ArrayList<String> vehicle : _availableVehicles)
				if (vehicle.get(IDX_PLATE) == _plates.getSelectedItem().toString())
					_currentVehicle = vehicle;
		}

		if (pEvent.getSource() == _checkUserBT) {
			if (!_idTx.getText().trim().equals("")) {
				_currentClient = _usersDB.findUser(_idTx.getText().trim());
				if (_currentClient != null) {
					_clientNameLB.setText(_currentClient.get(IDX_NAME) + " " + _currentClient.get(IDX_LASTNAME));
					_row4.setVisible(true);
					_row5.setVisible(true);
				}
			}
		}

		if (pEvent.getSource() == _rentalDaysTx) {
			updateCurrentVehicle();
			if (!_rentalDaysTx.getText().trim().equals("")) {
				setTotalAmount();
				_row6.setVisible(true);
			}
		}

		if (pEvent.getSource() == _rentBT) {
			updateCurrentVehicle();
			setTotalAmount();
			int result = _rentDB.insert(_plates.getSelectedItem().toString(), _idTx.getText().trim(),
					Integer.parseInt(_rentalDaysTx.getText().trim()), Integer.parseInt(_totalCalculatedLB.getText()));
			if (result == 0) {
				_vehiclesDB.updateOneField(_currentVehicle.get(IDX_PLATE), VEHICLE_FIELDS[IDX_STATE], STATES[1]);
			} else // REPLACE ME!!
				System.out.println("Error al ingresar la renta del vehículo");

		}

	}

}
