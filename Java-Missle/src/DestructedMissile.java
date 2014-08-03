import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DestructedMissile extends Thread {

	private static Logger 	logger;

	private LocalDateTime 	current_time;

	private Missile 		missile;
	private int 			destructAfterLaunch;
	private FileHandler 	fileHandler;

	/**
	 * Constructor 
	 * @param missile
	 * @param destructAfterLaunch
	 */
	public DestructedMissile(Missile missile, int destructAfterLaunch) {
		super();
		this.missile = missile;
		this.destructAfterLaunch = destructAfterLaunch;
	}

	/**
	 * Add file handler and filter log by object
	 * @param fileHandler
	 */
	public void addFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
		ObjectFilter filter = (ObjectFilter) fileHandler.getFilter();
		filter.addFilter(this);
		fileHandler.setFormatter(new MyFormatter());
		logger = Logger.getLogger("warLogger");
		logger.addHandler(this.fileHandler);
	}

	/** Run object */
	public void run() {
		Object arr[] = { this, missile };
		try {
			sleep(this.destructAfterLaunch * War.TIME_INTERVAL); // wait untill destroy after war launch

			current_time = LocalDateTime.now();

			synchronized (this) {
				logger.log(Level.INFO, "Trying to destroy missile :"
						  + missile.getMissileId()
						  + " from Missile Destructor", arr);
				// try to destroy missile
				if (destroyMissile(missile)) {
					// print to log that missile was destroyed
					String print_log = "Missle " + missile.getMissileId()
							         + " was destroyed at time "
							         + WarUtility.currentTime(current_time);
					logger.log(Level.INFO, print_log, arr);
				}
			}
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), arr);
		}
	}

	/**
	 * method to destroy a selected missile
	 * @param missile
	 * @return
	 * @throws Exception
	 */
	public boolean destroyMissile(Missile missile) throws Exception {
		if (missile.isRunning()) {
			if (this.destructAfterLaunch < (missile.getLaunchTime() 
					+ missile.getFlyTime())) {
				// generate random success
				double rate = Math.random();
				// if rate bigger than success rate it will destroy
				if (rate > War.SUCCESS_RATE) {
					missile.interrupt();
					War.total_destroyed_missiles++;
					return true;
				} 
				else {
					throw new Exception("Destruction of missile "
									   + missile.getMissileId() 
									   + " was failed");
				}
			}
		} 
		else {
			throw new Exception("Destruction of missile "
							   + missile.getMissileId() 
					           + " was failed - Missile is not running");
		}
		return false;
	}

}
