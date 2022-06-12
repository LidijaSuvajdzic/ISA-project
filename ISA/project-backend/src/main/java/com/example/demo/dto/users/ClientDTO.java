package com.example.demo.dto.users;

import com.example.demo.enums.LoyalityType;
import com.example.demo.model.entities.Address;
import com.example.demo.model.users.Client;

public class ClientDTO {

    private int id;

    private String password;

    private String firstname;

    private String lastname;

    private String reasonForRegistration;

    private String email;

    private String role;

    private int penals;

    private LoyalityType loyalityType;

    private int poents;

    private Address address;

    private String telephone;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.password = client.getPassword();
        this.firstname = client.getName();
        this.lastname = client.getSurname();
        this.email = client.getEmail();
        this.role = client.getRole().getName();
        this.reasonForRegistration = client.getReasonForRegistration();
        this.penals = client.getPenals();
        this.loyalityType = client.getLoyalityType();
        this.poents = client.getPoents();
        this.address = client.getAddress();
        this.telephone = client.getTelephone();
    }

    public ClientDTO() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getReasonForRegistration() {
        return reasonForRegistration;
    }
    public void setReasonForRegistration(String reasonForRegistration) {
        this.reasonForRegistration = reasonForRegistration;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPenals() {
        return penals;
    }

    public void setPenals(int penals) {
        this.penals = penals;
    }

    public LoyalityType getLoyalityType() {
        return loyalityType;
    }

    public void setLoyalityType(LoyalityType loyalityType) {
        this.loyalityType = loyalityType;
    }

    public int getPoents() {
        return poents;
    }

    public void setPoents(int poents) {
        this.poents = poents;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
