package com.asistencia.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asistencia.exceptions.PasswordDoesNotMatch;
import com.asistencia.exceptions.PasswordMismatchedException;
import com.asistencia.exceptions.PleaseLoginException;
import com.asistencia.exceptions.UserAlreadyExistsException;
import com.asistencia.exceptions.UserDoesNotExistException;
import com.asistencia.models.Employee;
import com.asistencia.request.AnimalSignUpRequest;
import com.asistencia.request.LogInRequest;
import com.asistencia.request.SignUpRequest;
import com.asistencia.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController

{
	
	
	
	@Autowired
	private UserService userService;
	@RequestMapping(method= RequestMethod.POST, path="/signup")
	public ResponseEntity<String> createUser(@RequestBody SignUpRequest signUpRequest) {
		try {
			userService.createUser(signUpRequest);
			return new ResponseEntity<>("SignUp Successfully", HttpStatus.ACCEPTED);
		}
		catch (PasswordMismatchedException e){
			return new ResponseEntity<>("Confirm password is not same as password", HttpStatus.BAD_REQUEST);
		}
		catch (UserAlreadyExistsException e) {
			return new ResponseEntity<>("User with this email already exists ", HttpStatus.BAD_REQUEST);
		}
		catch (ClassNotFoundException e) {
			return new ResponseEntity<>("Somthing went wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method= RequestMethod.POST, path="/signup")
	public ResponseEntity<String> createUser(@RequestBody AnimalSignUpRequest AnimalSignUpRequest) 
	{
		try {
		userService.animalCreateUser(AnimalSignUpRequest);
		return new ResponseEntity<>("SignUp Successfully", HttpStatus.ACCEPTED);
		}
		catch (PasswordMismatchedException e){
			return new ResponseEntity<>("Confirm password is not same as password", HttpStatus.BAD_REQUEST);
		}
		catch (UserAlreadyExistsException e) {
			return new ResponseEntity<>("User with this email already exists ", HttpStatus.BAD_REQUEST);
		}
		catch (ClassNotFoundException e) {
			return new ResponseEntity<>("Somthing went wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method= RequestMethod.POST, path="/login")
	public ResponseEntity<Employee> logInUser (@RequestBody LogInRequest logInRequest) throws SQLException {
		
		try {
			Employee employee = userService.logInUser(logInRequest);
			return new ResponseEntity<>(employee , HttpStatus.ACCEPTED);
		}
		catch (UserDoesNotExistException e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		catch (PasswordDoesNotMatch e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		catch (ClassNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method= RequestMethod.POST, path= "/logout")
	public ResponseEntity<String> logOutUser () {
		return new ResponseEntity<> ("Logout Successful!", HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(method= RequestMethod.DELETE, path= "/delete")
	public ResponseEntity<String>deleteUser(@RequestBody LogInRequest logInRequest) throws ClassNotFoundException, SQLException, PleaseLoginException, UserDoesNotExistException, PasswordDoesNotMatch {
		try {
			userService.deleteUser(logInRequest);
			return new ResponseEntity<>("User deleted succesfully.", HttpStatus.ACCEPTED);
		} catch (PleaseLoginException e) {
			return new ResponseEntity<>("Please first login", HttpStatus.BAD_REQUEST);
		}
	} 
	
	
}
