import java.io.IOException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Program {
	private static final String LAUNCHER = "launcher";
	private static final String MISSILE = "missile";

	private static Logger logger = Logger.getLogger("warLogger");
	private static Scanner input = new Scanner(System.in);
	public static LocalDateTime start_time;

	public static void main(String[] args) {
		try {
			XMLparser xml = new XMLparser();
			War war = xml.readXML();
			start_time = LocalDateTime.now();
			war.start();
			menu(war);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** Print menu */
	public static void menu(War war) throws SecurityException, IOException {
		while (true) {
			System.out.println("press 1: to add new lanucher destructor \n"
					+ "press 2: to add new missile destructor \n"
					+ "press 3: to add new lanucher\n"
					+ "press 4: to lanuch missile\n"
					+ "press 5: to destroy Launcher\n"
					+ "press 6: to destroy missile\n"
					+ "press 7: to display statistics\n"
					+ "press 8: to end war and display statistics\n"
					+ "Please choose option");
			int option = 0;
			try {
				option = input.nextInt();
				input.nextLine();
				switch (option) {
				case 1:
					addNewDestructor(war);
					break;
				case 2:
					addNewDestructor(war);
					break;
				case 3:
					addNewLauncher(war);
					break;
				case 4:
					launchMissile(war);
					break;
				case 5:
					destructLauncherOrMissile(war, LAUNCHER);
					break;
				case 6:
					destructLauncherOrMissile(war, MISSILE);
					break;
				case 7:
					displayStatistics(option);
					break;
				case 8:
					displayStatistics(option);
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("The Input was Invalid... Please try again");
				input.nextLine();
			}
		}
	}

	/**
	 * Add new Destructor (Iron Dome/Plane/Ship)
	 * 
	 * @param war
	 * @throws InputMismatchException
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void addNewDestructor(War war) throws InputMismatchException,
			SecurityException, IOException {
		System.out.print("Please Insert Id: ");
		String id = input.nextLine();
		System.out.print("Please Insert Type(Plane/Ship) : ");
		String type = input.nextLine();
		if (type.equals("Plane") || type.equals("Ship")) {
			Destructor<DestructedLanucher> desctructor = new Destructor<DestructedLanucher>(
					id, type, new Vector<DestructedLanucher>());
			war.addLauncherDestructor(desctructor);
		} else if (type.equals("Iron Dome")) {
			Destructor<DestructedMissile> desctructor = new Destructor<DestructedMissile>(
					id, type, new Vector<DestructedMissile>());
			war.addMissileDestructor(desctructor);
		} else {
			throw new InputMismatchException();
		}
	}

	/**
	 * add new launcher to war
	 * 
	 * @param war
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void addNewLauncher(War war) throws SecurityException,
			IOException {
		System.out.print("Please Insert Id: ");
		String id = input.nextLine();
		boolean is_hidden = ((int) (Math.random()) == 1) ? true : false;
		war.addLauncher(new Launcher(id, is_hidden));
	}

	/**
	 * search for launcher and add missile to it from user's input
	 * 
	 * @param war
	 * @throws InputMismatchException
	 */
	public static void launchMissile(War war) throws InputMismatchException {
		// choose a launcher from launcher list
		System.out.println("Choose launcher "
				+ "from the following Launchers list:");
		Vector<Launcher> launchers = war.getMissileLaunchers();
		printLaunchers(launchers);
		System.out.print("\nEnter your Choise: ");
		// input.next(); //clean buffer
		String launcher_id = input.nextLine();
		// find selected launcher so we can add missile to it
		Launcher selected_launcher = WarUtility.getLauncherById(launcher_id,
				war);
		if (selected_launcher == null) {
			throw new InputMismatchException();
		}
		// now create new missile from user input
		System.out.print("Please insert Missile id: ");
		String missile_id = input.nextLine();
		System.out.print("Please insert your destination: ");
		String destination = input.nextLine();
		System.out.print("Please insert fly time: ");
		int fly_time = input.nextInt();
		System.out.print("Please insert potential damage: ");
		int damage = input.nextInt();

		selected_launcher.addMissile(missile_id, destination, 0, fly_time,
				damage);
		War.total_launched_missiles++;

	}

	/**
	 * choose a destructor and select a launcher to destruct
	 * 
	 * @param war
	 * @param option choose what is the target : LAUNCHER or MISSILE
	 */
	private static <E> void destructLauncherOrMissile(War war, String option) {
		// first pick a destructor
		System.out.println("Choose destructor "
				+ "from the following Destructors list:");
		if (option.equals(LAUNCHER)) {
			Vector<Destructor<DestructedLanucher>> destructors = war
					.getMissileLauncherDestructors();
			printDestructors(destructors);
		} else if (option.equals(MISSILE)) {
			Vector<Destructor<DestructedMissile>> destructors = war
					.getMissileDestructors();
			printDestructors(destructors);
		}

		System.out.print("\nEnter your Choise: ");
		String destructor_id = input.nextLine();
		Destructor<E> selected_destructor = WarUtility.getDestructorById(
				destructor_id, war);
		if (selected_destructor == null) {
			throw new InputMismatchException();
		}

		// now choose it's target
		System.out.println("Choose a launcher to destruct "
				+ "from the following Launchers list:");
		Vector<Launcher> launchers = war.getMissileLaunchers();
		printLaunchers(launchers);

		System.out.print("\nEnter your Choise: ");
		String target_id = input.nextLine();
		Thread target = (option.equals(LAUNCHER)) ? WarUtility.getLauncherById(
				target_id, war) : WarUtility.getMissileById(target_id, war);
		if (target == null) {
			throw new InputMismatchException();
		}
		// assign destructor to destruct the launcher
		E assigned_destructor = (E) ((option.equals(LAUNCHER)) ? new DestructedLanucher(
				(Launcher) target, 0) : new DestructedMissile((Missile) target,	0));
		selected_destructor.addDestructMissile(assigned_destructor);
	}

	/**
	 * print all Destructor's id from destructors array
	 * 
	 * @param destructors
	 */
	private static <E> void printDestructors(Vector<Destructor<E>> destructors) {
		for (Destructor<E> d : destructors) {
			System.out.print("[" + d.getType() + " - " + d.getDestructorId()
					+ "] ");
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
	 * The method checks the option that receives if option = 7 - only display
	 * statistics of war if option = 8 - end war and display statistics of war
	 * 
	 * @param option
	 */
	private static void displayStatistics(int option) {
		String statistic = "The statistics of war is: \n"
				+ "The number of missiles launched:\t"
				+ War.total_launched_missiles + "\n"
				+ "The number of missiles destroyed:\t"
				+ War.total_destroyed_missiles + "\n"
				+ "The number of missiles that hit:\t" + War.total_missiles_hit
				+ "\n" + "The number of missile were destroyed:\t"
				+ War.total_destroyed_launchers + "\n"
				+ "The total value of damage caused:\t" + War.total_damage
				+ "\n";
		System.out.println(statistic);
		if (option == 8) {
			logger.log(Level.INFO, "end war\n" + statistic);
			System.exit(0);
		}
	}

}