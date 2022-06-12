package com.example.demo.dto.business;

import java.util.Date;

public class UnavailablePeriodDTO {


    public Date dateFrom;
    public Date dateTo;
    public int entityId;


    public UnavailablePeriodDTO() {
    }

    public UnavailablePeriodDTO(Date dateFrom, Date dateTo, int entityId) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.entityId = entityId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
}
