import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TheLogger {
	static FileHandler fileHandler;
	static Logger logger = Logger.getLogger("warLog");
	
	
	public TheLogger(FileHandler fileHandler) {
		super();
		try {
			TheLogger.fileHandler = new FileHandler("fileHandler.toString()"+".xml", false);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.addHandler(fileHandler);
		logger.setUseParentHandlers(false);
		TheLogger.fileHandler.setFormatter(new MyFormatter());
	}
	
	static void printLog(String string) {
		fileHandler.setFormatter(new MyFormatter());
		logger.log(Level.INFO, string);
	}
	
}
