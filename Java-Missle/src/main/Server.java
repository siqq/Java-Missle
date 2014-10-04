package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import launcher.Launcher;
import missile.Missile;


public class Server {
    Socket socket = null;
    ServerSocket server;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    Launcher launcher = null;
    Missile missile = null;
    public Server(){
    try {
	server = new ServerSocket(7000);
	System.out.println(new Date() + " --> Server waits for client...");
	socket = server.accept(); // blocking
	System.out.println(new Date() + " --> Client connected from "
		+ socket.getInetAddress() + ":" + socket.getPort());

	outputStream = new ObjectOutputStream(socket.getOutputStream());
	inputStream = new ObjectInputStream(socket.getInputStream());

	do {
	    if( inputStream.readObject() instanceof Missile) {
		missile = (Missile)inputStream.readObject();
		outputStream.writeObject(missile);
	    }
	    else if( inputStream.readObject() instanceof Launcher) {
		launcher = (Launcher)inputStream.readObject();
		outputStream.writeObject(launcher);
	    }

	} while (launcher != null || missile != null);
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
