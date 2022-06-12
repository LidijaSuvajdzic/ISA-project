package com.example.demo.model.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.enums.LoyalityType;
import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.Cottage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cottageOwner")
@Proxy(lazy = false)
@DiscriminatorValue("CO")
public class CottageOwner extends User{

    @OneToMany(mappedBy = "cottageOwner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cottageOwner")
    private List<Cottage> cottageList;



    public List<Cottage> getCottageList() {
        return cottageList;
    }

    public void setCottageList(List<Cottage> cottageList) {
        this.cottageList = cottageList;
    }

    public CottageOwner() {}

    public CottageOwner(String name, String surname, String email, String password, String telephone, Address address, List<Cottage> cottageList) {
        super(name, surname, email, password, telephone, address);
        this.cottageList = cottageList;
    }

    public CottageOwner(UserRequest user) {
        super(user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getTelephone(), user.getAddress(), user.getRole());
        //cottageList je null
    }

    public CottageOwner(User user) {
        super(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getTelephone(), user.getAddress(), true, user.getRole(), user.getLastPasswordResetDate());
        setReasonForRegistration(user.getReasonForRegistration());
        //cottageList je null
    }
}
