package com.ehage.ems.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.ehage.ems.exception.NoSuchRecordException;
import com.ehage.ems.model.Employee;

@Repository
public class EmployeeLocalStorage implements EmployeeDao {
	
	private static final Map<String, Optional<Employee>> employeeMap
		= new HashMap<>();
	
	private static final AtomicLong employeeIdSequence
		= new AtomicLong(0);		

	/**
	 * Left this one alone for you to play with :-)
	 * */
	@Override
	public Optional<Employee> create(Employee employee) {
		String employeeId = String.valueOf(employeeIdSequence.getAndIncrement());
		employee.setEmployeeId(employeeId);
		employee.setRecordVersion(1);
		employeeMap.put(employeeId, Optional.of(employee));
		return employeeMap.get(employeeId);
	}

	@Override
	public Optional<List<Employee>> readAll() {
		return Optional.of(employeeMap.values()
				.parallelStream()
				.map(optionalEmployee -> optionalEmployee.get())
				.collect(Collectors.toList())
		);
	}
	
	@Override
	public Optional<Employee> readById(String id) {
			return employeeMap.get(id);

	}

	@Override
	public Optional<Employee> update(Employee employee) {
		Optional<Employee> employee1 = employeeMap.get(employee.getEmployeeId())
				   .map(emp -> emp.setRecordVersion(emp.getRecordVersion() + 1));


		employeeMap.put(employee1.map(e -> e.getEmployeeId()).get(),employee1);
		return employee1;


	}

	@Override
	public void deleteById(String employeeId) {
		employeeMap.get(employeeId)
				.ifPresent(emp -> employeeMap.remove(employeeId));
	}
}
