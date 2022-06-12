package com.example.demo.dto.business;

import java.util.Date;

public class DateDTO {

    private Date date;
    private String time;
    private Integer number;
    private Integer id;

    public DateDTO(){}

    public DateDTO(Date date, String time, Integer number) {
        this.date = date;
        this.time = time;
        this.number = number;
    }

    public DateDTO(Date date, String time, Integer number, Integer id) {
        this.date = date;
        this.time = time;
        this.number = number;
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
