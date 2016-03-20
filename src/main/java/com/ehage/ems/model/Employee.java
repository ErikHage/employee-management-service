package com.ehage.ems.model;

import java.time.LocalDateTime;

public class Employee {
	
	private String employeeId;
	private int recordVersion;
	
	private String firstName;
	private String lastName;
	private String phone; 
	private String email; 
	private String title;
	
	private Address address;
	
	private LocalDateTime birthDate;
	private LocalDateTime startDate;
	
	private boolean isActive;

	private double wage;
	
	public Employee()
	{
		//empty constructor, use EmployeeFactory
	}
	
	/**
	 * Returns the String value of this Employee in the format of id number, 
	 * first name, and then last name.
	 * @return String - employeeID: First_Name Last_Name
	 */
	@Override
	public String toString() {
		return employeeId + ": " + firstName + " " + lastName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + recordVersion;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (recordVersion != other.recordVersion)
			return false;
		return true;
	}

	public String getEmployeeId() {
		return employeeId;
	}
	
	public int getRecordVersion() {
		return recordVersion;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Address getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public double getWage() {
		return wage;
	}
	
	public String getTitle() {
		return title;
	}
	
	public LocalDateTime getBirthDate() {
		return birthDate;
	}
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public Employee setRecordVersion(int recordVersion) {
		this.recordVersion = recordVersion;
		return this;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setWage(double wage) {
		this.wage = wage;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}
	
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
