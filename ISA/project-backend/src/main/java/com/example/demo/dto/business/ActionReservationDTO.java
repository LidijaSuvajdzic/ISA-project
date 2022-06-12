package com.example.demo.dto.business;

import com.example.demo.dto.entities.AdditionalServiceDTO;

import java.util.ArrayList;
import java.util.Date;

public class ActionReservationDTO {

    private Date dateStart;
    private String timeStart;

    private double price;
    private int duration;
    private int entityId;
    private Date dateEndAction;

    private ArrayList<AdditionalServiceDTO> additionalServices;

    public ActionReservationDTO(Date dateStart, String timeStart, double price, int duration, int entityId, Date dateEndAction,  ArrayList<AdditionalServiceDTO> additionalServices) {
        this.dateStart = dateStart;
        this.timeStart = timeStart;

        this.price = price;
        this.duration = duration;
        this.entityId = entityId;
        this.dateEndAction = dateEndAction;

        this.additionalServices = additionalServices;
    }

    public ActionReservationDTO() {
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

    public Date getDateEndAction() {
        return dateEndAction;
    }

    public void setDateEndAction(Date dateEndAction) {
        this.dateEndAction = dateEndAction;
    }



    public ArrayList<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(ArrayList<AdditionalServiceDTO> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
