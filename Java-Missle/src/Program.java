import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class Program {
	public static void main(String[]args) {
		try {
			XMLparser xml = new XMLparser();
			War war = xml.readXML();
			war.start();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
