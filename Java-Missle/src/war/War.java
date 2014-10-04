package war;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import launcher.Destructor;
import launcher.Launcher;
import logger.LogFormatter;
import missile.AbstractMissile;
import missile.DestructedLanucher;
import missile.DestructedMissile;
import missile.Missile;

import org.xml.sax.SAXException;

import war.controller.WarController;
import war.controller.WarEventListener;
import war.db.WarDBConnection;


/**
 * A war class that holds all the war objects
 * 
 * @author first part: Avishay and Dvir
 * @author 2nd and 3rd parts: Andrey & Gal
 * 
 */

public class War extends Thread {
    public static final int 		 TIME_INTERVAL = 1000; 	//sleep time for threads
    public static final double 	   	 SUCCESS_RATE = 0.2;	//success rate for destructors
    public static final String 		 LAUNCHER = "launcher";
    public static final String 		 MISSILE = "missile";
    public static final int 		 TAKES_TIME_MIN = 1;
    public static final int 		 TAKES_TIME_MAX = 10;

    private static Logger 			 logger;

    private Vector<Launcher> 		 missileLaunchers = new Vector<>();
    private Vector<Destructor>	 	 missileDestructors = new Vector<>();
    private Vector<Destructor> 		 missileLauncherDestructors = new Vector<>();
    private Vector<WarEventListener> listeners;

    /**
     * Constructor for the war which take from XML the stats to begin
     * @param missileLaunchers2 
     * @param missileLauncherDestructors2 
     * @param missileDestructors2 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public War(Vector<Destructor> missileDestructors, 
	    Vector<Destructor> missileLauncherDestructors, 
	    Vector<Launcher> missileLaunchers) 
		    throws ParserConfigurationException, SAXException, IOException {

	FileHandler fileHandler = new FileHandler("war_log.txt");
	fileHandler.setFormatter(new LogFormatter());
	logger = Logger.getLogger("warLogger");
	logger.addHandler(fileHandler);
	logger.setUseParentHandlers(false);
	listeners = new Vector<WarEventListener>();
	this.missileLaunchers = missileLaunchers;
	this.missileDestructors = missileDestructors;
	this.missileLauncherDestructors = missileLauncherDestructors;
    }

    public War() throws SecurityException, IOException {
	FileHandler fileHandler = new FileHandler("war_log.txt");
	fileHandler.setFormatter(new LogFormatter());
	logger = Logger.getLogger("warLogger");
	logger.addHandler(fileHandler);
	logger.setUseParentHandlers(false);
	listeners = new Vector<WarEventListener>();
    }

    public Vector<Launcher> getMissileLaunchers() {
	return missileLaunchers;
    }

    public Vector<Destructor> getMissileDestructors() {
	return missileDestructors;
    }

    public Vector<Destructor> getMissileLauncherDestructors() {
	return missileLauncherDestructors;
    }

    public void addLauncherDestructor(Destructor desctructor) {
	this.missileLauncherDestructors.add(desctructor);	
	fireAddLauncherDestructorEvent(desctructor);
	desctructor.start();
    }

    public void fireAddLauncherDestructorEvent(Destructor desctructor) {
	for (WarEventListener l : listeners) {
	    l.addedLuncherDestructorToModelEvent(desctructor.getDestructorId(),desctructor.getType());
	}

    }

    public void addMissileDestructor(Destructor desctructor) {
	this.missileDestructors.add(desctructor);	
	fireAddMissileDestructorEvent(desctructor);
	desctructor.start();
    }

    public void fireAddMissileDestructorEvent(Destructor desctructor) {
	for (WarEventListener l : listeners) {
	    l.addedMissileDestructorToModelEvent(desctructor.getDestructorId(),desctructor.getType());
	}

    }


    public Vector<WarEventListener> getListeners() {
	return listeners;
    }

    public void addLauncher(Launcher launcher) {
	this.missileLaunchers.add(launcher);
	fireAddLauncherEvent(launcher);
	launcher.start();
    }



    public void fireAddLauncherEvent(Launcher launcher) {
	for (WarEventListener l : listeners) {
	    l.addedLauncherToModelEvent(launcher.getLauncherId());
	}

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
		Destructor d = missileDestructors.get(i);
		d.start();
	    }

	    size = missileLauncherDestructors.size();
	    for (int i = 0; i < size; i++) {
		Destructor d =	missileLauncherDestructors.get(i);
		d.start();
	    }
	}
    }



    public void registerListener(WarController warController) {
	listeners.add(warController);

    }

    public void addLauncher(String id) throws Exception {
	if ((id.isEmpty()) || (WarUtility.getLauncherById(id, this) != null)) {
	    throw new Exception("This Id is empty or already exist");
	}
	boolean is_hidden = (Math.round(Math.random()) == 1) ? true : false;
	addLauncher(new Launcher(id, is_hidden , listeners));
	int stat = (is_hidden == true) ? 1 : 0; 
	WarDBConnection.addNewLauncher(id, stat);
    }



    public void addDestructor(String id, String type) throws SecurityException, IOException {

	if (WarUtility.getDestructorById(id, this, type) != null) {
	    // tell controller the id already exists
	}
	if (type.equals("Plane") || type.equals("Ship")) {
	    Destructor desctructor = new Destructor(
		    id, type, new Vector<AbstractMissile>());
	    addLauncherDestructor(desctructor);
	} else if (type.equals("Iron Dome")) {
	    Destructor desctructor = new Destructor(
		    id, type, new Vector<AbstractMissile>());
	    addMissileDestructor(desctructor);
	} 
	WarDBConnection.addNewDestructor(id, type);
    }

    public void addMissile(String name, String dest, String damage,
	    String flyTime,String launcherId) {
	int damageInt = Integer.parseInt(damage);
	int flyTimeInt = Integer.parseInt(flyTime);
	for(Launcher launcher : missileLaunchers){
	    if(launcherId.equalsIgnoreCase(launcher.getLauncherId())){
		launcher.addMissile(name, dest, 0, flyTimeInt, damageInt);
		WarDBConnection.addNewMissile(name, dest, damageInt, flyTimeInt, "wait");
	    }
	}   
    }



    public void destroyLauncher(String destructor_id, String target_id) {		

	Destructor selected_destructor = 
		WarUtility.getDestructorById(destructor_id, this, LAUNCHER);
	if (selected_destructor == null) {
	    //		throw new Exception("Destructor does not exist");
	}
	Launcher target =  WarUtility.getLauncherById(target_id,this);
	if (target == null) {
	    //		throw new Exception("Target does not exist");
	}
	int destruct_time = (int) (TAKES_TIME_MIN + (Math.random() * 
		(TAKES_TIME_MAX - TAKES_TIME_MIN + 1)));				
	// assign destructor to destruct the launcher

	DestructedLanucher assigned_destructor = new DestructedLanucher(target, destruct_time, 
		selected_destructor, selected_destructor.getFileHandler(),listeners);

	selected_destructor.addDestructMissile(assigned_destructor);
	for (WarEventListener l : listeners) {
	    l.addedLauncherToDestroy(destructor_id,target_id,destruct_time);
	}


    }

    public void startMissileInterception(String missileId , String ironDome) {
	Destructor selected_destructor = WarUtility.getDestructorById(
		ironDome, this, MISSILE);
	int destruct_time = (int) (TAKES_TIME_MIN + (Math.random() * 
		(TAKES_TIME_MAX - TAKES_TIME_MIN + 1)));
	Missile target = WarUtility.getMissileById(missileId, this);
	DestructedMissile assigned_destructor = new DestructedMissile(target, destruct_time, 
		selected_destructor, selected_destructor.getFileHandler(), this.getListeners());
	selected_destructor.addDestructMissile(assigned_destructor);
	for (WarEventListener l : listeners) {
	    l.addedMissileToDestroy(ironDome,missileId,destruct_time);
	}

    }

    public void addMessageToClient(String id, String dest, String damage,
	    String flyTime , String launcherName) {
	Socket socket = null;
	ObjectInputStream fromNetInputStream;
	ObjectOutputStream toNetOutputStream;

	try {
	    socket = new Socket("localhost", 7000);			
	    toNetOutputStream = new ObjectOutputStream(socket.getOutputStream());
	    fromNetInputStream = new ObjectInputStream(socket.getInputStream());

	    boolean fContinue = true;
	    String text = (String)fromNetInputStream.readObject();
	    System.out.println("Recieved from server: " + text);
	    do  {
		if(id == null){ // request to add launcher from server

		    Launcher launcher = new Launcher(launcherName, false, listeners);
		    toNetOutputStream.writeObject(launcher);
		}
		else{
		    for(Launcher launcher : missileLaunchers){
			if(launcherName.equalsIgnoreCase(launcher.getLauncherId())){
			    Missile missile = launcher.CreatWithoutAddMissile(id, dest, 0, flyTime, damage);
			    toNetOutputStream.writeObject(launcher);
			    toNetOutputStream.writeObject(missile);
			}
		    }  
		    //			    Missile missile = launcher.addMissile(name, dest, 0, flyTimeInt, damageInt);
		}

//		Point readPoint = (Point)fromNetInputStream.readObject();

//		if (readPoint == null)
//		    fContinue = false;
//		else
//		    System.out.println(new Date() + " --> Recieved from server: "
//			    + readPoint.toString());
	    } while (fContinue);

	} catch (Exception e) {	System.out.println("*** " + e.getMessage());
	} finally {
	    try {
		socket.close();
		System.out.println("Client said goodbye..");
	    } catch (IOException e) {}
	}
    }



}




