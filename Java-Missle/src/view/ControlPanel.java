package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.SpringLayout;

public class ControlPanel extends JPanel {
	public ControlPanel() {
				
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		
		
		
		JButton exitButton = new JButton();
		springLayout.putConstraint(SpringLayout.NORTH, exitButton, 374, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, exitButton, 234, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, exitButton, -3, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, exitButton, -3, SpringLayout.EAST, this);
		
		exitButton.setIcon(new ImageIcon(ControlPanel.class.getResource("/drawable/exitButton.png")));
		exitButton.setOpaque(false);
		exitButton.setFocusPainted(false);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		validate();
		
		add(exitButton);
		
		JButton startButton = new JButton();
		springLayout.putConstraint(SpringLayout.NORTH, startButton, 0, SpringLayout.NORTH, exitButton);
		springLayout.putConstraint(SpringLayout.WEST, startButton, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, startButton, 0, SpringLayout.SOUTH, exitButton);
		springLayout.putConstraint(SpringLayout.EAST, startButton, 71, SpringLayout.WEST, this);
		startButton.setIcon(new ImageIcon(ControlPanel.class.getResource("/drawable/startButton.png")));
		
		startButton.setOpaque(false);
		startButton.setFocusPainted(false);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		validate();
		add(startButton);


		
	}
}
