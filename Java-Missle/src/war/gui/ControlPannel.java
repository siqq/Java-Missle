package war.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControlPannel extends JPanel {
	public ControlPannel() {
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBounds(800, 0, 219, 385);
		setLayout(null);
		
		JButton btnNewButton = new JButton("HELLO");
		btnNewButton.setBounds(10, 43, 181, 32);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("JAVA");
		btnNewButton_1.setBounds(10, 107, 181, 32);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_2.setBounds(10, 182, 181, 41);
		add(btnNewButton_2);
	}
}
