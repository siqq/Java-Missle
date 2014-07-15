import java.util.ArrayList;


public class Launcher extends Thread{
	private int id;
	private boolean isHidden;
	private ArrayList<Missle> missles = new ArrayList();
	
	public Launcher(int id, ArrayList<Missle> missles) {
		super();
		this.id = id;
		this.missles = missles;
	}	
}
