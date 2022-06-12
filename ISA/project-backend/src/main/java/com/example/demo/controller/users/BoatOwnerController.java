package com.example.demo.controller.users;

import com.example.demo.dto.users.BoatOwnerDTO;
import com.example.demo.dto.users.CottageOwnerDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.model.users.BoatOwner;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.BoatService;
import com.example.demo.service.users.BoatOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/boatOwner")
public class BoatOwnerController {


    private BoatOwnerService boatOwnerService;
    private BoatService boatService;

    public  BoatOwnerController(BoatOwnerService boatOwnerService, BoatService boatService){
        this.boatOwnerService = boatOwnerService;
        this.boatService = boatService;
    }

    public BoatOwner findByEmail(String email){
        return  boatOwnerService.findByEmail(email);

    }

    @GetMapping("/profileBoatOwner")
    public ResponseEntity<BoatOwnerDTO> getProfileBoatOwner(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user.getRole().getName().equals("ROLE_BOAT_OWNER")){
            BoatOwner boatOwner = boatOwnerService.findByEmail(user.getEmail());
            //boatOwner.setBoatList(boatService.findBoatsForBoatOwner(boatOwner.getId()));
            BoatOwnerDTO dto = new BoatOwnerDTO(boatOwner); //bez liste brodova

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/boatOwnerUser/{id}")
    public  ResponseEntity<UserDTO> fetchBoatOwnerByBoat(@PathVariable int id){
        User user = this.boatOwnerService.fetchBoatOwnerByBoat(id);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

}
