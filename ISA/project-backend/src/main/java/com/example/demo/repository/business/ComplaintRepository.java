package com.example.demo.repository.business;

import com.example.demo.model.business.Complaint;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    Complaint findById(int id);
    Complaint save(Complaint complaint);
}
