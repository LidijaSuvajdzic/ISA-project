package com.example.demo.service.users;

import com.example.demo.enums.LoyalityType;
import com.example.demo.enums.Role;
import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.users.BoatOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.users.BoatOwnerRepository;
import com.example.demo.service.entities.AddressService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoatOwnerService {

    private BoatOwnerRepository boatOwnerRepository;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private AddressService addressService;

    public BoatOwnerService(RoleService roleService,AddressService addressService,PasswordEncoder passwordEncoder, BoatOwnerRepository boatOwnerRepository, UserService userService){
        this.boatOwnerRepository = boatOwnerRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.addressService=addressService;
        this.roleService=roleService;
    }

    public BoatOwner save(User boatOwner){
        BoatOwner owner= new BoatOwner();
        owner.setName(boatOwner.getName());
        owner.setSurname(boatOwner.getSurname());
        owner.setAddress(boatOwner.getAddress());
        owner.setBoatList(new ArrayList<>()); //inicijalno prazna
//        owner.setPassword(passwordEncoder.encode(boatOwner.getPassword()));
        owner.setPassword(boatOwner.getPassword());
        owner.setEmail(boatOwner.getEmail());
        owner.setTelephone(boatOwner.getTelephone());
        owner.setRole(boatOwner.getRole());
        owner.setEnabled(true);
        owner.setMust_change_password(false); //Cemu?
        owner.setLastPasswordResetDate(null); //???
        owner.setReasonForRegistration(boatOwner.getReasonForRegistration());

       return this.boatOwnerRepository.save(owner);
    }

    public  BoatOwner findById(int id){
       return this.boatOwnerRepository.findById(id);
    }

    public  BoatOwner findByEmail(String email){
        return this.boatOwnerRepository.findByEmail(email);
    }

    public  void remove(int id){
        boatOwnerRepository.deleteById(id);
    }

    public User fetchBoatOwnerByBoat(int idBoat){
        Boat boat = this.boatOwnerRepository.fetchBoatOwnerByBoat(idBoat);
        return this.userService.findByEmail(boat.getBoatOwner().getEmail());
    }
    public BoatOwner getOwnerWithBoats(int id) {
        return boatOwnerRepository.findOwnerWithBoats(id);
}


    public void saveBoatOwner(UserRequest userRequest) {
        BoatOwner u = new BoatOwner();
        u.setEmail(userRequest.getEmail());
        u.setEnabled(false);
        u.setMust_change_password(false);
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setName(userRequest.getFirstname());
        u.setReasonForRegistration(userRequest.getReasonForRegistration());
        u.setSurname(userRequest.getLastname());
        u.setTelephone(userRequest.getTelephone());
        u.setLoyalityType(LoyalityType.REGULAR);
        u.setPoents(0);
        u.setIncome(0.0);
        Role r = this.roleService.findByName("ROLE_BOAT_OWNER");
        if (r==null) {
            Role newRole = new Role(userRequest.getRole());
            this.roleService.save(newRole);
            u.setRole(newRole);
        }else {
            u.setRole(r);
        }

        u.setGrade(0);
        Address a = new Address(userRequest.getAddress().getCountry(),userRequest.getAddress().getCity(),userRequest.getAddress().getStreet(),userRequest.getAddress().getNumber());
        this.addressService.save(a);
        u.setAddress(a);
        System.out.print("USPELA SAM HEHE");

        this.boatOwnerRepository.save(u);

    }
}
