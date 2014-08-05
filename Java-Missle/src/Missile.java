import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Missile extends Thread {

	private static Logger 	logger;

	private boolean 		isRunning;
	private String 			missileId;
	private String 			destination;
	private int 			launchTime;
	private int 			flyTime;
	private int 			damage;
	private FileHandler 	fileHandler;
	private Launcher 		launcher; 

	/**
	 * Constructor 
	 * @param id
	 * @param destination
	 * @param launchTime
	 * @param flyTime
	 * @param damage
	 * @param fileHandler
	 * @param launcher
	 */
	public Missile(String id, String destination, int launchTime, int flyTime,
			int damage, FileHandler fileHandler, Launcher launcher) {
		this.isRunning = false;
		this.missileId = id;
		this.destination = destination;
		this.launchTime = launchTime;
		this.flyTime = flyTime;
		this.damage = damage;
		this.launcher = launcher;

		addFileHandler(fileHandler);
		
	}
	
	/**
	 * Add file handler and filter log by object
	 * @param fileHandler
	 */
	public void addFileHandler(FileHandler fileHandler) {
		this.setFileHandler(fileHandler);
		ObjectFilter filter = (ObjectFilter) fileHandler.getFilter();
		filter.addFilter(this);
		fileHandler.setFormatter(new MyFormatter());
		logger = Logger.getLogger("warLogger");
	}

	/** Return id of missile */ 
	public String getMissileId() {
		return missileId;
	}

	/** Return status of missile 
	 * true = running 
	 * false = not running */
	public boolean isRunning() {
		return isRunning;
	}

	/** Return launch time */
	public int getLaunchTime() {
		return launchTime;
	}

	/** Return fly time */
	public int getFlyTime() {
		return flyTime;
	}

	/** Return damage */
	public int getDamage() {
		return damage;
	}

	/** Return file handler */
	public FileHandler getFileHandler() {
		return fileHandler;
	}

	/**
	 * Set file handler 
	 * @param fileHandler
	 */
	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}

	/** Run missile */
	public void run() {
		try {
			sleep(launchTime * War.TIME_INTERVAL);

			synchronized (launcher) {
				if (launcher.isRunning()) {
					this.isRunning = true;
					String print_log = "Missle "+ this.missileId 
									 + " was launched from launcher: "
									 + this.launcher.getLauncherId();
					logger.log(Level.INFO, print_log, this);
					War.total_launched_missiles++;
					sleep(flyTime * War.TIME_INTERVAL);
					// print to log that missile successfully hit targer
					print_log = "Missle "+ this.missileId + " hit " 
							  + this.destination + " with " 
							  + this.damage + " damage";
					logger.log(Level.INFO, print_log, this);
					this.isRunning = false;		
					launcher.revealYourSelf(); // make launcher not hidden for X amount of time
					War.total_missiles_hit++;
					War.total_damage += this.damage;
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
