import java.util.ArrayList;


public class Destructor extends Thread{
	
	private String id;
	private String type;
	private ArrayList<?> Destructed;
	
	public Destructor(String id, String type, ArrayList<?> destructed) {
		super();
		this.id = id;
		this.type = type;
		this.Destructed = destructed;
	} 
	
	
	

}
