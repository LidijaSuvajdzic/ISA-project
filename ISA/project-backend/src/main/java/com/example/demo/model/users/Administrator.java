package com.example.demo.model.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.Adventure;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity()
@Table(name = "administrator")
@Proxy(lazy = false)
@DiscriminatorValue("AD")
public class Administrator extends User{

    public Administrator() {}

    public Administrator(String name, String surname, String email, String password, String telephone, Address address) {
        super(name, surname, email, password, telephone, address);
    }

    public Administrator(UserRequest user) {
        super(user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getTelephone(), user.getAddress(), user.getRole());
    }

    public Administrator(User user) {
        super(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getTelephone(), user.getAddress(), true, user.getRole(), user.getLastPasswordResetDate());
    }
}
