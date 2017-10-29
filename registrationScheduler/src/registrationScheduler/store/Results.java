
package registrationScheduler.store;

import registrationScheduler.util.StdoutDisplayInterface;
import registrationScheduler.util.Logger.DebugLevel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import registrationScheduler.student.Student;
import registrationScheduler.util.FileDisplayInterface;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;

/**
 * 
 * @author shali
 * Results class to store the data to the data structure
 *
 */
public class Results implements StdoutDisplayInterface, FileDisplayInterface, ScheduledResultInterface {

	private Map<String, Student> studentMapTable = null;
	private float avgPrefScore = 0;
	
	/**
	 * Constructor
	 */
	public Results() {
		// TODO Auto-generated constructor stub
		Logger.writeMessage("Constructor called: Results class", DebugLevel.CONSTRUCTOR);
		studentMapTable = new Hashtable<String, Student>();	
	}
	
	/**
	 * method to write the output to the screen
	 */
	public void writeScheduleToStdout() {
		Logger.writeMessage("The average preference value is "+avgPrefScore, DebugLevel.OUTPUT);
    	Logger.writeMessage("Content of Data Structure : "+toString(), DebugLevel.CONTENT_OF_DATASTRUCTURE);
   
		String str = null;
		Map<Integer, Student> map = new TreeMap<Integer, Student>();
		for(Entry<String, Student> eachstudent: studentMapTable.entrySet()){
			str = eachstudent.getKey();
			String token[] = str.split("_");
			map.put(Integer.parseInt(token[1]), eachstudent.getValue());
		}
		for(Entry<Integer, Student> stud: map.entrySet()){
			/*String line = stud.getValue().getStudentId() +"\t"+ 
					stud.getValue().getAllocatedList() +"\t"+stud.getValue().getPrefScore();*/
			String line = stud.toString();
			String token[] = line.split("=");
			System.out.println(token[1]);
		}
		System.out.println("Average preference_score is :"+avgPrefScore);
	}		
	   
	/**
	 * method to write the output to the file
	 * @param String output filename
	 */
	@Override
	public void writeToFile(String outFileName){
		
		FileOutputStream fileOutputStream = null;
		FileProcessor fileProcessor = null;
		try {
			fileOutputStream = new FileOutputStream(outFileName);
			fileProcessor = new FileProcessor(fileOutputStream, outFileName);
			String str = null;
			Map<Integer, Student> map = new TreeMap<Integer, Student>();
			for(Entry<String, Student> eachstudent: studentMapTable.entrySet()){
				str = eachstudent.getKey();
				String token[] = str.split("_");
				map.put(Integer.parseInt(token[1]), eachstudent.getValue());
			}
			for(Entry<Integer, Student> stud: map.entrySet()){
				String line = stud.toString();
				String token[] = line.split("=");
				fileProcessor.writeToFile(token[1]);
			}
			fileProcessor.writeToFile("Average preference_score is :"+avgPrefScore);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * method to add the content to the data structure
	 */
	@Override
	public void addToMapTable(String studentId,Student student) {
		// TODO Auto-generated method stub
		Logger.writeMessage("Entry "+studentId+" is added to data structure", DebugLevel.ADD_TO_DATASTRUCTURE);
		studentMapTable.put(student.getStudentId(), student);
	}
	
	/**
	 * method to calculate the Preference score of each student
	 */
	public void calculatePrefScore(){
		for(Entry<String, Student> eachStudent: studentMapTable.entrySet()){
			int i = 6 ;int score = 0;
			Student stud = eachStudent.getValue();
			for(String pref: stud.getPrefList()){
				for(String alloc: stud.getAllocatedList()){
					if(pref.equals(alloc)){
						score = score +i;
						stud.setPrefScore(score);
					}
				}
				i--;
			}	
		}
		
		for(Entry<String, Student> eachStudent: studentMapTable.entrySet()){
			Student stud = eachStudent.getValue();
			Set<String> set1 = new HashSet<String>();
			set1.addAll(stud.getPrefList());
			Set<String> set2 = new HashSet<String>();
			set2.addAll(stud.getAllocatedList());
			
			set2.removeAll(set1);
		
			for(String diff: set2){
				stud.setPrefScore(stud.getPrefScore()+1);		
			}
		}
	}
	
	/**
	 * method to calculate the average preference score.
	 */
	public void calculateAvgPrefScore(){
		float score = 0;
		for(Entry<String, Student> eachStudent: studentMapTable.entrySet()){
			Student stud = eachStudent.getValue();
			score = stud.getPrefScore() + score;
		}
		avgPrefScore = score/80;
		setAvgPrefScore(avgPrefScore);		
	}
	
	public Map<String, Student> getStudentMapTable() {
		return studentMapTable;
	}

	public void setStudentMapTable(Map<String, Student> studentMapTable) {
		this.studentMapTable = studentMapTable;
	}
	
	public float getAvgPrefScore() {
		return avgPrefScore;
	}
	
	public void setAvgPrefScore(float avgPrefScore) {
		this.avgPrefScore = avgPrefScore;
	}

	@Override
	public String readFromFile() {
	// TODO Auto-generated method stub
	return null;
	}
	
	/**
	 * overridden toString method
	 */
	public String toString(){
		return studentMapTable.toString();
		
	}

}
