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
 * @author Avishay and Dvir
 *
 */
public class War extends Thread {
	private ArrayList<Launcher> missileLaunchers = new ArrayList<>();
	private ArrayList<Destructor<DestructedMissile>> missileDestructors = new ArrayList<>();
	private ArrayList<Destructor<DestructedLanucher>> missileLauncherDestructors = new ArrayList<>();
	private FileHandler fileHandler;
	private Logger logger = Logger.getLogger("warLogger");

	
	/**
	 * Constructor for the war which take from XML the stats to begin
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public War() throws ParserConfigurationException, SAXException, IOException {
		//create a logger file to record every move in war into files
		fileHandler = new FileHandler("warLogger.xml", false);
		logger.addHandler(fileHandler);
		logger.setUseParentHandlers(false);
		fileHandler.setFormatter(new MyFormatter());

		//read the XNL file
		this.readXML();
	}

	
	/**
	 * Method to read the XML file and create objects from it
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void readXML() throws ParserConfigurationException, SAXException, IOException {
		// Get the DOM Builder Factory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Get the DOM Builder
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		Document document = builder.parse(ClassLoader.getSystemResourceAsStream("war.xml"));
		// Load and Parse the XML document
		// document contains the complete XML as a Tree.
		// Iterating through the nodes and extracting the data.
		NodeList rootNodeList = document.getDocumentElement().getChildNodes();
		//first we loop on 3 main array lists
		for (int i = 0; i < rootNodeList.getLength(); i++) {
			Node rootNode = rootNodeList.item(i);
			NodeList nodeList = rootNode.getChildNodes();// list of launchers
			//loop on each list to add launchers or destructors
			for (int j = 0; j < nodeList.getLength(); j++) {
				Node childNodeList = nodeList.item(j);
				this.addLauncherToArray(childNodeList, rootNode);
				//on each launcher or destructor we add a missile to it
				NodeList leafNode = childNodeList.getChildNodes();
				for (int k = 0; k < leafNode.getLength(); k++) {
					Node missile = leafNode.item(k);
					this.addMissileToArray(missile, j);				
				}
			}
		}
	}

	/**
	 * add a launcher or destructor to array
	 * @param launcher - a launcher or destructor we want to add to list
	 * @param rootNode - one of the 3 root array lists
	 * @throws SecurityException
	 * @throws IOException
	 */
	private void addLauncherToArray(Node launcher, Node rootNode) throws SecurityException, IOException {
		if (launcher instanceof Element) {
			String id = launcher.getAttributes().getNamedItem("id").getNodeValue();
			if (launcher.getNodeName().equals("launcher")) {
				boolean isHidden = Boolean.parseBoolean(launcher.getAttributes().getNamedItem("isHidden").getNodeValue());
				missileLaunchers.add(new Launcher(id, isHidden));
			} 
			else {
				String type = launcher.getAttributes().getNamedItem("type").getNodeValue();
				if ((rootNode.getNodeName().equals("missileDestructors"))) {
					missileDestructors.add(new Destructor<DestructedMissile>(id, type, new ArrayList<DestructedMissile>()));
				} 
				else {
					missileLauncherDestructors.add(new Destructor<DestructedLanucher>(id, type, new ArrayList<DestructedLanucher>()));
				}
			}
		}	
	}
	
	/**
	 * This method add a missile to the launcher's or destructor's array
	 * @param missile - the missile to add
	 * @param index - the index inside war's arrays
	 */
	private void addMissileToArray(Node missile, int index) {
		if (missile instanceof Element) {
			String id = missile.getAttributes().getNamedItem("id").getNodeValue();
			switch (missile.getNodeName()) {
			case "missile":
				// case one it is a missile so need to add it to
				// missile list
				String destination = missile.getAttributes().getNamedItem("destination").getNodeValue();
				int launchtime = Integer.parseInt(missile.getAttributes().getNamedItem("launchTime").getNodeValue());
				int flytime = Integer.parseInt(missile.getAttributes().getNamedItem("flyTime").getNodeValue());
				int damage = Integer.parseInt(missile.getAttributes().getNamedItem("damage").getNodeValue());
				//get the launcher and add missile to it
				Launcher launcher = missileLaunchers.get(index/2);
				launcher.addMissile(id, destination, launchtime, flytime, damage);
				break;	
			case "destructdMissile":
				//case 2 it is a missle to destruct missles need 
				//to add to destructors list
				int destructAfterLaunch = Integer.parseInt(missile.getAttributes().getNamedItem("destructAfterLaunch").getNodeValue());
				//get the destructor and then add missile destructor to it 
				DestructedMissile destructedM = new DestructedMissile(id, destructAfterLaunch);
				Destructor<DestructedMissile> destructor_m = missileDestructors.get(index/2);
				destructor_m.addDestructMissile(destructedM);
				break;			
			case "destructedLanucher":
				//case 3 it is a missle to destruct launchers need 
				//to add to destructors list
				int destructTime = Integer.parseInt(missile.getAttributes().getNamedItem("destructTime").getNodeValue());
				//get the destructor and then add missile launcher destructor to it
				DestructedLanucher destructedL = new DestructedLanucher(id, destructTime);
				Destructor<DestructedLanucher> destructor_l = missileLauncherDestructors.get(index/2);
				destructor_l.addDestructMissile(destructedL);
				break;
			}
		}		
	}
	/**
	 * This method start all the other threads 
	 * this is where all the war begins.
	 */
	public void run() {
		for(int i=0; i<missileLaunchers.size(); i++) {
			Launcher l = missileLaunchers.get(i);
			l.start();
		}
		/*
		Iterator<Launcher> iterator_launcher = missileLaunchers.iterator();
		while (iterator_launcher.hasNext()) {
			iterator_launcher.next().start();
		}

		Iterator<Destructor<DestructedLanucher>> iterator_destructL = missileLauncherDestructors.iterator();
		while (iterator_destructL.hasNext()) {
			iterator_destructL.next().start();
		}
		Iterator<Destructor<DestructedMissile>> iterator_destructM = missileDestructors.iterator();
		while (iterator_destructM.hasNext()) {
			iterator_destructM.next().start();
		}
		 */
	}
}
