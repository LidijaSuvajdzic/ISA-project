package com.example.demo.controller.business;

import com.example.demo.dto.business.ComplaintClientDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.model.business.Complaint;
import com.example.demo.model.users.User;
import com.example.demo.service.business.ComplaintService;

import com.example.demo.service.email.EmailSenderService;
import com.example.demo.service.users.UserService;
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
@RequestMapping(value = "api/complaint")
public class ComplaintController {

    private ComplaintService complaintService;
    private EmailSenderService service;
    public ComplaintController(ComplaintService complaintService,EmailSenderService service){
        this.complaintService = complaintService;
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComplaintClientDTO> saveComplaint(@RequestBody ComplaintClientDTO complaint){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        complaintService.saveComplaint(complaint,user);

        return new ResponseEntity<ComplaintClientDTO>(new ComplaintClientDTO(), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllComplaints")
    public ResponseEntity<List<ComplaintClientDTO>> getAllComplaints(){
        List<Complaint> allComplaints = complaintService.findAll();
        List<ComplaintClientDTO> complaints = new ArrayList<>();
        for(Complaint c : allComplaints ) {
            ComplaintClientDTO com = new ComplaintClientDTO();
            com.setId(c.getId());
            com.setComplaintType(c.getComplaintType().toString());
            com.setContentEntity(c.getContentEntity());
            com.setContentUser(c.getContentUser());
            com.setLastnameOfuserWhoSendsComplaint(c.getUserWhoSendsComplaint().getSurname());
            com.setNameOfuserWhoSendsComplaint(c.getUserWhoSendsComplaint().getName());
            com.setName(c.getUser().getName());
            com.setLastname(c.getUser().getSurname());
            com.setMail(c.getUser().getEmail());
            com.setMailOfuserWhoSendsComplaint(c.getUserWhoSendsComplaint().getEmail());
            com.setAnswered(c.getIsAnswered());
            System.out.print("da li je ?"+com.getIsAnswered());
            complaints.add(com);
        }
        return  new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @PostMapping(value = "/sendMails/{emailWhoSent}/{emailWhoReceive}/{content1}/{content2}/{id}")
    public ResponseEntity<Void> sendMails(@PathVariable String emailWhoSent,@PathVariable String emailWhoReceive,@PathVariable String content1,@PathVariable String content2,@PathVariable int id) throws InterruptedException, MessagingException {
                 service.sendEmailWithAttachment(emailWhoSent,
                    content1,
                    "Odgovor na zalbu");

                service.sendEmailWithAttachment(emailWhoReceive,
                content2,
                "Odgovor na zalbu");
                System.out.print("Id zalbe je "+id+"CCC");
                this.complaintService.changeComplaint(id);
            return new ResponseEntity<>(HttpStatus.OK);}
    }


