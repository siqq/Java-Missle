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
	private JTextField alert1Text;
	private JTextField alert2Text;
	private JTextField alert3Text;
	public OrefPanel() {
		setBackground(Color.DARK_GRAY);
		this.setBorder(null);
		setLayout(new GridLayout(4, 1));
		
		JLabel orefImageLable = new JLabel();
		orefImageLable.setIcon(new ImageIcon(OrefPanel.class.getResource(OREF_IMAGE_PATH)));
		add(orefImageLable);
		
		alert1Text = new JTextField();
		alert1Text.setBackground(new Color(255, 127, 80));
		add(alert1Text);
		alert1Text.setColumns(10);
		
		alert2Text = new JTextField();
		alert2Text.setBackground(new Color(255, 127, 80));
		add(alert2Text);
		alert2Text.setColumns(10);
		
		alert3Text = new JTextField();
		alert3Text.setBackground(new Color(255, 127, 80));
		add(alert3Text);
		alert3Text.setColumns(10);

		}
}
