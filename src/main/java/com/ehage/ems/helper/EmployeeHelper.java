package com.ehage.ems.helper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.ehage.ems.model.Employee;

public class EmployeeHelper {

	public static String getEmployeeJson(Employee e) {
		String jsonTemplate = getFile("json/employee.json");	
		String json = String.format(jsonTemplate, 
				e.getEmployeeId(),
				e.getRecordVersion(),
				e.getFirstName(),
				e.getLastName(),
				e.getPhone(),
				e.getEmail(),
				e.getTitle(),
				e.getAddress().getAddressId(),
				e.getAddress().getStreet(),
				e.getAddress().getCity(),
				e.getAddress().getState(),
				e.getAddress().getZipCode(),
				e.getAddress().getCountry(),
				localDateToJsonFormat(e.getBirthDate()),
				localDateToJsonFormat(e.getStartDate()),
				e.getWage(),
				e.isActive());		

		return json;
	}
	
	public static String localDateToJsonFormat(LocalDateTime dt) {
		StringBuilder sb = new StringBuilder();
		sb.append(dt.getYear() + ",");
		sb.append(dt.getMonthValue() + ",");
		sb.append(dt.getDayOfMonth() + ",");
		sb.append(dt.getHour() + ",");
		sb.append(dt.getMinute() + ",");
		sb.append(dt.getSecond() + ",");
		sb.append(dt.getNano());				
		return sb.toString();
	}	
	
	public static LocalDateTime jsonFormatToLocalDateTime(String json) {
		String[] items = json.split(",");
		LocalDateTime dt = LocalDateTime
				.of(Integer.parseInt(items[0]), 
					Integer.parseInt(items[1]), 
					Integer.parseInt(items[2]), 
					Integer.parseInt(items[3]), 
					Integer.parseInt(items[4]), 
					Integer.parseInt(items[5]), 
					Integer.parseInt(items[6]));		
		return dt;
	}
	
	public static String getFile(String filename) {
		StringBuilder result = new StringBuilder();		
		ClassLoader classLoader = EmployeeHelper.class.getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());
		
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}
			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return result.toString();
	}
	
}
