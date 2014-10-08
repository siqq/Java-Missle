package view;

import java.util.Vector;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import war.controller.WarUIEventsListener;

public class Missiletab extends AnchorPane{
    private ConsoleApp theApplication;
    private Vector<WarUIEventsListener> allListeners;

    public Missiletab(ConsoleApp theApplication) {
	this.theApplication = theApplication;
	allListeners = new Vector<WarUIEventsListener>();
	TextField missileID = new TextField();
	TextField destination = new TextField();
	TextField damage = new TextField();
	TextField flytime = new TextField();
	Button add = new Button("Resquest & Add missile from server");
	Button connect = new Button("Connect to server");
	this.setMaxSize(285.0, 371.0);

	missileID.setPromptText("missileID");
	missileID.setMaxSize(221, 25);
	missileID.setLayoutX(14.0);
	missileID.setLayoutY(37.0);

	destination.setPromptText("destination");
	destination.setMaxSize(221, 25);
	destination.setLayoutX(14.0);
	destination.setLayoutY(69.0);

	damage.setPromptText("damage");
	damage.setMaxSize(221, 25);
	damage.setLayoutX(14.0);
	damage.setLayoutY(101.0);

	flytime.setPromptText("flytime");
	flytime.setMaxSize(221, 25);
	flytime.setLayoutX(14.0);
	flytime.setLayoutY(133.0);

	add.setLayoutX(14.0);
	add.setLayoutY(167.0);

	connect.setLayoutX(14.0);
	connect.setLayoutY(5.0);

	//	add.setOnAction(new EventHandler<ActionEvent>() {
	//            @Override
	//            public void handle(ActionEvent event) {
	//                System.out.println("Hello World!");
	//		new Thread(new Runnable() {
	//			@Override
	//			public void run() {
	//				// while (true) {
	//
	//				String id = missileID.getText();
	//				String dest = destination.getText();
	//				String damageT = damage.getText();
	//				String flyTime = flytime.getText();
	//				if (id.isEmpty() || dest.isEmpty() || damageT.isEmpty()) {
	//					JOptionPane.showMessageDialog(null,
	//							"You must fill all details!");
	//					return;
	//				}
	//			}
	//		}).start();
	//            }
	//        });


	getChildren().addAll(connect, missileID, destination, damage, flytime,add);

    }
}
