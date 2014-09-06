package main;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import launcher.Destructor;
import launcher.Launcher;
import missile.AbstractMissile;
import missile.DestructedLanucher;
import missile.DestructedMissile;
import missile.Missile;
import org.xml.sax.SAXException;
import view.WarGui;
import war.War;
import war.WarUtility;
import war.controller.AbstractWarView;
import war.controller.WarController;

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
	public static void menu(final War war) {
		int option = 0;
		
		//thread that keep checking the status of war
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(War.TIME_INTERVAL);
//						checkEndOfWar(war);
					} catch (InterruptedException e) {
					}
				}	
			}
		}).start();
		
	    while (true) {
	    	
			System.out.println(
				      "press 1: to add new Missile/Launcher destructor\n"
					+ "press 2: to add new lanucher\n"
					+ "press 3: to lanuch missile\n"
					+ "press 4: to destroy Launcher\n"
					+ "press 5: to destroy missile\n"
					+ "press 6: to display statistics\n"
					+ "press 7: to end war and display statistics\n"
					+ "Please choose option");
			try {
				String stats;
				option = input.nextInt();
				input.nextLine();
				System.out.println();
				switch (option) {				
				case 5:
					destructMissile(war);
					break;
				case 6:
					stats = displayStatistics(war);
					System.out.println(stats);
					break;
				case 7:
					stats = displayStatistics(war);
					logger.log(Level.INFO, "end war\n" + stats);
					System.out.println(stats);
					System.exit(0);
					break;
				}

			} catch (InputMismatchException e) {
				System.err.println("The Input was Invalid... Please try again");	
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

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
    			String stats = displayStatistics(war);
    			logger.log(Level.INFO, "All launchers destroyed - end of war");
    			logger.log(Level.INFO, "end war\n" + stats);
    			System.out.println(stats);
    			System.exit(0);
    		}
    	}	
	}

	/**
	 * choose a destructor and select a missile to destruct
	 * @param war
	 * @throws Exception
	 */
	private static void destructMissile(War war) throws Exception {
		// first pick a destructor
		System.out.println("Choose destructor "
				+ "from the following Destructors list:");
		Vector<Destructor> destructors = war
				.getMissileDestructors();
		printDestructors(destructors);
		
		System.out.print("\nEnter your Choise: ");
		String destructor_id = input.nextLine();
		Destructor selected_destructor = WarUtility.getDestructorById(
				destructor_id, war, MISSILE);
		if (selected_destructor == null) {
			throw new Exception("Destructor does not exist");
		}
		// now choose it's target
		System.out.println("Choose a launcher to destruct "
				+ "from the following launchers list:");
		Vector<Launcher> launchers = war.getMissileLaunchers();
		printMissiles(launchers);
		
		System.out.print("\nEnter your Choise: ");
		String target_id = input.nextLine();
		Missile target = WarUtility.getMissileById(target_id, war);
		if (target == null) {
			throw new Exception("Target does not exist");
		}
		int destruct_time = (int) (TAKES_TIME_MIN + (Math.random() * 
				(TAKES_TIME_MAX - TAKES_TIME_MIN + 1)));
		DestructedMissile assigned_destructor = new DestructedMissile(target, destruct_time, 
				selected_destructor, selected_destructor.getFileHandler(), war.getListeners());
		selected_destructor.addDestructMissile(assigned_destructor);
	}

	/**
	 * print all Destructor's id from destructors array
	 * 
	 * @param destructors
	 */
	private static void printDestructors(Vector<Destructor> destructors) {
		for (Destructor d : destructors) {
			System.out.print("[" + d.getType() + " - " + d.getDestructorId() + "] ");
		}
	}

	/**
	 * print all active launcher's id from launchers array
	 * 
	 * @param launchers
	 */
	private static void printLaunchers(Vector<Launcher> launchers) {
		for (Launcher l : launchers) {
			if (l.isRunning()) {
				System.out.print(l.getLauncherId() + " ");
			}
		}
	}

	/**
	 * print all active missiles's id
	 * @param launchers
	 */
	private static void printMissiles(Vector<Launcher> launchers) {
		Launcher launcher;
		Missile missile;
		int launcher_size = launchers.size();
		for(int i = 0; i < launcher_size; i++) {
			launcher = launchers.get(i);
			for(int j = 0; j <launcher.getMissiles().size(); j++ ) {
				missile = launcher.getMissiles().get(j);
				if(missile.getStatus() == Missile.Status.Launched) {
					System.out.print(missile.getMissileId() + " ");
				}
			}
		}		
	}
	
	/**
	 * The method displays the status of war
	 * @param war
	 * @return string of statistic
	 */
	private static String displayStatistics(War war) {
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
		statistic = "The statistics of war is: \n"
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
		return statistic;
	}
}