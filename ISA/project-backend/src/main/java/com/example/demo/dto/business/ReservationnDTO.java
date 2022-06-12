package com.example.demo.dto.business;

import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.enums.EntityType;
import com.example.demo.model.business.Reservation;

import java.util.Date;

public class ReservationnDTO {

        private int id;

        private ReservedTermDTO term;
    private Date dateStart1;
    private Date dateEnd1;
        private double price;

        private EntityType entityType;

        private EntityDTO entity;

        private Boolean isCanceled;

        private int duration;
        private String dateStart;
        private String dateEnd;
        private String clientID;

    public Date getDateStart1() {
        return dateStart1;
    }

    public void setDateStart1(Date dateStart1) {
        this.dateStart1 = dateStart1;
    }

    public Date getDateEnd1() {
        return dateEnd1;
    }

    public void setDateEnd1(Date dateEnd1) {
        this.dateEnd1 = dateEnd1;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public int getId() {
            return id;
        }

        public String getDateStart() {
            return dateStart;
        }

        public void setDateStart(String dateStart) {
            this.dateStart = dateStart;
        }

        public String getDateEnd() {
            return dateEnd;
        }

        public void setDateEnd(String dateEnd) {
            this.dateEnd = dateEnd;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setEntity(EntityDTO entity) {
            this.entity = entity;
        }

        public EntityDTO getEntity() {
            return entity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public ReservedTermDTO getTerm() {
            return term;
        }

        public void setTerm(ReservedTermDTO term) {
            this.term = term;
        }

        public ReservationnDTO() {}
        public ReservationnDTO(Reservation reservation) {
            this.id = reservation.getId();
            this.term = new ReservedTermDTO(reservation.getTerm().getDateStart(), reservation.getTerm().getDateEnd() );
            this.price = reservation.getPrice();
            this.entityType = reservation.getEntityType();
            this.isCanceled = reservation.getCanceled();
            this.duration = reservation.getDuration();
            this.dateStart = reservation.getTerm().getDateStart().toString();
            this.dateEnd = reservation.getTerm().getDateEnd().toString();
            if (reservation.getClient() !=null) {
                this.clientID = String.valueOf(reservation.getClient().getId());
            }
        }


}
