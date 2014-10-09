package war;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import launcher.Launcher;
import missile.Missile;


public class Server extends Thread      {
    //	public static void main(String[] args) throws IOException {
	ObjectInputStream inputStream;
	ObjectOutputStream outputStream;
	War war;
	ServerSocket server;
	Launcher launcher = null;
	Missile missile = null;
	Socket socket = null;
    public Server() throws IOException{

    }


    private static void addLauncher(Launcher launcher) {
	// TODO Auto-generated method stub

    }


    public void setWar(War war) {
	this.war = war;
	
    }


    @Override
    public void run() {
	// TODO Auto-generated method stub
//	Socket socket = null;
        try {
	    server = new ServerSocket(7000);
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
//	ObjectInputStream inputStream;
//	ObjectOutputStream outputStream;
//	Launcher launcher = null;
//	Missile missile = null;
	try {
	    System.out.println(new Date() + " --> Server waits for client...");
	    socket = server.accept(); // blocking
	    System.out.println(new Date() + " --> Client connected from "
		    + socket.getInetAddress() + ":" + socket.getPort());

	    outputStream = new ObjectOutputStream(socket.getOutputStream());
	    inputStream = new ObjectInputStream(socket.getInputStream());

	    do {

		launcher = (Launcher) inputStream.readObject();
		System.out.println("read launcher");
					    if (launcher!=null){
						System.out.println("launcher");
						
					    }  

					    missile = (Missile) inputStream.readObject();
					    if (missile!=null ){
						System.out.println("missile");
						launcher.addMissile(missile);
					    }  
		if(launcher != null){
		    System.out.println("Server");
		    System.out.println(launcher.getLauncherId());
		    war.addLauncher(launcher);
		    System.out.println("sucess");
		}

	    } while (launcher != null);

	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    try {
		socket.close();
		server.close();
		System.out
		.println("Sever is closing after client is disconnected");
	    } catch (IOException e) { }
	}
    }
}


