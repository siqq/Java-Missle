package view;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LaunchersPanel extends JPanel {
	public static final String LAUNCHER_IMAGE_PATH = "/drawable/launcher87x70.png";
	private ImageIcon launcherIcon = new ImageIcon(LAUNCHER_IMAGE_PATH);
    private Queue<JButton> launchersQueue = new LinkedList<JButton>();
    
	public LaunchersPanel() {
		setLayout(new GridLayout(2, 3,3,3));
		

		addLauncherToPanel("404");	
		addLauncherToPanel("404");
		addLauncherToPanel("404");
		addLauncherToPanel("404");	
		addLauncherToPanel("404");
		addLauncherToPanel("404");
	}
	
	public void addLauncherToPanel(String id){
		
		JButton launcher = new JButton(id,new ImageIcon(LaunchersPanel.class.getResource(LAUNCHER_IMAGE_PATH)));
		launcher.setVerticalTextPosition(SwingConstants.BOTTOM);
		launcher.setHorizontalTextPosition(SwingConstants.CENTER);
		this.add(launcher);
		launchersQueue.add(launcher);
		repaint();
		
	}
}
