import java.util.logging.Filter;
import java.util.logging.LogRecord;


public class ObjectFilter implements Filter {

	private Object object;

	public ObjectFilter(Object toFilter) {
		this.object = toFilter;
	}

	@Override
	public boolean isLoggable(LogRecord rec) {
		System.out.println("fsdfsd");
		if(rec.getParameters() != null) {
			for(int i = 0; i < rec.getParameters().length; i++) {
				Object temp = rec.getParameters()[i];
				System.out.println(object + " = " + temp);
				if(object == temp)
					return true;
			}
		}
		return false;	
	}

}
