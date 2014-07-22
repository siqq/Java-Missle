import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TheLogger {
	private static Logger logger = Logger.getLogger("warLog");
	private static Object key = new Object();
	
	//everything need to be in war log also so we set up first a general log file
	static {
		try {
			FileHandler file = new FileHandler("warLog.txt");
			file.setFormatter(new MyFormatter());
			logger.addHandler(file);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * get string and write it to log file
	 * 
	 * @param file - a file handler to write the log to it
	 * @param level - the level of log (INFO/SEVERE/WARNING)
	 * @param string - the string we want to write to log file
	 */
	public static void printLog(FileHandler file, String level, String string) {
		synchronized(key) {
			logger.addHandler(file);
			logger.setUseParentHandlers(false);
			file.setFormatter(new MyFormatter());
			//we accept only 3 level types
			switch (level) {
			case "INFO":
				logger.log(Level.INFO, string);
				break;
			case "WARNING":
				logger.log(Level.WARNING, string);
				break;
			case "SEVERE":
				logger.log(Level.SEVERE, string);
				break;
			default:
				break;
			}
			logger.removeHandler(file);
		}	
	}
	
}
