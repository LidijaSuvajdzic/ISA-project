package com.example.demo.dto.entities;

public class SearchDTO {

    private String name;
    private String street;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public SearchDTO(String name, String street, String city) {
        this.name = name;
        this.street = street;
        this.city = city;
    }
    public SearchDTO(){ }
}
