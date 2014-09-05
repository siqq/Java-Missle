package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JTextField;

public class OrefPanel extends JPanel {
	public static final String OREF_IMAGE_PATH = "/drawable/orefImage225x70.png";
	private JTextField topAlert, middleAlert, buttomAlert;
	private JLabel orefImageLable;

	public OrefPanel() {
		setBackground(Color.DARK_GRAY);
		this.setBorder(null);
		setLayout(new GridLayout(4, 1));

		orefImageLable = new JLabel();
		orefImageLable.setIcon(new ImageIcon(OrefPanel.class
				.getResource(OREF_IMAGE_PATH)));
		add(orefImageLable);

		topAlert = new JTextField(" ");
		topAlert.setBackground(new Color(255, 127, 80));
		add(topAlert);
		topAlert.setColumns(10);

		middleAlert = new JTextField(" ");
		middleAlert.setBackground(new Color(255, 127, 80));
		add(middleAlert);
		middleAlert.setColumns(10);

		buttomAlert = new JTextField(" ");
		buttomAlert.setBackground(new Color(255, 127, 80));
		add(buttomAlert);
		buttomAlert.setColumns(10);

	}

	public void addMissileToOrefPanel(String destination) {
		if (topAlert.getText() == " ") {
			topAlert.setText(destination);
			validate();
		}
	}
}








