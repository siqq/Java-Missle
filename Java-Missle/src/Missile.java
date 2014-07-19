import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Missile extends Thread{
	
	private boolean isRunning = true;
	private String missileId;
	private String destination;
	private int launchTime;
	private int flyTime;
	private int damage;
	private Logger logger;
	private Lock locker;
//	private Condition wakeupConditionS;
	private CountDownLatch latch;
	
	public Missile(String id, String destination, int launchTime, int flyTime, int damage, Logger logger) {
		this.missileId = id;
		this.destination = destination;
		this.launchTime = launchTime;
		this.flyTime = flyTime;
		this.damage = damage;
		this.logger = logger;
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
			logger.log(Level.INFO, "Missle "+ this.missileId + "hit its target with "+ this.damage + "damage");
		} catch (InterruptedException e) {
			logger.log(Level.INFO, "Missle "+ this.missileId + "was Bombed");
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

	public void addlogger(Logger logger) {
		this.logger = logger;	
	}
	
	public void addLocker(Lock locker, CountDownLatch latch) {
		this.locker = locker; 
		this.latch = latch;
	}

}
