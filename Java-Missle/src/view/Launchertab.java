package view;

import java.util.Vector;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import war.controller.WarUIEventsListener;

public class Launchertab extends AnchorPane {
    private ConsoleApp theApplication;
    private Vector<WarUIEventsListener> allListeners;
    private Button connectLauncher, addLauncher;
    private TextField launcherId;
    public Launchertab(ConsoleApp theApplication) {
	this.theApplication = theApplication;
	allListeners = new Vector<WarUIEventsListener>();

	connectLauncher = new Button("Connect to server");
	launcherId = new TextField();
	addLauncher = new Button("Resquest & Add Launcher from server");
	setMaxSize(285.0, 371.0);

	launcherId.setPromptText("LauncherID");
	launcherId.setMaxSize(221, 25);
	launcherId.setLayoutX(14.0);
	launcherId.setLayoutY(37.0);

	addLauncher.setLayoutX(14.0);
	addLauncher.setLayoutY(78.0);

	connectLauncher.setLayoutX(14.0);
	connectLauncher.setLayoutY(5.0);

	getChildren().addAll(connectLauncher, launcherId, addLauncher);


    }
}
