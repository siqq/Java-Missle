package war.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class WarGui extends JFrame {
	public WarGui() {
		setResizable(false);
		setTitle("War");
		getContentPane().setLayout(null);
		
		ControlPannel controlPanel = new ControlPannel();
		StatisticsPanel statisticsPanel = new StatisticsPanel();
		GamePanel gamePanel = new GamePanel();
		
		
		getContentPane().add(controlPanel);
		getContentPane().add(statisticsPanel);		
		getContentPane().add(gamePanel);
		
		
	}
}
