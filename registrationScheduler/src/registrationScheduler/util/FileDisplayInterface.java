package registrationScheduler.util;

import registrationScheduler.store.Results;

/**
 * @author shali 
 * Class {@link Results} and {@link FileProcessor} implements {@link FileDisplayInterface}
 */
public interface FileDisplayInterface {

	public String readFromFile();
	public void writeToFile(String line);

}
