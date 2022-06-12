package com.example.demo.dto.business;

import com.example.demo.model.business.Evaluate;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;

public class EvaluateDTO {
    private int id;
    private String contentEntity;
    private String contentUser;
    private int gradeForUser;
    private int gradeForEntity;
    private User user;
    private EntityClass entity;
    private User userWhoSendsComplaint;
    private Boolean accepted;
    private String nameOfuserWhoSendsComplaint;
    private String lastnameOfuserWhoSendsComplaint;
    private String name;
    private String lastname;
    private String mailOfuserWhoSendsComplaint;
    private String mail;

    public String getNameOfuserWhoSendsComplaint() {
        return nameOfuserWhoSendsComplaint;
    }

    public void setNameOfuserWhoSendsComplaint(String nameOfuserWhoSendsComplaint) {
        this.nameOfuserWhoSendsComplaint = nameOfuserWhoSendsComplaint;
    }

    public String getLastnameOfuserWhoSendsComplaint() {
        return lastnameOfuserWhoSendsComplaint;
    }

    public void setLastnameOfuserWhoSendsComplaint(String lastnameOfuserWhoSendsComplaint) {
        this.lastnameOfuserWhoSendsComplaint = lastnameOfuserWhoSendsComplaint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMailOfuserWhoSendsComplaint() {
        return mailOfuserWhoSendsComplaint;
    }

    public void setMailOfuserWhoSendsComplaint(String mailOfuserWhoSendsComplaint) {
        this.mailOfuserWhoSendsComplaint = mailOfuserWhoSendsComplaint;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public EvaluateDTO() {}

    public EvaluateDTO(Evaluate e) {
        this.id = e.getId();
        this.contentEntity = e.getContentEntity();
        this.contentUser = e.getContentUser();
        this.gradeForEntity = e.getGradeForEntity();
        this.gradeForUser = e.getGradeForUser();
        this.user = e.getUser();
        this.entity = e.getEntity();
        this.userWhoSendsComplaint = e.getUserWhoSendsComplaint();
        this.accepted = e.getAccepted();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentEntity() {
        return contentEntity;
    }

    public void setContentEntity(String contentEntity) {
        this.contentEntity = contentEntity;
    }

    public String getContentUser() {
        return contentUser;
    }

    public void setContentUser(String contentUser) {
        this.contentUser = contentUser;
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
}

