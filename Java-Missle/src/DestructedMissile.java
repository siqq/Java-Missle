import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DestructedMissile extends AbstractMissile {
	private static Logger   logger;
	
	private Missile 		target;
	
	private Destructor destructor;

	/**
	 * Constructor 
	 * @param target
	 * @param destructAfterLaunch
	 */
	public DestructedMissile(Missile target, int destructAfterLaunch, 
			Destructor destructor, FileHandler fileHandler) {
		super(destructAfterLaunch, fileHandler);
		this.target = target;
		this.destructor = destructor;
		logger = Logger.getLogger("warLogger");
	}

	/** Run object */
	public void run() {
		Object arr[] = { this, target };
		try {
			sleep(super.getDelayBeforeLaunch() * War.TIME_INTERVAL); // wait untill destroy after war launch

			synchronized (destructor) {
				logger.log(Level.INFO, "Trying to destroy missile :"
						  + target.getMissileId()
						  + " from Missile Destructor", arr);
				// try to destroy missile
				destroyTarget();
			}
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), arr);
		}
	}

	
	@Override
	public void destroyTarget() throws Exception{
		Object arr[] = { this, target };
		
		if (target.isRunning()) {
			// generate random success
			double rate = Math.random();
			// if rate bigger than success rate it will destroy
			if (rate > War.SUCCESS_RATE) {
				synchronized (target) {
					target.interrupt();
				}
				// print to log that missile was destroyed
				String print_log = "Missle " + target.getMissileId()
						+ " was destroyed";
				logger.log(Level.INFO, print_log, arr);
			} 
			else {
				throw new Exception("Destruction of missile "
						+ target.getMissileId() 
						+ " was failed");
			}
		} 
		else {
			throw new Exception("Destruction of missile "
					+ target.getMissileId() 
					+ " was failed - Missile is not running");
		}
	}

}
