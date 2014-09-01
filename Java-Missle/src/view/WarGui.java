package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;

public class WarGui extends JFrame {
	public WarGui() {

		SpringLayout springLayout = new SpringLayout();
		
		ControlPanel controlPanel = new ControlPanel();
		DestroyersPanel destroyersPanel = new DestroyersPanel();
		IronDomesPanel ironDomesPanel = new IronDomesPanel();
		LaunchersPanel launchersPanel = new LaunchersPanel();
		ProgressPanel progressPanel = new ProgressPanel();
		OrefPanel orefPanel = new OrefPanel();		
		

		// SpringLayout for Control Panel
		springLayout.putConstraint(SpringLayout.EAST , controlPanel, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST , controlPanel, 6, SpringLayout.EAST, progressPanel);
		springLayout.putConstraint(SpringLayout.NORTH, controlPanel, 100, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, controlPanel, 654, SpringLayout.NORTH, getContentPane());

		// SpringLayout for Destroyers Panel
		springLayout.putConstraint(SpringLayout.EAST , destroyersPanel, -6, SpringLayout.WEST, progressPanel);
		springLayout.putConstraint(SpringLayout.WEST , destroyersPanel, 10, SpringLayout.WEST, getContentPane());	
		springLayout.putConstraint(SpringLayout.NORTH, destroyersPanel, 503, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, destroyersPanel, -10, SpringLayout.SOUTH, getContentPane());

		// SpringLayout for Iron Domes Panel
		springLayout.putConstraint(SpringLayout.EAST , ironDomesPanel, -6, SpringLayout.WEST, progressPanel);
		springLayout.putConstraint(SpringLayout.WEST , ironDomesPanel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, ironDomesPanel, 6, SpringLayout.SOUTH, launchersPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, ironDomesPanel, -6, SpringLayout.NORTH, destroyersPanel);

		// SpringLayout for Launchers Panel
		springLayout.putConstraint(SpringLayout.EAST , launchersPanel, -6, SpringLayout.WEST, progressPanel);
		springLayout.putConstraint(SpringLayout.WEST , launchersPanel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, launchersPanel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, launchersPanel, -410, SpringLayout.SOUTH, getContentPane());

		// SpringLayout for Oref Panel
		springLayout.putConstraint(SpringLayout.EAST , orefPanel, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST , orefPanel, 6, SpringLayout.EAST, progressPanel);
		springLayout.putConstraint(SpringLayout.NORTH, orefPanel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, orefPanel, -6, SpringLayout.NORTH, controlPanel);

		// SpringLayout for Progress Panel
		springLayout.putConstraint(SpringLayout.EAST , progressPanel, -241, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST , progressPanel, 324, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, progressPanel, 0, SpringLayout.NORTH, orefPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, progressPanel, -10, SpringLayout.SOUTH, getContentPane());
		

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
		setSize(1180, 693);
		setResizable(false);		
		setVisible(true);
	}
}
