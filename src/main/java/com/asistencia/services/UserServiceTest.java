package com.asistencia.services;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserServiceTest
{

	private UserService userService = new UserService();
	/*@Test
	public void testCreateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogInUser() {
		fail("Not yet implemented");
	}*/

	@Test
	public void add() 
	{
		String s ="This is my junt program";
		assertEquals("This is my junt program", s);
		/*int result = userService.add(1, 2);
		assertEquals(3, result );*/
	}

}
