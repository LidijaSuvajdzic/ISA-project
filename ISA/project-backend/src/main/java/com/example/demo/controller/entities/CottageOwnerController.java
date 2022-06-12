package com.example.demo.controller.entities;

import com.example.demo.controller.users.DeleteUserRequestController;
import com.example.demo.dto.users.ClientDTO;
import com.example.demo.dto.users.CottageOwnerDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.CottageService;
import com.example.demo.service.users.CottageOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "api/cottageOwner")
public class CottageOwnerController {

    private CottageOwnerService cottageOwnerService;
    private CottageService cottageService;


    public CottageOwnerController(CottageOwnerService cottageOwnerService, CottageService cottageServ){
        this.cottageOwnerService= cottageOwnerService;
        this.cottageService = cottageServ;
    }

    public CottageOwner findByEmail(String id) { return cottageOwnerService.findByEmail(id); }


    @GetMapping("/profileCottageOwner")
    public ResponseEntity<CottageOwnerDTO> getProfileCottageOwner(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user.getRole().getName().equals("ROLE_COTTAGE_OWNER")){
            CottageOwner cottageOwner = cottageOwnerService.findByEmail(user.getEmail());
            cottageOwner.setCottageList(cottageService.findAllOwnerCottages(cottageOwner.getId()));
            CottageOwnerDTO dto = new CottageOwnerDTO(cottageOwner);
           // dto.setCottageList(cottageService.findAllOwnerCottages(cottageOwner.getId()));
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<CottageOwnerDTO>> getAll(){
        List<CottageOwner> allOwners = cottageOwnerService.findAll();
        List<CottageOwnerDTO> cottages = new ArrayList<>();
        for(CottageOwner owner : allOwners ) {
            cottages.add(new CottageOwnerDTO(owner));
        }
        return  new ResponseEntity<>(cottages, HttpStatus.OK);
    }

    @GetMapping("/cottageOwnerUser/{id}")
    public  ResponseEntity<UserDTO> fetchCottageOwnerByCottage(@PathVariable int id){
        User user = this.cottageOwnerService.fetchCottageOwnerByCottage(id);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

}
