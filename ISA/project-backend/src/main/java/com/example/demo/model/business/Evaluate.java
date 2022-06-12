package com.example.demo.model.business;

import com.example.demo.dto.business.EvaluateDTO;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;

import javax.persistence.*;
@Entity
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String contentEntity;

    @Column
    private String contentUser;

    @Column
    private int gradeForUser;

    @Column
    private int gradeForEntity;

    @Column
    private Boolean accepted;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY )
    private EntityClass entity;

    @OneToOne(fetch = FetchType.LAZY)
    private User userWhoSendsComplaint;

    public Evaluate() {}

    public Evaluate(EvaluateDTO dto) {
        this.contentEntity = dto.getContentEntity();
        this.contentUser = dto.getContentUser();
        this.gradeForEntity = dto.getGradeForEntity();
        this.gradeForUser = dto.getGradeForUser();
        this.entity = dto.getEntity();
        this.user = dto.getUser();
    }

    public int getGradeForUser() {
        return gradeForUser;
    }

    public void setGradeForUser(int gradeForUser) {
        this.gradeForUser = gradeForUser;
    }

    public int getGradeForEntity() {
        return gradeForEntity;
    }

    public void setGradeForEntity(int gradeForEntity) {
        this.gradeForEntity = gradeForEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EntityClass getEntity() {
        return entity;
    }

    public void setEntity(EntityClass entity) {
        this.entity = entity;
    }

    public User getUserWhoSendsComplaint() {
        return userWhoSendsComplaint;
    }

    public void setUserWhoSendsComplaint(User userWhoSendsComplaint) {
        this.userWhoSendsComplaint = userWhoSendsComplaint;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public String getContentEntity() {
        return contentEntity;
    }

    public String getContentUser() {
        return contentUser;
    }

    public void setContentEntity(String contentEntity) {
        this.contentEntity = contentEntity;
    }

    public void setContentUser(String contentUser) {
        this.contentUser = contentUser;
    }
}



