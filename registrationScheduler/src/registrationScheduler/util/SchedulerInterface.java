package registrationScheduler.util;

import java.util.List;

import registrationScheduler.store.ScheduledResultInterface;
import registrationScheduler.student.Student;

/**
 * 
 * @author shali
 * Class {@link Scheduler} implements {@link SchedulerInterface}
 */
public interface SchedulerInterface {
	public void processScheduler(List<String> coursesList, Student student);

	public void insertPref(String fileInput, Student student);

	public Student insertAddDrop(String fileInput, ScheduledResultInterface results);
}
