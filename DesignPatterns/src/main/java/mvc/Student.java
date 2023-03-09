package mvc;

/**
 * @autor wenhoulai
 * Created on 2023-02-16
 */
public class Student {
	private String rollNo;
	private String name;
	public String getRollNo(){
		return rollNo;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
}
