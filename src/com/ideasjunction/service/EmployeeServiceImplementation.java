package com.ideasjunction.service;

import com.ideasjunction.helper.*;
import com.ideasjunction.model.*;

import java.util.List;
/*import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;

import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;

@Repository
public class EmployeeServiceImplementation implements EmployeeService{
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public EmployeeServiceImplementation(DataSource dataSource){
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Employee getEmpById(long empId){
		try{
			String query = "SELECT * FROM employee WHERE empId = ?";
			Employee employee = (Employee)jdbcTemplate.queryForObject(query, new Object[] { empId }, new EmployeeRowMapper());
			return employee;
		}
		catch(EmptyResultDataAccessException e){
			Employee employee = null;
			return employee;
		}
	}
	
	public Employee getEmpByUsername(String username){
		try{
    		String query = "SELECT * FROM employee WHERE username = ?;";

		    Employee employee = (Employee)jdbcTemplate.queryForObject(query, new Object[] { username }, new EmployeeRowMapper());
		    return employee;
		}
		catch(EmptyResultDataAccessException e){
			Employee employee = null;
			return employee;
		}
  	}
	
	public void addEmployee(Employee employee){
		try {   
			String query ="INSERT INTO employee (username, password, firstName,lastName, usertype) VALUES('" +  employee.getUserName() + "','" + employee.getPassword() + "','" + employee.getEmployeeFirstName() + "','" + employee.getEmployeeLastName() + "','" + employee.getUserType() + "')";
			jdbcTemplate.update(query);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean isEmployeeExist(Employee employee) {
		return getEmpByUsername(employee.getUserName()) != null;
	}

	public List<Employee> getAllEmployees(){
		try{
			String query = "SELECT * FROM employee;";
//			List<Employee> employees = jdbcTemplate.query(query, new BeanPropertyRowMapper(Employee.class));
			List<Employee> employees = jdbcTemplate.query(query, new Object[] { }, new RowMapper<Employee>(){
				public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
					Employee e = new Employee();
		            e.setEmpId(rs.getInt(1));
		            e.setUserName(rs.getString(2));
		            e.setPassword(rs.getString(3));
		            e.setEmployeeFirstName(rs.getString(4));
		            e.setEmployeeLastName(rs.getString(5));
		            e.setUserType(rs.getInt(6));
		            return e;
		        }
			});
			return employees;
		}
		catch(EmptyResultDataAccessException e){
			List<Employee> employees = null;
			return employees;
		}
	}
	public List<Employee> getUsers(){
		try{
			String query = "SELECT * FROM employee WHERE usertype=0;";
//			List<Employee> employees = jdbcTemplate.query(query, new BeanPropertyRowMapper(Employee.class));
			List<Employee> employees = jdbcTemplate.query(query, new Object[] { }, new RowMapper<Employee>(){
				public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
					Employee e = new Employee();
		            e.setEmpId(rs.getInt(1));
		            e.setUserName(rs.getString(2));
		            e.setPassword(rs.getString(3));
		            e.setEmployeeFirstName(rs.getString(4));
		            e.setEmployeeLastName(rs.getString(5));
		            e.setUserType(rs.getInt(6));
		            return e;
		        }
			});
			return employees;
		}
		catch(EmptyResultDataAccessException e){
			List<Employee> employees = null;
			return employees;
		}
	}
}
