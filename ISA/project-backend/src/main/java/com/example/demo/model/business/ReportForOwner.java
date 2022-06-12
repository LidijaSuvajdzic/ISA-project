package com.example.demo.model.business;

import com.example.demo.enums.EntityType;

import java.util.Date;

public class ReportForOwner {

    private int entityId;
    private String entityName;
    private EntityType type;
    private double grade;
    private int numberOfPeople;
    private double earning;
    private Date fromDate;
    private Date toDate;

    public ReportForOwner() {
    }

    public ReportForOwner(int entityId, String entityName, EntityType type, double grade, int numberOfPeople, double earning, Date fromDate, Date toDate) {
        this.entityId = entityId;
        this.entityName = entityName;
        this.type = type;
        this.grade = grade;
        this.numberOfPeople = numberOfPeople;
        this.earning = earning;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getEarning() {
        return earning;
    }

    public void setEarning(double earning) {
        this.earning = earning;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
