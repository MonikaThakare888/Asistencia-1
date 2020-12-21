package com.asistencia.services;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.sql.SQLException;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.asistencia.exceptions.InTimeAlreadyReceivedException;
import com.asistencia.exceptions.PleaseUpdateInTimeException;
import com.asistencia.models.Attendance;
import com.asistencia.repository.AttendanceRepository;
import com.asistencia.request.FilterByDateRequest;
import com.asistencia.request.TimeRequest;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceRepository attendanceRepository ;
	
	public void saveInTime(TimeRequest timeRequest) throws ClassNotFoundException, SQLException, InTimeAlreadyReceivedException {
		
		if(attendanceRepository.checkRecord(timeRequest.getEmployeeId(),stringToDate(timeRequest.getInTime())).size() == 0) {
			attendanceRepository.saveInTime(timeRequest.getEmployeeId(),stringToDate(timeRequest.getInTime()),stringToTime(timeRequest.getInTime()));
		} else
			throw new InTimeAlreadyReceivedException("In time has already been received."); 
	}
	
	public void saveOutTime(TimeRequest timeRequest) throws ClassNotFoundException, SQLException, PleaseUpdateInTimeException {
			
		int c = attendanceRepository.saveOutTime(timeRequest.getEmployeeId(),stringToDate(timeRequest.getInTime()),stringToTime(timeRequest.getInTime()));
		
		if(c == 0) {
			throw new PleaseUpdateInTimeException("Please update in time");
		}
	}
	
	public List<Attendance> getDataById(int id) throws DataAccessException, ClassNotFoundException, SQLException {
		List<Attendance> attendanceById =attendanceRepository.findAttendanceByEmployeeId(id);
		return attendanceById;
	}
	
	public List<Attendance> filterAttendanceByDate(FilterByDateRequest filterByDate) throws DataAccessException, ClassNotFoundException, SQLException {
		List<Attendance> attendanceBetweenDates = attendanceRepository.findDataByFilter(filterByDate.getId(), filterByDate.getFrom(), filterByDate.getTo());
		return attendanceBetweenDates;
	} 
	
	private Date stringToDate(String timeStamp) {
		LocalDateTime dateTime = LocalDateTime.parse(timeStamp);
		OffsetDateTime offsetdatetime = OffsetDateTime.of(dateTime, ZoneOffset.UTC); 
		java.sql.Date sqlDate = java.sql.Date.valueOf(offsetdatetime.toLocalDate());
	
		return sqlDate;
	}
	
	private Time stringToTime(String timeStamp) {
		LocalDateTime dateTime = LocalDateTime.parse(timeStamp);
		OffsetDateTime offsetdatetime = OffsetDateTime.of(dateTime, ZoneOffset.UTC); 
		java.sql.Time sqltime = java.sql.Time.valueOf(offsetdatetime.toLocalTime());
		
		return sqltime;
	}
}
