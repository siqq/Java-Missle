import java.io.IOException;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Launcher extends Thread {

	public static final int 	MIN_REVEAL = 1;
	public static final int 	MAX_REVEAL = 5;

	private static Logger 		logger;

	private String 				id;
	private boolean 			isHidden;
	private boolean 			isRunning;
	private Vector<Missile> 	missiles;
	private FileHandler 		fileHandler;

	/**
	 * Constructor 
	 * @param id
	 * @param isHidden
	 * @param missiles
	 * @throws SecurityException
	 * @throws IOException
	 */
	public Launcher(String id, boolean isHidden, Vector<Missile> missiles)
			throws SecurityException, IOException {
		super();
		this.id = id;
		this.isHidden = true;
		this.missiles = missiles;
		this.isRunning = true;

		fileHandler = new FileHandler("Launcher_" + this.id + ".txt", false);
		fileHandler.setFilter(new ObjectFilter(this));
		fileHandler.setFormatter(new MyFormatter());
		logger = Logger.getLogger("warLogger");
		logger.addHandler(this.fileHandler);
	}

	/**
	 * Constructor 
	 * @param id
	 * @param isHidden
	 * @throws SecurityException
	 * @throws IOException
	 */
	public Launcher(String id, boolean isHidden) throws SecurityException,
	IOException {
		super();
		this.id = id;
		this.isHidden = isHidden;
		this.missiles = new Vector<Missile>();
		this.isRunning = true;

		fileHandler = new FileHandler("Launcher_" + this.id + ".txt", false);
		fileHandler.setFilter(new ObjectFilter(this));
		fileHandler.setFormatter(new MyFormatter());
		logger = Logger.getLogger("warLogger");
		logger.addHandler(this.fileHandler);	
	}

	/** Return id of launcher */ 
	public String getLauncherId() {
		return id;
	}

	/** Return the status of launcher 
	 * true = hidden
	 * false = no hidden */
	public boolean isHidden() {
		return isHidden;
	}

	/** Return the status of launcher 
	 * true = running 
	 * false = not running */
	public boolean isRunning() {
		return isRunning;
	}
	/** Return the file handler */  
	public FileHandler getFileHandler() {
		return fileHandler;
	}

	/** Return vector of missiles */
	public Vector<Missile> getMissiles() {
		return missiles;
	}

	/** The method changes status running to false */
	public void stopLauncher() {
		this.isRunning = false;
	}

	/**
	 * The method add missile to selected launcher
	 * @param id
	 * @param destination
	 * @param launchtime
	 * @param flytime
	 * @param damage
	 */
	public void addMissile(String id, String destination, int launchtime,
			int flytime, int damage) {
		Missile missile = new Missile(id, destination, launchtime, 
				flytime, damage, this.fileHandler, this);
		this.missiles.add(missile);
		synchronized (this) {
			this.notify();
		}

	}

	/** The method checks the status of launcher 
	 * if hidden - changes status of launcher after launching 
	 * and returns it hidden
	 * if not hidden - do nothing */ 
	public void revealYourSelf() {
		//if launcher was not hidden before there is no need to unhidden it
		if (this.isHidden == false) {
			return;
		}
		this.isHidden = false;
		int sleep_time = MIN_REVEAL + (int)(Math.random() 
					   * ((MAX_REVEAL - MIN_REVEAL) + 1));
		logger.log(Level.WARNING,this.id + " sleep time is : " + sleep_time);
		try {
			sleep(sleep_time * War.TIME_INTERVAL);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.log(Level.WARNING,this.id + " sleep time is : " + sleep_time);
		this.isHidden = true;
	}

	/*
	//stop all missile that will not launch
	public void stopAllMissiles() {
		synchronized (this) {
			int size = this.missiles.size();
			for (int i=0; i<size; i++) {
				Missile missile = this.missiles.get(i);
				missile.interrupt();
			}
		}
	}*/

	/** Run launcher */ 
	public void run() {
		try {
			int size = this.getMissiles().size();
			synchronized (this) {
				//fix for new launcher 
				if (size == 0) {
					wait();
					size = this.getMissiles().size();
				}
				for(int i = 0; i < size; i++) {
					this.getMissiles().get(i).start();		
					if (i == size-1) {
						wait();
						size = this.getMissiles().size();
					}
				}
			}
		}
		catch (InterruptedException e) {
		}
	}
}
