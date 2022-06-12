package com.example.demo.controller.business;

import com.example.demo.dto.business.EvaluateDTO;
import com.example.demo.model.business.Evaluate;
import com.example.demo.model.users.User;
import com.example.demo.service.business.EvaluateService;
import com.example.demo.service.email.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "api/evaluate")
public class EvaluateController {
    private EvaluateService evaluateService;
    private EmailSenderService service;
    public EvaluateController(EvaluateService evaluateService,EmailSenderService service){
        this.evaluateService = evaluateService;
        this.service = service;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<EvaluateDTO>> getAll(){
        System.out.print("Here i am");
        List<Evaluate> allEvaluates = evaluateService.findAll();
        List<EvaluateDTO> evaluates = new ArrayList<>();
        for(Evaluate c : allEvaluates ) {
            if (c.getAccepted() == null) {
                EvaluateDTO evaluateDTO = new EvaluateDTO();
                evaluateDTO.setId(c.getId());
                evaluateDTO.setContentEntity(c.getContentEntity());
                evaluateDTO.setContentUser(c.getContentUser());
                evaluateDTO.setGradeForUser(c.getGradeForUser());
                evaluateDTO.setGradeForEntity(c.getGradeForEntity());
                evaluateDTO.setLastnameOfuserWhoSendsComplaint(c.getUserWhoSendsComplaint().getSurname());
                evaluateDTO.setNameOfuserWhoSendsComplaint(c.getUserWhoSendsComplaint().getName());
                evaluateDTO.setName(c.getUser().getName());
                evaluateDTO.setLastname(c.getUser().getSurname());
                evaluateDTO.setMail(c.getUser().getEmail());
                evaluateDTO.setMailOfuserWhoSendsComplaint(c.getUserWhoSendsComplaint().getEmail());
                evaluateDTO.setAccepted(c.getAccepted());
                evaluates.add(evaluateDTO);
            }
        }
        return  new ResponseEntity<>(evaluates, HttpStatus.OK);
    }

    @PostMapping(value = "/accept/{id}/{email}")
    public ResponseEntity<Void> acceptRequest(@PathVariable int id, @PathVariable String email) throws MessagingException {
        Evaluate e = this.evaluateService.findById(id);
        System.out.print("email je "+ email);
        if (e != null){
            this.evaluateService.acceptEvaluate(e);
            service.sendEmailWithAttachment(email,
                    "Postovani,pisana je revizija za vas entitet. Upravo je odobrena. ",
                    "Prihvatanje revizije za vas i vas entitet!");
            return new ResponseEntity<>(HttpStatus.OK);}
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/reject/{id}/{email}")
    public ResponseEntity<Void> rejectRequest(@PathVariable int id, @PathVariable String email) throws MessagingException {
        Evaluate e = this.evaluateService.findById(id);
        if (e != null){
            this.evaluateService.rejectEvaluate(e);
            service.sendEmailWithAttachment(email,
                    "Postovani,pisana je revizija za vas entitet. Upravo je odbijena. ",
                    "Odbijanje revizije za vas i vas entitet!");
            return new ResponseEntity<>(HttpStatus.OK);}
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody EvaluateDTO evalueteDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        this.evaluateService.save(new Evaluate(evalueteDTO), user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
