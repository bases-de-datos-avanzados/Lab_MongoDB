package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class VehicleInfoDisplay extends JFrame implements CONTANTSGUI {

	private JScrollPane _scrollPane;
	private JPanel _mainPanel, _topPanel, _contentPanel;

	private int _mode;
	private boolean _showTopPanel;
	private ArrayList<ArrayList<String>> _data;
	private ArrayList<String> _topData;

	public VehicleInfoDisplay(int pMode, boolean pShowTopPanel, ArrayList<ArrayList<String>> pData,
			ArrayList<String> pTopData) {
		this.setSize(WD_VH_INFO_WIDTH, WD_VH_INFO_HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		_mode = pMode;
		_showTopPanel = pShowTopPanel;
		_data = pData;
		_topData = pTopData;
		initElements();
		decideMode();
		this.setVisible(true);

	}

	private void initElements() {
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BorderLayout());
		this.setContentPane(_mainPanel);
		_topPanel = new JPanel();
		_contentPanel = new JPanel();
		_contentPanel.setLayout(new BoxLayout(_contentPanel, BoxLayout.Y_AXIS));
		_scrollPane = new JScrollPane(_contentPanel);
		_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	/**
	 * Decides which panels and elements show according to the mode
	 */
	private void decideMode() {
		if (_mode == 1) { // Show all vehicle info
			this.setTitle("Vehículos");
			if (_showTopPanel)
				addTopPanel();
			modeAllInfo();
		} else if (_mode == 2) { // Show half info
			if (_showTopPanel)
				addTopPanel();
			modelHalfInfo();
		}
	}

	/**
	 * Add the labels to the top panel to display the information
	 */
	private void addTopPanel() {
		_topPanel.add(new JLabel("# de vehículos:"));
		_topPanel.add(new JLabel(_topData.get(0)));
		_topPanel.add(new JLabel("Menor precio/día:"));
		_topPanel.add(new JLabel(_topData.get(1)));
		_topPanel.add(new JLabel("Mayor precio/día:"));
		_topPanel.add(new JLabel(_topData.get(2)));
		_topPanel.add(new JLabel("Precio promedio:"));
		_topPanel.add(new JLabel(_topData.get(3)));

		_mainPanel.add(_topPanel, BorderLayout.NORTH);
	}

	/**
	 * Creates a JPanel that serves as a spacer between elements
	 * 
	 * @return JPanel
	 */
	private JPanel createSpace() {
		JPanel spacePanel = new JPanel();
		spacePanel.setPreferredSize(new Dimension(WD_VH_INFO_WIDTH, 2));
		spacePanel.setMaximumSize(new Dimension(WD_VH_INFO_WIDTH, 2));
		spacePanel.setBackground(Color.BLACK);
		JLabel space = new JLabel("    ");
		spacePanel.add(space);
		return spacePanel;
	}

	/**
	 * Add label to different panels to create a "list" with the information of all
	 * the vehicles, inside the scroll panel
	 */
	private void modeAllInfo() {
		_contentPanel.add(createSpace());
		for (ArrayList<String> data : _data) {
			JPanel row1 = new JPanel();
			row1.add(new JLabel("Placa: " + data.get(IDX_PLATE)));
			row1.add(new JLabel("Capacidad: " + data.get(IDX_CAPACITY)));
			row1.add(new JLabel("Marca: " + data.get(IDX_BRAND)));
			row1.add(new JLabel("Estilo: " + data.get(IDX_STYLE)));
			row1.add(new JLabel("Modelo: " + data.get(IDX_MODEL)));
			_contentPanel.add(row1);

			JPanel row2 = new JPanel();
			row2.add(new JLabel("Color: " + data.get(IDX_COLOR)));
			row2.add(new JLabel("Cilindrada: " + data.get(IDX_CC)));
			row2.add(new JLabel("Año: " + data.get(IDX_YEAR)));
			row2.add(new JLabel("Extras: " + data.get(IDX_EXTRAS)));
			_contentPanel.add(row2);

			JPanel row3 = new JPanel();
			row3.add(new JLabel("Pasajeros: " + data.get(IDX_PASSENGERS)));
			row3.add(new JLabel("Precio/día: " + data.get(IDX_RENT_PRICE)));
			row3.add(new JLabel("Combustible: " + data.get(IDX_FUEL)));
			row3.add(new JLabel("Transmisión: " + data.get(IDX_TRANSMISSION)));
			row3.add(new JLabel("Estado: " + data.get(IDX_STATE)));
			_contentPanel.add(row3);

			_contentPanel.add(createSpace());
		}
		_mainPanel.add(_scrollPane, BorderLayout.CENTER);
	}

	private void modelHalfInfo() {
		_contentPanel.add(createSpace());
		for (ArrayList<String> data : _data) {
			JPanel row = new JPanel();
			row.add(new JLabel("Marca: " + data.get(IDX_BRAND)));
			row.add(new JLabel("Modelo: " + data.get(IDX_MODEL)));
			row.add(new JLabel("Cilindrada: " + data.get(IDX_CC)));
			row.add(new JLabel("Año: " + data.get(IDX_YEAR)));
			row.add(new JLabel("Precio/día: " + data.get(IDX_RENT_PRICE)));
			_contentPanel.add(row);

			_contentPanel.add(createSpace());
		}
		_mainPanel.add(_scrollPane, BorderLayout.CENTER);
	}

}
