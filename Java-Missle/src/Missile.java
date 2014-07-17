
public class Missile extends Thread{
	
	private String id;
	private String destination;
	private int launchTime;
	private int flyTime;
	private int damage;
	
	public Missile(String id, String destination, int launchTime, int flyTime, int damage) {
		this.id = id;
		this.destination = destination;
		this.launchTime = launchTime;
		this.flyTime = flyTime;
		this.damage = damage;
		System.out.println(this.toString());
		
	}

	@Override
	public String toString() {
		return "Missile [id=" + id + ", destination=" + destination + ", launchTime=" + launchTime + ", flyTime=" + flyTime + ", damage=" + damage
				+ "]";
	}
	

}
