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
	private Launcher 		launcher; 
	private Lock 			locker;
	private CountDownLatch 	latch;

	public Missile(String id, String destination, int launchTime, int flyTime,
			int damage, FileHandler fileHandler, Launcher launcher) {
		this.isRunning = true;
		this.missileId = id;
		this.destination = destination;
		this.launchTime = launchTime;
		this.flyTime = flyTime;
		this.damage = damage;
		this.launcher = launcher;
		
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
			Object arr[] = {this};
			synchronized (launcher) {
				sleep(launchTime * War.TIME_INTERVAL);
				String print_log = "Missle "+ this.missileId + " was launched from launcher: "
						+ this.launcher.getLauncherId();
				logger.log(Level.INFO, print_log, arr);
				latch.countDown();	// wake up destructor after missile launch
				sleep(flyTime * War.TIME_INTERVAL);
				
				// print to log that missile successfully hit targer
				print_log = "Missle "+ this.missileId + " hit " + this.destination + 
						" with " + this.damage + " damage";
				logger.log(Level.INFO, print_log, arr);
				this.isRunning = false;
				
			}	
		} catch (InterruptedException e) {
			this.isRunning = false;
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public void destroy(DestructedMissile destructor) {
		if (isRunning) {
			Object arr[] = {this, destructor};
			// print to log that missile was destroyed
			String print_log = "Missle "+ this.missileId +" was Bombed";
			logger.log(Level.INFO, print_log, arr);
			this.interrupt();
		}

	}


	public void addLocker(Lock locker, CountDownLatch latch) {
		this.locker = locker;
		this.latch = latch;
	}

}
