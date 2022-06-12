package com.example.demo.dto.business;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.dto.entities.AdventureDTO;

import java.util.List;

public class ActionRequestDTO {
    private String dateStart;
    private String dateEnd;
    private String timeStart;
    private String timeEnd;
    private int price;
    private double duration;
    private String name;
    private String validFrom;
    private String validTo;
    private List<AdditionalServiceDTO> additionalServices;
    private String selectedClient;

    public String getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(String selectedClient) {
        this.selectedClient = selectedClient;
    }

    public double getDuration() {
        return duration;
    }

    public String getTimeStart() {

        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public List<AdditionalServiceDTO> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<AdditionalServiceDTO> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public ActionRequestDTO() {
    }

    public ActionRequestDTO(String dateStart, String dateEnd, int price, String name, String validFrom, String validTo) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.price = price;
        this.name = name;
        this.validFrom = validFrom;
        this.validTo = validTo;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }
}
