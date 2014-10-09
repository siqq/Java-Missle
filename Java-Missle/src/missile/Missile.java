package missile;
import java.io.Serializable;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import launcher.Launcher;
import war.War;
import war.controller.WarEventListener;
import war.db.WarDBConnection;

public class Missile extends AbstractMissile implements Serializable   {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public enum Status {Waiting, Launched, Destroyed, Hit};   
    private static Logger 			logger;

    private transient List<WarEventListener>  allListeners;
    private String 					missileId;
    private String 					destination;
    private int 					flyTime;
    private int 					damage;
    private Launcher 				launcher;
    private Status					status;


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
	    int damage, FileHandler fileHandler, Launcher launcher , List<WarEventListener> allListeners) {
	super(launchTime, fileHandler);
	this.missileId = id;
	this.destination = destination;
	this.flyTime = flyTime;
	this.damage = damage;
	this.launcher = launcher;
	this.setStatus(Status.Waiting);
	this.allListeners = allListeners;
      //  this is dumb implementation. // not mine , those things ha been taken from avishay and dvir
        //still this is very problematic. try to add transient to every error. it tells the outputstream not to write that specific object
        //Ok, ill try to eork on it, Thans Bro
        //ill talk to you if there be anything else
        //ok lets just launch last time
	logger = Logger.getLogger("warLogger");
    }


    /**
     * @return id of missile 
     */
    public String getMissileId() {
	return missileId;
    }


    public String getDestination() {
	return destination;
    }

    /**
     * @return launch time
     */
    public int getLaunchTime() {
	return super.getDelayBeforeLaunch();
    }

    /**
     * @return fly time
     */
    public int getFlyTime() {
	return flyTime;
    }

    /**
     * @return damage
     */
    public int getDamage() {
	return damage;
    }

    /**
     * @return missile status 
     */
    public Status getStatus() {
	return status;
    }


    /**
     * set status
     * @param status
     */
    public void setStatus(Status status) {
	this.status = status;
    }

    /** 
     * Run missile 
     */
    public void run() {
	boolean reveal_status = false;
	try {
		WarDBConnection.addNewMissile(missileId,destination,damage,flyTime,status.toString());
	    sleep(getLaunchTime() * War.TIME_INTERVAL);

	    synchronized (launcher) {

		if (launcher.isRunning()) {
		    this.setStatus(Status.Launched);
		    WarDBConnection.updateMissileStatus(missileId, status.toString());
		    // make launcher not hidden for X amount of time
		    if (launcher.isHidden()) {
			launcher.revealYourSelf(); 
			reveal_status = true;
		    }
		    String print_log = "Missle "+ this.missileId 
			    + " was launched from launcher: "
			    + this.launcher.getLauncherId();
	//	    java.sql.Date sqlDate = Utils.utilDateToSqlDate(birthdate);
		    for (WarEventListener l : allListeners) {
			l.addedMissileToModelEvent(missileId,destination,damage,flyTime);
		    }
		    //					notify();
		    logger.log(Level.INFO, print_log, this);
//each launcher had missile so every missile must have launcher
		    //why listener though?
		    //to add things to gui and backward -- mvc
		    //your implementation is wrong. when you add a launcher or a missile you should call the function in the controller with the parameters
		    //for example l.addedMissileToModelEvent(missileId,destination,damage,flyTime);
		    try {
			for(int time = 0 ; time <= flyTime ; time++){
			    Thread.sleep(War.TIME_INTERVAL);
			    for (WarEventListener l : allListeners) {
				l.UpdatedMissileProgressToModelEvent(time, missileId , "missile",destination,damage,flyTime);
			    }
			}
			for (WarEventListener l : allListeners) {
			    l.DestroyMissileProgressBar(missileId , "missile");
			}
		    } catch (InterruptedException e) {
		    }	
		    destroyTarget();
		    if (reveal_status == true) {
			launcher.hideYourSelf(); // make launcher hide again
		    }
		}
	    }	
	} catch (InterruptedException e) {
	    //missile is destroyed
	    this.setStatus(Status.Destroyed);
	    WarDBConnection.updateMissileStatus(missileId, status.toString());
	    
	    if (reveal_status == true) {
		launcher.hideYourSelf(); // make launcher hide again
	    }
	} catch (SecurityException e) {

	} catch (Exception e) {
	    logger.log(Level.INFO, e.getMessage(), this);
	}
    }


    @Override
    public void destroyTarget() {
	// print to log that missile successfully hit targer

	String print_log = "Missle "+ this.missileId + " hit " 
		+ this.destination + " with " 
		+ this.damage + " damage";
	logger.log(Level.INFO, print_log, this);
	this.setStatus(Status.Hit);
	WarDBConnection.updateMissileStatus(missileId, status.toString());
    }

}
