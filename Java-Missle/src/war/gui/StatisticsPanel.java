package war.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class StatisticsPanel extends JPanel {
	public StatisticsPanel() {
		this.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		this.setBounds(0, 385, 1019, 73);
		setLayout(null);
		
		JButton btnNewButton = new JButton("STATISTICS");
		btnNewButton.setBounds(78, 11, 653, 37);
		add(btnNewButton);
	}
}
