package main;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import launcher.Launcher;
import missile.Missile;

import org.xml.sax.SAXException;

import view.WarGui;
import war.War;
import war.controller.AbstractWarView;
import war.controller.WarController;

public class Program {
	/**
     * 
     */
	public static final String 	LAUNCHER = "launcher";
	public static final String 	MISSILE = "missile";
	public static final int 	TAKES_TIME_MIN = 1;
	public static final int 	TAKES_TIME_MAX = 10;
			
	private static Logger 		logger = Logger.getLogger("warLogger");
	private static Scanner 		input = new Scanner(System.in);

	public static void main(String[] args) {
		
		try {
			War warModel = new War();
			AbstractWarView viewGui = new WarGui();	
			WarController controller = new WarController(warModel,viewGui);
			XMLparser xml = new XMLparser(controller, warModel);
			
//		    new WarDBConnection();
//			WarDBConnection.clearWarDataBase();
//		    new Server();

			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	

}