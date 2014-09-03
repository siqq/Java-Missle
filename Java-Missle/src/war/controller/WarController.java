package war.controller;


import launcher.Destructor;
import launcher.Launcher;
import war.War;

public class WarController implements WarEventListener,WarUIEventsListener {
	private War warModel;
	private AbstractWarView  warView;
	
    public WarController(War model, AbstractWarView view) {
    	warModel = model;
    	warView  = view;
        
    	warModel.registerListener(this);
    	warView.registerListener(this);
    }
    
    
	public void addLauncherToUI(String id) {

	}

	public void addDestructorToUI(Destructor destructor) {

	}

	public void addMissileProgressBarToUI(Launcher launcher) {

	}
}
