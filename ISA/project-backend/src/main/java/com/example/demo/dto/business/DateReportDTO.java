package com.example.demo.dto.business;

import java.util.Date;

public class DateReportDTO {


    public Date dateFrom;
    public Date dateTo;
    public int type;

    public DateReportDTO() {
    }

    public DateReportDTO(Date dateFrom, Date dateTo, int type) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
