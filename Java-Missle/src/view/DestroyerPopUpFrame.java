package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import war.controller.WarUIEventsListener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class DestroyerPopUpFrame extends JFrame {
	private List<WarUIEventsListener> allListeners;
	private JRadioButton ironDomeRadioButton, shipRadioButton,
			planeDomeRadioButton;
	private JLabel top, center;
	private JTextField txtId;
	private JButton addButton;
	private String destroyerType;

	public DestroyerPopUpFrame(List<WarUIEventsListener> allListeners) {
		this.allListeners = allListeners;
		center = new JLabel();
		center.setLayout(new FlowLayout());
		addButton = new JButton("add");
		txtId = new JTextField(8);

		ironDomeRadioButton = new JRadioButton("Iron Dome");
		shipRadioButton = new JRadioButton("Ship");
		planeDomeRadioButton = new JRadioButton("Plane");

		setLayout(new GridLayout(2, 1));
		top = new JLabel();
		top.setLayout(new FlowLayout());

		ButtonGroup bg = new ButtonGroup();

		bg.add(ironDomeRadioButton);
		bg.add(shipRadioButton);
		bg.add(planeDomeRadioButton);

		top.add(ironDomeRadioButton);
		top.add(shipRadioButton);
		top.add(planeDomeRadioButton);

		getContentPane().add(top);

		ironDomeRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showIronDomeLable();

			}
		});

		shipRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showShipLable();

			}
		});

		planeDomeRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPlaneLable();

			}
		});

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireAddnewDestroyerPressed();

			}
		});

		setSize(500, 250);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void fireAddnewDestroyerPressed() {
		destroyerType = null;
		String id = txtId.getText();
		if (id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "You must fill a name first!");
			return;
		}
		if (ironDomeRadioButton.isSelected()) {
			destroyerType = ironDomeRadioButton.getText();
		} else if (shipRadioButton.isSelected()) {
			destroyerType = shipRadioButton.getText();
		} else if (planeDomeRadioButton.isSelected()) {
			destroyerType = planeDomeRadioButton.getText();
		}
		
		for (WarUIEventsListener l : allListeners) {
			l.addDestructorToUI(id,destroyerType);
		}

		dispose();
		

	}

	public void showPlaneLable() {
		center.removeAll();
		center.add(new JLabel("Enter Plane Id"));
		center.add(txtId);
		center.add(addButton);
		center.setVisible(true);
		add(center);
		repaint();
		validate();
	}

	public void showShipLable() {
		center.removeAll();
		center.add(new JLabel("Enter ship Id"));
		center.add(txtId);
		center.add(addButton);
		center.setVisible(true);
		add(center);
		repaint();
		validate();
	}

	public void showIronDomeLable() {
		center.removeAll();
		center.add(new JLabel("Enter Iron Dome id"));
		center.add(txtId);
		center.add(addButton);
		center.setVisible(true);
		add(center);
		repaint();
		validate();
	}

}
