package view;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import war.controller.WarUIEventsListener;

public class MissilePopUpFrame extends JFrame{
	JTextField txtId, txtDamage,txtDest,txtFlyTime;
	JButton addButton;
	private List<WarUIEventsListener> allListeners;
//ff
	public MissilePopUpFrame(List<WarUIEventsListener> allListeners)
			throws HeadlessException {
		this.allListeners = allListeners;
		setLayout(new FlowLayout());
		txtId = new JTextField(10);
		txtId.setText("Enter missile id");
		txtDest = new JTextField(10);
		txtDest.setText("Enter missile destination");
		txtDamage = new JTextField(10);
		txtDamage.setText("Enter missile damage");
		txtFlyTime = new JTextField(10);
		txtFlyTime.setText("Enter missile fly time");

		addButton = new JButton("Select The launcher");
		add(txtId);
		add(txtDest);
		add(txtDamage);
		add(txtFlyTime);
		add(addButton);
		setSize(200, 200);
		setLocationRelativeTo(null);
		setVisible(true);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fireSelectPressed();

			}

		});
	}

	public void fireSelectPressed() {
		String id = txtId.getText();
		String dest = txtDest.getText();
		String damage = txtDamage.getText();
		String flyTime = txtFlyTime.getText();
		if (id.isEmpty() || dest.isEmpty() || damage.isEmpty()) {
			JOptionPane.showMessageDialog(null, "You must fill all details!");
			return;
		}
		for (WarUIEventsListener l : allListeners) {
		    //sending missile details to the rlevant gui launcher
			l.addMissileToUI(id , dest , damage , flyTime);

		}

		dispose();

	}


}
