package com.example.demo.controller.entities;

import com.example.demo.dto.business.ReservationSearchDTO;
import com.example.demo.dto.entities.*;
import com.example.demo.model.entities.AdditionalService;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.BoatOwner;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.AdditionalServicesService;
import com.example.demo.service.entities.BoatService;
import com.example.demo.service.users.BoatOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "api/boats")
public class BoatController {

    private BoatService boatService;
    private AdditionalServicesService additionalServicesService;
    private BoatOwnerService boatOwnerService;

    public BoatController(BoatService boatService, AdditionalServicesService adService, BoatOwnerService boatOwnerService) {
        this.boatService = boatService;
        this.additionalServicesService = adService;
        this.boatOwnerService = boatOwnerService;
    }

    @GetMapping
    public ResponseEntity<List<BoatDTO>> getAll() {
        List<Boat> allBoats = boatService.findAll();
        List<BoatDTO> cottages = new ArrayList<>();
        for (Boat boat : allBoats) {
            cottages.add(new BoatDTO(boat));
        }
        return new ResponseEntity<>(cottages, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<BoatDTO>> searchStartPage(@RequestBody SearchDTO searchParam) {
        List<BoatDTO> ret = new ArrayList<>();
        if( boatService.searchBoatsStartPage(searchParam).size() != 0) {
            for (Boat c : boatService.searchBoatsStartPage(searchParam)) {
                ret.add(new BoatDTO(c));
            }
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping("/reservationSearch")
    public ResponseEntity<List<EntityDTO>> searchReservation(@RequestBody ReservationSearchDTO searchParam){
        ArrayList<EntityDTO> ret = new ArrayList<>();

        for(EntityClass e : boatService.searchReservation(searchParam)){
            ret.add(new EntityDTO(e));
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping("/searchBoatOwner")
    public ResponseEntity<List<BoatDTO>> searchBoatOwner(@RequestBody SearchDTO searchParam) {
        List<BoatDTO> ret = new ArrayList<>();
        if( boatService.searchBoatsStartPage(searchParam).size() != 0) {
            for (Boat c : boatService.searchBoatsOwner(searchParam)) {
                ret.add(new BoatDTO(c));
            }
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
    @GetMapping("/myBoats/{id}")
    public ResponseEntity<List<BoatDTO>> getOwnersBoats(@PathVariable int id) {

        List<Boat> allOwnerBoats = boatService.findBoatsForBoatOwner(id);
        List<BoatDTO> ret = new ArrayList<>();
        Set<AdditionalServiceDTO> services = new HashSet<>();
        if (allOwnerBoats.size() != 0) {
            for (Boat b : allOwnerBoats) {
                List<AdditionalService> allServices = this.additionalServicesService.getServicesForCottage(b.getId()); //uzmem servise
                if (allServices.size() != 0) {
                    for (AdditionalService a : allServices) {
                        services.add(new AdditionalServiceDTO(a));

                    }
                    BoatDTO boat = new BoatDTO(b);
                    boat.setAdditionalServices(services);
                    ret.add(boat);
                } else {
                    BoatDTO boat = new BoatDTO(b);
                    boat.setAdditionalServices(services);
                    ret.add(boat);
                }
            }
                return new ResponseEntity<>(ret, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    @PostMapping("/newBoat")
    public ResponseEntity<Void> saveBoat(@RequestBody BoatDTO newBoat){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        BoatOwner owner = boatOwnerService.findById(user.getId());
        Boat boat = new Boat(newBoat);
        boat.setBoatOwner(owner);
        //boat.setAddress(addressService.save(newCottage.getAddress()));
        if(newBoat.getAdditionalServices().size() != 0){

            for (AdditionalServiceDTO dto : newBoat.getAdditionalServices()){
                AdditionalService additionalService = new AdditionalService(dto);
                additionalService.setEntities(boat);
                boat.getAdditionalServices().add(additionalService);
            }
        }
        this.boatService.save(boat);
        return  new ResponseEntity<>( HttpStatus.OK);

    }

    @GetMapping("/boat/{id}")
    public ResponseEntity<BoatDTO> getById(@PathVariable int id){
        BoatDTO boat = new BoatDTO(boatService.findOne(id));
        List<AdditionalService> allServices= this.additionalServicesService.getServicesForCottage(id); //uzmem servise
        Set<AdditionalServiceDTO> services = new HashSet<>();
        if( allServices.size() != 0 ) {
            for (AdditionalService a : allServices) {

                services.add(new AdditionalServiceDTO(a));
                boat.setAdditionalServices(services);
            }
        } else{
            boat.setAdditionalServices(services);
        }
        return  new ResponseEntity<>(boat, HttpStatus.OK);
    }

    @PostMapping("/editBoat")
    public ResponseEntity<Void> editBoat(@RequestBody BoatDTO editBoat){
        Set<AdditionalService> newServices = new HashSet<>();
        Boat boat = boatService.findOne(editBoat.getId());
        if(editBoat.getAdditionalServices().size() != 0){

            for (AdditionalServiceDTO dto : editBoat.getAdditionalServices()){
                AdditionalService additionalService = new AdditionalService(dto);
                additionalService.setEntities(boat);
                newServices.add(additionalService);

            }
        }
        this.boatService.update(editBoat, newServices);
        return  new ResponseEntity<>( HttpStatus.OK);

    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        boatService.deleteById(id);

        return  new ResponseEntity<>( HttpStatus.OK);

    }
    
    @GetMapping("/getMaxPeople/{id}")
    public ResponseEntity<Integer> getMaxPeople( @PathVariable int id){
        return new ResponseEntity<>(this.boatService.getMaxPeople(id),HttpStatus.OK);
    }

    }
