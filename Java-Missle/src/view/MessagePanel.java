package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import war.controller.WarUIEventsListener;

import javax.swing.JTextPane;
import javax.swing.JLabel;

public class MessagePanel extends JPanel {
    
	private JLabel label;
	private List<WarUIEventsListener> allListeners;
	private boolean fireMissileButtonPressed;
	private boolean destroyLauncherButtonPressed;
	private WarGui warGui;
	private JPanel panel;
	
	public MessagePanel(List<WarUIEventsListener> allListeners, WarGui warGui) {
//		this.warGui = warGui;
//		setPreferredSize(new Dimension(307, 100));
//		setBorder(new LineBorder(new Color(0, 0, 0)));
//		this.allListeners = allListeners;
//		this.fireMissileButtonPressed = false;
//		this.destroyLauncherButtonPressed = false;
//		setLayout(new FlowLayout());
		setLayout(new BorderLayout(0, 0));
		

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(305, 100));
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
		        public void adjustmentValueChanged(AdjustmentEvent e) {  
		            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
		        }
		    });
		add(scrollPane, BorderLayout.CENTER);
	}

	public boolean isFireMissileButtonPressed() {
		return fireMissileButtonPressed;
	}

	public void setFireMissileButtonPressed(boolean fireMissileButtonPressed) {
		this.fireMissileButtonPressed = fireMissileButtonPressed;
	}

	public void addMessageToPanel(String id) {
	    	label = new JLabel();
		label.setText(id);
		label.setToolTipText(id);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setVerticalTextPosition(SwingConstants.BOTTOM);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(300, 30));
		panel.add(label);
		repaint();
		validate();


	}

}
