package com.example.demo.dto.business;

import com.example.demo.enums.EntityType;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.entities.EntityClass;

public class ReservationDTO {

    private int id;

    private ReservedTermDTO term;

    private double price;

    private EntityType entityType;

    private EntityClass entity;

    private Boolean isCanceled;

    private int duration;
    private String dateStart;
    private String dateEnd;
    public int getId() {
        return id;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEntity(EntityClass entity) {
        this.entity = entity;
    }

    public EntityClass getEntity() {
        return entity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
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

    public ReservedTermDTO getTerm() {
        return term;
    }

    public void setTerm(ReservedTermDTO term) {
        this.term = term;
    }

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.term = new ReservedTermDTO(reservation.getTerm().getDateStart(), reservation.getTerm().getDateEnd() );
        this.price = reservation.getPrice();
        this.entityType = reservation.getEntityType();
        this.isCanceled = reservation.getCanceled();
        this.duration = reservation.getDuration();
        this.dateStart = reservation.getTerm().getDateStart().toString();
        this.dateEnd = reservation.getTerm().getDateEnd().toString();
    }
    public ReservationDTO() {

    }
}
