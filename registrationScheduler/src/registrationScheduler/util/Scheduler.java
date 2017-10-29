package registrationScheduler.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import registrationScheduler.objectPool.Singleton;
import registrationScheduler.store.Results;
import registrationScheduler.store.ScheduledResultInterface;
import registrationScheduler.student.Student;
import registrationScheduler.util.Logger.DebugLevel;
/**
 * 
 * @author shali
 * Scheduler class to process Scheduling Algorithm
 */
public class Scheduler implements SchedulerInterface {
	
	private String studentId = null;
	
	/**
	 * Constructor
	 */
	public Scheduler(){
		Logger.writeMessage("Constructor called: Scheduler class", DebugLevel.CONSTRUCTOR);
	}
	
	/**
	 * method to insert the preference courses to data structure.
	 * @param String, Student 
	 */
	public synchronized void insertPref(String fileInput, Student student) {
		String[] token = null;
		token = fileInput.split("\\s+");
		
		studentId = token[0];
		student.setStudentId(studentId);
		List<String> coursesList = new ArrayList<>();;
		for (int i = 1; i < token.length; i++) {
			
			coursesList.add(token[i]);
		}
		student.setPrefList(coursesList);
		processScheduler(coursesList,student);
	}
	
	/**
	 * method to insert the requested add/drop courses to data structure.
	 * @param String, ScheduledResultInterface 
	 */
	public synchronized Student insertAddDrop(String fileInput, ScheduledResultInterface results){
		String[] token = null;
		token = fileInput.split("\\s+");
		studentId = token[0];
		int addDropValue = Integer.parseInt(token[1]);
		Map<String, Student> map = ((Results)results).getStudentMapTable();
		List<String> coursesList = new ArrayList<>();
		for(int i = 2; i < token.length; i++){
			coursesList.add(token[i]);
		}
		if(map.containsKey(studentId)){
			if(1 == addDropValue){
				processScheduler(coursesList, map.get(studentId));
				return map.get(studentId);
			}
			else if (0 == addDropValue){
				List<String> list = map.get(studentId).getAllocatedList();
				for(String eachCourse: coursesList){
					if(list.contains(eachCourse)){
						list.remove(eachCourse);
						Singleton singleton = Singleton.getInstance();
						singleton.returnCourse(eachCourse);
						map.get(studentId).setAllocatedList(list);
					}		
				}
				return map.get(studentId);
			}	
		}
		return null;	
	}
	
	/**
	 * method to process scheduling
	 * @param List<String>, Student 
	 */
	public void processScheduler(List<String> coursesList, Student student){
		
		Singleton instance = Singleton.getInstance();
		List<String> courses = new ArrayList<>();
		for(String course: coursesList){
			boolean value = instance.check(course, student);
			if(value==true && !student.getAllocatedList().contains(course)){
				instance.decrementSeatNum(course);
				courses = student.getAllocatedList();
				courses.add(course);
				student.setAllocatedList(courses);
				
			}
			else {
				//System.out.println("None ");
			}
		}	
	}
	
	/**
	 * Overridden toString method
	 */
	public String toString() {
		return "Scheduler processed scheduling for "+studentId;
	}
}
