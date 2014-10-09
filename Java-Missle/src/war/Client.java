package war;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;

import launcher.Launcher;
import missile.Missile;

public class Client extends Thread     {
	static Socket socket = null;
	static ObjectInputStream fromNetInputStream;
	static ObjectOutputStream toNetOutputStream;
    public Client() throws IOException{
//	    socket = new Socket("localhost", 7000);
//	    System.out.println(new Date() + " --> Connected to server at "
//		    + socket.getLocalAddress() + ":" + socket.getLocalPort());
//
//	    toNetOutputStream = new ObjectOutputStream(socket.getOutputStream());
//	    fromNetInputStream = new ObjectInputStream(socket.getInputStream());
    }
	public void sendObjectToServer(Launcher launchers, Missile missile) throws IOException, ClassNotFoundException {
		toNetOutputStream.writeObject(launchers);
		toNetOutputStream.writeObject(missile);
    }
	public void sendObjectToServer(Launcher launcher) throws IOException {
		toNetOutputStream.writeObject(launcher);
		System.out.println("Client");
	    
	}
	@Override
	public void run() {
	    // TODO Auto-generated method stub
	    try {
		socket = new Socket("localhost", 7000);
	    } catch (UnknownHostException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    } catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	    System.out.println(new Date() + " --> Connected to server at "
		    + socket.getLocalAddress() + ":" + socket.getLocalPort());

	    try {
		toNetOutputStream = new ObjectOutputStream(socket.getOutputStream());
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    try {
		fromNetInputStream = new ObjectInputStream(socket.getInputStream());
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
}

