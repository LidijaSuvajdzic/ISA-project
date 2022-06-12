package com.example.demo.controller.entities;

import com.example.demo.dto.business.ReservationSearchDTO;
import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.AddressService;
import com.example.demo.service.entities.EntityService;
import com.example.demo.service.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/entities")
public class EntityController {

    private EntityService entityService;
    private AddressService addressService;
    private UserService userService;


    public EntityController(EntityService entityService,AddressService addressService,UserService userService){
        this.entityService = entityService;
        this.addressService=addressService;
        this.userService= userService;
    }
    public EntityClass findById(int id) { return entityService.findById(id);}

    @GetMapping(value = "/getAllEntities")
    public ResponseEntity<List<EntityDTO>> getAll(){
        List<EntityClass> allEntities = entityService.findAll();
        List<EntityDTO> entities = new ArrayList<>();
        for(EntityClass entity : allEntities ) {
            EntityDTO entityDTO = new EntityDTO();
            entityDTO.setId(entity.getId());
            entityDTO.setGrade(entity.getGrade());
            entityDTO.setName(entity.getName());
            entityDTO.setPrice(entity.getPrice());
            entityDTO.setPromoDescription(entity.getPromoDescription());
            entityDTO.setRules(entity.getRules());
            Address a = entity.getAddress();
            entityDTO.setAddress(a);

            entities.add(entityDTO);
        }
        return  new ResponseEntity<>(entities, HttpStatus.OK);
    }



    @DeleteMapping(value = "/deleteEntity/{deleteId}")
    public ResponseEntity<Void> deleteEntity (@PathVariable int deleteId) {
        List<EntityClass> entities = entityService.findAll();
        for(EntityClass e : entities) {
            if (e.getId()== deleteId) {
                System.out.print("Usla sam heeeereee");
                this.entityService.deleteById(deleteId);

            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
