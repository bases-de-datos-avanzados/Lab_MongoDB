package GUI;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class RentACar extends JFrame implements CONTANTSGUI{
	
	private JPanel _mainPanel;
	private JTabbedPane _tabbedPane;
	private JPanel _welcomePane;
	private MainUserPanel _mainUserPanel;
	private MainVehiclesPanel _mainVehiclesPanel;
	private MainRentPanel _mainRentPanel;
	

	/**
	 * Class constructor
	 */
	public RentACar() {
		this.setSize(WD_WIDTH, WD_HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new GridLayout(1,1));
		_tabbedPane = new JTabbedPane();
		_mainUserPanel = new MainUserPanel();
		_mainVehiclesPanel = new MainVehiclesPanel();
		_mainRentPanel = new MainRentPanel();
		this.setElements();
		_mainPanel.add(_tabbedPane);
		this.setContentPane(_mainPanel);
		this.setVisible(true);
	}
	
	
	public void setElements() {
		_welcomePane = new JPanel();
		JLabel filler = new JLabel("Bienvenido");
        filler.setHorizontalAlignment(JLabel.CENTER);
        _welcomePane.setLayout(new GridLayout(1, 1));
        _welcomePane.add(filler);
        
        _tabbedPane.addTab(START_LB, _welcomePane);
        _tabbedPane.addTab(USERS_LB, _mainUserPanel);
        _tabbedPane.addTab(VEHICLES_LB, _mainVehiclesPanel);
        _tabbedPane.addTab(RENTS_LB, _mainRentPanel);
	}
	



}
