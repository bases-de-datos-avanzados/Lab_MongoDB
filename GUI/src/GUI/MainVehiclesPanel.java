package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DB.Vehicles;

@SuppressWarnings("serial")
public class MainVehiclesPanel extends JPanel implements ActionListener, CONTANTSGUI {

	private JButton _newCar, _search, _edit, _update;
	private JTextField _searchField;

	private JPanel _topPanel;
	private JPanel _bottomPanel;

	private GridBagLayout _gbLayout;
	private GridBagConstraints _gbc;

	private VehiclePanel _vehiclePanel;
	private Vehicles _vehicles;

	/**
	 * Class constructor
	 */
	public MainVehiclesPanel() {
		_gbLayout = new GridBagLayout();
		_gbc = new GridBagConstraints();
		this.setLayout(new BorderLayout());
		_vehicles = new Vehicles();
		initElements();
		addTopElements();
		bottomElements();
		this.add(_topPanel, BorderLayout.NORTH);
		this.add(_bottomPanel, BorderLayout.CENTER);
	}

	/**
	 * Initialize all the objects
	 */
	private void initElements() {
		_topPanel = new JPanel();
		_bottomPanel = new JPanel();
		_topPanel.setLayout(_gbLayout);
		_newCar = new JButton("Nuevo");
		_search = new JButton("Buscar");
		_edit = new JButton("Editar");
		_update = new JButton("Actualizar");
		_searchField = new JTextField();
		_searchField.setColumns(15);
		_searchField.setToolTipText("Placa");

		_newCar.addActionListener(this);
		_search.addActionListener(this);
		_edit.addActionListener(this);
		_edit.setVisible(false);
		_update.addActionListener(this);
		_update.setVisible(false);
	}

	/**
	 * Add the elements of the top panel
	 */
	private void addTopElements() {
		_gbc.fill = GridBagConstraints.HORIZONTAL;
		_gbc.insets = new Insets(10, 10, 10, 10);

		_gbc.gridx = 0;
		_gbc.gridy = 0;
		_topPanel.add(_newCar, _gbc);

		_gbc.gridx = 1;
		_gbc.gridy = 0;
		_topPanel.add(_search, _gbc);

		// _gbc.insets = new Insets(10,10,10,400);
		_gbc.gridx = 2;
		_gbc.gridy = 0;
		_topPanel.add(_searchField, _gbc);

		_gbc.gridx = 3;
		_gbc.gridy = 0;
		_topPanel.add(_edit, _gbc);

		_gbc.gridx = 4;
		_gbc.gridy = 0;
		_topPanel.add(_update, _gbc);
	}

	/**
	 * Add the elements of the bottom panel
	 */
	private void bottomElements() {
		_vehiclePanel = new VehiclePanel(_vehicles);
		_vehiclePanel.setVisible(false);
		_bottomPanel.add(_vehiclePanel);
	}

	@Override
	public void actionPerformed(ActionEvent pEvent) {
		if (pEvent.getSource() == _newCar) {
			_vehiclePanel.flush();
			_vehiclePanel.enableElements();
			_vehiclePanel.showCreateButton();
			_vehiclePanel.setVisible(true);
			_edit.setVisible(false);
			_update.setVisible(false);
		}

		if (pEvent.getSource() == _search) {
			_vehiclePanel.setVisible(false);
			_edit.setVisible(false);
			_update.setVisible(false);
			if (!_searchField.getText().equals("")) {
				ArrayList<String> vehicle = _vehicles.findVehicle(_searchField.getText().trim());
				if (vehicle != null) {
					_vehiclePanel.flush();
					_vehiclePanel.displayVehicle(vehicle);
					_vehiclePanel.disableElements();
					_vehiclePanel.setVisible(true);
					_edit.setVisible(true);
					_update.setVisible(true);
				}
			}
		}

		if (pEvent.getSource() == _edit) {
			_vehiclePanel.enableElements();
		}

		if (pEvent.getSource() == _update) {
			int result = _vehiclePanel.updateVehicle();
			if (result != 0) // REPLACE ME!!
				System.out.println("Error al actualizar el vehiculo");
		}

	}

}
