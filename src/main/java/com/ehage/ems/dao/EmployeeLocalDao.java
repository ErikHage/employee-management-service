package com.ehage.ems.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.ehage.ems.model.Employee;

@Repository
public class EmployeeLocalDao implements EmployeeDao {
	
	private static final Map<String, Employee> employeeMap
		= new HashMap<String, Employee>();	
	
	@Override
	public Optional<Employee> create(Employee employee) {
		employee.setRecordVersion(1);
		employeeMap.put(employee.getEmployeeId(), employee);
		return Optional.of(employeeMap.get(employee.getEmployeeId()));
	}

	@Override
	public List<Employee> readAll() {
		return employeeMap.values()
				.parallelStream()
				.collect(Collectors.toList()
		);
	}
	
	@Override
	public Optional<Employee> readById(String id) {
		return employeeMap.values()
				.parallelStream()
				.filter((emp) -> emp.getEmployeeId() == id)
				.findAny();			
	}

	@Override
	public Optional<Employee> update(Employee employee) {		
		Optional<Employee> employeeOpt = 
				employeeMap.values()
					.parallelStream()
					.filter((emp) -> emp.getEmployeeId() == employee.getEmployeeId())					
					.map((emp) -> employee.setRecordVersion(emp.getRecordVersion()+1))
					.findAny();	
		
		if(employeeOpt.isPresent()) {
			employeeMap.put(employee.getEmployeeId(), employeeOpt.get());
		}
		
		return employeeOpt;
	}

	@Override
	public boolean deleteById(String employeeId) {
		if(employeeMap.containsKey(employeeId)) {
			employeeMap.remove(employeeId);
			return true;
		}
		return false;		
	}
	
	protected void deleteAll() {
		employeeMap.clear();
	}
		

}
