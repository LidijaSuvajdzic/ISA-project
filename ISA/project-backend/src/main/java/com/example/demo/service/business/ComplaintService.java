package com.example.demo.service.business;

import com.example.demo.dto.business.ComplaintClientDTO;
import com.example.demo.model.business.Complaint;
import com.example.demo.model.users.User;
import com.example.demo.repository.business.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    private ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository){
        this.complaintRepository = complaintRepository;
    }

    public Complaint saveComplaint(ComplaintClientDTO complaint, User userWhoSendsComplaint) {
        Complaint newComplaint = new Complaint();
        newComplaint.setUser(complaint.getUser());
        newComplaint.setEntity(complaint.getEntity());
        newComplaint.setUserWhoSendsComplaint(userWhoSendsComplaint);
        newComplaint.setContentEntity(complaint.getContentEntity()); //komentar na entitet
        newComplaint.setContentUser(complaint.getContentUser()); //komentar na vlasnika/instruktora
        return this.complaintRepository.save(newComplaint);
    }

    public List<Complaint> findAll () {
        return complaintRepository.findAll();
    }

    public void changeComplaint(int id) {
        Complaint c = this.complaintRepository.findById(id);
        System.out.print("OVDE SAM STIGAO"+c.getIsAnswered());
        c.setIsAnswered(true);
        this.complaintRepository.save(c);
    }

    public void deleteById(int id) {
        Complaint c = this.complaintRepository.findById(id);
        this.complaintRepository.delete(c);
    }
}
