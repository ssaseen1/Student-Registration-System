package registrationScheduler.objectPool;

import registrationScheduler.student.Student;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * 
 * @author shali
 * Singleton class to create a uniqueInstance.
 */
public class Singleton {
	
	private static Singleton uniqueInstance;

	private ObjectPool pool = null;
	private Course obj = null;

	/**
	 * Constructor
	 */
	private Singleton() {
		// TODO Auto-generated constructor stub
		Logger.writeMessage("Constructor called: Singleton class", DebugLevel.CONSTRUCTOR);
		pool = new Course();
		
	}
	
	/**
	 * method to create a uniqueInstance of class Singleton
	 * @return Singleton
	 */
	public static synchronized Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		} 
		return uniqueInstance;
	}

	/**
	 * Method to check the availability of seats in a course
	 * @param course
	 * @param student
	 * @return boolean
	 */
	public boolean check(String course, Student student){	
		
		try{
			obj = pool.borrowObject(); 
			if(obj.getCoursesTable().get(course) > 0){
				return true;
			}
			else
				return false;
		}
		
		catch(Exception e){
			pool.inValidate(obj);
			obj = null;
		}
		finally{
		    if (null != obj) {
		        pool.returnObject(obj);
		    } 
		}
		return false;
	}
	
	/**
	 * Method to decrement the number of seat in a course
	 * @param course
	 */
	public void decrementSeatNum(String course){	
		
		try{
			
			if(obj.getCoursesTable().get(course) != 0){
				int numOfSeats = obj.getCoursesTable().get(course);
				//System.out.println("Number of seats "+obj.toString()+ " "+course);
				obj.getCoursesTable().put(course, numOfSeats-1);
				
			}
		}
		
		catch(Exception e){
			pool.inValidate(obj);
			obj = null;
		}
		finally{
		    if (null != obj) {
		        pool.returnObject(obj);
		    } 
		}
	}
	
	/**
	 * Method to return the obj to the pool
	 * @param eachCourse
	 */
	public void returnCourse(String eachCourse){
	int value = obj.getCoursesTable().get(eachCourse);
	obj.getCoursesTable().put(eachCourse, value + 1);
	pool.returnObject(obj);
	}
	
	/**
	 * overridden the toString method
	 */
	public String toString() {
		return "Singleton =" +uniqueInstance.toString();
	}
}
