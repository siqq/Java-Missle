import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Program {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			XMLparser xml = new XMLparser();
			War war = xml.readXML();
			war.start();
			manu(war);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void manu(War war) throws SecurityException, IOException {
		while (true) {
			System.out.println("Please choose option");
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
					destructLauncher(war);
					break;
				case 9:
					// finish war
					System.exit(0);
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
		System.out
				.println("Choose launcher from the following Launchers list:");
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

		selected_launcher.addMissile(missile_id, destination, 0, fly_time, damage);

	}

	/**
	 * pick up a destructor and select a launcher to destruct
	 * @param war
	 */
	private static void destructLauncher(War war) {
		//first pick a destructor
		System.out
				.println("Choose destructor from the following Destructors list:");
		Vector<Destructor<DestructedLanucher>> destructors = war
				.getMissileLauncherDestructors();
		printDestructors(destructors);	
		System.out.print("\nEnter your Choise: ");
		String destructor_id = input.nextLine();
		Destructor<DestructedLanucher> selected_destructor = WarUtility.getDestructorById(destructor_id, war);
		if (selected_destructor == null) {
			throw new InputMismatchException();
		}
		//now choose it's target
		System.out
				.println("Choose a launcher to destruct from the following Launchers list:");
		Vector<Launcher> launchers = war.getMissileLaunchers();
		printLaunchers(launchers);
		System.out.print("\nEnter your Choise: ");
		String launcher_id = input.nextLine();
		Launcher selected_launcher = WarUtility.getLauncherById(launcher_id, war);
		if (selected_launcher == null) {
			throw new InputMismatchException();
		}
		//assign destructor to destruct the launcher
		selected_destructor.addDestructMissile(new DestructedLanucher(selected_launcher, 0));
	}

	/**
	 * print all Destructor's id from destructors array
	 * @param destructors
	 */
	private static <E> void printDestructors(Vector<Destructor<E>> destructors) {
		for (Destructor<E> d : destructors) {
			System.out.print("[" + d.getType() + " - " + d.getDestructorId()
					+ "] ");
		}
	}

	/**
	 * print all launcher's id from launchers array
	 * 
	 * @param launchers
	 */
	private static void printLaunchers(Vector<Launcher> launchers) {
		for (Launcher l : launchers) {
			System.out.print(l.getLauncherId() + " ");
		}

	}
}