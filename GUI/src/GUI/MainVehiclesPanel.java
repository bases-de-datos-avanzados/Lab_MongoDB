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

	private JButton _newCarBT, _searchBT, _editBT, _updateBT, _advanceSearhBT;
	private JTextField _searchField;

	private JPanel _topPanel;
	private JPanel _bottomPanel;

	private GridBagLayout _gbLayout;
	private GridBagConstraints _gbc;

	private VehiclePanel _vehiclePanel;
	private AdvanceSearchPanel _advancePanel;
	private Vehicles _vehicles;

	/**
	 * Class constructor
	 */
	public MainVehiclesPanel() {
		this.setLayout(new BorderLayout());
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
		_vehicles = new Vehicles();
		_gbLayout = new GridBagLayout();
		_gbc = new GridBagConstraints();
		_topPanel = new JPanel();
		_bottomPanel = new JPanel(new BorderLayout());
		_topPanel.setLayout(_gbLayout);
		_newCarBT = new JButton("Nuevo");
		_searchBT = new JButton("Buscar");
		_editBT = new JButton("Editar");
		_updateBT = new JButton("Actualizar");
		_searchField = new JTextField();
		_searchField.setColumns(15);
		_searchField.setToolTipText("Placa");
		_advanceSearhBT = new JButton("Busqueda avanzada");

		_newCarBT.addActionListener(this);
		_searchBT.addActionListener(this);
		_advanceSearhBT.addActionListener(this);
		_editBT.addActionListener(this);
		_editBT.setVisible(false);
		_updateBT.addActionListener(this);
		_updateBT.setVisible(false);
	}

	/**
	 * Add the elements of the top panel
	 */
	private void addTopElements() {
		_gbc.fill = GridBagConstraints.HORIZONTAL;
		_gbc.insets = new Insets(10, 10, 10, 10);

		_gbc.gridx = 0;
		_gbc.gridy = 0;
		_topPanel.add(_newCarBT, _gbc);

		_gbc.gridx = 1;
		// _gbc.gridy = 0;
		_topPanel.add(_searchBT, _gbc);

		// _gbc.insets = new Insets(10,10,10,400);
		_gbc.gridx = 2;
		// _gbc.gridy = 0;
		_topPanel.add(_searchField, _gbc);

		_gbc.gridx = 3;
		_topPanel.add(_advanceSearhBT, _gbc);

		_gbc.gridx = 4;
		// _gbc.gridy = 0;
		_topPanel.add(_editBT, _gbc);

		_gbc.gridx = 5;
		// _gbc.gridy = 0;
		_topPanel.add(_updateBT, _gbc);
	}

	/**
	 * Add the elements of the bottom panel
	 */
	private void bottomElements() {
		_vehiclePanel = new VehiclePanel(_vehicles);
		_vehiclePanel.setVisible(false);
		_bottomPanel.add(_vehiclePanel, BorderLayout.NORTH);

		_advancePanel = new AdvanceSearchPanel(_vehicles);
		_advancePanel.setVisible(false);
		_bottomPanel.add(_advancePanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent pEvent) {
		if (pEvent.getSource() == _newCarBT) {
			_vehiclePanel.flush();
			_vehiclePanel.enableElements();
			_vehiclePanel.showCreateButton();
			_advancePanel.setVisible(false);
			_vehiclePanel.setVisible(true);
			_editBT.setVisible(false);
			_updateBT.setVisible(false);
		}

		if (pEvent.getSource() == _searchBT) {
			_vehiclePanel.setVisible(false);
			_editBT.setVisible(false);
			_updateBT.setVisible(false);
			_advancePanel.setVisible(false);
			if (!_searchField.getText().equals("")) {
				ArrayList<String> vehicle = _vehicles.findVehicle(_searchField.getText().trim());
				if (vehicle != null) {
					_vehiclePanel.flush();
					_vehiclePanel.displayVehicle(vehicle);
					_vehiclePanel.disableElements();
					_vehiclePanel.setVisible(true);
					_editBT.setVisible(true);
					_updateBT.setVisible(true);
				}
			}
		}

		if (pEvent.getSource() == _editBT) {
			_vehiclePanel.enableElements();
		}

		if (pEvent.getSource() == _updateBT) {
			int result = _vehiclePanel.updateVehicle();
			if (result != 0) // REPLACE ME!!
				System.out.println("Error al actualizar el vehiculo");
		}

		if (pEvent.getSource() == _advanceSearhBT) {
			_vehiclePanel.setVisible(false);
			_editBT.setVisible(false);
			_updateBT.setVisible(false);
			_advancePanel.setVisible(true);
		}

	}

}
