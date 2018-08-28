package com.ideasjunction.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ideasjunction.model.Employee;

public class EmployeeRowMapper implements RowMapper{	
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