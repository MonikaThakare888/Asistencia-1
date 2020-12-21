package com.asistencia.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asistencia.exceptions.PasswordDoesNotMatch;
import com.asistencia.exceptions.PasswordMismatchedException;
import com.asistencia.exceptions.PleaseLoginException;
import com.asistencia.exceptions.UserAlreadyExistsException;
import com.asistencia.exceptions.UserDoesNotExistException;
import com.asistencia.models.Employee;
import com.asistencia.repository.EmployeeRepository;
import com.asistencia.request.AnimalSignUpRequest;
import com.asistencia.request.LogInRequest;
import com.asistencia.request.SignUpRequest;

@Service
public class UserService {
	
	@Autowired
	private EmployeeRepository employeeRepository ;
	
	public int  add(int a,int b)
	{
		return a+b;
	}
	
	
	public Employee createUser(SignUpRequest signUpRequest) throws PasswordMismatchedException, UserAlreadyExistsException, ClassNotFoundException {
		
		if(!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
			throw new PasswordMismatchedException("Values of Password and confirm Password Mismatch");
		}		
			
		Employee n = new Employee (signUpRequest.getEmail(), signUpRequest.getUserName(), signUpRequest.getDepartment(), signUpRequest.getPassword());
		try {
			employeeRepository.save(n);
		} catch (SQLException e) {
			throw new UserAlreadyExistsException("User already exists");
		}catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("MYSQL driver class not found");
		}
		
		return n;
	}
	
	public Employee logInUser(LogInRequest logInRequest) throws UserDoesNotExistException, PasswordDoesNotMatch, ClassNotFoundException, SQLException {
		
		Employee e = employeeRepository.findByEmail(logInRequest.getEmail());
		
		if(e == null) {
			throw new UserDoesNotExistException("User does not exists");
		} else if(e.getPassword().equals(logInRequest.getPassword())) {
			return e;
		} else throw new PasswordDoesNotMatch("Incorrect password");
	}
	
	public boolean deleteUser(LogInRequest logInRequest) throws ClassNotFoundException, SQLException, PleaseLoginException, UserDoesNotExistException, PasswordDoesNotMatch {
		
		Employee e = employeeRepository.findByEmail(logInRequest.getEmail());
		
		if(e == null) {
			throw new PleaseLoginException("Please first login");
		} else if(e.getPassword().equals(logInRequest.getPassword())) {
			employeeRepository.deleteAfterLogin(logInRequest.getEmail());
			return true;
		} throw new PleaseLoginException("Please first login");
	}


	public void animalCreateUser(AnimalSignUpRequest animalSignUpRequest) 
	{
		if(!animalSignUpRequest.getPassword().equals(animalSignUpRequest.getConfirmPassword())) {
			throw new PasswordMismatchedException("Values of Password and confirm Password Mismatch");
		}		
			
		Employee n = new Employee (animalSignUpRequest.getEmail(), animalSignUpRequest.getUserName(), signUpRequest.getDepartment(), signUpRequest.getPassword());
		try {
			employeeRepository.save(n);
		} catch (SQLException e) {
			throw new UserAlreadyExistsException("User already exists");
		}catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("MYSQL driver class not found");
		}
		
		return n;
		
		
	}


	
	
	
}
