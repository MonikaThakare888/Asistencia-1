package com.asistencia.services;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class DemoClass {

	public static void main(String[] args) 
	{
		Result r =JUnitCore.runClasses(UserServiceTest.class);
		
		for(Failure f : r.getFailures())
		{
			System.out.println(f.toString());
		}
		
		System.out.println("Result "+ r.wasSuccessful());
		System.out.println(r);
	}

}
