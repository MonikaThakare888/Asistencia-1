package com.asistencia.models;

public class Employee {
	
	private Integer id;
	private String email;
	private String userName;
	private String department;
	private String password;
	
	
	public Employee(Integer id, String email, String userName, String department, String password) {
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.department = department;
		this.password = password;
	}

	public Employee(String email, String userName, String department, String password) {
		this.email = email;
		this.userName = userName;
		this.department = department;
		this.password = password;
	}
	
	public Employee(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public Employee() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
