import java.io.IOException;
import java.util.Vector;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A war class that holds all the war objects
 * 
 * @author Avishay and Dvir
 * 
 */
public class War extends Thread {

	private static Logger logger = Logger.getLogger("warLogger");

	private Vector<Launcher> missileLaunchers = new Vector<>();
	private Vector<Destructor<DestructedMissile>> missileDestructors = new Vector<>();
	private Vector<Destructor<DestructedLanucher>> missileLauncherDestructors = new Vector<>();

	/**
	 * Constructor for the war which take from XML the stats to begin
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public War() throws ParserConfigurationException, SAXException, IOException {
		FileHandler fileHandler = new FileHandler("war_log.txt");
		fileHandler.setFormatter(new MyFormatter());
		logger.addHandler(fileHandler);
	}

	public void setMissileLaunchers(Vector<Launcher> missileLaunchers) {
		this.missileLaunchers = missileLaunchers;
	}

	public void setMissileDestructors(
			Vector<Destructor<DestructedMissile>> missileDestructors) {
		this.missileDestructors = missileDestructors;
	}

	public void setMissileLauncherDestructors(
			Vector<Destructor<DestructedLanucher>> missileLauncherDestructors) {
		this.missileLauncherDestructors = missileLauncherDestructors;
	}
	
	/**
	 * This method start all the other threads this is where all the war begins.
	 */
	public void run() {
		synchronized (this) {
			int size = missileDestructors.size();
			for (int i = 0; i < size; i++) {
				Destructor<DestructedMissile> d = missileDestructors.get(i);
				d.start();
			}
			/*
			size = missileLauncherDestructors.size();
			for (int i = 0; i < size; i++) {
				Destructor<DestructedLanucher> d = missileLauncherDestructors.get(i);
				d.start();
			}
			*/
			
			size = missileLaunchers.size();
			for (int i = 0; i < size; i++) {
				Launcher l = missileLaunchers.get(i);
				l.start();
			}
		}
		
	}

}
