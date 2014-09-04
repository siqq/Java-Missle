package war.controller;

public interface WarEventListener {

	void addDestructorToModel(String id, String type);
	void addLauncherToModel(String id);
	void addedLauncherToModelEvent(String launcherId);
	void addedLuncherDestructorToModelEvent(String destructorId, String type);
	void addedMissileDestructorToModelEvent(String destructorId, String type);

}
