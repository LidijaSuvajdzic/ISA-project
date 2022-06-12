package com.example.demo.controller.users;

import com.example.demo.dto.users.CottageOwnerDTO;
import com.example.demo.dto.users.DeleteUserRequestDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.DeleteUserRequest;
import com.example.demo.model.users.User;
import com.example.demo.service.email.EmailSenderService;
import com.example.demo.service.users.CustomUserDetailsService;
import com.example.demo.service.users.UserService;
import com.example.demo.service.users.DeleteUserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/userDeleteReq")
public class DeleteUserRequestController {

    private DeleteUserRequestService deleteUserRequestService;
    private  UserService userService;
    @Autowired
    private EmailSenderService service;

    public DeleteUserRequestController(DeleteUserRequestService deleteUserRequestService,UserService userService){
        this.deleteUserRequestService = deleteUserRequestService;
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteUserRequest> saveRequest(@RequestBody DeleteUserRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<DeleteUserRequest>(this.deleteUserRequestService.saveRequest(new DeleteUserRequest(request.getExplanation(), user)), HttpStatus.OK);
    }

    @GetMapping(value = "/getRequests")
    public ResponseEntity<List<DeleteUserRequestDTO>> getRequests(){
        List<DeleteUserRequest> allRequests = deleteUserRequestService.findAll();
        System.out.print("Broj requestova je"+ allRequests.size());
        List<DeleteUserRequestDTO> requests = new ArrayList<>();
        for(DeleteUserRequest u : allRequests ) {
            int id = u.getUser().getId();
            User user = this.userService.findById(id);
            System.out.print("Pronasla sam usera"+ user.getName());
            DeleteUserRequestDTO du = new DeleteUserRequestDTO(u);
            System.out.print("OVO");
            du.setName(user.getName());
            du.setSurname(user.getSurname());
            du.setMail(user.getEmail());
            System.out.print("OVO1");
            if (du.getAccepted()==null && du.getRejected()==null) {
                requests.add(du);
            }
        }
        return  new ResponseEntity<>(requests, HttpStatus.OK);
    }
    @PostMapping(value = "/confirm/{selectID}/{reason}/{userEmail}")
    public ResponseEntity<Void> acceptRequest(@PathVariable int selectID,@PathVariable String reason,@PathVariable String userEmail) throws MessagingException {
        DeleteUserRequest du = this.deleteUserRequestService.findById(selectID);
        if (du != null){
            this.deleteUserRequestService.acceptRequest(du);
            this.userService.deleteUserById(du.getUser().getId());
            service.sendEmailWithAttachment(userEmail,
                    "Razlog prihvatanja:"+reason,
                    "Prihvatanje zahteva za brisanje naloga!");
            this.deleteUserRequestService.deleteRequest(du);
            System.out.print("ID je" + du.getUser().getId());

            return new ResponseEntity<>(HttpStatus.OK);}
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/reject/{selectID}/{reasonn}/{userEmail}")
    public ResponseEntity<Void> rejectRequest(@PathVariable int selectID,@PathVariable String reasonn,@PathVariable String userEmail) throws MessagingException {
        DeleteUserRequest du = this.deleteUserRequestService.findById(selectID);
        if (du != null){
            this.deleteUserRequestService.rejectRequest(du);
            service.sendEmailWithAttachment(userEmail,
                    "Razlog odbijanja:"+reasonn,
                    "Odbijanje zahteva za brisanje naloga!");
            return new ResponseEntity<>(HttpStatus.OK);}
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*@Controller
    @RequestMapping(value = "api/cottageOwner")
    public static class CottageOwnerController {


        private CustomUserDetailsService.CottageOwnerService cottageOwnerService;

        public CottageOwnerController(CustomUserDetailsService.CottageOwnerService cottageOwnerService) {
            this.cottageOwnerService = cottageOwnerService;
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
        public  ResponseEntity<UserDTO> fetchCottageOwnerByCottage(@PathVariable  int id){
            User user = this.cottageOwnerService.fetchCottageOwnerByCottage(id);
            return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
        }
    }*/
}
