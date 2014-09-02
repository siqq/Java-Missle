package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import launcher.Launcher;
import missile.Missile;
import war.War;

import java.awt.FlowLayout;


public class ProgressPanel extends JPanel {
    private Queue <JProgressBar> progressBars = new LinkedList<JProgressBar>();	  
    private JLabel label;
    Font myFont = new Font("Tahoma",Font.BOLD,14);
    public ProgressPanel(War war) {
	this.setBorder(new LineBorder(new Color(0, 0, 0)));
	setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	

	
//	JProgressBar progressBar = new JProgressBar();
//	progressBar.setPreferredSize(new Dimension(445, 30));
//	add(progressBar);
//	
//	JProgressBar progressBar_2 = new JProgressBar();
//	progressBar_2.setPreferredSize(new Dimension(445, 30));
//	add(progressBar_2);
//	
//	JProgressBar progressBar_3 = new JProgressBar();
//	progressBar_3.setPreferredSize(new Dimension(445, 30));
//	add(progressBar_3);
//	
//	JProgressBar progressBar_4 = new JProgressBar();
//	progressBar_4.setPreferredSize(new Dimension(445, 30));
//	add(progressBar_4);
//
//	JProgressBar progressBar_5 = new JProgressBar();
//	progressBar_5.setPreferredSize(new Dimension(445, 30));
//	add(progressBar_5);
//	
//	JProgressBar progressBar_6 = new JProgressBar();
//	progressBar_6.setPreferredSize(new Dimension(445, 30));
//	add(progressBar_6);
//	
//	JProgressBar progressBar_7 = new JProgressBar();
//	progressBar_7.setPreferredSize(new Dimension(445, 30));
//	add(progressBar_7);
	//	    	progressBars = new LinkedList<JProgressBar>();


	//	    	for(int progressBarNo=0 ; progressBarNo < 10 ; progressBarNo++){
	//	    	    
	//	    	    progressBars.add(new JProgressBar());
	//	    	    
	//	    	}


	int frameLimit = 0;
//	while(true){ //always running until the program stops
	    for( Launcher launcher : war.getMissileLaunchers() ){
		if(frameLimit < 10){
		    for( Missile missile : launcher.getMissiles()){
//			JProgressBar tempProgressBar = missile.getProgressBar();
//			if(missile.getStatus() == Missile.Status.Launched){
//			    tempProgressBar.setValue(missile.getLaunchTime()+1);
				JProgressBar tempProgressBar = missile.getProgressBar();
				tempProgressBar.setLayout(new BorderLayout(5, 5));
				tempProgressBar.setPreferredSize(new Dimension(600, 50));
//				add(progressBar_7);
				label = new JLabel();
				label.setHorizontalTextPosition(JLabel.CENTER);
				label.setVerticalTextPosition(JLabel.CENTER);
				label.setBorder(new EmptyBorder(15, 15, 15, 15));
				label.setFont(myFont);
				label.setText(missile.getMissileId() +" "+ missile.getDestination() + " " + missile.getDamage());
				tempProgressBar.add(label, BorderLayout.CENTER);
				add(missile.getProgressBar());
				frameLimit++;
//			}
			//    tempProgressBar.setValue(missile.getFlyTime());

		    } 
		}
	    }
	}



//    }
}
