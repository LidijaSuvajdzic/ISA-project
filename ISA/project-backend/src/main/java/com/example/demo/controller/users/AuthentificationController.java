package com.example.demo.controller.users;

import com.example.demo.dto.users.JwtAuthenticationRequest;
import com.example.demo.dto.users.UserRequest;
import com.example.demo.dto.users.UserTokenState;
import com.example.demo.model.users.*;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.users.*;
import com.example.demo.utils.TokenUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping(value = "api/auth")
public class AuthentificationController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private TokenUtils tokenUtils;
    private EmailService emailService;
    private ClientService clientService;
    private CottageOwnerService cottageOwnerService;
    private AdministratorService administratorService;
    private InstructorService instructorService;
    private BoatOwnerService boatOwnerService;

    public AuthentificationController (AuthenticationManager authenticationManager, UserService userService, TokenUtils tokenUtils, EmailService emailService, ClientRegistrationTokenService clientRegistrationTokenService, ClientService clientService, CottageOwnerService cottageOwnerService, AdministratorService administratorService, InstructorService instructorService,BoatOwnerService boatOwnerService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenUtils = tokenUtils;
        this.emailService = emailService;
        this.clientService = clientService;
        this.cottageOwnerService = cottageOwnerService;
        this.administratorService = administratorService;
        this.instructorService = instructorService;
        this.boatOwnerService = boatOwnerService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {

        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getEmail());
        int expiresIn = tokenUtils.getExpiredIn();
        if (user.isEnabled() == false){
            return ResponseEntity.ok(new UserTokenState(jwt, expiresIn,user.getRole().getName(), user.isEnabled(),user.isMust_change_password()));
        }

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn,user.getRole().getName(), user.isEnabled(),user.isMust_change_password()));
    }

    // Endpoint za registraciju novog korisnika
    @RequestMapping(value="/signup", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest) throws InterruptedException {
        User existUser = this.userService.findByEmail(userRequest.getEmail());
        User user = null;
        Client client = null;
       if (existUser != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (userRequest.getRole().equals("ROLE_CLIENT")) {
                System.out.print("HHHA");
                this.clientService.saveClient(userRequest);
                System.out.print("HHHAqqq");
                //emailService.sendEmailForUserAuthentication(client);
            }
            if (userRequest.getRole().equals("ROLE_ADMIN") || userRequest.getRole().equals("ROLE_PREDEF_ADMIN")) {
                //user = this.userService.saveAdmin(userRequest);
                administratorService.saveAdmin(userRequest);
            }
            if (userRequest.getRole().equals("ROLE_COTTAGE_OWNER")) {
               // user = this.userService.save(userRequest);
                this.cottageOwnerService.saveCottageOwner(userRequest);
            }
            if (userRequest.getRole().equals("ROLE_INSTRUCTOR")) {
                this.instructorService.saveInstructor(userRequest);
            }
            if (userRequest.getRole().equals("ROLE_BOAT_OWNER")) {
                //user = this.userService.save(userRequest);
                this.boatOwnerService.saveBoatOwner(userRequest);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Client> confirmAccount(@RequestParam("email") String email) throws URISyntaxException {
        User user = this.userService.findByEmail(email);

        Client client = this.clientService.save(user);
        this.userService.deleteById(user);
        URI frontend = new URI("http://localhost:8082/");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(frontend);

        return new ResponseEntity<>(new Client(), httpHeaders, HttpStatus.SEE_OTHER);
    }

}
