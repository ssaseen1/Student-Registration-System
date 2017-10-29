package registrationScheduler.threadMgmt;

import registrationScheduler.store.ScheduledResultInterface;
import registrationScheduler.student.Student;
import registrationScheduler.util.FileDisplayInterface;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;
import registrationScheduler.util.SchedulerInterface;

/**
 * @author shali
 * Class WorkerThread implements Runnable
 */
public class WorkerThread implements Runnable {
	
	private FileDisplayInterface fileProcessor = null;
	private ScheduledResultInterface results = null;
	private SchedulerInterface scheduler = null;
	private int i = 0;

	/**
	 * Constructor
	 * @param iIn
	 * @param fileProcessorIn
	 * @param resultsIn
	 * @param scheduler2
	 */
	public WorkerThread(int iIn, FileDisplayInterface fileProcessorIn, ScheduledResultInterface resultsIn, SchedulerInterface scheduler2) {
		// TODO Auto-generated constructor stub
		Logger.writeMessage("Constructor called: WorkerThread class", DebugLevel.CONSTRUCTOR);
		i = iIn;
		fileProcessor = fileProcessorIn;
		results = resultsIn;
		scheduler = scheduler2;
		
	}

	/**
	 * run method is called to spawn the 
	 * specified number of threads
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
    	Logger.writeMessage("Run Method called: WorkerThread class", DebugLevel.RUN_METHOD);

		if(1 == i){
			String fileInput = null;
			fileInput = fileProcessor.readFromFile();
			while(null != fileInput){
				Student student = new Student();
				scheduler.insertPref(fileInput,student); 
				results.addToMapTable(student.getStudentId(), student);
				fileInput = fileProcessor.readFromFile();
			}
		}
		else if(2 == i){
			String fileInput = null;
			fileInput = fileProcessor.readFromFile();
			while(null != fileInput){
				Student student = scheduler.insertAddDrop(fileInput, results);
				if(null != student ){
					results.addToMapTable(student.getStudentId(), student);
				}
				fileInput = fileProcessor.readFromFile();	
			}	
		}
	}
	
	/**
	 * overridden the toString method
	 */
	public String toString() {
		return "WorkerThread will spawn the threads";
	}
}


