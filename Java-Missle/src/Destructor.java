import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class Destructor<E> extends Thread{
	
	private String id;
	private String type;
	private ArrayList<E> Destructed;
	private FileHandler fileHandler;
	private Logger logger = Logger.getLogger("Destuctor_"+this.id);
	
	public Destructor(String id, String type, ArrayList<E> destructed) throws SecurityException, IOException {
		super();
		this.id = id;
		this.type = type;
		this.Destructed = destructed;
		System.out.println(this.toString());
		this.setLogger();
	} 
	
	public void setLogger() throws SecurityException, IOException {
		fileHandler = new FileHandler("Destructor_"+this.id+".xml", false);
		logger.addHandler(fileHandler);
		fileHandler.setFormatter(new MyFormatter());
	}
	
	@Override
	public String toString() {
		return "Destructor [id=" + id + ", type=" + type + ", Destructed=" + Destructed + "]";
	}


	public void addDestructMissile(E destruct) {
		if (destruct instanceof DestructedMissile) {
			((DestructedMissile)(destruct)).addLogger(logger);
		}
		else if (destruct instanceof DestructedLanucher) {
			((DestructedLanucher)(destruct)).addLogger(logger);
		}
		this.Destructed.add(destruct);
	}
	
	
	
	
	

}
