import java.util.logging.Logger;


public class DestructedMissile extends Thread{
	
	private String 	id;
	private int 	destructAfterLaunch;
	private Logger 	logger;
	
	public DestructedMissile(String id, int destructAfterLaunch) {
		super();
		this.id = id;
		this.destructAfterLaunch = destructAfterLaunch;
	}

	@Override
	public String toString() {
		return "DestructedMissile [id=" + id + ", destructAfterLaunch=" + destructAfterLaunch + "]";
	}

	public void addLogger(Logger logger) {
		this.logger = logger;
	}


}
