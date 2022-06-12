package com.example.demo.repository.business;

import com.example.demo.model.business.ReportOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportOwnerRepository extends JpaRepository<ReportOwner, Integer> {

ReportOwner findById(int id);
}
