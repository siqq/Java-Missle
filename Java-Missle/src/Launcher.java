import java.util.ArrayList;


public class Launcher extends Thread{
	private String id;
	private boolean isHidden;
	private ArrayList<Missile> missiles = new ArrayList();
	
	public Launcher(String id, ArrayList<Missile> missiles) {
		super();
		this.id = id;
		this.missiles = missiles;
	}	
}
