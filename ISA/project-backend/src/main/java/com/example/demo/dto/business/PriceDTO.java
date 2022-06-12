package com.example.demo.dto.business;

import com.example.demo.dto.entities.AdditionalServiceDTO;

import java.util.List;

public class PriceDTO {

    private double entityPrice;
    private List<AdditionalServiceDTO> services;

    public PriceDTO() {}

    public PriceDTO(int entityPrice, List<AdditionalServiceDTO> services) {
        this.entityPrice = entityPrice;
        this.services = services;
    }

    public double getEntityPrice() {
        return entityPrice;
    }

    public void setEntityPrice(double entityPrice) {
        this.entityPrice = entityPrice;
    }

    public List<AdditionalServiceDTO> getServices() {
        return services;
    }

    public void setServices(List<AdditionalServiceDTO> services) {
        this.services = services;
    }
}
