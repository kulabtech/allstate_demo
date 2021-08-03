package com.jwt.model;

public class EmployeeModel {
	
	private String empName;
	private String empAddress;
	private String empAge;
	
	public EmployeeModel () {
		
	}
	
	@Override
	public String toString() {
		return "EmployeeModel [empName=" + empName + ", empAddress=" + empAddress + ", empAge=" + empAge + "]";
	}

	public EmployeeModel(String empName, String empAddress, String empAge) {
		super();
		this.empName = empName;
		this.empAddress = empAddress;
		this.empAge = empAge;
	}
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public String getEmpAge() {
		return empAge;
	}
	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}
	
	

}
