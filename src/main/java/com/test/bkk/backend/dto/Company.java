package com.test.bkk.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class Company {
    private int id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "Catchphrase is required")
    private String catchPhrase;
    @NotBlank(message = "Bs is required")
    private String bs;

    // public Company(int id, String name, String catchPhrase, String bs) {
    //     this.id = id;
    //     this.name = name;
    //     this.catchPhrase = catchPhrase;
    //     this.bs = bs;
    // }

    // Getter & Setters
    public int getId() { return id; }
    public void setId(int id){ this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCatchPhrase() { return catchPhrase; }
    public void setCatchPhrase(String catchP) { this.catchPhrase = catchP; }
    
    public String getBs() { return bs; }
    public void setBs(String bs) { this.bs = bs; } 
}
