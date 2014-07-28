import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Destructor<E> extends Thread{
	
	private static Logger logger = Logger.getLogger("warLogger");
	
	private String 				id;
	private String 				type;
	private Vector<E> 			Destructed;
	private FileHandler 		fileHandler;
	private Lock 				locker;
	private CountDownLatch 		latch;
	
	public Destructor(String id, String type, Vector<E> destructed) throws SecurityException, IOException {
		super();
		this.id = id;
		this.type = type;
		this.Destructed = destructed;
		
		fileHandler = new FileHandler("Destructor_"+this.id+".txt", false);
		fileHandler.setFilter(new ObjectFilter(this));
		fileHandler.setFormatter(new MyFormatter());
		logger.addHandler(fileHandler);
		
	} 

	public void addDestructMissile(E destruct) {
		
		if (destruct instanceof DestructedMissile) {
			System.out.println(1);
			((DestructedMissile)(destruct)).addFileHandler(this.fileHandler);
		}
		else if (destruct instanceof DestructedLanucher) {
			((DestructedLanucher)(destruct)).addFileHandler(this.fileHandler);
		}
		this.Destructed.add(destruct);
	}
	
	@Override
	public void run() {
		Iterator<E> iterator = Destructed.iterator();
		while (iterator.hasNext()) {
			E destruct = iterator.next();

			((Thread) destruct).start();
			/*
			try {
				((Thread) destruct).join(); // wait untill the destructor will hit or miss
			} catch (InterruptedException e) {

			}*/
		}
	}

}
