package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import war.controller.WarUIEventsListener;

public class MissilePopUpFrame extends JFrame {
	private JTextField txtId, txtDamage, txtDest, txtFlyTime;
	private String launcherId;
	private JButton addButton;
	private List<WarUIEventsListener> allListeners;
	private LaunchersPanel launchersPanel;
	
	public MissilePopUpFrame(List<WarUIEventsListener> allListeners,
			String launcherId, LaunchersPanel launchersPanel)
			throws HeadlessException {
		this.launchersPanel = launchersPanel;
		this.allListeners = allListeners;
		this.launcherId = launcherId;
		setLayout(new FlowLayout());

		txtId = new JTextField(15);
		txtId.setText("Enter missile id");
		mouseListener(txtId);
		txtDest = new JTextField(15);
		txtDest.setText("Enter missile destination");
		mouseListener(txtDest);
		txtDamage = new JTextField(15);
		txtDamage.setText("Enter missile damage");
		mouseListener(txtDamage);
		txtFlyTime = new JTextField(15);
		txtFlyTime.setText("Enter missile fly time");
		mouseListener(txtFlyTime);

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
				fireAddNewMissilePressed();				

			}

		});
	}

	public void fireAddNewMissilePressed() {
		String id = txtId.getText();
		String dest = txtDest.getText();
		String damage = txtDamage.getText();
		String flyTime = txtFlyTime.getText();
		if (id.isEmpty() || dest.isEmpty() || damage.isEmpty()) {
			JOptionPane.showMessageDialog(null, "You must fill all details!");
			return;
		}
		for (WarUIEventsListener l : allListeners) {
			// sending missile details to the relevant GUI launcher
			l.addMissileToUI(id, dest, damage, flyTime,launcherId);

		}
		// change launcherPanel view back to normal
		launchersPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		launchersPanel.setFireMissileButtonPressed(false);
		
		dispose();

	}

	public void mouseListener(final JTextField txtBox) {
		txtBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtBox.setText("");
			}
		});
	}

}
