package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;


public class ProgressPanel extends JPanel {
	private Queue <JProgressBar> progressBars = new LinkedList<JProgressBar>();	        
	public ProgressPanel() {
	    	this.setBorder(new LineBorder(new Color(0, 0, 0)));
	    	setLayout(new GridLayout(10, 1, 0, 0));
//	    	progressBars = new LinkedList<JProgressBar>();
		    

		
	    	for(int progressBarNo=0 ; progressBarNo < 10 ; progressBarNo++){
	    	    
	    	    progressBars.add(new JProgressBar());
	    	    this.add(progressBars.peek());
	    	}
//		JProgressBar progressBar_1 = new JProgressBar();
//		add(progressBar_1);
//		JProgressBar progressBar_2 = new JProgressBar();
//		add(progressBar_2);
		
//		JProgressBar progressBar_3 = new JProgressBar();
//		this.add(progressBar_3);
//		
//		JProgressBar progressBar_4 = new JProgressBar();
//		this.add(progressBar_4);
//		
//		JProgressBar progressBar_5 = new JProgressBar();
//		this.add(progressBar_5);
//		
//		JProgressBar progressBar_6 = new JProgressBar();
//		this.add(progressBar_6);
//		
//		JProgressBar progressBar_7 = new JProgressBar();
//		this.add(progressBar_7);
//		
//		JProgressBar progressBar_8 = new JProgressBar();
//		this.add(progressBar_8);
//		
//		JProgressBar progressBar_9 = new JProgressBar();
//		this.add(progressBar_9);
//		
//		JProgressBar progressBar = new JProgressBar();
//		this.add(progressBar);

		
	}
}
