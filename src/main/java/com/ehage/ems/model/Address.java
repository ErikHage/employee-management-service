package com.ehage.ems.model;

public class Address {

	private String addressId;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof Address)
		{
			Address other = (Address) obj;					
			return street.equals(other.street)
				&& city.equals(other.city)
				&& ((state==null && other.state==null) || state.equals(other.state))
				&& ((zipCode==null && other.zipCode==null) || zipCode.equals(other.zipCode))
				&& country.equals(other.country);
		}		
		return false;
	}
	
	@Override
	public int hashCode() 
	{
		return this.addressId.hashCode();
	}
	
	@Override
	public String toString() 
	{		
		StringBuilder addressString = new StringBuilder(street + ", " + city + ", ");		
		if(state != null)
			addressString.append(state + " ");		
		if(zipCode != null)
			addressString.append(zipCode + " ");		
		addressString.append(country);		
		return addressString.toString();
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}
	
	public String getCountry() 	{
		return country;
	}	

	public String getAddressId() {
		return addressId;
	}	

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public void setAddressId(String addressID) {
		this.addressId = addressID;
	}
	
	
}
