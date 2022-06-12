package com.example.demo.dto.users;


import com.example.demo.model.entities.Address;
import com.example.demo.model.users.User;

public class UpdateUserDTO {

    private int id;

    private String firstname;

    private String lastname;

    private Address address;
    private String password;
    private String telephone;
    private String email;
    private String role;

    public UpdateUserDTO() {}
    public UpdateUserDTO(User user){
        this.id = user.getId();
        this.firstname = user.getName();
        this.lastname = user.getSurname();
        this.address = user.getAddress();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.telephone = user.getTelephone();
        this.role = user.getRole().getName();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
