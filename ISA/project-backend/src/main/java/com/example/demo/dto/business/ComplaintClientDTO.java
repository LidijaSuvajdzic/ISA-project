package com.example.demo.dto.business;

import com.example.demo.model.business.Complaint;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;

public class ComplaintClientDTO {

    private int id;

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

    private String contentEntity;
    private String contentUser;
    private String complaintType;
    private User user;
    private EntityClass entity;
    private User userWhoSendsComplaint;
    private boolean isAnswered;
    private String nameOfuserWhoSendsComplaint;
    private String lastnameOfuserWhoSendsComplaint;
    private String name;
    private String lastname;
    private String mailOfuserWhoSendsComplaint;
    private String mail;

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

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

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

    public boolean getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(boolean answered) {
        isAnswered = answered;
    }

    public ComplaintClientDTO() {

    }
    public ComplaintClientDTO(Complaint complaint) {
        this.id = complaint.getId();
        this.contentEntity = complaint.getContentEntity();
        this.contentUser = complaint.getContentUser();
        this.complaintType = complaint.getComplaintType().toString();
        this.user = complaint.getUser();
        this.userWhoSendsComplaint = complaint.getUserWhoSendsComplaint();
        this.isAnswered = complaint.getIsAnswered();
    }
    public int getId() {
        return id;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public User getUser() {
        return user;
    }

    public EntityClass getEntity() {
        return entity;
    }

    public User getUserWhoSendsComplaint() {
        return userWhoSendsComplaint;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEntity(EntityClass entity) {
        this.entity = entity;
    }

    public void setUserWhoSendsComplaint(User userWhoSendsComplaint) {
        this.userWhoSendsComplaint = userWhoSendsComplaint;
    }
}
