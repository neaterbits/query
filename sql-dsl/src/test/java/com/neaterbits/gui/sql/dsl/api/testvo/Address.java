package com.neaterbits.gui.sql.dsl.api.testvo;

public class Address {

    
    private final Integer id;
    
    private final String street1;
    private final String street2;
    private final String zipCode;
    private final String city;
    private final String countryCode;
    
    public Address(int id, String street1, String street2, String zipCode, String city, String countryCode) {
        this.id = id;
        this.street1 = street1;
        this.street2 = street2;
        this.zipCode = zipCode;
        this.city = city;
        this.countryCode = countryCode;
    }

    
    public Integer getId() {
        return id;
    }

    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
