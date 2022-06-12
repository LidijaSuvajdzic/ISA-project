package com.example.demo.model.entities;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.model.business.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class AdditionalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "entity_id")
    @JsonIgnoreProperties("entity")
    private EntityClass entity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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



    public EntityClass getEntities() {
        return entity;
    }

    public void setEntities(EntityClass entities) {
        this.entity = entities;
    }

    public AdditionalService(AdditionalServiceDTO dto){
        this.setId(dto.getId());
        this.setName(dto.getName());
        this.setPrice(dto.getPrice());
    }

    public AdditionalService(int id, String name, double price, EntityClass entity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.entity = entity;
    }

    public AdditionalService(){}
}
