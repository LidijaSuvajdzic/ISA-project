package com.example.demo.dto.users;

import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.users.BoatOwner;

import java.util.List;

public class BoatOwnerDTO {

    public int id;
    public String name;
    public String surname;
    public String email;
    public String password;
    public String telephone;
    public Address address;
    public double grade;
    public List<Boat> boatList;

    public BoatOwnerDTO() {

    }

    public BoatOwnerDTO(int id, String name, String surname, String email, String password, String telephone, Address address, List<Boat> boatList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.address = address;
        this.boatList = boatList;
    }
    public  BoatOwnerDTO(BoatOwner b){
        this.address = b.getAddress();
      //  this.boatList = b.getBoatList();
        this.email = b.getEmail();
        this.password = b.getPassword();
        this.name = b.getName();
        this.surname = b.getSurname();
        this.telephone = b.getTelephone();
        this.id = b.getId();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Boat> getBoatList() {
        return boatList;
    }

    public void setBoatList(List<Boat> boatList) {
        this.boatList = boatList;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
