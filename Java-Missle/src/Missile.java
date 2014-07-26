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
	private int 			launchTime;
	private int 			flyTime;
	private int 			damage;
	private FileHandler 	fileHandler;
	private Lock 			locker;
	private CountDownLatch 	latch;

	public Missile(String id, String destination, int launchTime, int flyTime,
			int damage, FileHandler fileHandler) {
		this.isRunning = true;
		this.missileId = id;
		this.destination = destination;
		this.launchTime = launchTime;
		this.flyTime = flyTime;
		this.damage = damage;
		
		this.fileHandler = fileHandler;
		ObjectFilter filter = (ObjectFilter) fileHandler.getFilter();
		filter.addFilter(this);
		fileHandler.setFormatter(new MyFormatter());

	}

	public String getMissileId() {
		return missileId;
	}
	
	public boolean isRunning() {
		return isRunning;
	}

	public int getLaunchTime() {
		return launchTime;
	}

	public int getFlyTime() {
		return flyTime;
	}

	public int getDamage() {
		return damage;
	}
	
	@Override
	public void run() {
		try {
			sleep(launchTime * 1000);
			sleep(flyTime * 1000);
			
			// print to log that missile successfully hit targer
			String print_log = "Missle "+ this.missileId + " hit its target with " + this.damage + " damage";
			Object arr[] = {this};
			logger.log(Level.INFO, print_log, arr);

			latch.countDown();		// wake up launcher after missile finish
			
		} catch (InterruptedException e) {


		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public void destroy(DestructedMissile destructor) {
		synchronized (this) {
			Object arr[] = {this, destructor};
			// print to log that missile was destroyed
			String print_log = "Missle "+ this.missileId +" was Bombed";
			logger.log(Level.INFO, print_log, arr);
			
			if (isRunning) {
				this.interrupt();
			}
		}
	}


	public void addLocker(Lock locker, CountDownLatch latch) {
		this.locker = locker;
		this.latch = latch;
	}

}
