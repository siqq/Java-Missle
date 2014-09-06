package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import launcher.Destructor;
import launcher.Launcher;
import war.War;
import war.controller.AbstractWarView;
import war.controller.WarUIEventsListener;

import javax.swing.ImageIcon;

public class WarGui extends JFrame implements AbstractWarView {
	public static final String PROGRESS_LABLE_IMAGE_PATH = "/drawable/615x40.png";
	private List<WarUIEventsListener> allListeners;
	private ControlPanel controlPanel;
	private IronDomesPanel ironDomesPanel;
	private LaunchersPanel launchersPanel;
	private ProgressPanel progressPanel;
	private DestroyersPanel destroyersPanel;
	private OrefPanel orefPanel;
	
	public WarGui() {
		allListeners = new LinkedList<WarUIEventsListener>();
		controlPanel = new ControlPanel(allListeners, this);
		ironDomesPanel = new IronDomesPanel(allListeners,this);
		launchersPanel = new LaunchersPanel(allListeners,this);
		progressPanel = new ProgressPanel(allListeners, this);
		destroyersPanel = new DestroyersPanel(allListeners);
		orefPanel = new OrefPanel();
		setBackground(Color.DARK_GRAY);

		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, progressPanel, 56, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, progressPanel, -10, SpringLayout.SOUTH, getContentPane());

		springLayout.putConstraint(SpringLayout.NORTH, controlPanel, 233,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, controlPanel, -10,
				SpringLayout.EAST, getContentPane());

		springLayout.putConstraint(SpringLayout.WEST, destroyersPanel, 10,
				SpringLayout.WEST, getContentPane());

		springLayout.putConstraint(SpringLayout.WEST, ironDomesPanel, 10,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ironDomesPanel, -856,
				SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, launchersPanel, 10,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, launchersPanel, -6,
				SpringLayout.WEST, progressPanel);
		springLayout.putConstraint(SpringLayout.EAST, destroyersPanel, -6,
				SpringLayout.WEST, progressPanel);
		springLayout.putConstraint(SpringLayout.WEST, controlPanel, 6,
				SpringLayout.EAST, progressPanel);
		springLayout.putConstraint(SpringLayout.WEST, progressPanel, 6,
				SpringLayout.EAST, ironDomesPanel);

		// SpringLayout for Progress Panel
		springLayout.putConstraint(SpringLayout.EAST, progressPanel, -241,
				SpringLayout.EAST, getContentPane());

		springLayout.putConstraint(SpringLayout.NORTH, orefPanel, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, orefPanel, 6,
				SpringLayout.EAST, progressPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, orefPanel, -6,
				SpringLayout.NORTH, controlPanel);
		springLayout.putConstraint(SpringLayout.EAST, orefPanel, -10,
				SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, controlPanel, 654,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, destroyersPanel, 503,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, destroyersPanel, -10,
				SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, ironDomesPanel, 6,
				SpringLayout.SOUTH, launchersPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, ironDomesPanel, -6,
				SpringLayout.NORTH, destroyersPanel);
		springLayout.putConstraint(SpringLayout.NORTH, launchersPanel, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, launchersPanel, -410,
				SpringLayout.SOUTH, getContentPane());

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

		// Jlabel for the progressbar
		JLabel lblEnemyMissileInterception = new JLabel();
		springLayout.putConstraint(SpringLayout.SOUTH,
				lblEnemyMissileInterception, 0, SpringLayout.NORTH,
				progressPanel);
		lblEnemyMissileInterception.setIcon(new ImageIcon(WarGui.class.getResource(PROGRESS_LABLE_IMAGE_PATH)));
		springLayout.putConstraint(SpringLayout.EAST,
				lblEnemyMissileInterception, 0, SpringLayout.EAST,
				progressPanel);
		springLayout.putConstraint(SpringLayout.NORTH,
				lblEnemyMissileInterception, 10, SpringLayout.NORTH,
				getContentPane());
		lblEnemyMissileInterception.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnemyMissileInterception
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemyMissileInterception.setBackground(Color.WHITE);
		lblEnemyMissileInterception.setForeground(Color.BLACK);
		springLayout.putConstraint(SpringLayout.WEST,
				lblEnemyMissileInterception, 6, SpringLayout.EAST,
				launchersPanel);

		getContentPane().add(lblEnemyMissileInterception);
		setSize(1180, 693);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void registerListener(WarUIEventsListener listener) {
		allListeners.add(listener);

	}

	@Override
	public void addLauncherToUI(String launcherId) {
		launchersPanel.addLauncherToPanel(launcherId);
	}

	@Override
	public void addDestructorToUI(Destructor destructor) {

	}

	@Override
	public void addLauncherDestructorToUI(String destructorId, String type) {
		destroyersPanel.addLauncherDestructorToUI(destructorId, type);

	}

	@Override
	public void addMissileDestructorToModelEvenet(String destructorId,
			String type) {
		ironDomesPanel.addMissileDestructorToUI(destructorId, type);

	}

	@Override
	public void addMissileToUI(String missileId, String destination, int damage,
		int flyTime) {
	    progressPanel.addMissileToProgressBar(missileId,destination,damage,flyTime);
	    
	    
	}


//	@Override
//	public void updateMissileProgress(int time, String missileId , String type) {
//		progressPanel.updateMissileTime(time , missileId , type);
//
//	}

	public void selectLauncherToFireFrom() {
		launchersPanel.selectLauncherTofireFrom();
	}

	public void selectLauncherToDestroy() {
		launchersPanel.selectLauncherToDestroy();
	}

	public void getLauncherDestroyer(String launcherId) {
	destroyersPanel.getLauncherDestroyer(launcherId);
	}

	@Override
	public void destroyMissileProgress(String missileId , String type) {
	   progressPanel.destroyProgress(missileId , type);
	    
	}

	@Override
	public void addDestroyerProgress(String destructor_id,
		String target_id, int destruct_time) {
	    progressPanel.addDestryoerToProgressBar(destructor_id ,target_id, destruct_time);
	    
	}

	@Override
	public void removeLauncherFromView(String launcherId) {
	   launchersPanel.removeLauncher(launcherId);
	    
	}

	@Override
	public void updateMissileProgress(int time, String missileId, String type,
			String destination, int damage, int flyTime) {
		progressPanel.updateMissileTime(time, missileId, type,destination,damage,flyTime);
		
		orefPanel.addMissileToOrefPanel(destination, time);
	}

	public void selectIronDomeToFireFrom(String missileId) {
	    ironDomesPanel.selectLauncherTofireFrom(missileId);
	    
	}

	public void getIronDome(String ironDomeId) {
	    progressPanel.getIronDome(ironDomeId);
	    
	}

	@Override
	public void RemoveCurrentElement(String destructorId) {
	   progressPanel.RemoveCurrentElement(destructorId);
	    
	}

}
