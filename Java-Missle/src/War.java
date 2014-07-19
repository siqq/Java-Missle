import java.io.IOException;
import java.util.ArrayList;
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

public class War extends Thread {
	private ArrayList<Launcher> missileLaunchers = new ArrayList<>();
	private ArrayList<Destructor<DestructedMissile>> missileDestructors = new ArrayList<>();
	private ArrayList<Destructor<DestructedLanucher>> missileLauncherDestructors = new ArrayList<>();
	private FileHandler fileHandler;
	private Logger logger = Logger.getLogger("warLogger");

	public War(ArrayList<Launcher> missleLaunchers, ArrayList<Destructor<DestructedMissile>> missileDestructors,
			ArrayList<Destructor<DestructedLanucher>> missileLauncherDestructors) {
		super();
		this.missileLaunchers = missleLaunchers;
		this.missileDestructors = missileDestructors;
		this.missileLauncherDestructors = missileLauncherDestructors;
	}

	public War() throws ParserConfigurationException, SAXException, IOException {
		fileHandler = new FileHandler("warLogger.xml", false);
		logger.addHandler(fileHandler);
		fileHandler.setFormatter(new MyFormatter());
		
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

		// get first list
		for (int i = 0; i < rootNodeList.getLength(); i++) {
			Node rootNode = rootNodeList.item(i);
			NodeList nodeList = rootNode.getChildNodes();// list of launchers
																		
			for (int j = 0; j < nodeList.getLength(); j++) {
				Node childNodeList = nodeList.item(j);
				
				if (childNodeList instanceof Element) {
					String id = childNodeList.getAttributes().getNamedItem("id").getNodeValue();
					if (childNodeList.getNodeName().equals("launcher")) {
						boolean isHidden = Boolean.parseBoolean(childNodeList.getAttributes().getNamedItem("isHidden").getNodeValue());
						missileLaunchers.add(new Launcher(id, isHidden));
						logger.log(Level.SEVERE, missileLaunchers.get(j/2).toString());
					} 
					else {
						String type = childNodeList.getAttributes().getNamedItem("type").getNodeValue();
						if ((rootNode.getNodeName().equals("missileDestructors"))) {
							missileDestructors.add(new Destructor<DestructedMissile>(id, type, new ArrayList<DestructedMissile>()));
							logger.log(Level.SEVERE, missileDestructors.get(j/2).toString());
						} 
						else {
							missileLauncherDestructors.add(new Destructor<DestructedLanucher>(id, type, new ArrayList<DestructedLanucher>()));
							logger.log(Level.SEVERE, missileLauncherDestructors.get(j/2).toString());

						}
					}
				}
				NodeList leafNode = childNodeList.getChildNodes();
				for (int k = 0; k < leafNode.getLength(); k++) {
					
					Node missile = leafNode.item(k);
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
							Launcher launcher = missileLaunchers.get(j/2);
							launcher.addMissile(id, destination, launchtime, flytime, damage);
							missileLaunchers.add(j/2, launcher);
							break;
							
						case "destructdMissile":
							//case 2 it is a missle to destruct missles need 
							//to add to destructors list
							int destructAfterLaunch = Integer.parseInt(missile.getAttributes().getNamedItem("destructAfterLaunch").getNodeValue());
							//get the destructor and then add missile destructor to it 
							DestructedMissile destructedM = new DestructedMissile(id, destructAfterLaunch);
							Destructor<DestructedMissile> destructor_m = missileDestructors.get(j/2);
							destructor_m.addDestructMissile(destructedM);
							missileDestructors.add(j/2, destructor_m);
							break;
							
						case "destructedLanucher":
							//case 3 it is a missle to destruct launchers need 
							//to add to destructors list
							int destructTime = Integer.parseInt(missile.getAttributes().getNamedItem("destructTime").getNodeValue());
							//get the destructor and then add missile launcher destructor to it
							DestructedLanucher destructedL = new DestructedLanucher(id, destructTime);
							Destructor<DestructedLanucher> destructor_l = missileLauncherDestructors.get(j/2);
							destructor_l.addDestructMissile(destructedL);
							missileLauncherDestructors.add(j/2, destructor_l);
							break;

						}
					}
				}
			}
		}
	}
}
