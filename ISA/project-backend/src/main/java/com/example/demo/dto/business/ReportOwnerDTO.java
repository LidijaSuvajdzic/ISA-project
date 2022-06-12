package com.example.demo.dto.business;

import com.example.demo.enums.RestrictionType;
import com.example.demo.model.users.User;

public class ReportOwnerDTO {
    public int id;
    public  int idClient;
    public int idOwner;
    public String comment;
    public RestrictionType restrictionType;
    public String type;
    public String clientName;
    public String clientLastname;
    public String ownerName;
    public String ownerLastname;
    private Boolean revise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getRevise() {
        return revise;
    }

    public void setRevise(Boolean revise) {
        this.revise = revise;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastname() {
        return clientLastname;
    }

    public void setClientLastname(String clientLastname) {
        this.clientLastname = clientLastname;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerLastname() {
        return ownerLastname;
    }

    public void setOwnerLastname(String ownerLastname) {
        this.ownerLastname = ownerLastname;
    }

    public String getType()

    {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public RestrictionType getRestictionType() {
        return restrictionType;
    }

    public void setRestictionType(RestrictionType restictionType) {
        this.restrictionType = restictionType;
    }

    public ReportOwnerDTO(int idClient, int idOwner, String comment, RestrictionType restictionType) {
        this.idClient = idClient;
        this.idOwner = idOwner;
        this.comment = comment;
        this.restrictionType = restictionType;
    }

    public ReportOwnerDTO() {
    }
}
