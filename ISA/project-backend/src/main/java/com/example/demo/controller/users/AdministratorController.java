package com.example.demo.controller.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.enums.Role;
import com.example.demo.model.entities.Address;
import com.example.demo.model.users.Administrator;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.AddressService;
import com.example.demo.service.users.AdministratorService;
import com.example.demo.service.users.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api/administrator")
public class AdministratorController {
    private AdministratorService administratorService;
    private AddressService addressService;
    private RoleService roleService;

    public AdministratorController(RoleService roleService,AdministratorService administratorService,AddressService addressService) {
        this.administratorService = administratorService;
        this.addressService = addressService;
        this.roleService = roleService;
    }

    @PostMapping(value = "/addAdministrator")
    public ResponseEntity<User> addAdmin(@RequestBody UserRequest userRequest){
        Administrator a = new Administrator();
        a.setName(userRequest.getFirstname());
        a.setSurname(userRequest.getLastname());
        a.setEnabled(true);
        a.setMust_change_password(true);
        a.setEmail(userRequest.getEmail());
        a.setPassword(userRequest.getPassword());
        a.setTelephone(userRequest.getTelephone());
        Address address = new Address(userRequest.getCountry(),userRequest.getCity(),userRequest.getStreet(),userRequest.getNumber());
        this.addressService.save(address);
        a.setAddress(address);
        Role r = this.roleService.findByName("ROLE_ADMIN");
        if (r==null) {
                Role newRole = new Role("ROLE_ADMIN");
                this.roleService.saveRole(newRole);
                a.setRole(newRole);
        }else {
            a.setRole(r);
        }

        if (a != null){
            administratorService.save(a);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            System.out.print("NULL je");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
