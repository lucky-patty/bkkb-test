package com.test.bkk.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class Geo {
    private int id;
    @NotBlank(message = "lat is required")
    private String lat;
    @NotBlank(message = "lng is required")
    private String lng;

    // public Geo(int id, String lat, String lng) {
    //     this.id = id;
    //     this.lat = lat;
    //     this.lng = lng;
    // }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id){ this.id = id; }
    
    public String getLat() { return lat; }
    public void setLat(String lat) { this.lat = lat; }
    
    public String getLng() {return lng;}
    public void setLng(String lng) { this.lng = lng; }

}
