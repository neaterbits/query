package com.neaterbits.query.jpatest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {

    
    private long id;
    
    private String street1;
    private String street2;
    private String zipCode;
    private String city;
    private String countryCode;
    
    public Address() {
    	
    }
    
    public Address(int id, String street1, String street2, String zipCode, String city, String countryCode) {
        this.id = id;
        this.street1 = street1;
        this.street2 = street2;
        this.zipCode = zipCode;
        this.city = city;
        this.countryCode = countryCode;
    }


    @Id @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
		this.id = id;
	}
    
    
    public String getStreet1() {
        return street1;
    }


	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
        return street2;
    }

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

    public String getZipCode() {
        return zipCode;
    }

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
    public String getCity() {
        return city;
    }


	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryCode() {
        return countryCode;
    }

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
