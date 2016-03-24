package com.ehage.ems.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ehage.ems.helper.EMSTestHelper;
import com.ehage.ems.model.Employee;

public class TestEmployeeLocalStorage {

	private EmployeeLocalStorage storage;
	
	@Before
	public void setUp() {
		storage = new EmployeeLocalStorage();
	}
	
	@After
	public void cleanUp() {
		storage.deleteAll();
		
	}
	
	@Test
	public void testCreate() {
		Employee e1 = EMSTestHelper.getEmployee("T1");	
		Optional<Employee> eopt1 = storage.create(e1);		
		Employee eout1 = eopt1.get();
		assertEquals(eout1.getFirstName(), "Erik");	
	}

	@Test
	public void testReadAll() {
		Employee e1 = EMSTestHelper.getEmployee("T1");	
		storage.create(e1);	
		Employee e2 = EMSTestHelper.getEmployee("T2");	
		storage.create(e2);		
				
		List<Employee> list = storage.readAll();
		
		assertEquals(list.size(), 2);		
	}

	@Test
	public void testReadById() {
		Employee e1 = EMSTestHelper.getEmployee("T1");	
		storage.create(e1);	
				
		Optional<Employee> eopt1 = storage.readById("T1"); 
		assertTrue(eopt1.isPresent());
		assertEquals(eopt1.get().getEmployeeId(), "T1");
	}

	@Test
	public void testUpdate() {
		Employee e1 = EMSTestHelper.getEmployee("T1");
		Employee e2 = EMSTestHelper.getEmployee("T1");
		e2.setFirstName("ErikUpdated");
	
		Optional<Employee> eopt1 = storage.create(e1);		
		Employee eout1 = eopt1.get();
		assertEquals(eout1.getFirstName(), "Erik");	
		
		Optional<Employee> eopt2 = storage.update(e2);
		Employee eout2 = eopt2.get();		
		assertEquals(eout2.getFirstName(), "ErikUpdated");
		
	}

	@Test
	public void testDeleteById() {
		Employee e1 = EMSTestHelper.getEmployee("T1");	
		storage.create(e1);
		assertEquals(storage.readAll().size(), 1);
		
		storage.deleteById("T1");
		assertEquals(storage.readAll().size(), 0);
		
	}

}
