package com.asistencia.request;

public class SignUpRequest {
	
	private String email;
	private String userName;
	private String department;
	private String password;
	private String confirmPassword;
	
	@Override
	public String toString() {
		return "SignUpRequest [email=" + email + ", userName=" + userName + ", department=" + department + ", password="
				+ password + ", confirmPassword=" + confirmPassword + "]";
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
