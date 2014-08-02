import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DestructedLanucher extends Thread {

	private static Logger logger = Logger.getLogger("warLogger");

	private Launcher launcher;
	private int destructTime;
	private FileHandler fileHandler;

	public DestructedLanucher(Launcher launcher, int destructTime) {
		super();
		this.launcher = launcher;
		this.destructTime = destructTime;
	}

	public void addFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
		ObjectFilter filter = (ObjectFilter) fileHandler.getFilter();
		filter.addFilter(this);
		fileHandler.setFormatter(new MyFormatter());

		logger.addHandler(this.fileHandler);
	}

	public void run() {
		Object arr[] = {this, launcher};
		try {
			sleep(this.destructTime * War.TIME_INTERVAL); // wait untill destroy after war launch
			synchronized (this) {
				logger.log(Level.INFO, "Trying to destroy launcher :"
						+ launcher.getLauncherId()
						+ " from Launcher Destructor -" + launcher.isHidden(), arr);
				// try to destroy launcher
				if (destroyLauncher(launcher)) {
					// print to log that missile was destroyed
					String print_log = "Launcher " + launcher.getLauncherId()
							+ " was destroyed at time "
							+ this.destructTime;
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
					return true;
				} 
				else {
					throw new Exception("Destruction of launcher "
							+ launcher.getLauncherId() + " was failed");
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
