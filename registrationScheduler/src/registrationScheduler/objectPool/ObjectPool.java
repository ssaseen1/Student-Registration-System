package registrationScheduler.objectPool;

/**
 * 
 * @author shali
 * Course Class implements ObjectPool Interface
 */
public interface ObjectPool {

	public  void addObject();
	public Course borrowObject();
	public void returnObject(ObjectPool obj);
	public void getNumIdle();
	public void inValidate(ObjectPool obj);
}
