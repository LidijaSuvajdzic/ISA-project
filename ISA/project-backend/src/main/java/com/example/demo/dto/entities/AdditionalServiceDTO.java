package com.example.demo.dto.entities;

import com.example.demo.model.entities.AdditionalService;

public class AdditionalServiceDTO {
    public String name;
    public double price;
    public int id;
    public String nameOfAdventure;

    public String getNameOfAdventure() {
        return nameOfAdventure;
    }

    public void setNameOfAdventure(String nameOfAdventure) {
        this.nameOfAdventure = nameOfAdventure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AdditionalServiceDTO(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }
    public AdditionalServiceDTO(AdditionalService a) {
        this.name = a.getName();
        this.price = a.getPrice();
        this.id =a.getId();

    }
    public AdditionalServiceDTO(){}
}
