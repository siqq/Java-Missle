import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;


public class MyFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		StringBuffer beffer = new StringBuffer(1000);
		Date date = new Date();
		beffer.append(date.toLocaleString());
		beffer.append(" ");
		beffer.append(formatMessage(record));
		beffer.append("\n");
		return beffer.toString();
	}
	
}
