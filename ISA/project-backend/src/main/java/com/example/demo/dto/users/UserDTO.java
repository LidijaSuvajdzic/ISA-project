package com.example.demo.dto.users;

import com.example.demo.model.entities.Address;
import com.example.demo.model.users.User;

public class UserDTO {

    private int id;

    private String password;

    private String firstname;

    private String lastname;

    private String email;

    private String reasonForRegistration;

    private String role;

    private Address address;

    private String telephone;

    private boolean must_change_password;

    private double grade;

    public UserDTO(User user){
        this.id = user.getId();
        this.firstname = user.getName();
        this.lastname = user.getSurname();
        this.address = user.getAddress();
        this.password = user.getPassword();
        this.reasonForRegistration = user.getReasonForRegistration();
        this.email = user.getEmail();
        this.telephone = user.getTelephone();
        this.role = user.getRole().getName();
        this.must_change_password = user.isMust_change_password();
        this.grade = user.getGrade();
    }

    public UserDTO(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMust_change_password() {
        return must_change_password;
    }

    public void setMust_change_password(boolean must_change_password) {
        this.must_change_password = must_change_password;
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

    public String getReasonForRegistration() {
        return reasonForRegistration;
    }
    public void setReasonForRegistration(String reasonForRegistration) {
        this.reasonForRegistration = reasonForRegistration;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
