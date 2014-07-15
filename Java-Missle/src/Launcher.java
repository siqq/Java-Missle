import java.util.ArrayList;


public class Launcher extends Thread{
	private int id;
	private boolean isHidden;
	private ArrayList<Missile> missiles = new ArrayList();
	
	public Launcher(int id, ArrayList<Missile> missiles) {
		super();
		this.id = id;
		this.missiles = missiles;
	}	
}
