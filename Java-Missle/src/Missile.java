
public class Missile extends Thread{
	
	private int id;
	private String destination;
	private int launchTime;
	private int flyTime;
	private int damage;
	
	public Missile(int id, String destination, int launchTime, int flyTime, int damage) {
		this.id = id;
		this.destination = destination;
		this.launchTime = launchTime;
		this.flyTime = flyTime;
		this.damage = damage;
		
	}

}
