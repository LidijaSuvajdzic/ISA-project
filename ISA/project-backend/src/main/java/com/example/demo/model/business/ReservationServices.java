package com.example.demo.model.business;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class ReservationServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private int id;

    @Column
    private String name;

    @Column
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "reservation_id")
    @JsonIgnoreProperties("reservation")
    private Reservation reservation;


    public ReservationServices() {
    }

    public ReservationServices(AdditionalServiceDTO dto){
        this.setName(dto.getName());
        this.setPrice(dto.getPrice());
    }

    public ReservationServices(int id, String name, double price, Reservation reservation) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.reservation = reservation;
    }

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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
