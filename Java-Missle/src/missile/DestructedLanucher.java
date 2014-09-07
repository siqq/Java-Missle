package missile;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import launcher.Destructor;
import launcher.Launcher;
import war.War;
import war.controller.WarEventListener;

public class DestructedLanucher extends AbstractMissile {

	private static Logger 			logger;

	private Launcher 				target;
	private Destructor 				destructor;
	private List<WarEventListener>  allListeners;

	/**
	 * Constructor
	 * @param launcher
	 * @param destructTime
	 */
	public DestructedLanucher(Launcher target, int destructTime, 
			Destructor destructor, FileHandler fileHandler  , List<WarEventListener> allListeners) {
		super (destructTime, fileHandler);
		this.target = target;
		this.destructor = destructor;
		this.allListeners = allListeners;
		logger = Logger.getLogger("warLogger");
	}

	/**
	 * Run object
	 */
	public void run() {
		Object arr[] = {this, target};
		try {
			// wait untill destroy after war launch
//			sleep(super.getDelayBeforeLaunch() * War.TIME_INTERVAL); 
			    try {
				for(int time = 0 ; time <= super.getDelayBeforeLaunch() ; time++){
				    Thread.sleep(War.TIME_INTERVAL);
				    for (WarEventListener l : allListeners) {
					l.UpdatedMissileProgressToModelEvent(time, target.getLauncherId() , "launcherDestroyer",(destructor.getType()+" #" + destructor.getDestructorId()),0,super.getDelayBeforeLaunch());
				    }
				}
				    for (WarEventListener l : allListeners) {
					l.RemoveCurrentElement(target.getLauncherId());
				    }
//				for (WarEventListener l : allListeners) {
//				    l.RemovedLauncherFromUI(target.getLauncherId());
//				}

			    } catch (InterruptedException e) {
			    }	

			synchronized (destructor) {
				logger.log(Level.INFO, "Trying to destroy launcher :"
						  + target.getLauncherId(), arr);
				destroyTarget();		// try to destroy launcher
			}
		} 
		catch (Exception e) { 
			// no success destroy target and the reason
			logger.log(Level.INFO, e.getMessage(), arr); 
		}
	}

	/**
	 * method to destroy a selected launcher
	 * @throws Exception if fails with message of fail reason
	 */
	@Override
	public void destroyTarget() throws Exception {
		Object arr[] = {this, target};
		double rate = 0 ;//Math.random();	// generate random success
		
		if (target.isRunning()) {
			if (!target.isHidden()) {
				// if rate bigger than success rate it will destroy
				if (rate > War.SUCCESS_RATE) {
					for (WarEventListener l : allListeners) {
					    l.RemovedLauncherFromUI(target.getLauncherId());
					}
					target.stopLauncher();
					String print_log = "Launcher " + target.getLauncherId()
									 + " was destroyed";
					logger.log(Level.INFO, print_log, arr);
				} 
				else {
				    for (WarEventListener l : allListeners) {
					l.AddMessageToGui("Destruction of launcher "
						   + target.getLauncherId() 
						   + " was failed");
				    }
					throw new Exception("Destruction of launcher "
									   + target.getLauncherId() 
									   + " was failed");
					
				}
			}
			else {
			    
			    for (WarEventListener l : allListeners) {
				l.AddMessageToGui("Destruction of launcher "
					   + target.getLauncherId() 
					   + " was failed - Launcher is hidden!");
			    }
				throw new Exception("Destruction of launcher "
								   + target.getLauncherId() 
								   + " was failed - Launcher is hidden!");
			}
		} 
		else {
		    for (WarEventListener l : allListeners) {
			l.AddMessageToGui("Destruction of launcher "
				   + target.getLauncherId() 
				   + " was failed - Launcher is not running!");
		    }
			throw new Exception("Destruction of launcher "
							   + target.getLauncherId() 
							   + " was failed - Launcher is not running!");
		}
	}

}
