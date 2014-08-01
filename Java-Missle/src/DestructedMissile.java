import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DestructedMissile extends Thread{

	private static Logger logger = Logger.getLogger("warLogger");

	private Missile 		missile;
	private int 			destructAfterLaunch;
	private FileHandler		fileHandler;

	public DestructedMissile(Missile missile, int destructAfterLaunch) {
		super();
		this.missile = missile;
		this.destructAfterLaunch = destructAfterLaunch;		
	}

	public void run() {
		try {

			sleep(this.destructAfterLaunch * War.TIME_INTERVAL); //wait untill destroy after war launch

			synchronized (this) {
				Object arr[] = {this};
				logger.log(Level.INFO, "Trying to destroy missile :" + missile.getMissileId() + " from Missile Destructor", arr);
				boolean destroy = false;
				//try to destroy missile only if it didnt hit target
				if (this.destructAfterLaunch < (missile.getLaunchTime() + missile.getFlyTime())) {
					//generate random success
					double rate = Math.random();
					//if rate bigger than success rate it will destroy
					if (rate > War.SUCCESS_RATE) { 
						this.destroyMissile(missile);
						destroy = true;
					}
				}
				if (!destroy) {
					logger.log(Level.INFO, "Destruction of missile " + missile.getMissileId() +" was failed at time " + this.destructAfterLaunch, arr);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void destroyMissile(Missile missile) {
		if (missile.isRunning()) {
			Object arr[] = {this, missile};
			// print to log that missile was destroyed
			String print_log = "Missle "+ missile.getMissileId() +" was destroyed";
			logger.log(Level.INFO, print_log, arr);
			missile.interrupt();
		}
	}
	
	public void addFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
		ObjectFilter filter = (ObjectFilter) fileHandler.getFilter();
		filter.addFilter(this);
		fileHandler.setFormatter(new MyFormatter());

		logger.addHandler(this.fileHandler);
	}



}
