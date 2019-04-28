package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DB.Users;

@SuppressWarnings("serial")
public class UserPanel extends JPanel implements ActionListener, CONTANTSGUI {

	private GridBagLayout _gbLayout;
	private GridBagConstraints _gbc;

	private JButton _create;
	private JLabel _id, _name, _lastName, _email, _phones;
	private JTextField _idTx, _nameTx, _lastNameTx, _emailTx, _phonesTx;

	private Users _users;

	/**
	 * Class constructor
	 * 
	 * @param pUsers Class that manage connection to the DB
	 */
	public UserPanel(Users pUsers) {
		_users = pUsers;
		_gbLayout = new GridBagLayout();
		_gbc = new GridBagConstraints();
		this.setLayout(_gbLayout);
		initElements();
		addElements();
	}

	/**
	 * Wipes the text fields
	 */
	public void flush() {
		_idTx.setText("");
		_nameTx.setText("");
		_lastNameTx.setText("");
		_emailTx.setText("");
		_phonesTx.setText("");
	}

	/**
	 * Initialize all the objects used
	 */
	private void initElements() {
		_create = new JButton("Crear");
		_create.addActionListener(this);
		_id = new JLabel("Cédula:");
		_name = new JLabel("Nombre:");
		_lastName = new JLabel("Apellido:");
		_email = new JLabel("Email:");
		_phones = new JLabel("Teléfono(s):");
		_idTx = new JTextField(9);
		_nameTx = new JTextField(20);
		_lastNameTx = new JTextField(20);
		_emailTx = new JTextField(20);
		_phonesTx = new JTextField(8);
	}

	/**
	 * Adds all the labels and text fields to the panel according to the position
	 * constraints.
	 */
	private void addElements() {
		_gbc.fill = GridBagConstraints.HORIZONTAL;
		_gbc.insets = new Insets(10, 2, 10, 2);

		_gbc.gridx = 0;
		_gbc.gridy = 0;
		this.add(_id, _gbc);
		_gbc.gridx = 1;
		_gbc.gridy = 0;
		this.add(_idTx, _gbc);

		_gbc.insets = new Insets(10, 30, 10, 2);
		_gbc.gridx = 2;
		_gbc.gridy = 0;
		this.add(_name, _gbc);
		_gbc.insets = new Insets(10, 2, 10, 2);
		_gbc.gridx = 3;
		_gbc.gridy = 0;
		this.add(_nameTx, _gbc);

		_gbc.insets = new Insets(10, 30, 10, 2);
		_gbc.gridx = 4;
		_gbc.gridy = 0;
		this.add(_lastName, _gbc);
		_gbc.insets = new Insets(10, 2, 10, 2);
		_gbc.gridx = 5;
		_gbc.gridy = 0;
		this.add(_lastNameTx, _gbc);

		// _gbc.insets = new Insets(2, 30, 2, 2);
		_gbc.gridx = 0;
		_gbc.gridy = 1;
		this.add(_phones, _gbc);
		_gbc.insets = new Insets(2, 2, 2, 2);
		_gbc.gridx = 1;
		_gbc.gridy = 1;
		this.add(_phonesTx, _gbc);

		_gbc.insets = new Insets(2, 30, 2, 2);
		_gbc.gridx = 2;
		_gbc.gridy = 1;
		this.add(_email, _gbc);
		_gbc.insets = new Insets(2, 2, 2, 2);
		_gbc.gridx = 3;
		_gbc.gridy = 1;
		this.add(_emailTx, _gbc);

		_gbc.insets = new Insets(2, 30, 2, 2);
		_gbc.gridx = 3;
		_gbc.gridy = 2;
		this.add(_create, _gbc);
	}

	/**
	 * Display the information of a user in the text fields
	 * 
	 * @param pFields Info of the user
	 */
	public void displayUser(ArrayList<String> pFields) {
		_create.setVisible(false);
		_idTx.setText(pFields.get(IDX_ID));
		_nameTx.setText(pFields.get(IDX_NAME));
		_lastNameTx.setText(pFields.get(IDX_LASTNAME));
		_phonesTx.setText(pFields.get(IDX_PHONE));
		_emailTx.setText(pFields.get(IDX_EMAIL));
	}

	/**
	 * Disable all the text fields so they can't be edited
	 */
	public void disableFields() {
		_idTx.setEditable(false);
		_nameTx.setEditable(false);
		_lastNameTx.setEditable(false);
		_phonesTx.setEditable(false);
		_emailTx.setEditable(false);
	}

	/**
	 * Enable all the text fields so they can be edited
	 */
	public void enableFields() {
		_idTx.setEditable(true);
		_nameTx.setEditable(true);
		_lastNameTx.setEditable(true);
		_phonesTx.setEditable(true);
		_emailTx.setEditable(true);
	}

	/**
	 * Set the visibility of the "Create" button to true
	 */
	public void showCreateButton() {
		_create.setVisible(true);
	}

	/**
	 * Extract the text in all text fields to update a user in the DB
	 * 
	 * @return Result of the update operation
	 */
	public int updateUser() {
		int result = _users.update(_idTx.getText().trim(), _nameTx.getText().trim(), _lastNameTx.getText().trim(),
				_emailTx.getText().trim(), _phonesTx.getText().trim());
		if (result != 0) // REPLACE ME!!
			System.out.println("Usuario ya esta registrado");

		return result;
	}

	@Override
	public void actionPerformed(ActionEvent pEvent) {
		if (pEvent.getSource() == _create) {
			int result = _users.insert(_idTx.getText().trim(), _nameTx.getText().trim(), _lastNameTx.getText().trim(),
					_emailTx.getText().trim(), _phonesTx.getText().trim());
			if (result != 0) // REPLACE ME!!
				System.out.println("Usuario ya esta registrado");
		}

	}

}
