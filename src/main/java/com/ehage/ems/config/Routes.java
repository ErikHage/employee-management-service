package com.ehage.ems.config;

public class Routes {

	public static final String MAIN_PAGE = "/";
	
	public static final String EMPLOYEE_CREATE     = "/api/ems/employee/create";
	public static final String EMPLOYEE_READ_ALL   = "/api/ems/employee/profiles";
	public static final String EMPLOYEE_READ_ONE   = "/api/ems/employee/profiles/{employeeId}";
	public static final String EMPLOYEE_UPDATE     = "/api/ems/employee/update";
	public static final String EMPLOYEE_DELETE_ONE = "/api/ems/employee/delete/{employeeId}";
	
}
