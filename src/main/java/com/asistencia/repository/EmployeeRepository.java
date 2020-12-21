package com.asistencia.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.asistencia.models.Employee;
import java.sql.*;

@Repository
public class EmployeeRepository  
{	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
		String url="jdbc:mysql://localhost:3306/mydb?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false";
		String user="root";
		String password="root";
		
		public static void initialize() throws ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		
		public int save(Employee employee) throws SQLException, ClassNotFoundException {
			initialize();
			String query= "insert into Employee(email, username, department, password_e) values(?,?,?,?)";
			
			Connection con = DriverManager.getConnection(url, user, password);
		    PreparedStatement st = con.prepareStatement(query);
			st.setString(1, employee.getEmail());
			st.setString(2, employee.getUserName());
			st.setString(3, employee.getDepartment());
			st.setString(4, employee.getPassword());
			int c = st.executeUpdate();
			return c;
		}
		
		public Employee findByEmail(String email) throws ClassNotFoundException, SQLException {
			initialize();
			String query = "SELECT * FROM Employee WHERE email = ?";
			
			return jdbcTemplate.query(query,  new Object[]{email}, rs -> 
			{
					if (rs.next()) 
					{
					Employee e = new Employee();
					e.setId(rs.getInt("id"));
					e.setEmail(rs.getString("email"));
					e.setUserName(rs.getString("userName"));
					e.setDepartment(rs.getString("department"));
					e.setPassword(rs.getString("password_e"));
					return e;
				} 	else
				{
						 return null;
				} 
			}); 
		}	
		
		public void deleteAfterLogin(String email) throws ClassNotFoundException, SQLException {
			
			initialize();
			String query = "DELETE FROM Employee WHERE email = ?";
		
				jdbcTemplate.update(query, email);
		} 
		
}


