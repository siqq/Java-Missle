import java.util.ArrayList;


public class Launcher extends Thread{
	private String id;
	private boolean isHidden;
	private ArrayList<Missile> missiles;
	
	public Launcher(String id, boolean isHidden, ArrayList<Missile> missiles) {
		super();
		this.id = id;
		this.isHidden = isHidden;
		this.missiles = missiles;
	}
	public Launcher(String id, boolean isHidden) {
		super();
		this.id = id;
		this.isHidden = isHidden;
		this.missiles = new ArrayList<Missile>();
		System.out.println(this.toString());
	}
	
	public void addMissile(Missile missile) {
		this.missiles.add(missile);
	}
	@Override
	public String toString() {
		return "Launcher [id=" + id + ", isHidden=" + isHidden + ", missiles=" + missiles + "]";
	}
}
