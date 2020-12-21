package com.asistencia.request;

import java.sql.Date;

public class FilterByDateRequest {
	
	private int id;
	private Date from;
	private Date to;
	
	public FilterByDateRequest(int id, Date from, Date to) {
		this.id = id;
		this.from = from;
		this.to = to;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}
	
	
}
