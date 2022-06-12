package com.example.demo.repository.business;

import com.example.demo.model.business.ReservationServices;
import com.example.demo.service.business.ReservationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationServicesRepository extends JpaRepository<ReservationServices, Integer> {
}
