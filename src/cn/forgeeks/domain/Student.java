package cn.forgeeks.domain;

public class Student {

	private String studentId;
	private String studentName;
	private String studentPassword;
	private String status;
	private String sex;
	private String classId;
	private String bedroomId;
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getBedroomId() {
		return bedroomId;
	}
	public void setBedroomId(String bedroomId) {
		this.bedroomId = bedroomId;
	}
	
}
