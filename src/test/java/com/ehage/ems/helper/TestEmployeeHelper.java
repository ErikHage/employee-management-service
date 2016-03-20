package com.ehage.ems.helper;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.util.StringUtils;

public class TestEmployeeHelper {

	@Test
	public void testLocalDateToJsonFormat() {
		LocalDateTime dt = LocalDateTime.of(1999,1,1,1,1,1,150);
		String expected = "1999,1,1,1,1,1,150";		
		assertEquals(EmployeeHelper.localDateToJsonFormat(dt), expected);
	}

	@Test
	public void testJsonFormatToLocalDateTime() {
		LocalDateTime expected = LocalDateTime.of(1999,1,1,1,1,1,150);
		String json = "1999,1,1,1,1,1,150";	
		assertEquals(EmployeeHelper.jsonFormatToLocalDateTime(json), expected);
	}

	@Test
	public void testGetFile() {
		String fileContents = EmployeeHelper.getFile("json/employee.json");
		assertFalse(StringUtils.isEmpty(fileContents));
	}

}
