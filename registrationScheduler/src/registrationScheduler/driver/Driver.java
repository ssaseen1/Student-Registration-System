package registrationScheduler.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import registrationScheduler.store.Results;
import registrationScheduler.store.ScheduledResultInterface;
import registrationScheduler.threadMgmt.CreateWorkers;
import registrationScheduler.util.FileDisplayInterface;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Scheduler;
import registrationScheduler.util.SchedulerInterface;
import registrationScheduler.util.StdoutDisplayInterface;

/**
 * 
 * @author shali
 * Driver class for Scheduling the courses using multiple threads
 */
public class Driver {

	public static void main(String args[]){
		
		/**
		 * Validate the correct number of arguments
		 */
		if(args.length != 5){
			System.err.println("Enter the correct number of command line arguments");
			System.exit(1);	
		}
		
		int numberThread = 0;
		int debugValue = 0; 
		String reg_preference_file = null;
		String add_drop_file = null;
		
		System.out.println("args[0] : reg-preference.txt "+args[0] );
		System.out.println("args[1] : add-drop.txt "+args[1]);
		System.out.println("args[2] : output.txt "+args[2]);
		System.out.println("args[3] : NUM_THREADS "+args[3]);
		System.out.println("args[4] : DEBUG_VALUE "+args[4]);
		
		reg_preference_file = args[0];
		add_drop_file = args[1];
		
		/**
		 * Validate the number format exception,
		 * expected command line argument is an Integer
		 */
		try{
			numberThread = Integer.parseInt(args[3]);
			debugValue = Integer.parseInt(args[4]);
		}
		catch (NumberFormatException e ){
			System.err.println("A numerical value is expected");
			e.printStackTrace();
			System.exit(1);
		}
		
		/**
		 * Validate the value of NUM_THREADS is between 1 and 3.
		 */
		if(numberThread < 1 || numberThread > 3){
			System.err.println("The value of NUM_THREADS should be between 1 and 3");
			System.exit(1);
		}
		
		/**
		 * Validate the DEBUG_VALUE is between 0 and 4.
		 */
		if(debugValue < 0 || debugValue > 4){
			System.err.println("The value of DEBUG_VALUE should be between 0 and 4");
			System.exit(1);
		}
		
		FileInputStream fileInputStream = null;
		FileDisplayInterface fileProcessor = null;
		
		
		File f1 = new File(reg_preference_file);
		File f2 = new File(add_drop_file);
		if (f1.exists() && !f1.isDirectory() && f2.exists() && !f2.isDirectory()) {
			long f1Size = f1.length();
			long f2Size = f2.length();
			if (f1Size == 0) {
				System.err.println("reg_preference_file is empty");
				System.exit(1);
			}
			if (f2Size == 0) {
				System.err.println("add_drop_file is empty");
				System.exit(1);
			}
			try {
				Logger.setDebugValue(debugValue);
				fileInputStream = new FileInputStream(reg_preference_file);
				fileProcessor = new FileProcessor(fileInputStream, reg_preference_file);
				
				ScheduledResultInterface results = new Results();
				SchedulerInterface scheduler = new Scheduler();
				
				CreateWorkers createWorkers = new CreateWorkers(1, fileProcessor, results, scheduler);
				
				createWorkers.startWorkers(numberThread);
				
				//((StdoutDisplayInterface)results).writeScheduleToStdout();
				//((FileDisplayInterface)results).writeToFile(args[2]);
				
				fileInputStream = new FileInputStream(add_drop_file);
				fileProcessor = new FileProcessor(fileInputStream, add_drop_file);
				CreateWorkers createWorkersNew = new CreateWorkers(2,fileProcessor, results, scheduler);
				
				createWorkersNew.startWorkers(numberThread);
				
				//System.out.println("UPDATED COURSES========>");
				((Results)results).calculatePrefScore();
				((Results)results).calculateAvgPrefScore();
				
				((StdoutDisplayInterface)results).writeScheduleToStdout();
				((FileDisplayInterface)results).writeToFile(args[2]);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.err.println("File not found");
				e.printStackTrace();
				System.exit(1);
			} finally {
				if (fileInputStream != null) {
					try {
						fileInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.exit(1);
					} finally {

					}
				}
			}
		} 
		else{
			System.err.println("File does not exist");
			System.exit(1);
		}
	
	}
}
