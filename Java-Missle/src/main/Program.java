package main;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import launcher.Launcher;
import missile.Missile;

import org.xml.sax.SAXException;

import view.WarGui;
import war.War;
import war.controller.AbstractWarView;
import war.controller.WarController;
import war.db.WarDBConnection;

public class Program {
	public static final String 	LAUNCHER = "launcher";
	public static final String 	MISSILE = "missile";
	public static final int 	TAKES_TIME_MIN = 1;
	public static final int 	TAKES_TIME_MAX = 10;
			
	private static Logger 		logger = Logger.getLogger("warLogger");
	private static Scanner 		input = new Scanner(System.in);

	public static void main(String[] args) {
		
		try {
//			War warModel = xml.readXML();
		    new WarDBConnection();
//			WarDBConnection.clearWarDataBase();
			War warModel = new War();
			AbstractWarView viewGui = new WarGui();	
						
			WarController controller = new WarController(warModel,viewGui);
			XMLparser xml = new XMLparser(controller);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** 
	 * Print menu 
	 */

	private static void checkEndOfWar(War war) {
		int counter = 0;
		int size = war.getMissileLaunchers().size();
		//test if all launchers has been destroyed 
    	if (size > 0) {
    		for (Launcher l : war.getMissileLaunchers() ) {
    			if (l.isRunning() == false) {
    				counter++;
    				//loop to check if there is a missile in air
    				int size_missiles = l.getMissiles().size();
    				for (int j = 0; j < size_missiles; j++) {
    					Missile m = l.getMissiles().get(j);
    					//once we find a missile in air we dont stop the war
    					if (m.getStatus() == Missile.Status.Launched) {
    						return;
    					}
    				}
    			}
    		}
    		if (counter == size) {
    			/*String stats = displayStatistics(war);
    			logger.log(Level.INFO, "All launchers destroyed - end of war");
    			logger.log(Level.INFO, "end war\n" + stats);
    			System.out.println(stats);
    			System.exit(0);*/
    		}
    	}	
	}
	
	/**
	 * The method displays the status of war
	 * @param war
	 * @return string of statistic
	 */
	private static void displayStatistics(War war) {
		int total_launched_missiles = 0;
		int total_destroyed_missiles = 0;
		int total_missiles_hit = 0;
		int total_destroyed_launchers = 0;
		int total_damage = 0;
		int size_launcher = war.getMissileLaunchers().size();
		int size_missiles;

		Launcher l;
		Missile m;
		String statistic = ""; 
		
		for (int i = 0; i < size_launcher; i++) {
			l = war.getMissileLaunchers().get(i);
			if (l.isRunning() == false) {
				total_destroyed_launchers++;
			}
			size_missiles = l.getMissiles().size();
			for (int j = 0; j < size_missiles; j++) {
				m = l.getMissiles().get(j);
				if (m.getStatus() != Missile.Status.Waiting) {
					total_launched_missiles++;
					if(m.getStatus() == Missile.Status.Hit) {
						total_missiles_hit++;
						total_damage += m.getDamage();
					} else if (m.getStatus() == Missile.Status.Destroyed){
						total_destroyed_missiles++;
					}
				}
			}
		}
/*		statistic = "The statistics of war is: \n"
				+ "The number of missiles launched:\t"
				+ total_launched_missiles + "\n"
				+ "The number of missiles destroyed:\t"
				+ total_destroyed_missiles + "\n"
				+ "The number of missiles that hit:\t" 
				+ total_missiles_hit
				+ "\n" + "The number launchers were destroyed:\t"
				+ total_destroyed_launchers + "\n"
				+ "The total value of damage caused:\t" 
				+ total_damage + "\n";
		return statistic;*/
	}
}