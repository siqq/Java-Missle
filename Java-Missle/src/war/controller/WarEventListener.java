package war.controller;

public interface WarEventListener {

	void addDestructorToModel(String id, String type);
	void addLauncherToModel(String id);
	void addedLauncherToModelEvent(String launcherId);
	void addedLuncherDestructorToModelEvent(String destructorId, String type);
	void addedMissileDestructorToModelEvent(String destructorId, String type);
	void addedMissileToModelEvent(String missileId, String destination,
		int damage, int flyTime);
	void addedLauncherToDestroy(String destructor_id, String target_id, int destruct_time);
	void DestroyMissileProgressBar(String missileId, String string);
	void UpdatedMissileProgressToModelEvent(int time, String missileId,
		String string);
	void RemovedLauncherFromUI(String launcherId);

}
