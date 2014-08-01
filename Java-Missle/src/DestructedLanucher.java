import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DestructedLanucher extends Thread{
	
	private static Logger logger = Logger.getLogger("warLogger");
	
	private Launcher		launcher;
	private int 			destructTime;
	private FileHandler		fileHandler;
	
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
		try {
			sleep(this.destructTime * War.TIME_INTERVAL); //wait untill destroy after war launch

			synchronized (this) {
				Object arr[] = {this};
				logger.log(Level.INFO, "Trying to destroy launcher :" + launcher.getLauncherId() + " from Launcher Destructor -" + launcher.isHidden(), arr);
				boolean destroy = false;
				//try to destroy missile only if it didnt hit target
				if (!launcher.isHidden()) {
					//generate random success
					double rate = Math.random();
					rate = 1;
					//if rate bigger than success rate it will destroy
					if (rate > War.SUCCESS_RATE) { 
						this.destroyLauncher(launcher);
						destroy = true;
					}
				}
				if (!destroy) {
					logger.log(Level.INFO, "Destruction of launcher " + launcher.getLauncherId() +" was failed at time " + this.destructTime, arr);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void destroyLauncher(Launcher launcher) {
		if (launcher.isRunning()) {
			Object arr[] = {this, launcher};
			// print to log that missile was destroyed
		//	synchronized (launcher) {
				launcher.interrupt();
				String print_log = "Launcher "+ launcher.getLauncherId() +" was destroyed";
				logger.log(Level.INFO, print_log, arr);
		//	}
			
			
		}
	}
	
}
