package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class ConsoleApp extends Application  {
    private Stage primaryStage;
    private Missiletab missiletab;
    private Launchertab launcherTab;
    public void start(Stage primaryStage) throws Exception {
	
        final Tab tabMissile = new Tab();
        tabMissile.setText("Missile");
        final Tab tabLauncher = new Tab();
        tabLauncher.setText("Launcher");
	TabPane tabPane = new TabPane();
	tabPane.setId("mainPane");
	
	missiletab = new Missiletab(this);
	launcherTab = new Launchertab(this);

	tabMissile.setContent(missiletab);
	tabLauncher.setContent(launcherTab);
	tabPane.getTabs().addAll(tabMissile , tabLauncher);
	
	Scene scene = new Scene(tabPane, 255, 240);
	scene.getStylesheets().add(this.getClass().getResource("ClientStyle").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.show();
    }

    	public static void main(String[] args) {
    		launch(args);
    	}

	public Stage getPrimaryStage() {
	    return primaryStage;
	}
    	
}
