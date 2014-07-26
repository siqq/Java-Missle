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
			sleep(missile.getLaunchTime()*1000);  //wait untill missile launch
			sleep(this.destructAfterLaunch*1000); //wait untill destroy after launch
			synchronized (this) {
				boolean destroy = false;
				//try to destroy missile only if it didnt hit target
				if (this.destructAfterLaunch < missile.getFlyTime()) {
					//generate random success
					double rate = Math.random();
					//80% success rate
					if (rate > 0.2) { 
						missile.destroy(this);
						destroy = true;
					}
				}
				if (!destroy) {
					int destruct_time = this.destructAfterLaunch + missile.getLaunchTime();
					Object arr[] = {this};
					logger.log(Level.INFO, "Destruction of missile " + missile.getMissileId() +" was failed at time " + destruct_time, arr);
				}
				
				latch.countDown();		// wake up destructor after missile finish
				
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
