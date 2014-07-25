import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Launcher extends Thread {
	
	private static Logger logger = Logger.getLogger("warLogger");
	
	private ArrayList<Object> arr = new ArrayList<Object>();
	private String 				id;
	private boolean 			isHidden;
	private boolean 			isRunning = true;
	private ArrayList<Missile> 	missiles;
	private Lock 				locker = new ReentrantLock();
	private CountDownLatch 		latch;
	private FileHandler 		fileHandler;

	public Launcher(String id, boolean isHidden, ArrayList<Missile> missiles)
			throws SecurityException, IOException {
		super();
		this.id = id;
		this.isHidden = true;
//		this.isRunning = true;
		this.missiles = missiles;
//		this.locker = new ReentrantLock();
		this.fileHandler = new FileHandler("Launcher_" + this.id + ".txt", false);
		fileHandler.setFilter(new ObjectFilter(this));
		fileHandler.setFormatter(new MyFormatter());
		
	}

	public Launcher(String id, boolean isHidden) throws SecurityException,
			IOException {
		super();
		this.id = id;
		this.isHidden = isHidden;
		this.missiles = new ArrayList<Missile>();
		this.fileHandler = new FileHandler("Launcher_" + this.id + ".txt", false);
		fileHandler.setFilter(new ObjectFilter(this));
		fileHandler.setFormatter(new MyFormatter());
		
		
	}

	@Override
	public void run() {
		arr.add(this);
		Iterator<Missile> iterator = missiles.iterator();
		while (iterator.hasNext() && isRunning) {
			Missile missile = iterator.next();
			arr.add(missile);
			logger.log(Level.INFO, "Launching Missle: " + missile.getMissileId(), this);
//			TheLogger.printLog(this.fileHandler, "INFO", "Launching Missle: "
//							 + missile.getMissileId());
			missile.start();
			try {
				latch = new CountDownLatch(1);
				missile.addLocker(locker, latch);
				latch.await();		// wait untill the missile will hit or destroy

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void addMissile(String id2, String destination, int launchtime,
			int flytime, int damage) {
		Missile missile = new Missile(id2, destination, launchtime, flytime,
				damage, this.id, this.fileHandler);
		this.missiles.add(missile);
	}

	@Override
	public String toString() {
		return "Launcher [id=" + id + ", isHidden=" + isHidden + ", missiles="
				+ missiles + "]";
	}
}
