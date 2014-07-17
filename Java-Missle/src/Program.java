import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Program {
	public static void main(String[]args) {
		try {
			War war = new War();
			LocalDateTime ldate = LocalDateTime.now();
			DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			System.out.println(ldate.format(date));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
