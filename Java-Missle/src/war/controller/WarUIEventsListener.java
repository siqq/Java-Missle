package war.controller;

import java.io.Serializable;


public interface WarUIEventsListener extends Serializable{
	void addLauncherToUI(String id);

	void addDestructorToUI(String id, String type);

	void addMissileToUI(String id, String dest, String damage, String flyTime,
			String launcherId);

	void addMissileToProgress(String name, String dest, String damage,
			String flyTime, String launcherId);

	void destroyLauncherInUI(String destroyerId, String launcherIdToDestroy);


	void addInterceptionToUI(String missileId, String ironDomeId);

	void addLauncherThroughClient(String id);

	void connectToServer();

	void addMissileThroughClient(String id, String dest, String damageT,
		String flyTime, String launcherId);

	int[] getStatistics();

	void finishProgram();

}
