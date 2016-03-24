package com.ehage.ems.helper;

import java.time.LocalDateTime;

import com.ehage.ems.model.Address;
import com.ehage.ems.model.Employee;

public class EMSTestHelper {

	public static Employee getEmployee(String employeeId) {
		Employee e = new Employee();
		Address a = new Address();

		a.setAddressId("1");
		a.setStreet("30-34 32nd street Apt 1C");
		a.setCity("Astoria");
		a.setState("NY");
		a.setZipCode("11102");
		a.setCountry("USA");		

		e.setEmployeeId(employeeId);
		e.setFirstName("Erik");
		e.setLastName("Hage");
		e.setPhone("6092906433");
		e.setEmail("ehage4@gmail.com");
		e.setTitle("Manager");			
		e.setAddress(a);
		e.setActive(true);
		e.setWage(15.75);
		e.setBirthDate(LocalDateTime.of(1986,10,17,0,0,0));
		e.setStartDate(LocalDateTime.now());		
		return e;
	}
	
}
