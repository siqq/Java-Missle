import java.time.LocalDateTime;
import java.util.Vector;

public class WarUtility {
	
	private static final int HOUR = 24;
	private static final int MINUTE = 60;
	private static final int SECOND = 60;

	/**
	 * get id and search for missile with that id in missileLaunchers vector
	 * @param id
	 * @return wanted Missile from war
	 */
	public static Missile getMissileById(String id, War war) {
		Vector<Launcher> missileLaunchers = war.getMissileLaunchers();
		int size_launcher = missileLaunchers.size();
		for (int i = 0; i < size_launcher; i++) {
			Launcher l = missileLaunchers.elementAt(i);
			int size_missile = l.getMissiles().size();
			for (int j=0; j < size_missile; j++){
				Missile m = l.getMissiles().elementAt(j);
				if (id.equals(m.getMissileId())) {
					return m;
				}
			}
		}
		return null;
	}

	/**
	 * get id and search for Launcher with that id in missileLaunchers vector
	 * @param id
	 * @return wanted Launcher from war
	 */
	public static Launcher getLauncherById(String id, War war) {
		Vector<Launcher> missileLaunchers = war.getMissileLaunchers();
		int size_launcher = missileLaunchers.size();
		for (int i = 0; i < size_launcher; i++) {
			Launcher l = missileLaunchers.elementAt(i);
			if (id.equals(l.getLauncherId())) {
				return l;
			}
		}
		return null;
	}

	/**
	 * The method receives id of destructe launcher
	 * @param id
	 * @param war
	 * @return destructe launcher by id
	 */
	@SuppressWarnings("unchecked")
	public static <E> Destructor<E> getDestructorById(String id, War war, String type) {
		Vector<E> destructors = null;
		int size_launcher = 0;
		if (type.equals("launcher")) {
			destructors = (Vector<E>) war.getMissileLauncherDestructors();
		}
		else {
			destructors = (Vector<E>) war.getMissileDestructors();
		}
		size_launcher = destructors.size();
		for (int i = 0; i < size_launcher; i++) {
			Destructor<E> d = (Destructor<E>) destructors.elementAt(i);
			if (id.equals(d.getDestructorId())) {
				return d;
			}
		}
		return null;
		
//		Object destruct_type = type;
//		Vector<E> destructors = null;
//		int size_launcher = 0;
//		if (destruct_type instanceof DestructedLanucher) {
//			destructors = (Vector<E>) war.getMissileLauncherDestructors();
//			
//		}
//		else {
//			destructors = (Vector<E>) war.getMissileDestructors();
//		}
//		size_launcher = destructors.size();
//		for (int i = 0; i < size_launcher; i++) {
//			Destructor<E> d = (Destructor<E>) destructors.elementAt(i);
//			if (id.equals(d.getDestructorId())) {
//				return d;
//			}
//		}
//		return null;
	}

	/**
	 * The method receives current time
	 * @param current_time
	 * @return string of difference with start time of war
	 */
	public static String currentTime(LocalDateTime current_time) {
		String time;
		int hour, minute, second;
		
		second = current_time.getSecond() - Program.start_time.getSecond();
		minute = current_time.getMinute() - Program.start_time.getMinute();
		hour = current_time.getHour() - Program.start_time.getHour();
		
		if(second < 0) {
			second = SECOND - Program.start_time.getSecond() 
					+ current_time.getSecond();
			minute--;
		}
		
		if(minute < 0) {
			minute += MINUTE - Program.start_time.getMinute() 
					+ current_time.getMinute(); 
			hour--;
		}
		
		if(hour < 0) {
			hour += HOUR - Program.start_time.getHour() 
					+ current_time.getHour();
		}
		time = hour + ":" + minute + ":" + second;
		return time;		
	}
}
