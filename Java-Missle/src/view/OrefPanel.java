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

		topAlert = createNewAlert();
		middleAlert = createNewAlert();
		buttomAlert = createNewAlert();

		add(topAlert);
		add(middleAlert);
		add(buttomAlert);

	}

	public JTextField createNewAlert() {
		JTextField textBox = new JTextField("");
		textBox.setBackground(new Color(255, 127, 80));
		textBox.setColumns(10);
		textBox.setFont(font);
		textBox.setForeground(Color.WHITE);
		textBox.setHorizontalAlignment(JTextField.CENTER);
		textBox.setEditable(false);
		return textBox;
	}

	public void addMissileToOrefPanel(String destination, int time, int flyTime) {
		if (time < ALERT_DISPLAY_TIME && time % 2 == 0) {
			topAlert.setText("Alert in " + destination);
			validate();
		} else if (time < ALERT_DISPLAY_TIME) {
			topAlert.setText("");

		} else {
			topAlert.setText("");
			// the alert is finished, free the topAlert Label
		}
		if (time == flyTime) {
			// if missile flyTime is less then ALERT_DISPLAY_TIME
			topAlert.setText("");

		}
	}

}
