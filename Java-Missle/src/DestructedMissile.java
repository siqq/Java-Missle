
public class DestructedMissile extends Thread{
	
	private String id;
	private int destructAfterLaunch;
	
	public DestructedMissile(String id, int destructAfterLaunch) {
		super();
		this.id = id;
		this.destructAfterLaunch = destructAfterLaunch;
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "DestructedMissile [id=" + id + ", destructAfterLaunch=" + destructAfterLaunch + "]";
	}


}
