package com.example.demo.dto.business;

import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.dto.users.ClientProfileDTO;
import com.example.demo.model.business.Reservation;

public class ReservationForOwnerDTO {
    private int id;

    private ReservedTermDTO term; //koliko je trajala

    private double price;

   // private EntityClass entity; //koji je entitet
    private EntityDTO entity;


    private Boolean isCanceled;

    private int duration;
    private  boolean action;

    private ClientProfileDTO user; //da bih prikazala profil klijenta

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public ReservationForOwnerDTO(int id, boolean action, ReservedTermDTO term, double price, EntityDTO entity, Boolean isCanceled, int duration, ClientProfileDTO user) {
        this.id = id;
        this.action= action;
        this.term = term;
        this.price = price;
        this.entity = entity;
        this.isCanceled = isCanceled;
        this.duration = duration;
        this.user = user;
    }

    public ReservationForOwnerDTO(Reservation reservation, ClientProfileDTO u) {
        this.id = reservation.getId();
        this.action = reservation.getAction();
        this.term = new ReservedTermDTO(reservation.getTerm());
        this.price = reservation.getPrice();
        this.entity = new EntityDTO(reservation.getEntity());
        this.isCanceled = reservation.getCanceled();
        this.duration = reservation.getDuration();
        this.user = u;
    }
    public ReservationForOwnerDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReservedTermDTO getTerm() {
        return term;
    }

    public void setTerm(ReservedTermDTO term) {
        this.term = term;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public EntityDTO getEntity() {
        return entity;
    }

    public void setEntity(EntityDTO entity) {
        this.entity = entity;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ClientProfileDTO getUser() {
        return user;
    }

    public void setUser(ClientProfileDTO user) {
        this.user = user;
    }
}
