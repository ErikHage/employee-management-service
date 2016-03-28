package com.ehage.ems.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ehage.ems.dao.EmployeeDao;
import com.ehage.ems.exception.NoSuchRecordException;
import com.ehage.ems.helper.EMSTestHelper;
import com.ehage.ems.model.Employee;
import com.ehage.ems.service.EmployeeServiceImpl;


public class TestEmployeeServiceImpl {

	@Mock
	private EmployeeDao mockDao;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreate() {
		Employee emp1 = EMSTestHelper.getEmployee("T1");
		emp1.setRecordVersion(1);
		
		when(mockDao.create(emp1)).thenReturn(Optional.of(emp1));
		
		Employee empOut = employeeService.create(emp1);
		assertEquals(emp1, empOut);
		
		verify(mockDao, times(1)).create(emp1);
		verifyNoMoreInteractions(mockDao);
	}

	@Test
	public void testReadOne() {
		Employee emp1 = EMSTestHelper.getEmployee("T1");
		emp1.setRecordVersion(1);
		
		when(mockDao.readById("T1")).thenReturn(Optional.of(emp1));
		
		Employee empOut = employeeService.readOne("T1");
		assertEquals(emp1, empOut);
		
		verify(mockDao, times(1)).readById("T1");
		verifyNoMoreInteractions(mockDao);
	}
	
	@Test(expected=NoSuchRecordException.class)
	public void testReadOneThrowNoSuchRecordException() {
		Employee emp1 = EMSTestHelper.getEmployee("T1");
		emp1.setRecordVersion(1);
		
		when(mockDao.readById("T1")).thenThrow(new NoSuchRecordException("No record found with id = 1", "T1"));
		
		employeeService.readOne("T1");
		
		fail("Expected NoSuchRecordException, but wasn't thrown");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testReadAll() {
		Employee emp1 = EMSTestHelper.getEmployee("T1");
		emp1.setRecordVersion(1);
		Employee emp2 = EMSTestHelper.getEmployee("T2");
		emp2.setRecordVersion(1);
		
		List<Optional<Employee>> list = 
				Arrays.asList(new Optional[] {Optional.of(emp1), Optional.of(emp2)});
		
		when(mockDao.readAll()).thenReturn(list);
		
		List<Employee> listIn = Arrays.asList(new Employee[] {emp1, emp2});
		List<Employee> listOut = employeeService.readAll();
		assertEquals(listIn, listOut);
		
		verify(mockDao, times(1)).readAll();
		verifyNoMoreInteractions(mockDao);
	}

	@Test
	public void testUpdate() {
		Employee emp1 = EMSTestHelper.getEmployee("T1");
		emp1.setRecordVersion(1);
		
		when(mockDao.update(emp1)).thenReturn(Optional.of(emp1.setRecordVersion(2)));
		
		Employee empOut = employeeService.update(emp1);
		
		assertEquals(empOut.getRecordVersion(), 2);
		
		verify(mockDao, times(1)).update(emp1);
		verifyNoMoreInteractions(mockDao);
	}

	@Test(expected=NoSuchRecordException.class)
	public void testUpdateThrowNoSuchRecordException() {
		Employee emp1 = EMSTestHelper.getEmployee("T1");
		emp1.setRecordVersion(1);
		
		when(mockDao.update(emp1)).thenThrow(new NoSuchRecordException("No record found with id = 1", "T1"));
		
		employeeService.update(emp1);
		
		fail("Expected NoSuchRecordException, but wasn't thrown");
	}
	
	@Test
	public void testDeleteById() {
		employeeService.deleteById("T1");		

		verify(mockDao, times(1)).deleteById("T1");
		verifyNoMoreInteractions(mockDao);
	}

}
