package GUI;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainRentPanel extends JPanel implements CONTANTSGUI {
	
	private JLabel _title;
	
	public MainRentPanel() {
		this.setLayout(new BorderLayout());
		_title = new JLabel(RENTS_LB);
		this.add(_title, BorderLayout.CENTER);
	}

}
