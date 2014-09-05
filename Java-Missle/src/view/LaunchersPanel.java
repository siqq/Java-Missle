package view;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import war.controller.WarUIEventsListener;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LaunchersPanel extends JPanel {
	public static final String LAUNCHER_IMAGE_PATH = "/drawable/launcher87x70.png";
	
	private Queue<JButton> launchersQueue = new LinkedList<JButton>();
	private List<WarUIEventsListener> allListeners;
	private boolean fireMissileButtonPressed;
	private boolean destroyLauncherButtonPressed;
	private WarGui warGui;

	public LaunchersPanel(List<WarUIEventsListener> allListeners, WarGui warGui) {
		this.warGui = warGui;
		setLayout(new GridLayout(2, 3, 3, 3));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.allListeners = allListeners;
		this.fireMissileButtonPressed = false;
		this.destroyLauncherButtonPressed = false;
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
		this.add(launcher);
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

}
