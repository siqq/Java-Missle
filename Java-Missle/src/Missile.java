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

	public Missile(String id, String destination, int launchTime, int flyTime,
			int damage, FileHandler fileHandler, Launcher launcher) {
		this.isRunning = false;
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
			sleep(launchTime * War.TIME_INTERVAL);
			
			synchronized (launcher) {
				if (launcher.isRunning()) {
					this.isRunning = true;
					String print_log = "Missle "+ this.missileId + " was launched from launcher: "
							+ this.launcher.getLauncherId();
					logger.log(Level.INFO, print_log, this);
					sleep(flyTime * War.TIME_INTERVAL);
					// print to log that missile successfully hit targer
					print_log = "Missle "+ this.missileId + " hit " + this.destination 
							  + " with " + this.damage + " damage";
					logger.log(Level.INFO, print_log, this);
					this.isRunning = false;		
					launcher.revealYourSelf(); // make launcher not hidden for X amount of time
				}
			}	
		} catch (InterruptedException e) {
			//missile is destroyed
			this.isRunning = false;
			launcher.revealYourSelf(); // make launcher not hidden for X amount of time
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

}
