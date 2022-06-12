package com.example.demo.service.entities;


import com.example.demo.model.business.Complaint;
import com.example.demo.model.business.Evaluate;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.repository.entities.EntityRepository;
import com.example.demo.service.business.ComplaintService;
import com.example.demo.service.business.EvaluateService;
import com.example.demo.service.business.ReservationService;
import com.example.demo.service.business.ReservedTermService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntityService {

    private EntityRepository entityRepository;

    public EntityService(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Transactional
    public EntityClass findById(int id) { return entityRepository.findById(id);}

    public List<EntityClass> findAll() {
        return entityRepository.findAll();
    }

    public EntityClass findOne(Integer id) {
        return entityRepository.findById(id).orElseGet(null);
    }

    public Page<EntityClass> findAll(Pageable page) {
        return entityRepository.findAll(page);
    }

    public EntityClass EntityClass(EntityClass entity) {
        return entityRepository.save(entity);
    }

    public void remove(Integer id) {
        entityRepository.deleteById(id);
    }


    public void save(EntityClass entity) {
        this.entityRepository.save(entity);
    }

    public EntityClass findByName(String name) {
        return this.entityRepository.findByName(name);
    }
    public List<Client> findSubscribedClients(int id){
        return this.entityRepository.findSubscribedClients(id);
    }

    public  EntityClass findEntityWithReservedTerms(int id){
        return this.entityRepository.findEntityWithReservedTerms(id);
    }

    public EntityClass findEntityWithReservations(int id){
        return this.entityRepository.findEntityWithReservations(id);
    }

    public void deleteById(int deleteId) {
        EntityClass e = this.entityRepository.findById(deleteId);
        this.entityRepository.delete(e);
    }
}
