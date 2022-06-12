package com.example.demo.service.entities;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.model.entities.AdditionalService;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.repository.entities.AdditionalServicesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdditionalServicesService {

    private AdditionalServicesRepository additionalServicesRepository;
    private EntityService entityService;

    public AdditionalServicesService(EntityService entityService,AdditionalServicesRepository repo){

        this.additionalServicesRepository = repo;
        this.entityService=entityService;
    }

    public List<AdditionalService> saveAll(List<AdditionalService> services ){
        List<AdditionalService> ret = new ArrayList<>();
        for (AdditionalService a: services ) {
           ret.add(this.additionalServicesRepository.save(a)) ;
        }
        return  ret;
    };

//    public List<AdditionalService> saveAll(List<AdditionalService> services){
//        return  this.additionalServicesRepository.saveAll(services);
//    }

    public List<AdditionalService> getServicesForCottage(int id){
        return  this.additionalServicesRepository.findAdditionalServicesForCottage(id);
    }

    public List<AdditionalService> findAdditionalServicesForAdventure(String name){
        return  this.additionalServicesRepository.findAdditionalServicesForAdventure(name);
    }

    public void deleteById(int id) {
        this.additionalServicesRepository.deleteById(id);
    }



    public AdditionalService save(AdditionalServiceDTO additionalServiceDTO) {
        EntityClass e = this.entityService.findByName(additionalServiceDTO.getNameOfAdventure());
        List<AdditionalService> list = this.additionalServicesRepository.findAll();
        System.out.print("Velicina liste je "+list.size());
        int id = list.size()+10;
        System.out.print(" id ce biti  "+id);
        AdditionalService as = new AdditionalService();
        as.setId(id);
        as.setEntities(e);
        as.setName(additionalServiceDTO.getName());
        as.setPrice(additionalServiceDTO.getPrice());

        return this.additionalServicesRepository.save(as);
    }

    public List<AdditionalService> getAll(){
        return  this.additionalServicesRepository.findAll();
    }

    public AdditionalService findById(int id) {
        return this.additionalServicesRepository.findById(id);
    }

    public AdditionalService findByName(String name) {
        return this.additionalServicesRepository.findByName(name);
    }
}
