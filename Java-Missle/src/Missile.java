import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.logging.FileHandler;
import java.util.logging.Level;


public class Missile extends Thread{
	
	private boolean isRunning = true;
	private String missileId;
	private String destination;
	private String launcherId;
	private int launchTime;
	private int flyTime;
	private int damage;
//	private Logger logger;
	private Lock locker;
	private FileHandler fileHandler;
	private CountDownLatch latch;
	
	
	public Missile(String id, String destination, int launchTime, int flyTime, int damage, String launcherId) {
		this.missileId = id;
		this.destination = destination;
		this.launchTime = launchTime;
		this.flyTime = flyTime;
		this.damage = damage;
		this.launcherId = launcherId;
//		this.logger = logger;
	}

	public String getMissileId() {
		return missileId;
	}

	@Override
	public void run() {
		try {
			sleep(launchTime*1000);
			sleep(flyTime*1000);
			latch.countDown();//wake up launcher after missile finish
//			logger.log(Level.INFO, "Missle "+ this.missileId + "hit its target with "+ this.damage + "damage");
			fileHandler = new FileHandler("Launcher_" + this.launcherId+".xml", false);
			TheLogger.logger.addHandler(fileHandler);
			
			TheLogger.logger.log(Level.INFO, "Missle " + this.launcherId + " hit its target with "+ this.damage + " damage");
		} catch (InterruptedException e) {
//			logger.log(Level.INFO, "Missle "+ this.missileId + "was Bombed");
			TheLogger.logger.log(Level.INFO, "Missle " + this.launcherId + " was Bombed");
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		return "Missile [id=" + missileId + ", destination=" + destination + ", launchTime=" + launchTime + ", flyTime=" + flyTime + ", damage=" + damage
				+ "]";
	}

//	public void addlogger(Logger logger) {
//		this.logger = logger;	
//	}
	
	public void addLocker(Lock locker, CountDownLatch latch) {
		this.locker = locker; 
		this.latch = latch;
	}

}
