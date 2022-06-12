package com.example.demo.dto.business;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.enums.EntityType;

import java.util.ArrayList;
import java.util.Date;

public class ReservationNewOwnerDTO {

    private Date dateStart;
    private String timeStart;
    private double price;
    private int duration;
    private int entityId;
    private int clientId;
    private EntityType type;
    private ArrayList<AdditionalServiceDTO> additionalServices;


    public ReservationNewOwnerDTO() {
    }

    public ReservationNewOwnerDTO(Date dateStart, String timeStart, double price, int duration, int entityId, int clientId, EntityType type, ArrayList<AdditionalServiceDTO> additionalServices) {
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.price = price;
        this.duration = duration;
        this.entityId = entityId;
        this.clientId = clientId;
        this.type = type;
        this.additionalServices = additionalServices;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public ArrayList<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(ArrayList<AdditionalServiceDTO> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
