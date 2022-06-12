package com.example.demo.dto.business;

import org.apache.tomcat.jni.Time;

import java.util.Date;

public class ReservationSearchDTO {

    private Date date;
    private String time;
    private String city;
    private String country;
    private int number;
    private int people;

    public ReservationSearchDTO() {}

    public ReservationSearchDTO(Date date, String city, String country) {
        this.date = date;
        this.city = city;
        this.country = country;
    }

    public ReservationSearchDTO( Date date, String time, String city, String country, int number, int people) {
        this.date = date;
        this.time = time;
        this.city = city;
        this.country = country;
        this.number = number;
        this.people = people;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }
}
