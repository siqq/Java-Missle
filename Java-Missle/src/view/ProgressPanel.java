package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import launcher.Destructor;
import launcher.Launcher;
import missile.Missile;
import war.War;

import java.awt.FlowLayout;


public class ProgressPanel extends JPanel {
    private JLabel label;
    private JProgressBar progressBar;
    private int flyTime;
    private Vector<String> 	missiles = new Vector<>();
    private Font myFont = new Font("Tahoma",Font.BOLD,14);
    public ProgressPanel() {
	this.setBorder(new LineBorder(new Color(0, 0, 0)));
	setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	setVisible(true);

	}
    public void addMissileToProgressBar(String missileId, String destination, int damage,
	    int flyTime) {
	missiles.add(missileId);
	 progressBar = new JProgressBar(1 , flyTime-1);
	 progressBar.setLayout(new BorderLayout(5, 5));
	 progressBar.setPreferredSize(new Dimension(600, 50));
	label = new JLabel();
	label.setHorizontalTextPosition(JLabel.CENTER);
	label.setVerticalTextPosition(JLabel.CENTER);
	label.setBorder(new EmptyBorder(15, 15, 15, 15));
	label.setFont(myFont);
	label.setText(missileId +" "+ destination + " " + damage);
	progressBar.add(label, BorderLayout.CENTER);
	add(progressBar);
//	setVisible(true);
	validate();	
    }
    public void updateProgressBar(){
	progressBar.setValue(0);
    }
   
    public void updateMissileTime(int time) {
	progressBar.setValue(time);
	
    }
    
    
}
