package com.ehage.ems.service;

import java.util.List;
import java.util.Optional;

import com.ehage.ems.exception.NoSuchRecordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehage.ems.dao.EmployeeDao;
import com.ehage.ems.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public Employee create(Employee employee) {
		return employeeDao.create(employee).get();
	}

	@Override
	public Employee readOne(String employeeId) {
		return employeeDao.readById(employeeId).orElseThrow(NoSuchRecordException::new);
	}

	@Override
	public List<Employee> readAll() {
		return employeeDao.readAll().orElseThrow(NoSuchRecordException::new);
	}

	@Override
	public Employee update(Employee employee) {
		return employeeDao.update(employee).orElseThrow(NoSuchRecordException::new);
	}

	@Override
	public void deleteById(String employeeId) {
		employeeDao.deleteById(employeeId);
	}


}
