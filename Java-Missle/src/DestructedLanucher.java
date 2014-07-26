import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class DestructedLanucher extends Thread{
	
	private static Logger logger = Logger.getLogger("warLogger");
	
	private String 			id;
	private int 			destructTime;
	private FileHandler		fileHandler;
	private Lock 			locker;
	private CountDownLatch 	latch;
	
	public DestructedLanucher(String id, int destructTime) {
		super();
		this.id = id;
		this.destructTime = destructTime;
	}
	
	public void addFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
		ObjectFilter filter = (ObjectFilter) fileHandler.getFilter();
		filter.addFilter(this);
		fileHandler.setFormatter(new MyFormatter());

		logger.addHandler(this.fileHandler);
	}
	
	public void addLocker(Lock locker, CountDownLatch latch) {
		this.locker = locker;
		this.latch = latch;		
	}
}
