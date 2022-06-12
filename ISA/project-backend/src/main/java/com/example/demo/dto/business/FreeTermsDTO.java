package com.example.demo.dto.business;

import com.example.demo.model.users.Instructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

public class FreeTermsDTO {

    private int id;
    private String freeFromDate;
    private String freeFromTime;
    private String freeToDate;
    private String freeToTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFreeFromDate() {
        return freeFromDate;
    }

    public void setFreeFromDate(String freeFromDate) {
        this.freeFromDate = freeFromDate;
    }

    public String getFreeFromTime() {
        return freeFromTime;
    }

    public void setFreeFromTime(String freeFromTime) {
        this.freeFromTime = freeFromTime;
    }

    public String getFreeToDate() {
        return freeToDate;
    }

    public void setFreeToDate(String freeToDate) {
        this.freeToDate = freeToDate;
    }

    public String getFreeToTime() {
        return freeToTime;
    }

    public void setFreeToTime(String freeToTime) {
        this.freeToTime = freeToTime;
    }

    public FreeTermsDTO() {
    }

    public FreeTermsDTO(int id, String freeFromDate, String freeFromTime, String freeToDate, String freeToTime) {
        this.id = id;
        this.freeFromDate = freeFromDate;
        this.freeFromTime = freeFromTime;
        this.freeToDate = freeToDate;
        this.freeToTime = freeToTime;
    }
}
