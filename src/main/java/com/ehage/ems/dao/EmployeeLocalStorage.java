package com.ehage.ems.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.ehage.ems.exception.NoSuchRecordException;
import com.ehage.ems.model.Employee;

@Repository
public class EmployeeLocalStorage implements EmployeeDao {
	
	private static final Map<String, Employee> employeeMap
		= new HashMap<String, Employee>();
	
	private static final AtomicLong employeeIdSequence
		= new AtomicLong(0);		
	
	@Override
	public Employee create(Employee employee) {
		String employeeId = String.valueOf(employeeIdSequence.getAndIncrement());
		employee.setEmployeeId(employeeId);
		employee.setRecordVersion(1);
		employeeMap.put(employeeId, employee);
		return employeeMap.get(employeeId);
	}

	@Override
	public List<Employee> readAll() {
		return (List<Employee>) employeeMap.values();
	}
	
	@Override
	public Employee readById(String id) {
		if(employeeMap.containsKey(id)) {
			return employeeMap.get(id);
		} else {
			throw new NoSuchRecordException("No employee found with id = " + id);
		}
	}

	@Override
	public Employee update(Employee employee) {
		if(employeeMap.containsKey(employee.getEmployeeId())) {
			employee.setRecordVersion(employee.getRecordVersion()+1);
			employeeMap.put(employee.getEmployeeId(), employee);
			return employeeMap.get(employee.getEmployeeId());
		} else {
			throw new NoSuchRecordException("No employee found with id = " + employee.getEmployeeId());
		}
	}

	@Override
	public void deleteById(String employeeId) {
		if(employeeMap.containsKey(employeeId)) {
			employeeMap.remove(employeeId);
		} else {
			throw new NoSuchRecordException("No employee found with id = " + employeeId);
		}	}

}
