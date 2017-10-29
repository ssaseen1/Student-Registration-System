package registrationScheduler.store;

import registrationScheduler.student.Student;

/**
 * 
 * @author shali
 * Class Results implements ScheduledResultInterface 
 */
public interface ScheduledResultInterface {
	

	public void addToMapTable(String studentId, Student student);
	
}

