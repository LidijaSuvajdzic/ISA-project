package com.example.demo.model.business;

import com.example.demo.enums.EntityType;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="id", unique=true, nullable=false)
    private int id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private ReservedTerm term;

    @Column(name="price", unique=false, nullable=false)
    private double price;

    @Column(name="entity_type")
    private EntityType entityType;

    @Column(nullable = true)
    private Date validFrom;
    @Column(nullable = true)
    private Date validTo;

    @Column(name="is_canceled",nullable=false)
    private Boolean isCanceled = false;

    @Column(name="is_successful",nullable=false)
    private Boolean isSuccessful = false;

    @Column(name="action",nullable=true)
    private Boolean action = false;

    @Column(name="duration", nullable = true)
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties("reservation")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id")
    @JsonIgnoreProperties("entities")
    private EntityClass entity;

    @Version
    private Integer version;

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public List<ReservationServices> getReservationServices() {
        return reservationServices;
    }

    public void setReservationServices(List<ReservationServices> reservationServices) {
        this.reservationServices = reservationServices;
    }

    @OneToMany(mappedBy= "reservation", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("reservation")
    private List<ReservationServices> reservationServices = new ArrayList<>();

    public Reservation() {

    }

    public Reservation(int id, ReservedTerm term , double price, Client client, int duration) {
        this.id = id;
        this.term = term;
        this.price = price;
        this.client = client;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public EntityClass getEntity() {
        return entity;
    }

    public void setEntity(EntityClass entity) {
        this.entity = entity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public ReservedTerm getTerm() {
        return term;
    }

    public void setTerm(ReservedTerm term) {
        this.term = term;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
