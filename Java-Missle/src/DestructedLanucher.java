import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DestructedLanucher extends AbstractMissile {

	private static Logger 	logger;

	private Launcher 		target; 
	private Destructor 		destructor;

	/**
	 * Constructor
	 * @param launcher
	 * @param destructTime
	 */
	public DestructedLanucher(Launcher target, int destructTime, 
			Destructor destructor, FileHandler fileHandler) {
		super(destructTime, fileHandler);
		this.target = target;
		this.destructor = destructor;

		logger = Logger.getLogger("warLogger");
	}

	/** Run object */
	public void run() {
		Object arr[] = {this, target};
		try {
			sleep(super.getDelayBeforeLaunch() * War.TIME_INTERVAL); // wait untill destroy after war launch

			synchronized (destructor) {
				logger.log(Level.INFO, "Trying to destroy launcher :"
						  + target.getLauncherId()
						  + " from Launcher Destructor -" 
						  + target.isHidden(), arr);
				// try to destroy launcher
				destroyTarget();
			}
		} 
		catch (Exception e) {
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
		
		if (target.isRunning()) {
			if (!target.isHidden()) {
				// generate random success
				double rate = Math.random();
				// if rate bigger than success rate it will destroy
				if (rate > War.SUCCESS_RATE) {
					target.stopLauncher();
					// print to log that missile was destroyed
					String print_log = "Launcher " + target.getLauncherId()
									 + " was destroyed";
					logger.log(Level.INFO, print_log, arr);
				} 
				else {
					throw new Exception("Destruction of launcher "
									   + target.getLauncherId() 
									   + " was failed");
				}
			}
			else {
				throw new Exception("Destruction of launcher "
								   + target.getLauncherId() 
								   + " was failed - Launcher is hidden!");
			}
		} 
		else {
			throw new Exception("Destruction of launcher "
							   + target.getLauncherId() 
							   + " was failed - Launcher is not running!");
		}
	}

}
