import java.io.IOException;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * A war class that holds all the war objects
 * 
 * @author Avishay and Dvir
 * 
 */
public class War extends Thread {

	public static final int 	TIME_INTERVAL = 1000; 	//sleep time for threads
	public static final double 	SUCCESS_RATE = 0.2;	//success rate for destructors

	private static Logger 		logger;

	public static int 			total_launched_missiles = 0;
	public static int 			total_destroyed_missiles = 0;
	public static int 			total_missiles_hit = 0;
	public static int 			total_destroyed_launchers = 0;
	public static int 			total_damage = 0;

	private Vector<Launcher> 						missileLaunchers = new Vector<>();
	private Vector<Destructor<DestructedMissile>> 	missileDestructors = new Vector<>();
	private Vector<Destructor<DestructedLanucher>> 	missileLauncherDestructors = new Vector<>();

	/**
	 * Constructor for the war which take from XML the stats to begin
	 * @param missileLaunchers2 
	 * @param missileLauncherDestructors2 
	 * @param missileDestructors2 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public War(Vector<Destructor<DestructedMissile>> missileDestructors, 
			   Vector<Destructor<DestructedLanucher>> missileLauncherDestructors, 
			   Vector<Launcher> missileLaunchers) 
		       throws ParserConfigurationException, SAXException, IOException {
		
		FileHandler fileHandler = new FileHandler("war_log.txt");
		fileHandler.setFormatter(new MyFormatter());
		logger = Logger.getLogger("warLogger");
		logger.addHandler(fileHandler);
		logger.setUseParentHandlers(false);
		this.missileLaunchers = missileLaunchers;
		this.missileDestructors = missileDestructors;
		this.missileLauncherDestructors = missileLauncherDestructors;
	}
	
	public War() {
		this.missileLaunchers = new Vector<>();
		this.missileDestructors = new Vector<>();
		this.missileLauncherDestructors = new Vector<>();
	}

	public Vector<Launcher> getMissileLaunchers() {
		return missileLaunchers;
	}

	public Vector<Destructor<DestructedMissile>> getMissileDestructors() {
		return missileDestructors;
	}

	public Vector<Destructor<DestructedLanucher>> getMissileLauncherDestructors() {
		return missileLauncherDestructors;
	}

	public void addLauncherDestructor(Destructor<DestructedLanucher> desctructor) {
		this.missileLauncherDestructors.add(desctructor);	
		desctructor.start();
	}

	public void addMissileDestructor(Destructor<DestructedMissile> desctructor) {
		this.missileDestructors.add(desctructor);	
		desctructor.start();
	}

	public void addLauncher(Launcher launcher) {
		this.missileLaunchers.add(launcher);
		launcher.start();
	}

	/** This method start all the other threads this is where all the war begins. */
	public void run() {
		synchronized (this) {
			String print_log = "War Has been started";
			logger.log(Level.INFO, print_log, this);

			int size = missileLaunchers.size();
			for (int i = 0; i < size; i++) {
				Launcher l = missileLaunchers.get(i);
				l.start();
			}

			size = missileDestructors.size();
			for (int i = 0; i < size; i++) {
				Destructor<DestructedMissile> d = missileDestructors.get(i);
				d.start();
			}

			size = missileLauncherDestructors.size();
			for (int i = 0; i < size; i++) {
				Destructor<DestructedLanucher> d =
						missileLauncherDestructors.get(i);
				d.start();
			}
		}
	}

}
