package war.controller;

import launcher.Destructor;
import launcher.Launcher;

public interface AbstractWarView {
	void registerListener(WarUIEventsListener listener);
	void addLauncherToUI(String id);
	void addDestructorToUI(Destructor destructor);
	void addMissileProgressBarToUI(Launcher launcher);
	void addLauncherDestructorToUI(String destructorId, String type);
	void addMissileDestructorToModelEvenet(String destructorId, String type);
	
}
