import java.util.logging.Logger;


public class DestructedLanucher extends Thread{
	private String 	id;
	private int 	destructTime;
	private Logger 	logger;
	
	public DestructedLanucher(String id, int destructTime) {
		super();
		this.id = id;
		this.destructTime = destructTime;
	}

	@Override
	public String toString() {
		return "DestructedLanucher [id=" + id + ", destructTime=" + destructTime + "]";
	}

	public void addLogger(Logger logger) {
		this.logger = logger;		
	}
	
}
