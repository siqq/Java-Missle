import java.util.logging.Logger;


public class Missile extends Thread{
	
	private String id;
	private String destination;
	private int launchTime;
	private int flyTime;
	private int damage;
	private Logger logger;
	
	public Missile(String id, String destination, int launchTime, int flyTime, int damage, Logger logger) {
		this.id = id;
		this.destination = destination;
		this.launchTime = launchTime;
		this.flyTime = flyTime;
		this.damage = damage;
		this.logger = logger;
	}
	
	@Override
	public void run() {
		
	}
	
	public void destroy() {
		synchronized (this) {
			
		}
	}
	
	@Override
	public String toString() {
		return "Missile [id=" + id + ", destination=" + destination + ", launchTime=" + launchTime + ", flyTime=" + flyTime + ", damage=" + damage
				+ "]";
	}

	public void addlogger(Logger logger) {
		this.logger = logger;	
	}
	

}
