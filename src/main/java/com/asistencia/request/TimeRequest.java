package com.asistencia.request;

public class TimeRequest {
	
	private int employeeId;
	private String inTime;
	
	public TimeRequest(int employeeId, String inTime) {
		this.employeeId = employeeId;
		this.inTime = inTime;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
}
