package com.example.demo.controller.business;

import com.example.demo.dto.business.DateReportDTO;
import com.example.demo.dto.business.InformationsForChart;
import com.example.demo.dto.business.ReportOwnerDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.model.business.ReportForOwner;
import com.example.demo.model.business.ReportOwner;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.DeleteUserRequest;
import com.example.demo.model.users.User;
import com.example.demo.service.business.ReportOwnerService;
import com.example.demo.service.email.EmailSenderService;
import com.example.demo.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/reportOwner")
public class ReportOwnerController {

    private ReportOwnerService reportOwnerService;
    private UserService userService;

    @Autowired
    private EmailSenderService service;
    public  ReportOwnerController(UserService userService,ReportOwnerService reportOwnerService){
        this.reportOwnerService = reportOwnerService;
        this.userService = userService;
    }

    @GetMapping(value = "/getAllReports")
    public ResponseEntity<List<ReportOwnerDTO>> getAllReports(){
        List<ReportOwner> reports = this.reportOwnerService.findAll();
        List<ReportOwnerDTO> report = new ArrayList<>();
        for(ReportOwner r : reports ) {
            if (!r.getRevise()) {
                ReportOwnerDTO repo = new ReportOwnerDTO();
                repo.setComment(r.getComment());
                repo.setIdOwner(r.getOwner().getId());
                System.out.print("Ovde Samm");
                repo.setIdClient(r.getClient().getId());
                repo.setRevise(r.getRevise());
                System.out.print("Ovde Samm1");
                if (r.getRestrictionType().toString().equals("BEZ_PENALA")) {
                    repo.setType("BEZ_PENALA");
                } else if (r.getRestrictionType().toString().equals("NEGATIVAN_KOMENTAR")) {
                    repo.setType("NEGATIVAN_KOMENTAR");
                } else {
                    repo.setType("NIJE_SE_POJAVIO");
                }
                System.out.print("Ovde Samm2");
                User client = this.userService.findById(r.getClient().getId());
                User owner = this.userService.findById(r.getOwner().getId());
                System.out.print("Ovde Samm4");
                repo.setClientName(client.getName());
                repo.setClientLastname(client.getSurname());
                repo.setOwnerName(owner.getName());
                repo.setOwnerLastname(owner.getSurname());
                repo.setId(r.getId());
                System.out.print("Ovde Samm5");
                report.add(repo);
            }
        }
        return  new ResponseEntity<>(report, HttpStatus.OK);
    }

    @PostMapping("/reportCottage")
    public ResponseEntity<HttpStatus> addReportForCottageOwner(@RequestBody ReportOwnerDTO dto){
        this.reportOwnerService.addReportCottageOwner(dto);

        return  new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping("/reportAdventure")
    public ResponseEntity<HttpStatus> addReportForInstructor(@RequestBody ReportOwnerDTO dto){
        System.out.print("ID KLIJENTA JE"+ dto.getIdClient());
        System.out.print("ID INSTRUCTORA JE"+ dto.getIdOwner());
        System.out.print("KOMENTAR JE"+ dto.getComment());
        System.out.print("tip je"+ dto.getType());
        this.reportOwnerService.addReportCottageOwner(dto);

        return  new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteRequest/{id}")
    public ResponseEntity<Void> deleteRequest (@PathVariable int id) throws MessagingException {
        List<ReportOwner> reportOwners = this.reportOwnerService.findAll();
        for(ReportOwner r : reportOwners) {
            if (r.getId()== id) {
                System.out.print("Usla sam heeeereee");
                this.reportOwnerService.delete(id);
                String email =r.getOwner().getEmail();
                service.sendEmailWithAttachment(email,
                        "Postovani/a,vas zahtev je odbijen",
                        "Odbijanje zahteva za izvestaj o korisniku!");
            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/confirm/{id}/{typee}")
    public ResponseEntity<Void> acceptRequest(@PathVariable int id,@PathVariable String typee) throws MessagingException {

            ReportOwner du = this.reportOwnerService.findById(id);
            if (du != null){
                System.out.print("CAO CAO JA SAM LDIJA" +typee);
            if (typee.equals("BEZ_PENALA")) {
                this.reportOwnerService.acceptRequestRegularly(du);
            }else if (typee.equals("NIJE_SE_POJAVIO")) {
                this.reportOwnerService.acceptRequestWithPenals(du);
          }
    }
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/confirm1/{id}/{choise}")
    public ResponseEntity<Void> acceptRequest1(@PathVariable int id,@PathVariable String choise) throws MessagingException {
        System.out.print("USAO SAM");
        ReportOwner du = this.reportOwnerService.findById(id);
        if (du != null){
            if (choise.equals("NE")) {
                this.reportOwnerService.acceptRequestRegularly(du);
            }else if (choise.equals("DA")) {
                this.reportOwnerService.acceptRequestWithPenals(du);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/reportOwner")
//    public ResponseEntity<ReportForOwner> addReportForBusiness(@RequestBody ReportForOwner dto){
//        return  new ResponseEntity<>(this.reportOwnerService.createReportForBusiness(dto), HttpStatus.OK);
//    }

    @PostMapping("/reportOwner")
    public ResponseEntity<InformationsForChart> addReportForBusiness(@RequestBody DateReportDTO dto){
        return  new ResponseEntity<>(this.reportOwnerService.getReportForChart(dto), HttpStatus.OK);
    }
}
