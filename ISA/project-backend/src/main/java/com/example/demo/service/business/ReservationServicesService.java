package com.example.demo.service.business;

import com.example.demo.model.business.ReservationServices;
import com.example.demo.repository.business.ReservationServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServicesService {

    private ReservationServicesRepository reservationServicesRepository;

    public ReservationServicesService(ReservationServicesRepository reservationServicesRepository) {
        this.reservationServicesRepository = reservationServicesRepository;
    }

    public void save(ReservationServices a) {
        this.reservationServicesRepository.save(a);
    }

    public List<ReservationServices> findAll() {
        return this.reservationServicesRepository.findAll();
    }
}
