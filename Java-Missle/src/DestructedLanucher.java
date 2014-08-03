import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DestructedLanucher extends Thread {

	private static Logger logger;

	private LocalDateTime current_time;

	private Launcher launcher;
	private int destructTime;
	private FileHandler fileHandler;

	/**
	 * Constructor
	 * @param launcher
	 * @param destructTime
	 */
	public DestructedLanucher(Launcher launcher, int destructTime) {
		super();
		this.launcher = launcher;
		this.destructTime = destructTime;
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
		Object arr[] = {this, launcher};
		try {
			sleep(this.destructTime * War.TIME_INTERVAL); // wait untill destroy after war launch

			current_time = LocalDateTime.now();
			synchronized (this) {
				logger.log(Level.INFO, "Trying to destroy launcher :"
						  + launcher.getLauncherId()
						  + " from Launcher Destructor -" 
						  + launcher.isHidden(), arr);
				// try to destroy launcher
				if (destroyLauncher(launcher)) {
					// print to log that missile was destroyed
					String print_log = "Launcher " + launcher.getLauncherId()
									 + " was destroyed at time " 
							         + WarUtility.currentTime(current_time);
					logger.log(Level.INFO, print_log, arr);
				}
			}
		} 
		catch (Exception e) {
			logger.log(Level.INFO, e.getMessage(), arr);
		}
	}

	/**
	 * method to destroy a selected launcher
	 * @param launcher - the target that need to be destroyed
	 * @return true if destruction was success
	 * @throws Exception with message of fail reason
	 */
	public boolean destroyLauncher(Launcher launcher) throws Exception {
		if (launcher.isRunning()) {
			if (!launcher.isHidden()) {
				// generate random success
				double rate = Math.random();
				// if rate bigger than success rate it will destroy
				if (rate > War.SUCCESS_RATE) {
					launcher.stopLauncher();
					War.total_destroyed_launchers++;
					return true;
				} 
				else {
					throw new Exception("Destruction of launcher "
									   + launcher.getLauncherId() 
									   + " was failed");
				}
			}
			else {
				throw new Exception("Destruction of launcher "
								   + launcher.getLauncherId() 
								   + " was failed - Launcher is hidden!");
			}
		} 
		else {
			throw new Exception("Destruction of launcher "
							   + launcher.getLauncherId() 
							   + " was failed - Launcher is not running!");
		}
	}

}
