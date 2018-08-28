package com.ideasjunction.model;

public class Employee {
	private long EmpId;
	private String userName;
	private String employeeFirstName;
	private String employeeLastName;
	private String password;
	private int userType;
	
	public long getEmpId() {
		return EmpId;
	}
	public void setEmpId(long empId) {
		this.EmpId = empId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "Employee [EmpId=" + EmpId + ", userName=" + userName
				+ ", employeeFirstName=" + employeeFirstName
				+ ", employeeLastName=" + employeeLastName + ", password="
				+ password + ", userType=" + userType + "]";
	}

	
}
