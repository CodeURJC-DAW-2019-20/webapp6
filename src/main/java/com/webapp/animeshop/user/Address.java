package com.webapp.animeshop.user;

import javax.persistence.Embeddable;

@Embeddable
public class Address{
	
	private String shippingname;
	private String lastname;
	private String company;
	private String number;
	private String email;
	private String street;
	private String floor;
	private String city;
	private String country;
	private String zipcode;
	
	public Address() {
		this.shippingname = "";
		this.lastname = "";
		this.company = "";
		this.number = "";
		this.email = "";
		this.street = "";
		this.floor = "";
		this.city = "";
		this.country = "";
		this.zipcode = "";
	}
	
	public Address(Address address) {
		this.shippingname = address.shippingname;
		this.lastname = address.lastname;
		this.company = address.company;
		this.number = address.number;
		this.email = address.email;
		this.street = address.street;
		this.floor = address.floor;
		this.city = address.city;
		this.country = address.country;
		this.zipcode = address.zipcode;
	}
	
	public Address(String name, String lastname, String company, String number, String email,
				   String street, String floor, String city, String country, String zipcode) {
		this.shippingname = name;
		this.lastname = lastname;
		this.company = company;
		this.number = number;
		this.email = email;
		this.street = street;
		this.floor = floor;
		this.city = city;
		this.country = country;
		this.zipcode = zipcode;
	}
	
	public String getName() {
		return shippingname;
	}
	public void setName(String name) {
		this.shippingname = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@Override
	public String toString() {
		return "\nNombre: " + this.shippingname + " "+ " Appelidos: " + this.lastname + "\nNúmero: " + this.number
				+ "\nDirección: " + this.street + ", " + this.floor + "\n" + this.country + ", " + this.city + ", " + this.zipcode + "\n";
	}

}
