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

public class DestroyersPanel extends JPanel {
	public static final String F16_IMAGE_PATH = "/drawable/f16-100x80.png";
	public static final String SHIP_IMAGE_PATH = "/drawable/ship.png";
	
	private ImageIcon shipIcon = new ImageIcon(SHIP_IMAGE_PATH);
	private ImageIcon f16Icon = new ImageIcon(F16_IMAGE_PATH);
    private Queue<JButton> domeQueue = new LinkedList<JButton>();
    
	public DestroyersPanel() {
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new GridLayout(2, 3,3,3));
		addf16ToPanel("404");	
		addshipToPanel("404");	
		}
	
	public void addf16ToPanel(String id){		
		JButton f16 = new JButton(id,new ImageIcon(LaunchersPanel.class.getResource(F16_IMAGE_PATH)));
		f16.setVerticalTextPosition(SwingConstants.BOTTOM);
		f16.setHorizontalTextPosition(SwingConstants.CENTER);
		this.add(f16);
		domeQueue.add(f16);
		repaint();
		
	}
	public void addshipToPanel(String id){		
		JButton ship = new JButton(id,new ImageIcon(LaunchersPanel.class.getResource(SHIP_IMAGE_PATH)));
		ship.setVerticalTextPosition(SwingConstants.BOTTOM);
		ship.setHorizontalTextPosition(SwingConstants.CENTER);
		this.add(ship);
		domeQueue.add(ship);
		repaint();
		
	}
}
