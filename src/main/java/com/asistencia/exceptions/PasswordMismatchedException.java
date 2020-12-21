package com.asistencia.exceptions;

public class PasswordMismatchedException extends Exception {

	private static final long serialVersionUID = 1L;
	 public PasswordMismatchedException(String s) 
	    { 
	        super(s); 
	    }
}
