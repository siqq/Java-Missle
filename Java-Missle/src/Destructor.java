import java.util.ArrayList;


public class Destructor<E> extends Thread{
	
	private String id;
	private String type;
	private ArrayList<E> Destructed;
	
	public Destructor(String id, String type, ArrayList<E> destructed) {
		super();
		this.id = id;
		this.type = type;
		this.Destructed = destructed;
		System.out.println(this.toString());
	} 
	
	
	@Override
	public String toString() {
		return "Destructor [id=" + id + ", type=" + type + ", Destructed=" + Destructed + "]";
	}


	public void addMissile(E missile) {
		this.Destructed.add(missile);
		
	}
	
	
	
	
	

}
