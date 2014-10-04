package view;

import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import war.controller.WarUIEventsListener;

public class ConsoleApp extends Application  {
//    private List<WarUIEventsListener> allListeners;
//    private String launcherName;
//    public ConsoleApp(List<WarUIEventsListener> allListeners, WarGui warGui) {
//	this.allListeners = allListeners;
//	this.launcherName = launcherName; 
//	main(null);
//    }





    public void start(Stage primaryStage) throws Exception {
//	Group root = new Group();
//	primaryStage.setScene(new Scene(root));
        final Tab tabMissile = new Tab();
//        tabMissile.setId("tabMissile");
        tabMissile.setText("Missile");
        final Tab tabLauncher = new Tab();
//        tabLauncher.setId("tabLauncher");
        tabLauncher.setText("Launcher");
	TabPane tabPane = new TabPane();
	tabPane.setId("mainPane");
	
	AnchorPane Missiletab = new AnchorPane();
	AnchorPane LauncherTab = new AnchorPane();
	final TextField missileID = new TextField();
	final TextField destination = new TextField();
	final TextField damage = new TextField();
	final TextField flytime = new TextField();
	Button add = new Button("Resquest & Add missile from server");
	final Button connect = new Button("Connect to server");
	Button connectLauncher = new Button("Connect to server");
	TextField launcherId = new TextField();
	Button addLauncher = new Button("Resquest & Add Launcher from server");
	Missiletab.setMaxSize(285.0, 371.0);
	LauncherTab.setMaxSize(285.0, 371.0);

	missileID.setPromptText("missileID");
	missileID.setMaxSize(221, 25);
	missileID.setLayoutX(14.0);
	missileID.setLayoutY(37.0);
	
	launcherId.setPromptText("LauncherID");
	launcherId.setMaxSize(221, 25);
	launcherId.setLayoutX(14.0);
	launcherId.setLayoutY(37.0);

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
	
	addLauncher.setLayoutX(14.0);
	addLauncher.setLayoutY(78.0);

	connect.setLayoutX(14.0);
	connect.setLayoutY(5.0);

	add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
		new Thread(new Runnable() {
			@Override
			public void run() {
				// while (true) {

				String id = missileID.getText();
				String dest = destination.getText();
				String damageT = damage.getText();
				String flyTime = flytime.getText();
				if (id.isEmpty() || dest.isEmpty() || damageT.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"You must fill all details!");
					return;
				}
//				for (WarUIEventsListener l : allListeners) {
//					l.addMessageThroughClient(id, dest, damageT, flyTime , launcherName);
//				}

			}
		}).start();
            }
        });


	connectLauncher.setLayoutX(14.0);
	connectLauncher.setLayoutY(5.0);

	Missiletab.getChildren().addAll(connect, missileID, destination, damage, flytime,add);
	LauncherTab.getChildren().addAll(connectLauncher, launcherId, addLauncher);
	tabMissile.setContent(Missiletab);
	tabLauncher.setContent(LauncherTab);
	tabPane.getTabs().addAll(tabMissile , tabLauncher);
	
	Scene scene = new Scene(tabPane, 255, 240);
//	primaryStage.setScene(scene);
//	root.getChildren().add(tabPane);
	scene.getStylesheets().add(this.getClass().getResource("ClientStyle").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.show();
    }





    	public static void main(String[] args) {
    		launch(args);
    	}
    	
}
