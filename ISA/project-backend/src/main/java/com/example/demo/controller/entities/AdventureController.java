package com.example.demo.controller.entities;
import com.example.demo.dto.entities.*;

import com.example.demo.dto.business.ReservationSearchDTO;
import com.example.demo.dto.entities.AdventureDTO;
import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.enums.CancelationType;
import com.example.demo.model.entities.*;
import com.example.demo.model.users.BoatOwner;
import com.example.demo.model.users.Instructor;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.AddressService;
import com.example.demo.service.entities.AdventureService;
import com.example.demo.service.users.InstructorService;
import com.example.demo.service.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Controller
@RequestMapping(value = "api/adventures")
public class AdventureController {

    private AdventureService adventureService;
    public UserService userService;
    public InstructorService instructorService;
    private AddressService addressService;
    public AdventureController(AdventureService adventureService,UserService userService,InstructorService instructorService, AddressService addressService){
        this.adventureService = adventureService;
        this.userService=userService;
        this.instructorService=instructorService;
        this.addressService=addressService;
    }

    @GetMapping
    public ResponseEntity<List<AdventureDTO>> getAll(){
        List<Adventure> allAdventures = adventureService.findAll();
        List<AdventureDTO> adventures = new ArrayList<>();
        for(Adventure adventure : allAdventures ) {
            adventures.add(new AdventureDTO(adventure));
        }
        return  new ResponseEntity<>(adventures, HttpStatus.OK);
    }

    @GetMapping("/getAdventure/{name}")
    public ResponseEntity<AdventureDTO> getAdventure(@PathVariable String name){
        List<Adventure> allAdventures = adventureService.findAll();
        AdventureDTO adventure = new AdventureDTO();
        for(Adventure a : allAdventures ) {
            if (a.getNameOfAdventure().contains(name)) {
                adventure = new AdventureDTO(a);
            }
        }
        return  new ResponseEntity<>(adventure, HttpStatus.OK);
    }

    @GetMapping("/getMyAdventures")
    public ResponseEntity<List<AdventureDTO>> getMyAdventures(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        User i = this.userService.findByEmail(user.getEmail());
        List<Adventure> allAdventures = adventureService.findAll();
        List<AdventureDTO> adventures = new ArrayList<>();
        for(Adventure adventure : allAdventures ) {
            if (adventure.getInstructor().getId() == i.getId()) {
                adventures.add(new AdventureDTO(adventure));
            }
        }
        return  new ResponseEntity<>(adventures, HttpStatus.OK);
    }

    @GetMapping("/findAdventure/{inputText}")
    public ResponseEntity<List<AdventureDTO>> findAdventures(@PathVariable String inputText){
        List<Adventure> allAdventures = adventureService.findAll();
        List<AdventureDTO> adventures = new ArrayList<>();
        for(Adventure adventure : allAdventures ) {
            if (adventure.getNameOfAdventure().toLowerCase(Locale.ROOT).contains(inputText.toLowerCase(Locale.ROOT))) {
                adventures.add(new AdventureDTO(adventure));
            }
        }
        return  new ResponseEntity<>(adventures, HttpStatus.OK);
    }


    @GetMapping("/instructorUser/{id}")
    public  ResponseEntity<UserDTO> fetchInstructor(@PathVariable int id){
        User user = this.adventureService.fetchInstructorByAdventure(id);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }


    @PostMapping("/search")
    public ResponseEntity<List<AdventureDTO>> searchStartPage(@RequestBody SearchDTO searchParam) {
        List<AdventureDTO> ret = new ArrayList<>();
        if( adventureService.searchAdventuresStartPage(searchParam).size() != 0) {
            for (Adventure c : adventureService.searchAdventuresStartPage(searchParam)) {
                ret.add(new AdventureDTO(c));
            }
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping(value="/editAdventure",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateAdventure(@RequestBody AdventureHelpDTO adventureDTO){
        System.out.print("Real name je "+ adventureDTO.getRealName());
        Boolean bool = this.adventureService.validate(adventureDTO.getRealName());
        if (bool == true) {
            this.adventureService.update(adventureDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            System.out.printf("U ovom sam delu");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
    }

    @PostMapping("/newAdventure")
    public ResponseEntity<Void> saveAdventure(@RequestBody AdventureRequestDTO newAdventure){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Instructor i = this.instructorService.findByEmail(user.getEmail());
        System.out.print("OVDE SAM2 "+i.getId());
        Adventure adventure = new Adventure(newAdventure);
        adventure.setInstructor(i);

        Address address = new Address(newAdventure.getCountry(),newAdventure.getCity(),newAdventure.getStreet(),newAdventure.getNumber());
        adventure.setAddress(this.addressService.save(address));
        if (newAdventure.getCancelationType().equalsIgnoreCase("BESPLATNO")) {
            adventure.setCancelationType(CancelationType.BESPLATNO);
        }else {
            adventure.setCancelationType(CancelationType.SA_PROCENTOM);
        }
        this.adventureService.save(adventure);
        return  new ResponseEntity<>( HttpStatus.OK);

    }


    @DeleteMapping(value = "/deleteAdventure/{name}")
    public ResponseEntity<HttpStatus> deleteAdventure (@PathVariable String name) {
        List<Adventure> adventures = adventureService.findAll();
        System.out.print("Poslali ste "+name);
        for (Adventure a : adventures) {
            if (a.getNameOfAdventure().contains(name)) {
                System.out.print("Usla sam heeeereee");
                boolean bool = this.adventureService.deleteAdventureByName(a);
                if (bool == true) {
                    System.out.print("CAO MACKO");
                    return new ResponseEntity<>(HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }

        }
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
    @PostMapping("/reservationSearch")
    public ResponseEntity<List<EntityDTO>> searchReservation(@RequestBody ReservationSearchDTO searchParam){
        ArrayList<EntityDTO> ret = new ArrayList<>();

        for(EntityClass e : adventureService.searchReservation(searchParam)){
            ret.add(new EntityDTO(e));
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/getMaxPeople/{id}")
    public ResponseEntity<Integer> getMaxPeople( @PathVariable int id){
        return new ResponseEntity<>(this.adventureService.getMaxPeople(id),HttpStatus.OK);
    }
}
