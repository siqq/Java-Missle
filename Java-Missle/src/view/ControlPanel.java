package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.SpringLayout;

import war.controller.WarUIEventsListener;


public class ControlPanel extends JPanel {
	public static final String EXIT_IMAGE_PATH = "/drawable/exitButton.png";
	public static final String START_IMAGE_PATH = "/drawable/startButton.png";
	public static final String FIRE_MISSILE_IMAGE_PATH = "/drawable/fireMissile.png";
	public static final String ADD_LAUNCHER_IMAGE_PATH = "/drawable/addNewLauncher.png";
	public static final String INTERCEPT_MISSILE_IMAGE_PATH ="/drawable/interceptMissile.png";
	public static final String ADD_DESTRUCTOR_IMAGE_PATH ="/drawable/addNewDestructor.png";
	public static final String WAR_STATISTICS_IMAGE_PATH ="/drawable/warStatistics.png";	
	public static final String BACKGROUND_IMAGE_PATH = "/drawable/desert.png";
	
	JButton exitButton, startButton,fireMissileButton,addNewLauncherButton,interceptMissileButton,addNewDestructorButton,statisticsButton;
	private List<WarUIEventsListener> allListeners;

	public ControlPanel(List<WarUIEventsListener> allListeners) {
		setBackground(Color.DARK_GRAY);
		this.allListeners = allListeners;
		
		exitButton = new JButton();
		startButton = new JButton();
		fireMissileButton = new JButton();
		addNewLauncherButton = new JButton();
		interceptMissileButton = new JButton();
		addNewDestructorButton = new JButton();
		statisticsButton = new JButton();

		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, startButton, 0, SpringLayout.SOUTH, this);
		

		
		setLayout(springLayout);

		///////// EXIT BUTTON CREATION  /////////
		springLayout.putConstraint(SpringLayout.SOUTH, exitButton, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, exitButton, 0, SpringLayout.EAST, this);
		exitButton.setIcon(new ImageIcon(ControlPanel.class.getResource(EXIT_IMAGE_PATH)));
		exitButton.setOpaque(false);
		exitButton.setFocusPainted(false);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		validate();
		add(exitButton);
		
		///////////////////////////////////////////
		
		
		///////// START BUTTON CREATION  /////////
		springLayout.putConstraint(SpringLayout.WEST, startButton, 0, SpringLayout.WEST, this);
		startButton.setIcon(new ImageIcon(ControlPanel.class.getResource(START_IMAGE_PATH)));
		startButton.setOpaque(false);
		startButton.setFocusPainted(false);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		validate();
		add(startButton);
				
		
		
		
		fireMissileButton.setIcon(new ImageIcon(ControlPanel.class.getResource(FIRE_MISSILE_IMAGE_PATH)));
		springLayout.putConstraint(SpringLayout.NORTH, fireMissileButton, 24, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, fireMissileButton, 0, SpringLayout.WEST, this);
		fireMissileButton.setOpaque(false);
		fireMissileButton.setFocusPainted(false);
		fireMissileButton.setBorderPainted(false);
		fireMissileButton.setContentAreaFilled(false);
		validate();
		add(fireMissileButton);
		
		addNewLauncherButton.setIcon(new ImageIcon(ControlPanel.class.getResource(ADD_LAUNCHER_IMAGE_PATH)));
		springLayout.putConstraint(SpringLayout.SOUTH, fireMissileButton, 0, SpringLayout.SOUTH, addNewLauncherButton);
		springLayout.putConstraint(SpringLayout.NORTH, addNewLauncherButton, 24, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, addNewLauncherButton, 122, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, addNewLauncherButton, 0, SpringLayout.EAST, exitButton);
		addNewLauncherButton.setOpaque(false);
		addNewLauncherButton.setFocusPainted(false);
		addNewLauncherButton.setBorderPainted(false);
		addNewLauncherButton.setContentAreaFilled(false);
		validate();
		add(addNewLauncherButton);				
		
		interceptMissileButton.setIcon(new ImageIcon(ControlPanel.class.getResource(INTERCEPT_MISSILE_IMAGE_PATH)));
		springLayout.putConstraint(SpringLayout.EAST, fireMissileButton, 0, SpringLayout.EAST, interceptMissileButton);
		springLayout.putConstraint(SpringLayout.WEST, interceptMissileButton, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, interceptMissileButton, 161, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, interceptMissileButton, -123, SpringLayout.NORTH, startButton);
		interceptMissileButton.setOpaque(false);
		interceptMissileButton.setFocusPainted(false);
		interceptMissileButton.setBorderPainted(false);
		interceptMissileButton.setContentAreaFilled(false);
		validate();
		add(interceptMissileButton);
		
		addNewDestructorButton.setIcon(new ImageIcon(ControlPanel.class.getResource(ADD_DESTRUCTOR_IMAGE_PATH)));
		springLayout.putConstraint(SpringLayout.NORTH, addNewDestructorButton, 157, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, addNewDestructorButton, -128, SpringLayout.NORTH, exitButton);
		springLayout.putConstraint(SpringLayout.EAST, interceptMissileButton, -21, SpringLayout.WEST, addNewDestructorButton);
		springLayout.putConstraint(SpringLayout.WEST, addNewDestructorButton, 122, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, addNewDestructorButton, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, addNewLauncherButton, -53, SpringLayout.NORTH, addNewDestructorButton);
		
		addNewDestructorButton.setOpaque(false);
		addNewDestructorButton.setFocusPainted(false);
		addNewDestructorButton.setBorderPainted(false);
		addNewDestructorButton.setContentAreaFilled(false);
		validate();
		add(addNewDestructorButton);
		
		statisticsButton.setIcon(new ImageIcon(ControlPanel.class.getResource(WAR_STATISTICS_IMAGE_PATH)));
		springLayout.putConstraint(SpringLayout.NORTH, statisticsButton, 273, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, statisticsButton, 59, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, statisticsButton, -11, SpringLayout.NORTH, startButton);
		springLayout.putConstraint(SpringLayout.EAST, statisticsButton, 59, SpringLayout.EAST, fireMissileButton);
		statisticsButton.setOpaque(false);
		statisticsButton.setFocusPainted(false);
		statisticsButton.setBorderPainted(false);
		statisticsButton.setContentAreaFilled(false);
		validate();
		add(statisticsButton);
		
		///////////////////////////////////////////		
		
		
		// ACTION LISTENERS //
		
		addNewLauncherButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg) {
				addNewLauncherPopUpFrame();				
			}
		});
		
		addNewDestructorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addNewDestroyerPopUpFrame();
			}
		});
		fireMissileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addNewMissilePopUpFrame();
			}
		});
		
		
		
		
		
		
		
	}

	protected void addNewMissilePopUpFrame() {
	    new MissilePopUpFrame(allListeners);
	    
	}

	protected void addNewDestroyerPopUpFrame() {
			new DestroyerPopUpFrame(allListeners);
	}

	protected void addNewLauncherPopUpFrame() {
		new LauncherPopUpFrame(allListeners);		
	}




}


