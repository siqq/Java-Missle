package war.controller;


import launcher.Destructor;
import launcher.Launcher;
import war.War;

public class WarController implements WarUIEventsListener,WarEventListener {
	private War warModel;
	private AbstractWarView  warView;
	
    public WarController(War model, AbstractWarView view) {
    	warModel = model;
    	warView  = view;
        
    	warModel.registerListener(this);
    	warView.registerListener(this);
    	warModel.start();
    }

	@Override
	public void addDestructorToModel(String id, String type) {		
		
	}

	@Override
	public void addLauncherToModel(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLauncherToUI(String id) {
		try {
			warModel.addLauncher(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addDestructorToUI(String id, String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addedLauncherToModelEvent(String launcherId) {
		warView.addLauncherToUI(launcherId);
	}
    
    
}
