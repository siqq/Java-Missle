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

public class Server extends Thread {
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    War war;
    Queue<Object> vec;
    ServerSocket server;
    Launcher launcher = null;
    Missile missile = null;
    Socket socket = null;

    public Server() throws IOException {
    }

    public void setWar(War war) {
	this.war = war;
    }

    @Override
    public void run() {
	try {
	    server = new ServerSocket(7000);
	} catch (IOException e1) {
	    e1.printStackTrace();
	}
	try {
	    System.out.println(new Date() + " --> Server waits for client...");
	    socket = server.accept(); // blocking
	    System.out.println(new Date() + " --> Client connected from "
		    + socket.getInetAddress() + ":" + socket.getPort());
	    vec = new LinkedList<>();
	    outputStream = new ObjectOutputStream(socket.getOutputStream());
	    inputStream = new ObjectInputStream(socket.getInputStream());
	    vec = (Queue<Object>) inputStream.readObject();
	    System.out.println("work");
	} catch (Exception e) {
	    System.out.println(e);
	} finally {
	    // socket.close();
	    // server.close();
	    for (Object obj : vec) {
		if (obj instanceof Missile) {
		    missile = (Missile) obj;
		}
		if (obj instanceof Launcher) {
		    launcher = (Launcher) obj;
		}
	    }
	    if (missile != null && launcher != null) {
		launcher = WarUtility.getLauncherById(launcher.getLauncherId(),war);
		missile = WarUtility.getMissileById(missile.getMissileId(), war);
		launcher.addMissile(missile);
	    } else {
		war.addLauncher(launcher);
	    }
	}
    }
}
