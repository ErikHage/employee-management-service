package com.ehage.ems.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.ehage.ems.model.Employee;

@Repository
public class EmployeeLocalStorage implements EmployeeDao {
	
	private static final Map<String, Optional<Employee>> employeeMap
		= new HashMap<String, Optional<Employee>>();	
	
	@Override
	public Optional<Employee> create(Employee employee) {
		employee.setRecordVersion(1);
		employeeMap.put(employee.getEmployeeId(), Optional.of(employee));
		return employeeMap.get(employee.getEmployeeId());
	}

	@Override
	public List<Employee> readAll() {
		return employeeMap.values()
				.parallelStream()
				.map(optionalEmployee -> optionalEmployee.get())
				.collect(Collectors.toList()
		);
	}
	
	@Override
	public Optional<Employee> readById(String id) {
		return employeeMap.values()
				.parallelStream()
				.filter((opt) -> opt.isPresent() 
								 && opt.get().getEmployeeId() == id)
				.findAny()
				.orElse(Optional.empty());				
	}

	@Override
	public Optional<Employee> update(Employee employee) {		
		Optional<Employee> employeeOpt = 
				employeeMap.values()
					.parallelStream()
					.filter((opt) -> opt.isPresent()
							&& opt.get().getEmployeeId() == employee.getEmployeeId())					
					.map(e -> employee.setRecordVersion(e.get().getRecordVersion()+1))
					.findAny();	
		
		employeeMap.put(employee.getEmployeeId(), employeeOpt);		
		
		return employeeMap.get(employee.getEmployeeId());
	}

	@Override
	public void deleteById(String employeeId) {
		employeeMap.get(employeeId).ifPresent(emp -> employeeMap.remove(employeeId));		
	}
	
	protected void deleteAll() {
		employeeMap.clear();
	}
		

}
