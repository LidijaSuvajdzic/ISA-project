package com.example.demo.dto.business;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.enums.EntityType;

import java.util.ArrayList;
import java.util.Date;

public class ReservationNewDTO {
    private Date dateStart;
    private String timeStart;
    private Date dateEnd;
    private double price;
    private int duration;
    private int entityId;
    private EntityType type;
    private ArrayList<AdditionalServiceDTO> additionalServices;

    public ReservationNewDTO() {}

    public ReservationNewDTO(Date dateStart, String timeStart, double price, int duration, int entityId, ArrayList<AdditionalServiceDTO> additionalServices, EntityType type) {
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.price = price;
        this.duration = duration;
        this.entityId = entityId;
        this.additionalServices = additionalServices;
        this.type = type;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public ArrayList<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(ArrayList<AdditionalServiceDTO> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }
}
