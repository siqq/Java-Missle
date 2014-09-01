package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class WarGui extends JFrame {
	public WarGui() {

		SpringLayout springLayout = new SpringLayout();
		
		ControlPanel controlPanel = new ControlPanel();
		springLayout.putConstraint(SpringLayout.EAST, controlPanel, -10, SpringLayout.EAST, getContentPane());
		DestroyersPanel destroyersPanel = new DestroyersPanel();
		springLayout.putConstraint(SpringLayout.WEST, destroyersPanel, 10, SpringLayout.WEST, getContentPane());
		IronDomesPanel ironDomesPanel = new IronDomesPanel();
		springLayout.putConstraint(SpringLayout.WEST, ironDomesPanel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ironDomesPanel, -856, SpringLayout.EAST, getContentPane());
		LaunchersPanel launchersPanel = new LaunchersPanel();
		springLayout.putConstraint(SpringLayout.WEST, launchersPanel, 10, SpringLayout.WEST, getContentPane());
		ProgressPanel progressPanel = new ProgressPanel();
		springLayout.putConstraint(SpringLayout.EAST, launchersPanel, -6, SpringLayout.WEST, progressPanel);
		springLayout.putConstraint(SpringLayout.EAST, destroyersPanel, -6, SpringLayout.WEST, progressPanel);
		springLayout.putConstraint(SpringLayout.WEST, controlPanel, 6, SpringLayout.EAST, progressPanel);
		springLayout.putConstraint(SpringLayout.WEST, progressPanel, 6, SpringLayout.EAST, ironDomesPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, progressPanel, -10, SpringLayout.SOUTH, getContentPane());
		
				// SpringLayout for Progress Panel
		springLayout.putConstraint(SpringLayout.EAST , progressPanel, -241, SpringLayout.EAST, getContentPane());
		OrefPanel orefPanel = new OrefPanel();		
		springLayout.putConstraint(SpringLayout.WEST, orefPanel, 6, SpringLayout.EAST, progressPanel);
		springLayout.putConstraint(SpringLayout.EAST, orefPanel, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, progressPanel, 42, SpringLayout.NORTH, orefPanel);
		springLayout.putConstraint(SpringLayout.NORTH, controlPanel, 100, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, controlPanel, 654, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, destroyersPanel, 503, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, destroyersPanel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, ironDomesPanel, 6, SpringLayout.SOUTH, launchersPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, ironDomesPanel, -6, SpringLayout.NORTH, destroyersPanel);
		springLayout.putConstraint(SpringLayout.NORTH, launchersPanel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, launchersPanel, -410, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, orefPanel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, orefPanel, -6, SpringLayout.NORTH, controlPanel);
		

		// Add all panels to the main Frame
		getContentPane().add(progressPanel);
		getContentPane().add(controlPanel);	
		getContentPane().add(orefPanel);
		getContentPane().add(ironDomesPanel);		
		getContentPane().add(destroyersPanel);
		getContentPane().add(launchersPanel);

		
		// Main Frame Settings
		setTitle("War");
		getContentPane().setLayout(springLayout);		
		
		//Jlabel for the progressbar
		JLabel lblEnemyMissileInterception = new JLabel("ENEMY MISSILE INTERCEPTION STATUS");
		lblEnemyMissileInterception.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnemyMissileInterception.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemyMissileInterception.setBackground(Color.WHITE);
		lblEnemyMissileInterception.setForeground(Color.BLACK);
		springLayout.putConstraint(SpringLayout.NORTH, lblEnemyMissileInterception, 0, SpringLayout.NORTH, orefPanel);
		springLayout.putConstraint(SpringLayout.WEST, lblEnemyMissileInterception, 6, SpringLayout.EAST, launchersPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, lblEnemyMissileInterception, -6, SpringLayout.NORTH, progressPanel);
		springLayout.putConstraint(SpringLayout.EAST, lblEnemyMissileInterception, 0, SpringLayout.EAST, progressPanel);
		
		
		getContentPane().add(lblEnemyMissileInterception);
		setSize(1180, 693);
		setResizable(false);		
		setVisible(true);
	}
}
