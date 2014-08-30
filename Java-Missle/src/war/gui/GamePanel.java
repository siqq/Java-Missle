package war.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JButton;

public class GamePanel extends JPanel {
	public GamePanel(){
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBounds(0, 0, 800, 385);
		setLayout(null);
		
		JButton btnMain = new JButton("MAIN");
		btnMain.setBounds(320, 94, 148, 38);
		add(btnMain);
		
		JButton btnNewButton = new JButton("GAME");
		btnNewButton.setBounds(473, 160, 148, 45);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("WINDOW");
		btnNewButton_1.setBounds(631, 239, 138, 38);
		add(btnNewButton_1);
	}
}
