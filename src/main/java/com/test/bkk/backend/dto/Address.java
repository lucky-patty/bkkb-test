package com.test.bkk.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Address {
    private int id;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "Suite is required")
    private String suite;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "Zip is required")
    private String zipcode;
    @NotNull(message = "Geo is required")
    private Geo geo;

    // public Address(int id, String street, String suite, String city, String zipcode, Geo geo) {
    //     this.id = id;
    //     this.street = street;
    //     this.suite = suite;
    //     this.city = city;
    //     this.zipcode = zipcode;
    //     this.geo = geo;
    // }

    // Getter & Setters
    public int getId() { return id; }
    public void setId(int id){ this.id = id; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    
    public String getSuite() { return suite; }
    public void setSuite(String suite) { this.suite = suite; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipCode) { this.zipcode = zipCode; }
    
    public Geo getGeo() { return geo; }
    public void setGeo(Geo geo) { this.geo = geo; }
}
