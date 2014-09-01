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

public class WarGui extends JFrame {
	public WarGui() {
		setSize(1180, 693);
		setResizable(false);
		setTitle("War");
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		ControlPanel controlPanel = new ControlPanel();
		DestroyersPanel destroyersPanel = new DestroyersPanel();
		IronDomesPanel ironDomesPanel = new IronDomesPanel();
		LaunchersPanel launchersPanel = new LaunchersPanel();
		OrefPanel orefPanel = new OrefPanel();
		ProgressPanel progressPanel = new ProgressPanel();
		
		springLayout.putConstraint(SpringLayout.NORTH, progressPanel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, progressPanel, 324, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, progressPanel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, progressPanel, -241, SpringLayout.EAST, getContentPane());
		getContentPane().add(progressPanel);
		
		springLayout.putConstraint(SpringLayout.WEST, controlPanel, 6, SpringLayout.EAST, progressPanel);
		springLayout.putConstraint(SpringLayout.NORTH, controlPanel, 100, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, controlPanel, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, controlPanel, 654, SpringLayout.NORTH, getContentPane());
		getContentPane().add(controlPanel);
		
		springLayout.putConstraint(SpringLayout.NORTH, orefPanel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, orefPanel, -6, SpringLayout.NORTH, controlPanel);
		springLayout.putConstraint(SpringLayout.WEST, orefPanel, 6, SpringLayout.EAST, progressPanel);
		springLayout.putConstraint(SpringLayout.EAST, orefPanel, 231, SpringLayout.EAST, progressPanel);
		getContentPane().add(orefPanel);
		
		springLayout.putConstraint(SpringLayout.EAST, ironDomesPanel, -6, SpringLayout.WEST, progressPanel);
		springLayout.putConstraint(SpringLayout.WEST, ironDomesPanel, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(ironDomesPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, ironDomesPanel, -6, SpringLayout.NORTH, destroyersPanel);
		
		springLayout.putConstraint(SpringLayout.EAST, destroyersPanel, -6, SpringLayout.WEST, progressPanel);
		springLayout.putConstraint(SpringLayout.WEST, destroyersPanel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, destroyersPanel, -151, SpringLayout.SOUTH, progressPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, destroyersPanel, 0, SpringLayout.SOUTH, progressPanel);
		getContentPane().add(destroyersPanel);
		
		springLayout.putConstraint(SpringLayout.SOUTH, launchersPanel, -410, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, ironDomesPanel, 6, SpringLayout.SOUTH, launchersPanel);
		springLayout.putConstraint(SpringLayout.NORTH, launchersPanel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, launchersPanel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, launchersPanel, -6, SpringLayout.WEST, progressPanel);
		getContentPane().add(launchersPanel);
	
		setVisible(true);
		
		
	}
}
