
public class DestructedLanucher extends Thread{
	private String id;
	private int destructTime;
	
	public DestructedLanucher(String id, int destructTime) {
		super();
		this.id = id;
		this.destructTime = destructTime;
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "DestructedLanucher [id=" + id + ", destructTime=" + destructTime + "]";
	}
	
	
	
	
}
