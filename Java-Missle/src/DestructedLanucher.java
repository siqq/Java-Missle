import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DestructedLanucher extends Thread {

	private static Logger 	logger;

	private LocalDateTime 	current_time;
	
	private int 			destructTime;
	private Launcher 		target; 
	private FileHandler 	fileHandler;
	
	private Destructor<DestructedLanucher> destructor;

	/**
	 * Constructor
	 * @param launcher
	 * @param destructTime
	 */
	public DestructedLanucher(Launcher target, int destructTime, 
			Destructor<DestructedLanucher> destructor) {
		super();
		this.target = target;
		this.destructTime = destructTime;
		this.destructor = destructor;
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
	}

	/** Run object */
	public void run() {
		Object arr[] = {this, target};
		try {
			sleep(this.destructTime * War.TIME_INTERVAL); // wait untill destroy after war launch

			current_time = LocalDateTime.now();
			synchronized (destructor) {
				logger.log(Level.INFO, "Trying to destroy launcher :"
						  + target.getLauncherId()
						  + " from Launcher Destructor -" 
						  + target.isHidden(), arr);
				// try to destroy launcher
				if (destroyLauncher(target)) {
					// print to log that missile was destroyed
					String print_log = "Launcher " + target.getLauncherId()
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
	 * @param target - the target that need to be destroyed
	 * @return true if destruction was success
	 * @throws Exception with message of fail reason
	 */
	public boolean destroyLauncher(Launcher target) throws Exception {
		if (target.isRunning()) {
			if (!target.isHidden()) {
				// generate random success
				double rate = Math.random();
				// if rate bigger than success rate it will destroy
				if (rate > War.SUCCESS_RATE) {
					target.stopLauncher();
					War.total_destroyed_launchers++;
					
					return true;
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
