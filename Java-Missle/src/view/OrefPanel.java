package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JTextField;

import war.controller.WarUIEventsListener;

public class OrefPanel extends JPanel {
	public static final String OREF_IMAGE_PATH = "/drawable/orefImage225x70.png";
	public static final int ALERT_DISPLAY_TIME = 8;
	private JTextField topAlert, middleAlert, buttomAlert;
	private JLabel orefImageLable;
	private Font font = new Font("SansSerif", Font.BOLD, 20);

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

		topAlert.setFont(font);
		middleAlert.setFont(font);
		buttomAlert.setFont(font);

		topAlert.setForeground(Color.WHITE);
		;

		topAlert.setEditable(false);
		middleAlert.setEditable(false);
		buttomAlert.setEditable(false);
	}

	public void addMissileToOrefPanel(String destination, int time) {
		if (time < ALERT_DISPLAY_TIME && time % 2 == 0) {
			topAlert.setText("     Alert in " + destination);
			validate();
		} else if (time < ALERT_DISPLAY_TIME) {
			topAlert.setText("");

		} else {
			// the alert is finished, free the topAlert Label
		}
	}

}
