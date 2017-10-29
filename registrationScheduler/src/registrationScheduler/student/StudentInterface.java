package registrationScheduler.student;

import java.util.List;

/**
 * 
 * @author shali
 * Class Student implements StudentInterface
 */
public interface StudentInterface {

	public String getStudentId();
	public void setStudentId(String studentId);
	public List<String> getPrefList();
	public void setPrefList(List<String> prefList);
	public List<String> getAllocatedList();
	public void setAllocatedList(List<String> allocatedList);
	public int getPrefScore();
	public void setPrefScore(int prefScore);
	
}
