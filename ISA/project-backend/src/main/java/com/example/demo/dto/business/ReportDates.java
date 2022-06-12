package com.example.demo.dto.business;

import java.util.Date;

public class ReportDates {

    public Date dateFrom;
    public Date dateTo;
    public int idOwner;

    public ReportDates(Date dateFrom, Date dateTo, int id) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.idOwner= id;}
    public ReportDates() {

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

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }
}
