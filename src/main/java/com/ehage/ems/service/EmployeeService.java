package com.ehage.ems.service;

import java.util.List;

import com.ehage.ems.model.Employee;

public interface EmployeeService {

	public Employee create(Employee employee);
	public Employee readOne(String employeeId);
	public List<Employee> readAll();
	public Employee update(Employee employee);
	public boolean deleteById(String employeeId);
	
}
