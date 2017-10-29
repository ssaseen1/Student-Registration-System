package registrationScheduler.threadMgmt;

import registrationScheduler.store.ScheduledResultInterface;
import registrationScheduler.util.FileDisplayInterface;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;
import registrationScheduler.util.SchedulerInterface;

/**
 * 
 * @author shali
 * CreateWorker class calls the WorkerThread class for the
 * specified number of threads
 */
public class CreateWorkers {
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
	public CreateWorkers(int iIn, FileDisplayInterface fileProcessorIn, ScheduledResultInterface resultsIn, SchedulerInterface scheduler2) {
		// TODO Auto-generated constructor stub
		Logger.writeMessage("Constructor called: CreateWorkers class", DebugLevel.CONSTRUCTOR);
		i = iIn;
		fileProcessor = fileProcessorIn;
		results = resultsIn;
		scheduler = scheduler2;
	}

	/**
	 * method to call the WorkerThread constructor
	 * @param numberThreads
	 */
	public void startWorkers(int numberThreads) {
		// TODO Auto-generated method stub
		Thread[] thread = new Thread[numberThreads];
		WorkerThread workerThread = null;
		
			if(1 == i){
				for(int j = 0; j < numberThreads; j++){
					workerThread = new WorkerThread(1,fileProcessor, results, scheduler);
					thread[j] = new Thread(workerThread);
					thread[j].start();
				}
			}
			else{
				for(int j = 0; j < numberThreads; j++){
					workerThread = new WorkerThread(2,fileProcessor, results, scheduler);
					thread[j] = new Thread(workerThread);
					thread[j].start();
				}
			}

		for(int i = 0; i < numberThreads; i++){
			try {
				thread[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Thread is Interrupted");
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	/**
	 * Overridden toString method
	 */
	public String toString() {
		return "CreateWorkers class to call the WorkerThread class";
	}

}
