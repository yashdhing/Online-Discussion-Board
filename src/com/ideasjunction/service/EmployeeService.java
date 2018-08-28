package com.ideasjunction.service;

import com.ideasjunction.model.*;
import java.util.List;

public interface EmployeeService {
	Employee getEmpById(long empId);
	Employee getEmpByUsername(String username);
	List<Employee> getAllEmployees();
	List<Employee> getUsers();
	void addEmployee(Employee employee);
	boolean isEmployeeExist(Employee employee);
}
