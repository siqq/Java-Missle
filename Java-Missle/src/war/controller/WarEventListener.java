package war.controller;

public interface WarEventListener {

	void addDestructorToModel(String id, String type);
	void addLauncherToModel(String id);

}
