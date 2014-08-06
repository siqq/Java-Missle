import java.util.logging.FileHandler;

public abstract class AbstractMissile extends Thread{
	
	private int 			delayBeforeLaunch;
	private FileHandler 	fileHandler;
	
	public AbstractMissile(int delayBeforeLaunch, 
			FileHandler fileHandler) {
		super();
		this.delayBeforeLaunch = delayBeforeLaunch;
		
		this.addFileHandler(fileHandler);
	}

	/**
	 * Add file handler and filter log by object
	 * @param fileHandler
	 */
	public void addFileHandler(FileHandler fileHandler) {
		this.setFileHandler(fileHandler);
		ObjectFilter filter = (ObjectFilter) fileHandler.getFilter();
		filter.addFilter(this);
		fileHandler.setFormatter(new MyFormatter());

	}

	public FileHandler getFileHandler() {
		return fileHandler;
	}

	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}
	
	public int getDelayBeforeLaunch() {
		return delayBeforeLaunch;
	}
	
	/**
	 * method to destroy a selected target 
	 * @throws Exception if fail with relevant message
	 */
	public abstract void destroyTarget() throws Exception;
	
}
