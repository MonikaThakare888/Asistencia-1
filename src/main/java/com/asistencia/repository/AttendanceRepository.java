package com.asistencia.repository;

import java.sql.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.asistencia.models.Attendance;

@Repository
public class AttendanceRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	static String url="jdbc:mysql://localhost:3306/mydata?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false";
	static String user="root";
	static String password="1019";
	
	public static void initialize() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	
		public List<Attendance> checkRecord(int id, Date date) throws DataAccessException, ClassNotFoundException, SQLException {
			initialize();
			String query = "select employee_id from attendance where employee_id = ? and a_date = ?";
			Object[] n = {id, date};
			
			List<Attendance> attendance = this.jdbcTemplate.query(query,
					n,
					new RowMapper<Attendance>() {
						public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
							Attendance a = new Attendance();
							a.setId(rs.getInt("employee_id"));
							return a;
						}
					}); 
			return attendance;
		} 
		
		public int saveInTime(int id, Date date, Time time) throws ClassNotFoundException, SQLException {
			initialize();
			String query = "insert into attendance(employee_id, a_date, in_time) values(?,?,?) ";
			
			Connection con = DriverManager.getConnection(url, user, password);
		    PreparedStatement st = con.prepareStatement(query);
	
		    st.setInt(1, id);
		    st.setDate(2, date);
		    st.setTime(3, time);
		    
		    int c = st.executeUpdate();
		    System.out.println(c);
		    return c;
			
		} 
		
		public int saveOutTime(int id, Date date, Time time) throws ClassNotFoundException, SQLException {
			initialize();
			
			String query = "update attendance set out_time = ? where employee_id = ? and a_date = ? and in_time is not null and out_time is null";
			
			Connection con = DriverManager.getConnection(url, user, password);
		    PreparedStatement st = con.prepareStatement(query);	
			
		    st.setTime(1, time);
		    st.setInt(2, id);
		    st.setDate(3, date);
		    
		    int c = st.executeUpdate();
		    return c;
		}
		
		public List<Attendance> findAttendanceByEmployeeId(int id) throws DataAccessException, ClassNotFoundException, SQLException {
			initialize();
			String query = "Select id, employee_id, a_date, in_time, out_time from attendance where employee_id= ?";
			
			List<Attendance> attendance = this.jdbcTemplate.query(query,
					new Object[] {id},
					new RowMapper<Attendance>() {
						public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
							Attendance a = new Attendance();
							a.setId(rs.getInt("id"));
							a.setEmployeeId(rs.getInt("employee_id"));
							a.setDate(rs.getDate("a_date"));
							a.setInTime(rs.getTime("in_time"));
							a.setOutTime(rs.getTime("out_time"));
							return a;
						}
					}); 
			return attendance;
		}		
		
		
		public List<Attendance> findDataByFilter(int id, Date a, Date b) throws DataAccessException, ClassNotFoundException, SQLException {
			String query = "Select id, employee_id, a_date, in_time, out_time from attendance where employee_id= ? and a_date between ? and ?";
			Object[] obj = {id, a, b}; 
			
			List<Attendance> attendance = this.jdbcTemplate.query(query,
					obj,
					new RowMapper<Attendance>() {
						public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
							Attendance a = new Attendance();
							a.setId(rs.getInt("id"));
							a.setEmployeeId(rs.getInt("employee_id"));
							a.setDate(rs.getDate("a_date"));
							a.setInTime(rs.getTime("in_time"));
							a.setOutTime(rs.getTime("out_time"));
							return a;
						}
					}); 
			return attendance;
		}
	}
