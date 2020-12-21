package com.asistencia.exceptions;

public class PasswordDoesNotMatch extends Exception{
	
	private static final long serialVersionUID = 1L;
	 public PasswordDoesNotMatch(String s) 
	    { 
	        super(s); 
	    }
}
