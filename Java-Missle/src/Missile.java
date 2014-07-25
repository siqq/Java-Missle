import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Missile extends Thread {

	private static Logger logger = Logger.getLogger("warLogger");

	private boolean 		isRunning;
	private String 			missileId;
	private String 			destination;
	private String 			launcherId;
	private int 			launchTime;
	private int 			flyTime;
	private int 			damage;
	private Lock 			locker;
	private FileHandler 	fileHandler;
	private CountDownLatch 	latch;

	public Missile(String id, String destination, int launchTime, int flyTime,
			int damage, String launcherId, FileHandler fileHandler) {
		this.isRunning = true;
		this.missileId = id;
		this.destination = destination;
		this.launchTime = launchTime;
		this.flyTime = flyTime;
		this.damage = damage;
		this.launcherId = launcherId;
		
		this.fileHandler = fileHandler;
		ObjectFilter filter = (ObjectFilter) fileHandler.getFilter();
		filter.addFilter(this);
		fileHandler.setFormatter(new MyFormatter());

	}

	public String getMissileId() {
		return missileId;
	}

	@Override
	public void run() {
		try {
			sleep(launchTime * 1000);
			sleep(flyTime * 1000);
			latch.countDown();		// wake up launcher after missile finish

			// print to log that missile successfully hit targer
			String print_log = "Missle "+ this.missileId +" from launcher " + this.launcherId
							 + " hit its target with " + this.damage + " damage";
			Object arr[] = {this};
			logger.log(Level.INFO, print_log, arr);

		} catch (InterruptedException e) {


		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public void destroy(Destructor<DestructedMissile> destructor) {
		synchronized (this) {
			Object arr[] = {this, destructor};
			// print to log that missile was destroyed
			String print_log = "Missle "+ this.missileId +" from launcher " + this.launcherId + " was Bombed";
			logger.log(Level.INFO, print_log, arr);
			
			if (isRunning) {
				this.interrupt();
			}
		}
	}

	@Override
	public String toString() {
		return "Missile [id=" + missileId + ", destination=" + destination
				+ ", launchTime=" + launchTime + ", flyTime=" + flyTime
				+ ", damage=" + damage + "]";
	}

	public void addLocker(Lock locker, CountDownLatch latch) {
		this.locker = locker;
		this.latch = latch;
	}

}
