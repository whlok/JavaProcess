package mvc;

/**
 * @autor wenhoulai
 * Created on 2023-02-16
 */
public class StudentController {
	private Student model;
	private StudentView view;

	public StudentController(Student model, StudentView view) {
		this.model = model;
		this.view = view;
	}

	public String getStudentName(){
		return model.getName();
	}

	public void setStudentRollNo(String rollNo){
		model.setRollNo(rollNo);
	}

	public String getStudentRollNo(){
		return model.getRollNo();
	}


	public void updateView(){
		view.printStudentDetails(model.getName(), model.getRollNo());
	}

	public void setStudentName(String name) {
		model.setName(name);
	}
}
