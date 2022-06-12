package com.example.demo.controller.business;

import com.example.demo.dto.business.FreeTermsDTO;
import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.business.FreeTerms;
import com.example.demo.model.users.Administrator;
import com.example.demo.model.users.Instructor;
import com.example.demo.model.users.User;
import com.example.demo.service.business.FreeTermsService;
import com.example.demo.service.users.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(value = "api/freeTerms")
public class FreeTermsController {
    private FreeTermsService freeTermsService;
    private InstructorService instructorService;

    public FreeTermsController(FreeTermsService freeTermsService,InstructorService instructorService) {
        this.instructorService=instructorService;
        this.freeTermsService=freeTermsService;
    }

    @PostMapping(value = "/period")
    public ResponseEntity<FreeTerms> addPeriod(@RequestBody FreeTermsDTO freeTermsDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Instructor i = this.instructorService.findByEmail(user.getEmail());

        System.out.print("Datum od"+freeTermsDTO.getFreeFromDate());
        System.out.print("Vreme od"+freeTermsDTO.getFreeFromTime());
        System.out.print("Datum od"+freeTermsDTO.getFreeToDate());
        System.out.print("Datum od"+freeTermsDTO.getFreeToTime());
        FreeTerms ft = new FreeTerms();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String[] parts1 = freeTermsDTO.getFreeFromDate().split("-");
        String year1 = parts1[0];
        String month1 = parts1[1];
        String day1 =parts1[2];

        String[] parts2 = freeTermsDTO.getFreeFromTime().split(":");
        String hour1 = parts2[0];
        String mins1 = parts2[1];

        String freeFrom = year1+"-"+month1+"-"+day1+" "+hour1+":"+mins1;
        LocalDateTime dateFrom = LocalDateTime.parse(freeFrom, formatter);


        String[] parts3 = freeTermsDTO.getFreeToDate().split("-");
        String year3 = parts3[0];
        String month3 = parts3[1];
        String day3=parts3[2];

        String[] parts4 = freeTermsDTO.getFreeToTime().split(":");
        String hour4 = parts4[0];
        String mins4 = parts4[1];

        String freeTo = year3+"-"+month3+"-"+day3+" "+hour4+":"+mins4;
        LocalDateTime dateTo = LocalDateTime.parse(freeTo, formatter);

        ft.setFreeFrom(dateFrom);
        ft.setFreeTo(dateTo);
        ft.setInstructor(i);

        if (ft != null){
            this.freeTermsService.save(ft);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            System.out.print("NULL je");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
