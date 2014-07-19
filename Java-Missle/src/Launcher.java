import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class Launcher extends Thread{
	private String id;
	private boolean isHidden;
	private ArrayList<Missile> missiles;
	private FileHandler fileHandler;
	private Logger logger = Logger.getLogger("Launcher_"+this.id);
	
	
	public Launcher(String id, boolean isHidden, ArrayList<Missile> missiles) throws SecurityException, IOException {
		super();
		this.id = id;
		this.isHidden = isHidden;
		this.missiles = missiles;
		this.setLogger();
	}
	public Launcher(String id, boolean isHidden) throws SecurityException, IOException {
		super();
		this.id = id;
		this.isHidden = isHidden;
		this.missiles = new ArrayList<Missile>();
		this.setLogger();
	}
	
	public void setLogger() throws SecurityException, IOException {
		fileHandler = new FileHandler("Launcher_"+this.id+".xml", false);
		logger.addHandler(fileHandler);
		logger.setUseParentHandlers(false);
		fileHandler.setFormatter(new MyFormatter());
	}
	
	public void addMissile(String id2, String destination, int launchtime, int flytime, int damage) {
		Missile missile = new Missile(id2, destination, launchtime, flytime, damage, logger);
		this.missiles.add(missile);
	}
	@Override
	public String toString() {
		return "Launcher [id=" + id + ", isHidden=" + isHidden + ", missiles=" + missiles + "]";
	}
}
