package com.asistencia.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asistencia.exceptions.InTimeAlreadyReceivedException;
import com.asistencia.exceptions.PleaseUpdateInTimeException;
import com.asistencia.models.Attendance;
import com.asistencia.request.FilterByDateRequest;
import com.asistencia.request.TimeRequest;
import com.asistencia.services.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController
{	
	@Autowired
	private AttendanceService attendanceService;
	
	@RequestMapping(method= RequestMethod.POST, path= "/inTime")
	public ResponseEntity<String> inTime (@RequestBody TimeRequest timeRequest) throws ClassNotFoundException, SQLException, InTimeAlreadyReceivedException
	{
		try 
		{
			attendanceService.saveInTime(timeRequest);
			return new ResponseEntity<>("In time recorded succesfully", HttpStatus.ACCEPTED);
		} 
		catch (InTimeAlreadyReceivedException e) 
		{
			return new ResponseEntity<>("In time has already been received", HttpStatus.BAD_REQUEST);
		} 
		catch (ClassNotFoundException e) 
		{
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@RequestMapping(method= RequestMethod.POST, path= "/outTime")
	public ResponseEntity<String> outTime (@RequestBody TimeRequest timeRequest) throws ClassNotFoundException, SQLException, PleaseUpdateInTimeException 
	{
		try
		{
			attendanceService.saveOutTime(timeRequest);
			return new ResponseEntity<>("Out time recorded succesfully", HttpStatus.ACCEPTED);
		} 
		catch (PleaseUpdateInTimeException e) 
		{
			return new ResponseEntity<>("Please update in time", HttpStatus.BAD_REQUEST);
		}
		catch (ClassNotFoundException e)
		{
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method= RequestMethod.GET, path = "/history/{employeeId}")
	public ResponseEntity<List<Attendance>> findData(@RequestBody int id) throws DataAccessException, ClassNotFoundException, SQLException 
	{
		try 
		{
			List<Attendance> a = attendanceService.getDataById(id);
			return new ResponseEntity<>(a, HttpStatus.ACCEPTED);
		} 
		catch (ClassNotFoundException e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method= RequestMethod.GET, path = "/history/filter")
	public ResponseEntity<List<Attendance>> filterDataByDate(@RequestBody FilterByDateRequest filterByDate) throws DataAccessException, ClassNotFoundException, SQLException 
	{
		try 
		{
			List<Attendance> a = attendanceService.filterAttendanceByDate(filterByDate);
			return new ResponseEntity<>(a, HttpStatus.ACCEPTED);
		}
		catch (ClassNotFoundException e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
