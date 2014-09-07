package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import war.controller.WarUIEventsListener;

public class LaunchersPanel extends JPanel {
	public static final String LAUNCHER_IMAGE_PATH = "/drawable/launcher87x70.png";
	
	private Queue<JButton> launchersQueue = new LinkedList<JButton>();
	private List<WarUIEventsListener> allListeners;
	private boolean fireMissileButtonPressed;
	private boolean destroyLauncherButtonPressed;
	private WarGui warGui;
	private JPanel panel;
	
	public LaunchersPanel(List<WarUIEventsListener> allListeners, WarGui warGui) {
		this.warGui = warGui;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.allListeners = allListeners;
		this.fireMissileButtonPressed = false;
		this.destroyLauncherButtonPressed = false;
		setLayout(new BorderLayout(0, 0));
		

		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		add(panel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(panel);

		scrollPane.setPreferredSize(new Dimension(305, 100));
		
		add(scrollPane, BorderLayout.EAST);
	}

	public boolean isFireMissileButtonPressed() {
		return fireMissileButtonPressed;
	}

	public void setFireMissileButtonPressed(boolean fireMissileButtonPressed) {
		this.fireMissileButtonPressed = fireMissileButtonPressed;
	}

	public void addLauncherToPanel(final String id) {

		JButton launcher = new JButton(id, new ImageIcon(
		LaunchersPanel.class.getResource(LAUNCHER_IMAGE_PATH)));
		launcher.setVerticalTextPosition(SwingConstants.BOTTOM);
		launcher.setHorizontalTextPosition(SwingConstants.CENTER);
		launcher.setPreferredSize(new Dimension(100, 100));
		panel.add(launcher);
		launchersQueue.add(launcher);
		repaint();
		validate();
		launcher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// If user clicked the FIRE MISSILE Button
				if (fireMissileButtonPressed) {
					String launcherId = ((JButton) e.getSource()).getText();

					addNewMissilePopUp(launcherId);
				}
				// If user clicked the DESTROY LAUNCHER Button
				if(destroyLauncherButtonPressed){
					String launcherId = ((JButton) e.getSource()).getText(); // id of the launcher to destroy
					setBorder(new LineBorder(new Color(0, 0, 0)));
					validate();
					getLauncherDestroyer(launcherId);
					
				}
			}

		});

	}
	public void getLauncherDestroyer(String launcherId) {
		warGui.getLauncherDestroyer(launcherId);
		
	}

	public void addNewMissilePopUp(String launcherId) {
		new MissilePopUpFrame(allListeners, launcherId, this);

	}


	public void selectLauncherTofireFrom() {
		this.setBorder(new LineBorder(new Color(255, 0, 0), 2));
		fireMissileButtonPressed = true;
	}

	public void selectLauncherToDestroy() {
		this.setBorder(new LineBorder(new Color(255, 0, 0), 2));
		destroyLauncherButtonPressed = true;
	}

	public void removeLauncher(String launcherId) {
	   for(JButton launcher : launchersQueue){
	       if(launcherId.equalsIgnoreCase(launcher.getText())){
		   launcher.setVisible(false);
//		   launcher.setEnabled(false);
	       }
	   }
	    
	}

}
