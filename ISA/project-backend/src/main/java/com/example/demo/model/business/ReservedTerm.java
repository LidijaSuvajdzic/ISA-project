package com.example.demo.model.business;

import com.example.demo.model.entities.EntityClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ReservedTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Date dateStart;
    @Column
    private Date dateEnd;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id")
    @JsonIgnoreProperties("entities")
    private EntityClass entity;
    @Column
    private boolean isCanceled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public EntityClass getEntity() {
        return entity;
    }

    public void setEntity(EntityClass entity) {
        this.entity = entity;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public ReservedTerm() {}

    public ReservedTerm(Date dateStart, Date dateEnd, EntityClass entity) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.entity = entity;
    }
    public ReservedTerm(Date dateStart, Date dateEnd, EntityClass entity, boolean canceld) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.entity = entity;
        this.isCanceled= canceld;
    }
    public ReservedTerm(int id, Date dateStart, Date dateEnd, EntityClass entity, boolean isCanceled) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.entity = entity;
        this.isCanceled = isCanceled;
    }
}
