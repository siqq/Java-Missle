import java.util.ArrayList;


public class Destructor extends Thread{
	
	private int id;
	private String type;
	private ArrayList<?> Destructed;
	
	public Destructor(int id, String type, ArrayList<?> destructed) {
		super();
		this.id = id;
		this.type = type;
		this.Destructed = destructed;
	} 
	
	
	

}
