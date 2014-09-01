package view;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

public class ProgressPanel extends JPanel {
	public ProgressPanel() {
		this.setBorder(new LineBorder(new Color(0, 0, 0)));

		
	}
}

/*

		JProgressBar progressBar_1 = new JProgressBar();
		progressPanel.add(progressBar_1);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressPanel.add(progressBar_2);
		
		JProgressBar progressBar_3 = new JProgressBar();
		progressPanel.add(progressBar_3);
		
		JProgressBar progressBar = new JProgressBar();
		progressPanel.add(progressBar);
*/