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

import DB.Users;

@SuppressWarnings("serial")
public class MainUserPanel extends JPanel implements ActionListener, CONTANTSGUI {

	private JButton _newUser, _search, _edit, _update;
	private JTextField _searchField;

	private JPanel _topPanel;
	private JPanel _bottomPanel;

	private UserPanel _userPanel;

	private GridBagLayout _gbLayout;
	private GridBagConstraints _gbc;

	private Users _users;

	/**
	 * Class constructor
	 */
	public MainUserPanel() {
		_gbLayout = new GridBagLayout();
		_gbc = new GridBagConstraints();
		this.setLayout(new BorderLayout());
		_users = new Users();
		initElements();
		addTopElements();
		bottomElements();
		this.add(_topPanel, BorderLayout.NORTH);
		this.add(_bottomPanel, BorderLayout.CENTER);
	}

	/**
	 * Initialize all the objects used
	 */
	private void initElements() {
		_topPanel = new JPanel();
		_bottomPanel = new JPanel();
		_topPanel.setLayout(_gbLayout);
		_newUser = new JButton("Nuevo");
		_search = new JButton("Buscar");
		_edit = new JButton("Editar");
		_update = new JButton("Actualizar");
		_searchField = new JTextField();
		_searchField.setColumns(10);
		_searchField.setToolTipText(ID_LB);

		_newUser.addActionListener(this);
		_search.addActionListener(this);
		_edit.addActionListener(this);
		_edit.setVisible(false);
		_update.addActionListener(this);
		_update.setVisible(false);
	}

	/**
	 * Add the elements of the top panel
	 * 
	 */
	private void addTopElements() {
		_gbc.fill = GridBagConstraints.HORIZONTAL;
		_gbc.insets = new Insets(10, 10, 10, 10);

		_gbc.gridx = 0;
		_gbc.gridy = 0;
		_topPanel.add(_newUser, _gbc);

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
		_userPanel = new UserPanel(_users);
		_userPanel.setVisible(false);
		_bottomPanel.add(_userPanel);
	}

	@Override
	public void actionPerformed(ActionEvent pEvent) {

		if (pEvent.getSource() == _newUser) {
			_userPanel.flush();
			_userPanel.enableFields();
			_userPanel.showCreateButton();
			_userPanel.setVisible(true);
			_edit.setVisible(false);
			_update.setVisible(false);
		}

		if (pEvent.getSource() == _search) {
			_userPanel.setVisible(false);
			_edit.setVisible(false);
			_update.setVisible(false);
			if (!_searchField.getText().equals("")) {
				ArrayList<String> user = _users.findUser(_searchField.getText().trim());
				if (user != null) {
					_userPanel.flush();
					_userPanel.displayUser(user);
					_userPanel.disableFields();
					_userPanel.setVisible(true);
					_edit.setVisible(true);
					_update.setVisible(true);
				}
			}
		}

		if (pEvent.getSource() == _edit) {
			_userPanel.enableFields();
		}

		if (pEvent.getSource() == _update) {
			int result = _userPanel.updateUser();
			if (result != 0) // REPLACE ME!!!!
				System.out.println("Error actualizando usuario");
		}

	}

}
