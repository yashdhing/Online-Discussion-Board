package com.ideasjunction.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ideasjunction.model.Employee;

import com.ideasjunction.model.*;
import com.ideasjunction.service.EmployeeService;
import com.ideasjunction.service.EmployeeServiceImplementation;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;

import java.sql.ResultSet;



import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	 
	@RequestMapping(value = "/employee/{empId}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable("empId") long empId) { 
		Employee employee = employeeService.getEmpById(empId);
	       if (employee == null) {
	           return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	       }
	       return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public ResponseEntity<Employee> getEmployee( @RequestBody LoginCredentials loginCredentials, HttpServletRequest request) {
		Employee employee = employeeService.getEmpByUsername(loginCredentials.getUsername());
		if(employee != null && employee.getPassword().equals(loginCredentials.getPassword())){
			HttpSession session = request.getSession();
			session.setAttribute("employee", employee);
			Employee emp = (Employee)session.getAttribute("employee");
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		Employee nullEmployee = null;
		return new ResponseEntity<Employee>(nullEmployee, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/addemployee/", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addEmployee( @RequestBody Employee employee, HttpServletRequest request) {
		if (employeeService.isEmployeeExist(employee)) {
	        return new ResponseEntity<Boolean>(new Boolean(false),HttpStatus.OK);
	    }
		employeeService.addEmployee(employee);
		return new ResponseEntity<Boolean>(new Boolean(true),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if(employees.isEmpty()){
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
	@RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getUsers() {
        List<Employee> employees = employeeService.getUsers();
        if(employees.isEmpty()){
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
}

class EmployeeRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setEmpId(rs.getInt("EmpId"));
		employee.setUserName(rs.getString("userName"));
		employee.setEmployeeFirstName(rs.getString("firstName"));
		employee.setEmployeeLastName(rs.getString("lastName"));
		employee.setPassword(rs.getString("password"));
		employee.setUserType(rs.getInt("userType"));
		return employee;
	}
}

class LoginCredentials{
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginCredentials [username =" + username + ", password="
				+ password + "]";
	}
}