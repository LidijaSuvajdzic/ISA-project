package com.example.demo.model.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.enums.LoyalityType;
import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.Adventure;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "instructor")
@Proxy(lazy = false)
@DiscriminatorValue("II")
public class Instructor extends User{

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Adventure> adventuresList;




    public Set<Adventure> getAdventuresList() {
        return adventuresList;
    }

    public void setAdventuresList(Set<Adventure> adventuresList) {
        this.adventuresList = adventuresList;
    }

    public Set<Adventure> getAdventures() {
        return adventuresList;
    }

    public void setAdventures(Set<Adventure> adventuresList) {
        this.adventuresList = adventuresList;
    }

    public Instructor() {}

    public Instructor(String name, String surname, String email, String password, String telephone, Address address) {
        super(name, surname, email, password, telephone, address);
    }

    public Instructor(UserRequest user) {
        super(user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getTelephone(), user.getAddress(), user.getRole());
    }

    public Instructor(User user) {
        super(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getTelephone(), user.getAddress(), true, user.getRole(), user.getLastPasswordResetDate());
    }
}
