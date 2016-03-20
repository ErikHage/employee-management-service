package com.ehage.ems.dao;

import java.util.List;
import java.util.Optional;

import com.ehage.ems.model.Employee;

public interface EmployeeDao {

	public Optional<Employee> create(Employee employee);
	public Optional<Employee> readById(String id);
	public Optional<List<Employee>> readAll();
	public Optional<Employee> update(Employee employee);
	public void deleteById(String employeeId);
	
}
