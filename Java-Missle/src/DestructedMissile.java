import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DestructedMissile extends Thread {

	private static Logger 	logger;

	private LocalDateTime 	current_time;

	private Missile 		target;
	private int 			destructAfterLaunch;
	private FileHandler 	fileHandler;
	
	private Destructor<DestructedMissile> destructor;

	/**
	 * Constructor 
	 * @param target
	 * @param destructAfterLaunch
	 */
	public DestructedMissile(Missile target, int destructAfterLaunch, 
			Destructor<DestructedMissile> destructor, FileHandler fileHandler) {
		super();
		this.target = target;
		this.destructAfterLaunch = destructAfterLaunch;
		this.destructor = destructor;
		addFileHandler(fileHandler);
	}

	/**
	 * Add file handler and filter log by object
	 * @param fileHandler
	 */
	public void addFileHandler(FileHandler fileHandler) {
		this.setFileHandler(fileHandler);
		ObjectFilter filter = (ObjectFilter) fileHandler.getFilter();
		filter.addFilter(this);
		fileHandler.setFormatter(new MyFormatter());
		logger = Logger.getLogger("warLogger");
	}
	
	public FileHandler getFileHandler() {
		return fileHandler;
	}

	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}

	/** Run object */
	public void run() {
		Object arr[] = { this, target };
		try {
			sleep(this.destructAfterLaunch * War.TIME_INTERVAL); // wait untill destroy after war launch

			current_time = LocalDateTime.now();

			synchronized (destructor) {
				logger.log(Level.INFO, "Trying to destroy missile :"
						  + target.getMissileId()
						  + " from Missile Destructor", arr);
				// try to destroy missile
				if (destroyMissile(target)) {
					// print to log that missile was destroyed
					String print_log = "Missle " + target.getMissileId()
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
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public boolean destroyMissile(Missile target) throws Exception {
		if (target.isRunning()) {
			if (this.destructAfterLaunch < (target.getLaunchTime() 
					+ target.getFlyTime())) {
				// generate random success
				double rate = Math.random();
				// if rate bigger than success rate it will destroy
				if (rate > War.SUCCESS_RATE) {
					synchronized (target) {
						target.interrupt();
					}
					War.total_destroyed_missiles++;
					return true;
				} 
				else {
					throw new Exception("Destruction of missile "
									   + target.getMissileId() 
									   + " was failed");
				}
			}
		} 
		else {
			throw new Exception("Destruction of missile "
							   + target.getMissileId() 
					           + " was failed - Missile is not running");
		}
		return false;
	}

}
