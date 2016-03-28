package com.ehage.ems.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehage.ems.dao.EmployeeDao;
import com.ehage.ems.exception.NoSuchRecordException;
import com.ehage.ems.exception.PersistenceException;
import com.ehage.ems.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public Employee create(Employee employee) {
		Optional<Employee> optEmployee = employeeDao.create(employee);
		return optEmployee
			.orElseThrow(() -> new PersistenceException("Error persisting employee"));
	}

	@Override
	public Employee readOne(String employeeId) {
		Optional<Employee> optEmployee = employeeDao.readById(employeeId);
		return optEmployee
			.orElseThrow(() -> new NoSuchRecordException("No record found for employee with id = " + employeeId, employeeId));
	}

	@Override
	public List<Employee> readAll() {
		return employeeDao.readAll()
				.parallelStream()
				.filter(optEmployee -> optEmployee.get() != null)
				.map(optEmployee -> optEmployee.get())
				.collect(Collectors.toList());
	}

	@Override
	public Employee update(Employee employee) {
		String id = employee.getEmployeeId();
		Optional<Employee> optEmployee = employeeDao.update(employee);
		return optEmployee
			.orElseThrow(() -> new NoSuchRecordException("No record found for employee with id = " + id, id));
	}

	@Override
	public void deleteById(String employeeId) {
		employeeDao.deleteById(employeeId);
	}

}
