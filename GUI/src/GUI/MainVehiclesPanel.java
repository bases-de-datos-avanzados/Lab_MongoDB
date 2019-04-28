package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainVehiclesPanel extends JPanel implements ActionListener, CONTANTSGUI{
	
	private JButton _newCar, _search, _edit, _update;
	private JTextField _searchField;
	
	private JPanel _topPanel;
	private JPanel _bottomPanel;
	
	private GridBagLayout _gbLayout;
	private GridBagConstraints _gbc;
	
	public MainVehiclesPanel() {
		_gbLayout = new GridBagLayout();
		_gbc = new GridBagConstraints();
		this.setLayout(new BorderLayout());
		initElements();
		addTopElements();
		this.add(_topPanel, BorderLayout.NORTH);
		this.add(_bottomPanel, BorderLayout.CENTER);
	}
	
	
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
	
	
	private void addTopElements() {
		_gbc.fill = GridBagConstraints.HORIZONTAL;
		_gbc.insets = new Insets(10,10,10,10);
		
		_gbc.gridx = 0; _gbc.gridy = 0;
		_topPanel.add(_newCar, _gbc);
		
		_gbc.gridx = 1; _gbc.gridy = 0;
		_topPanel.add(_search, _gbc);
		
		//_gbc.insets = new Insets(10,10,10,400);
		_gbc.gridx = 2; _gbc.gridy = 0;
		_topPanel.add(_searchField, _gbc);
		
		_gbc.gridx = 3; _gbc.gridy = 0;
		_topPanel.add(_edit, _gbc);
		
		_gbc.gridx = 4; _gbc.gridy = 0;
		_topPanel.add(_update, _gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
