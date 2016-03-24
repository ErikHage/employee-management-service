package com.ehage.ems.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ehage.ems.config.Constants;
import com.ehage.ems.config.Routes;
import com.ehage.ems.exception.NoSuchRecordException;
import com.ehage.ems.exception.RestExceptionMessage;
import com.ehage.ems.model.Employee;
import com.ehage.ems.service.EmployeeService;

@RestController
public class EmployeeRestController {
	
	private static final Log logger = LogFactory.getLog(EmployeeRestController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = Routes.EMPLOYEE_CREATE,
			consumes=Constants.APPLICATION_JSON,
			produces=Constants.APPLICATION_JSON,
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Employee createEmployee(@RequestBody Employee employee) {
		logger.debug("endpoint: ..." + Routes.EMPLOYEE_CREATE);
		return employeeService.create(employee);
	}
	
	@RequestMapping(value = Routes.EMPLOYEE_READ_ONE,
			produces=Constants.APPLICATION_JSON,
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Employee getEmployeeById(@PathVariable String employeeId) {
		logger.debug("endpoint: ..." + Routes.EMPLOYEE_READ_ONE.replace("{employeeId}", employeeId));
		return employeeService.readOne(employeeId);
	}
	
	@RequestMapping(value = Routes.EMPLOYEE_READ_ALL,
			produces=Constants.APPLICATION_JSON,
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getAllEmployees() {
		logger.debug("endpoint: ..." + Routes.EMPLOYEE_READ_ALL);
		return employeeService.readAll();
	}
	
	@RequestMapping(value = Routes.EMPLOYEE_UPDATE,
			consumes=Constants.APPLICATION_JSON,
			produces=Constants.APPLICATION_JSON,
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Employee updateEmployee(@RequestBody Employee employee) {
		logger.debug("endpoint: ..." + Routes.EMPLOYEE_UPDATE);
		return employeeService.update(employee);
	}
	
	@RequestMapping(value = Routes.EMPLOYEE_DELETE_ONE,
			produces=Constants.APPLICATION_JSON,
			method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteEmployeeById(@PathVariable String employeeId) {
		logger.debug("endpoint: ..." + Routes.EMPLOYEE_DELETE_ONE.replace("{employeeId}", employeeId));
		employeeService.deleteById(employeeId);
	}
	
	@ExceptionHandler(NoSuchRecordException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public RestExceptionMessage handle(NoSuchRecordException ex) {		
		RestExceptionMessage message = new RestExceptionMessage(ex.getMessage(), ex.getId());		
		return message;
	}
	
	
}
