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

public class WarGui extends JFrame {
	public WarGui() {
		setSize(1046, 489);
		setResizable(false);
		setTitle("War");
		getContentPane().setLayout(null);
		
		ControlPanel controlPanel = new ControlPanel();
		OrefPanel orefPanel = new OrefPanel();
		ProgressPanel progressPanel = new ProgressPanel();
		LaunchersPanel launchersPanel = new LaunchersPanel();
		IronDomesPanel ironDomesPanel = new IronDomesPanel();
		DestroyersPanel destroyersPanel = new DestroyersPanel();
		
		getContentPane().add(controlPanel);
		getContentPane().add(orefPanel);
		getContentPane().add(progressPanel);
		getContentPane().add(launchersPanel);
		getContentPane().add(ironDomesPanel);
		getContentPane().add(destroyersPanel);
	
		setVisible(true);
		
		
	}

}
