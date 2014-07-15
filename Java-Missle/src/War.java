import java.util.ArrayList;


public class War extends Thread{
	private int id;
	private String name;
	private ArrayList<Launcher> missleLaunchers = new ArrayList();
	private ArrayList<Destructor> missileDestructors = new ArrayList();
	private ArrayList<Destructor> missileLauncherDestructors = new ArrayList();
	
	public War(int id, String name, ArrayList<Launcher> missleLaunchers) {
		super();
		this.id = id;
		this.name = name;
		this.missleLaunchers = missleLaunchers;
	}
}
