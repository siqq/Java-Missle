import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class Destructor<E> extends Thread{

	private static Logger logger = Logger.getLogger("warLogger");

	private String 				id;
	private String 				type;
	private Vector<E> 			Destructed;
	private FileHandler 		fileHandler;

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

	public String getDestructorId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void addDestructMissile(E destruct) {

		if (destruct instanceof DestructedMissile) {
			((DestructedMissile)(destruct)).addFileHandler(this.fileHandler);
		}
		else if (destruct instanceof DestructedLanucher) {
			((DestructedLanucher)(destruct)).addFileHandler(this.fileHandler);
		}
		this.Destructed.add(destruct);
		synchronized (this) {
			this.notify();
		}
	}



	@Override
	public void run() {
		try {
			int size = this.Destructed.size();
			synchronized (this) {
				//fix for new launcher 
				if (size == 0) {
					wait();
					size = this.Destructed.size();
				}
				for(int i = 0; i < size; i++) {
					((Thread) this.Destructed.get(i)).start();		
					if (i == size-1) {
						wait();
						size = this.Destructed.size();
					}
				}
			}
		}
		catch (InterruptedException e) {
		}
	}

}
