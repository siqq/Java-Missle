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
    private ImageIcon launcherIcon = new ImageIcon(LAUNCHER_IMAGE_PATH);
    private Queue<JButton> launchersQueue = new LinkedList<JButton>();
    private List<WarUIEventsListener> allListeners;
    private String dest,name,damage, flyTime;

    public LaunchersPanel(List<WarUIEventsListener> allListeners) {
	setLayout(new GridLayout(2, 3,3,3));
	this.allListeners = allListeners;

    }

    public void addLauncherToPanel(final String id){

	JButton launcher = new JButton(id,new ImageIcon(LaunchersPanel.class.getResource(LAUNCHER_IMAGE_PATH)));
	launcher.setVerticalTextPosition(SwingConstants.BOTTOM);
	launcher.setHorizontalTextPosition(SwingConstants.CENTER);
	this.add(launcher);
	launchersQueue.add(launcher);
	repaint();
	validate();
	launcher.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		//				for (WarUIEventsListener l : allListeners) {
		//					l.addMissileToProgress(name, dest,damage, flyTime,id);
		//
		//				}
		new Thread(new Runnable() {
		    @Override
		    public void run() {

			for (WarUIEventsListener l : allListeners) {
			    l.addMissileToProgress(name, dest,damage, flyTime,id);

			}

		    }
		}).start();

	    }

	});

    }
    //	public void actionPerformed(ActionEvent arg) {
    //		int result = JOptionPane.showConfirmDialog(theSurvivorPanel,
    //				"Are you sure you want to move to the other tribe?", "Move?",
    //				JOptionPane.YES_NO_OPTION);
    //		if (result == JOptionPane.YES_OPTION) {
    //			setEnabled(false); // here still in the EDT
    //			new Thread(new Runnable() {
    //				@Override
    //				public void run() {
    //					try {
    //						Thread.currentThread().sleep(5000);
    //					} catch (InterruptedException e) {
    //						e.printStackTrace();
    //					}
    //					
    //					// switch back to the EDT (swing thread)
    //					SwingUtilities.invokeLater(new Runnable() {
    //						@Override
    //						public void run() {
    //							theSurvivorPanel.getTribePanel().moveSurvivorToOtherTribe(
    //							theSurvivorPanel);
    //							setEnabled(true);
    //						}
    //					});
    //				}
    //			}).start();
    //		}
    //	} 

    public void setMissileElements(String id, String dest, String damage, String flyTime) {
	this.name = id;
	this.dest=dest;
	this.damage=damage;
	this.flyTime=flyTime;

    }

}
