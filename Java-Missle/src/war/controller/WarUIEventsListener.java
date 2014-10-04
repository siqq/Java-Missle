package war.controller;

import javafx.scene.control.TextField;

public interface WarUIEventsListener {
	void addLauncherToUI(String id);

	void addDestructorToUI(String id, String type);

	void addMissileToUI(String id, String dest, String damage, String flyTime,
			String launcherId);

	void addMissileToProgress(String name, String dest, String damage,
			String flyTime, String launcherId);

	void destroyLauncherInUI(String destroyerId, String launcherIdToDestroy);


	void addInterceptionToUI(String missileId, String ironDomeId);

	void addMessageThroughClient(String id, String dest, String damageT,
		String flyTime, String launcherName);

}
