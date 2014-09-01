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
		setSize(1180, 693);
		setResizable(false);
		setTitle("War");
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		ControlPanel controlPanel = new ControlPanel();
		springLayout.putConstraint(SpringLayout.EAST, controlPanel, -10, SpringLayout.EAST, getContentPane());
		DestroyersPanel destroyersPanel = new DestroyersPanel();
		springLayout.putConstraint(SpringLayout.NORTH, destroyersPanel, 503, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, destroyersPanel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, destroyersPanel, -10, SpringLayout.SOUTH, getContentPane());
		IronDomesPanel ironDomesPanel = new IronDomesPanel();
		springLayout.putConstraint(SpringLayout.WEST, ironDomesPanel, 10, SpringLayout.WEST, getContentPane());
		LaunchersPanel launchersPanel = new LaunchersPanel();
		springLayout.putConstraint(SpringLayout.WEST, launchersPanel, 10, SpringLayout.WEST, getContentPane());
		OrefPanel orefPanel = new OrefPanel();
		springLayout.putConstraint(SpringLayout.EAST, orefPanel, -10, SpringLayout.EAST, getContentPane());
		ProgressPanel progressPanel = new ProgressPanel();
		progressPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.EAST, launchersPanel, -6, SpringLayout.WEST, progressPanel);
		springLayout.putConstraint(SpringLayout.EAST, destroyersPanel, -6, SpringLayout.WEST, progressPanel);
		springLayout.putConstraint(SpringLayout.EAST, ironDomesPanel, -6, SpringLayout.WEST, progressPanel);
		springLayout.putConstraint(SpringLayout.WEST, orefPanel, 6, SpringLayout.EAST, progressPanel);
		springLayout.putConstraint(SpringLayout.WEST, controlPanel, 6, SpringLayout.EAST, progressPanel);
		
		springLayout.putConstraint(SpringLayout.NORTH, progressPanel, 50, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, progressPanel, 324, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, progressPanel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, progressPanel, -241, SpringLayout.EAST, getContentPane());
		getContentPane().add(progressPanel);
		springLayout.putConstraint(SpringLayout.NORTH, controlPanel, 100, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, controlPanel, 654, SpringLayout.NORTH, getContentPane());
		getContentPane().add(controlPanel);
		
		springLayout.putConstraint(SpringLayout.NORTH, orefPanel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, orefPanel, -6, SpringLayout.NORTH, controlPanel);
		getContentPane().add(orefPanel);
		getContentPane().add(ironDomesPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, ironDomesPanel, -6, SpringLayout.NORTH, destroyersPanel);
		getContentPane().add(destroyersPanel);
		
		springLayout.putConstraint(SpringLayout.SOUTH, launchersPanel, -410, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, ironDomesPanel, 6, SpringLayout.SOUTH, launchersPanel);
		springLayout.putConstraint(SpringLayout.NORTH, launchersPanel, 10, SpringLayout.NORTH, getContentPane());
		
		progressPanel.setLayout(new GridLayout(10, 1, 20, 5));
		getContentPane().add(launchersPanel);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressPanel.add(progressBar_1);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressPanel.add(progressBar_2);
		
		JProgressBar progressBar_3 = new JProgressBar();
		progressPanel.add(progressBar_3);
		
		JProgressBar progressBar = new JProgressBar();
		progressPanel.add(progressBar);
		
		JLabel lblNewLabel = new JLabel("Enemy missile Progress Bar");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, orefPanel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 170, SpringLayout.EAST, launchersPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -6, SpringLayout.NORTH, progressPanel);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -182, SpringLayout.WEST, orefPanel);
		lblNewLabel.setLabelFor(this);
		getContentPane().add(lblNewLabel);
	
		setVisible(true);
		
		
	}
}
