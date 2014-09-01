package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ProgressPanel extends JPanel {
	public ProgressPanel() {
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBounds(360, 0, 480, 460);
	}
}
