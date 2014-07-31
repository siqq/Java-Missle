import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DestructedMissile extends Thread{

	private static Logger logger = Logger.getLogger("warLogger");

	private Missile 		missile;
	private int 			destructAfterLaunch;
	private FileHandler		fileHandler;
	private Lock 			locker;
	private CountDownLatch 	latch;

	public DestructedMissile(Missile missile, int destructAfterLaunch) {
		super();
		this.missile = missile;
		this.destructAfterLaunch = destructAfterLaunch;		
	}

	public void run() {
		try {
			latch = new CountDownLatch(1);
			missile.addLocker(locker, latch);
			latch.await();	// wait untill the missile will be launched

			sleep(this.destructAfterLaunch * War.TIME_INTERVAL); //wait untill destroy after launch

			synchronized (this) {
				Object arr[] = {this};
				logger.log(Level.INFO, "Launching Missile Destructor for missile :" + missile.getMissileId(), arr);
				boolean destroy = false;
				//try to destroy missile only if it didnt hit target
				if (this.destructAfterLaunch < missile.getFlyTime()) {
					//generate random success
					double rate = Math.random();
					//if rate bigger than success rate it will destroy
					if (rate > War.SUCCESS_RATE) { 
						missile.destroy(this);
						destroy = true;
					}
				}
				if (!destroy) {
					int destruct_time = this.destructAfterLaunch + missile.getLaunchTime();
					logger.log(Level.INFO, "Destruction of missile " + missile.getMissileId() +" was failed at time " + destruct_time, arr);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void addFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
		ObjectFilter filter = (ObjectFilter) fileHandler.getFilter();
		filter.addFilter(this);
		fileHandler.setFormatter(new MyFormatter());

		logger.addHandler(this.fileHandler);
	}

	public void addLocker(Lock locker, CountDownLatch latch) {
		this.locker = locker;
		this.latch = latch;		
	}


}
