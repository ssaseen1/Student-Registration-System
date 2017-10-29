package registrationScheduler.objectPool;

import java.util.Hashtable;
import java.util.Map;

import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * 
 * @author shali
 * Class Course - object pool with the methods to add, drop courses
 */
public class Course implements ObjectPool {

	private Map<String, Integer> coursesTable = null;
	
	/**
	 * Constructor
	 */
	public Course(){
		
		Logger.writeMessage("Constructor called: Course class", DebugLevel.CONSTRUCTOR);

		coursesTable = new Hashtable<String, Integer>(); 
		coursesTable.put("A", 60);
		coursesTable.put("B", 60);
		coursesTable.put("C", 60);
		coursesTable.put("D", 60);
		coursesTable.put("E", 60);
		coursesTable.put("F", 60);
		coursesTable.put("G", 60);
		coursesTable.put("H", 60);
		
	}
	
	public Map<String, Integer> getCoursesTable() {
		return coursesTable;
	}

	public void setCoursesTable(Map<String, Integer> coursesTable) {
		this.coursesTable = coursesTable;
	}

	@Override
	public void addObject() {
		// TODO Auto-generated method stub
		/*coursesTable.put("A", 60);
		coursesTable.put("B", 60);
		coursesTable.put("C", 60);
		coursesTable.put("D", 60);
		coursesTable.put("E", 60);
		coursesTable.put("F", 60);
		coursesTable.put("G", 60);
		coursesTable.put("H", 60);*/
		
	}

	/**
	 * Method to borrow the course
	 * @return Course
	 */
	@Override
	public Course borrowObject() {
		// TODO Auto-generated method stub
		
		return this;	}

	/**
	 * Method to return the course
	 * @return void
	 * @param ObjectPool
	 */
	@Override
	public void returnObject(ObjectPool obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getNumIdle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inValidate(ObjectPool obj) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Overridden toString method
	 */
	public String toString() {
		return "Courses =" +coursesTable;
	}
}
