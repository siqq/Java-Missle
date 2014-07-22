import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.logging.FileHandler;

public class Missile extends Thread {

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
	}

	public String getMissileId() {
		return missileId;
	}

	@Override
	public void run() {
		try {
			sleep(launchTime * 1000);
			sleep(flyTime * 1000);
			latch.countDown();// wake up launcher after missile finish

			// print to log that missile successfully hit targer
			String print_log = "Missle from launcher " + this.launcherId
							 + " hit its target with " + this.damage + " damage";
			TheLogger.printLog(fileHandler, "INFO", print_log);

		} catch (InterruptedException e) {
			// print to log that missile was destroyed
			String print_log = "Missle from launcher " + this.launcherId + " was Bombed";
			TheLogger.printLog(fileHandler, "INFO", print_log);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		synchronized (this) {
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
