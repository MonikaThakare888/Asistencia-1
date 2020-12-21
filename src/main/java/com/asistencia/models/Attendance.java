package com.asistencia.models;

import java.sql.Date;
import java.sql.Time;

public class Attendance {
	private int id;
	private int employeeId;
	private Date date;
	private Time inTime;
	private Time outTime;

	public Attendance(int employeeId, Date date, Time inTime, Time outTime) {
		this.employeeId = employeeId;
		this.date = date;
		this.inTime = inTime;
		this.outTime = outTime;
	}

	public Attendance() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getInTime() {
		return inTime;
	}

	public void setInTime(Time time) {
		this.inTime = time;
	}

	public Time getOutTime() {
		return outTime;
	}

	public void setOutTime(Time outTime) {
		this.outTime = outTime;
	}
	
	
}
