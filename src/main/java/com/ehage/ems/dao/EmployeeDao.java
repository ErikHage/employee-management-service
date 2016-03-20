package com.ehage.ems.dao;

import java.util.List;

import com.ehage.ems.model.Employee;

public interface EmployeeDao {

	public Employee create(Employee employee);
	public Employee readById(String id);
	public List<Employee> readAll();
	public Employee update(Employee employee);
	public void deleteById(String employeeId);
	
}
