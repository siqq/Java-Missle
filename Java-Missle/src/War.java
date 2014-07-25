import java.io.IOException;
import java.util.ArrayList;
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

	private ArrayList<Launcher> missileLaunchers = new ArrayList<>();
	private ArrayList<Destructor<DestructedMissile>> missileDestructors = new ArrayList<>();
	private ArrayList<Destructor<DestructedLanucher>> missileLauncherDestructors = new ArrayList<>();

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
	
	public void setMissileLaunchers(ArrayList<Launcher> missileLaunchers) {
		this.missileLaunchers = missileLaunchers;
	}

	public void setMissileDestructors(
			ArrayList<Destructor<DestructedMissile>> missileDestructors) {
		this.missileDestructors = missileDestructors;
	}

	public void setMissileLauncherDestructors(
			ArrayList<Destructor<DestructedLanucher>> missileLauncherDestructors) {
		this.missileLauncherDestructors = missileLauncherDestructors;
	}
	/**
	 * This method start all the other threads this is where all the war begins.
	 */
	public void run() {
		for (int i = 0; i < missileLaunchers.size(); i++) {
			Launcher l = missileLaunchers.get(i);
			l.start();
		}
		/*
		 * Iterator<Launcher> iterator_launcher = missileLaunchers.iterator();
		 * while (iterator_launcher.hasNext()) {
		 * iterator_launcher.next().start(); }
		 * 
		 * Iterator<Destructor<DestructedLanucher>> iterator_destructL =
		 * missileLauncherDestructors.iterator(); while
		 * (iterator_destructL.hasNext()) { iterator_destructL.next().start(); }
		 * Iterator<Destructor<DestructedMissile>> iterator_destructM =
		 * missileDestructors.iterator(); while (iterator_destructM.hasNext()) {
		 * iterator_destructM.next().start(); }
		 */
	}

}
