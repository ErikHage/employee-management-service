package com.ehage.ems.controller;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*; 

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ehage.ems.EMServiceApplication;
import com.ehage.ems.config.Constants;
import com.ehage.ems.config.Routes;
import com.ehage.ems.exception.PersistenceException;
import com.ehage.ems.helper.EMSTestHelper;
import com.ehage.ems.helper.EmployeeHelper;
import com.ehage.ems.model.Employee;
import com.ehage.ems.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes 
		= {EMServiceApplication.class})
public class TestEmployeeRestController {

	@Mock
	private EmployeeService mockEmployeeService;

	@InjectMocks
	private EmployeeRestController controller;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(controller).build();  
	}

	@Test
	public void testCreateEmployee_GivenEmployee_ExpectSuccess() throws Exception {
		Employee employee = EMSTestHelper.getEmployee("1");
		String json = EmployeeHelper.getEmployeeJson(employee);

		when(mockEmployeeService.create(employee)).thenReturn(employee);

		mockMvc.perform(post(Routes.EMPLOYEE_CREATE)
				.contentType(Constants.APPLICATION_JSON)
				.content(json))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(Constants.APPLICATION_JSON))
				.andExpect(jsonPath("employeeId", is("1"))
		);
		
		verify(mockEmployeeService, times(1)).create(employee);
		verifyNoMoreInteractions(mockEmployeeService);
	}
	
	@Test
	public void testReadOne_GivenValidEmployeeId_ExpectEmployeeFound() throws Exception {
		Employee employee = EMSTestHelper.getEmployee("1");
		
		when(mockEmployeeService.readOne("1")).thenReturn(employee);
		
		mockMvc.perform(get(Routes.EMPLOYEE_READ_ONE.replace("{employeeId}", "1")))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(Constants.APPLICATION_JSON))
				.andExpect(jsonPath("employeeId", is("1"))
		);
		
		verify(mockEmployeeService, times(1)).readOne("1");
		verifyNoMoreInteractions(mockEmployeeService);
	}
	
	@Test
	public void testReadOne_GivenInValidEmployeeID_ExpectEmployeeNotFound() throws Exception {
		when(mockEmployeeService.readOne("1")).thenThrow(
				new PersistenceException("No employee found with id = 1", "1"));
		
		mockMvc.perform(get(Routes.EMPLOYEE_READ_ONE.replace("{employeeId}", "1")))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(Constants.APPLICATION_JSON))
				.andExpect(jsonPath("message", is("No employee found with id = 1"))
		);
		
		verify(mockEmployeeService, times(1)).readOne("1");
		verifyNoMoreInteractions(mockEmployeeService);
	}
	
	@Test
	public void testReadAllEmployees_GivenCall_ExpectListOfEmployees() throws Exception {
		List<Employee> employees = new ArrayList<Employee>();		
		employees.add(EMSTestHelper.getEmployee("1"));
		employees.add(EMSTestHelper.getEmployee("2"));
					
		when(mockEmployeeService.readAll()).thenReturn(employees);
		
		mockMvc.perform(get(Routes.EMPLOYEE_READ_ALL))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(Constants.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2))
		);
		
		verify(mockEmployeeService, times(1)).readAll();
		verifyNoMoreInteractions(mockEmployeeService);
	}	

	@Test
	public void testUpdateEmployee_GivenUpdatedEmployee_ExpectUpdatedEmployee() throws Exception {
		Employee employee = EMSTestHelper.getEmployee("1");
		String json = EmployeeHelper.getEmployeeJson(employee);

		when(mockEmployeeService.update(employee)).thenReturn(employee);

		mockMvc.perform(post(Routes.EMPLOYEE_UPDATE)
				.contentType(Constants.APPLICATION_JSON)
				.content(json))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(Constants.APPLICATION_JSON))
				.andExpect(jsonPath("employeeId", is("1"))
		);
		
		verify(mockEmployeeService, times(1)).update(employee);
		verifyNoMoreInteractions(mockEmployeeService);
	}	

	@Test
	public void testUpdateEmployee_GivenInvalidIdInUpdate_ExpectEmployeeNotFound() throws Exception {
		Employee employee = EMSTestHelper.getEmployee("1");
		String json = EmployeeHelper.getEmployeeJson(employee);
		
		when(mockEmployeeService.update(employee)).thenThrow(
				new PersistenceException("No employee found with id = 1","1"));

		mockMvc.perform(post(Routes.EMPLOYEE_UPDATE)
				.contentType(Constants.APPLICATION_JSON)
				.content(json))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(Constants.APPLICATION_JSON))
				.andExpect(jsonPath("message", is("No employee found with id = 1"))
		);
		
		verify(mockEmployeeService, times(1)).update(employee);
		verifyNoMoreInteractions(mockEmployeeService);
	}
	
	@Test
	public void testDeleteEmployee_GivenValidEmployeeId_ExpectSuccess() throws Exception {
		mockMvc.perform(delete(Routes.EMPLOYEE_DELETE_ONE.replace("{employeeId}", "1")))
				.andDo(print())
				.andExpect(status().isOk());
		
		verify(mockEmployeeService, times(1)).deleteById("1");
		verifyNoMoreInteractions(mockEmployeeService);
	}
	
	@Test
	public void testDeleteEmployee_GivenInvalidEmployeeId_ExpectEmployeeNotFound() throws Exception {
		doThrow(new PersistenceException("No employee found with id = 1","1"))
			.when(mockEmployeeService).deleteById("1");

		mockMvc.perform(delete(Routes.EMPLOYEE_DELETE_ONE.replace("{employeeId}", "1")))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(Constants.APPLICATION_JSON))
				.andExpect(jsonPath("message", is("No employee found with id = 1"))
		);
		
		verify(mockEmployeeService, times(1)).deleteById("1");
		verifyNoMoreInteractions(mockEmployeeService);
	}

}
