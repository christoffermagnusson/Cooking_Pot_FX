/**
 *
 */
package domain.models;

/**
 * @author Christoffer
 *
 */
public class TimeUnit {

	private String timeUnit;
	private int timeCount;

	public TimeUnit(int timeCount, String timeUnit){
		this.timeUnit=timeUnit;
		this.timeCount=timeCount;
	}
	public String getUnit(){
		return this.timeUnit;
	}
	public void setUnit(String newUnitType){
		this.timeUnit=newUnitType;
	}
	public int getCount(){
		return this.timeCount;
	}
	public void setCount(int newCount){
		this.timeCount=newCount;
	}

}
