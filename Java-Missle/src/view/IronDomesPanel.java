package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class IronDomesPanel extends JPanel {
	public static final String LAUNCHER_IMAGE_PATH = "/drawable/dome60x89.png";
	private ImageIcon domeIcon = new ImageIcon(LAUNCHER_IMAGE_PATH);
    private Queue<JButton> domeQueue = new LinkedList<JButton>();
	public IronDomesPanel() {
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new GridLayout(2, 3,3,3));

		}
	public void addIronDomeToPanel(String id){
		
		JButton dome = new JButton(id,new ImageIcon(LaunchersPanel.class.getResource(LAUNCHER_IMAGE_PATH)));
		dome.setVerticalTextPosition(SwingConstants.BOTTOM);
		dome.setHorizontalTextPosition(SwingConstants.CENTER);
		this.add(dome);
		domeQueue.add(dome);
		repaint();
		validate();
		
	}
	public void addMissileDestructorToUI(String destructorId, String type) {
		// type could be used for future functions if there are other Missile Destructors
		addIronDomeToPanel(destructorId);
	}
}
