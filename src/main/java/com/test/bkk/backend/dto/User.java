package com.test.bkk.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class User {
    private int id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Username is required")
    private String username;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
    @NotNull(message = "Address is required")
    private Address address;
    @NotBlank(message = "Phone is required")
    private String phone;
    @NotBlank(message = "Website is required")
    private String website;
    @NotNull(message = "Company is required")
    private Company company;
 
    
    // public User(int id, String name, String username, String email, Address address, String phone, String website, Company company) {
    //     this.id = id;
    //     this.name = name;
    //     this.username = username;
    //     this.email = email;
    //     this.address = address;
    //     this.phone = phone;
    //     this.website = website;
    //     this.company = company;
    // }

    // Getter & Setters 
    public int getId() {  return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
}
