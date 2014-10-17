package war;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import launcher.Launcher;
import missile.Missile;


public class Server extends Thread      {
    //	public static void main(String[] args) throws IOException {
	ObjectInputStream inputStream;
	ObjectOutputStream outputStream;
	War war;
	Queue<Object> vec;
	ServerSocket server;
	Launcher launcher = null;
	Missile missile = null;
	Socket socket = null;
    public Server() throws IOException{

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
	    vec = new LinkedList<>();
	    outputStream = new ObjectOutputStream(socket.getOutputStream());
	    inputStream = new ObjectInputStream(socket.getInputStream());

//	    do {
		vec = (Queue<Object>) inputStream.readObject();
//		vec.add(obj);
		
		
//		outputStream.writeObject(obj);
//		launcher = (Launcher) inputStream.readObject();
//		System.out.println("read launcher");
//					    if (launcher!=null){
//						System.out.println("launcher");
//						
//					    }  
//
//					    missile = (Missile) inputStream.readObject();
//					    if (missile!=null ){
//						System.out.println("missile");
//						{
		
//					    }  
//		if(launcher != null){
//		    System.out.println("Server");
//		    System.out.println(launcher.getLauncherId());
//		    war.addLauncher(launcher);
//		    System.out.println("sucess");
//		}
		 System.out.println("work");

//	    } while (inputStream.readObject() != null);

	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    //		socket.close();
	    //		server.close();
	    		for(Object obj : vec){
	    		   if(obj instanceof Missile){
	    		       missile = (Missile)obj;
	    		   }
	    		   if ( obj instanceof Launcher){
	    		       launcher = (Launcher)obj;
	    		   }
	    		}
	    		   if( missile != null && launcher != null){
//	    		       launcher.addMissile(missile);
	    		       launcher = WarUtility.getLauncherById(launcher.getLauncherId(), war);
	    		       missile = WarUtility.getMissileById(missile.getMissileId(), war);
	    		       launcher.addMissile(missile);
//	    		       war.addMissileFromServer(missile.getMissileId(), missile.getDestination(),missile.getDamage(), missile.getFlyTime(), launcher.getLauncherId());
	    		   }
	    		   else{
	    		       war.addLauncher(launcher);
	    		   }
	}
    }
}


