package registrationScheduler.student;

import java.util.List;
import java.util.Vector;

import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * 
 * @author shali
 * Pojo Class for Object Student
 */
public class Student {
	private String studentId = null;
	private List<String> prefList = null;
	private List<String> allocatedList = null;
	private int prefScore = 0;
	
	/**
	 * Constructor
	 */
	public Student(){
		Logger.writeMessage("Constructor called: Student class", DebugLevel.CONSTRUCTOR);
		prefList = new Vector<String>();
		allocatedList = new Vector<String>();
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public List<String> getPrefList() {
		return prefList;
	}

	public void setPrefList(List<String> prefList) {
		this.prefList = prefList;
	}

	public List<String> getAllocatedList() {
		return allocatedList;
	}

	public void setAllocatedList(List<String> allocatedList) {
		this.allocatedList = allocatedList;
	}

	public int getPrefScore() {
		return prefScore;
	}
	
	public void setPrefScore(int prefScore) {
		this.prefScore = prefScore;
	}
		
	/**
	 * Overridden toString method
	 */
	public String toString() {
		
		/*StringBuilder courses = new StringBuilder();
		for(String each: prefList){
			courses.append(each+" ");
		}*/
		StringBuilder coursesNew = new StringBuilder();
		for(String each: allocatedList){
			coursesNew.append(each+" ");
		}
		return studentId +"\t"+coursesNew+"\t"+prefScore;	
	}	
}
