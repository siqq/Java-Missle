package war.controller;


import java.io.IOException;

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
		try {
			warModel.addDestructor(id,type);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addedLauncherToModelEvent(String launcherId) {
		warView.addLauncherToUI(launcherId);
	}

	@Override
	public void addedLuncherDestructorToModelEvent(String destructorId,
			String type) {
		warView.addLauncherDestructorToUI(destructorId,type);
	}

	@Override
	public void addedMissileDestructorToModelEvent(String destructorId,
			String type) {
		warView.addMissileDestructorToModelEvenet(destructorId,type);
		
	}
    
    
}
