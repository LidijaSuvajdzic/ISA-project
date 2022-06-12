package com.example.demo.service.entities;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.dto.entities.CottageDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.entities.AdditionalService;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.entities.AdditionalServicesRepository;
import com.example.demo.dto.business.ReservationSearchDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.repository.entities.CottageRepository;
import com.example.demo.repository.users.CottageOwnerRepository;
import com.example.demo.service.users.CottageOwnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CottageService {

    private CottageRepository cottageRepository;

    private  AdditionalServicesService aditionalServicesService;
    private  EntityService entityService;
   // private CottageOwnerService ownerService;
    private CottageOwnerRepository cottageOwnerRepository;


    public CottageService(CottageRepository cottageRepository, AdditionalServicesService additionalServicesService, EntityService entityService, CottageOwnerRepository cottageOwnerRepository){
        this.cottageRepository = cottageRepository;
        this.aditionalServicesService = additionalServicesService;
        this.entityService = entityService;
       // this.ownerService = coService;
        this.cottageOwnerRepository = cottageOwnerRepository;
    }

    public List<Cottage> findAll() {
        return cottageRepository.findAll();
    }

    public Cottage findOne(Integer id) {
        Cottage cottage = cottageRepository.findById(id).orElseGet(null);
        return cottage;
    }

    public Page<Cottage> findAll(Pageable page) {
        return cottageRepository.findAll( page);
    }

    public Cottage save(Cottage course) {
        return cottageRepository.save(course);
    }

    public void remove(Integer id) {
        cottageRepository.deleteById(id);
    }

    public List<Cottage> searchStartPage(SearchDTO searchParam){
        List<Cottage> ret = new ArrayList<>();
        for (Cottage cottage : this.findAll()) {

            //prazno sve
            if( searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                ret.add(cottage);
            }
            //prazno ime
            else if(searchParam.getName().equals("") && !searchParam.getStreet().equals("") &&  !searchParam.getCity().equals("")){
                if( cottage.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase()) && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())){
                    ret.add(cottage);
                }
            }
            //prazno ime i ulica
            else if(searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  !searchParam.getCity().equals("")){
                if(cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())){
                    ret.add(cottage);
                }
            }
            //prazno ime i grad
            else if(searchParam.getName().equals("") && !searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                if( cottage.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase())){
                    ret.add(cottage);
                }
            }
            //prazna ulica i grad
            else if(!searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                if(cottage.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) ){
                    ret.add(cottage);
                }
            }
            //prazno grad
            else if(!searchParam.getName().equals("") && !searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                if(cottage.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) && cottage.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase()) ){
                    ret.add(cottage);
                }
            }
            //prazna ulica
            else if(!searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  !searchParam.getCity().equals("")){
                if(cottage.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) ){
                    ret.add(cottage);
                }
            }
        }

        return  ret;
    }

    public List<Cottage> searchCottages(SearchDTO searchParam){
        List<Cottage> ret = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        if(user.getRole().getName().equals("ROLE_COTTAGE_OWNER")){
            List<Cottage> ownersCottages = this.findAllOwnerCottages(user.getId());
            for (Cottage c : ownersCottages ) {
                //prazno sve
                if( searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                   //vratice sve
                        ret.add(c);
                  }
                //prazno ime
                else if(searchParam.getName().equals("") && !searchParam.getStreet().equals("") &&  !searchParam.getCity().equals("")){
                    if( c.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase()) && c.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())){
                        ret.add(c);
                    }
                }
                //prazno ime i ulica
                else if(searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  !searchParam.getCity().equals("")){
                    if(c.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())){
                        ret.add(c);
                    }
                }
                //prazno ime i grad
                else if(searchParam.getName().equals("") && !searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                    if( c.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase())){
                        ret.add(c);
                    }
                }
                //prazna ulica i grad
                else if(!searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                    if(c.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) ){
                        ret.add(c);
                    }
                }
                //prazno grad
                else if(!searchParam.getName().equals("") && !searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                    if(c.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) && c.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase()) ){
                        ret.add(c);
                    }
                }
                //prazna ulica
                else if(!searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  !searchParam.getCity().equals("")){
                    if(c.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) && c.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) ){
                        ret.add(c);
                    }
                }
            }
        }
        return  ret;

    }

    public List<EntityClass> searchReservation(ReservationSearchDTO searchParam){
    List<EntityClass> ret = new ArrayList<>();
    String[] time = searchParam.getTime().split(":");
    String hour = time[0];
    String minutes = time[1];

    Calendar calStart = Calendar.getInstance();
    calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
    calStart.setTime(searchParam.getDate());
    calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
    calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
    Calendar calEnd = Calendar.getInstance();
    calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
    calEnd.setTime(searchParam.getDate());
    calEnd.add(Calendar.DAY_OF_YEAR, searchParam.getNumber());

    Boolean isNotReserved = true;
    for (Cottage cottage : this.findAll()) {
        for (ReservedTerm term : cottage.getReservedTerms()) {
            if (!term.isCanceled()) {
                //  |***term***|
                //      |----cal---|
                if (calStart.getTime().after(term.getDateStart()) && calStart.getTime().before(term.getDateEnd()) && calEnd.getTime().after(term.getDateEnd())
                ) {
                    isNotReserved = false;
                }
                //  |----cal---|
                //     |***term***|
                if (calStart.getTime().before(term.getDateStart()) && calEnd.getTime().before(term.getDateEnd()) && calEnd.getTime().after(term.getDateStart())) {
                    isNotReserved = false;
                }
                //  |------cal------|
                //     |***term***|
                if (calStart.getTime().before(term.getDateStart()) && calEnd.getTime().after(term.getDateEnd())
                ) {
                    isNotReserved = false;
                }
                //    |----cal----|
                //  |******term******|
                if (calStart.getTime().after(term.getDateStart()) && calEnd.getTime().before(term.getDateEnd())
                ) {
                    isNotReserved = false;
                }
            }

        }

        if (isNotReserved && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) && cottage.getAddress().getCountry().toLowerCase().equals(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() <= cottage.getBedsByRoom() * cottage.getRoomsNumber()  && searchParam.getPeople() > 0) {
            ret.add(cottage);
        }
        //country + people
        if (isNotReserved && searchParam.getCity().equals("") && cottage.getAddress().getCountry().toLowerCase().equals(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() <= cottage.getBedsByRoom() * cottage.getRoomsNumber()  && searchParam.getPeople() > 0) {
            ret.add(cottage);
        }
        //city + people
        if (isNotReserved && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) && searchParam.getCountry().equals("") && searchParam.getPeople() <= cottage.getBedsByRoom() * cottage.getRoomsNumber()  && searchParam.getPeople() > 0) {

            ret.add(cottage);
        }
        //city + country
        if (isNotReserved && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) && cottage.getAddress().getCountry().toLowerCase().equals(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() == 0) {
            ret.add(cottage);
        }
        //no city, no country, no people
        if (isNotReserved && searchParam.getCity().equals("") && searchParam.getCountry().equals("") && searchParam.getPeople() == 0) {
            ret.add(cottage);
        }
        //country
        if (isNotReserved && searchParam.getCity().equals("") && cottage.getAddress().getCountry().toLowerCase().equals(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() == 0) {
            ret.add(cottage);
        }
        //city
        if (isNotReserved && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) && searchParam.getCountry().equals("") && searchParam.getPeople() == 0) {
            ret.add(cottage);
        }
        //people
        if (isNotReserved && searchParam.getCity().equals("") && searchParam.getCountry().equals("") && searchParam.getPeople() <= cottage.getBedsByRoom() * cottage.getRoomsNumber() && searchParam.getPeople() > 0) {
            ret.add(cottage);
        }
       // isNotReserved = true;
    }
        return ret;
    }

    public List<Cottage> findAllOwnerCottages(int id){
        List<Cottage> ret = this.cottageRepository.findAllCottagesForOwner(id);
        return ret;
    }

    public void deleteById(int id) {
        Cottage cottage = this.cottageRepository.getCottageWithServices(id);
        CottageOwner owner = this.findCottageOwner(id);

        for(Cottage c: cottage.getCottageOwner().getCottageList()){
            if(c.getId() == id) {
                c.getCottageOwner().getCottageList().remove(c);
                for(AdditionalService a : c.getAdditionalServices() ){
                    if(a.getEntities().getId() == id ){
                        aditionalServicesService.deleteById(a.getId()); //oni su one to many, tako da se brisu odmah
                    }
                }


                cottageOwnerRepository.save(owner);
                break;
            }
        }

        this.cottageRepository.delete(cottage);


    }

    public Cottage saveCottage(Cottage newCottage){
        return this.cottageRepository.save(newCottage);
    }

    public void update(CottageDTO editCottage, Set<AdditionalService> newServices) {
        Cottage newCottageState = this.cottageRepository.getCottageWithServices(editCottage.getId());

        newCottageState.setAddress(editCottage.getAddress());
        newCottageState.setName(editCottage.getName());
        newCottageState.setPrice(editCottage.getPrice());
        newCottageState.setPromoDescription(editCottage.getPromoDescription());
        newCottageState.setRules(editCottage.getRules());
        newCottageState.setRoomsNumber(editCottage.getRoomsNumber());
        newCottageState.setBedsByRoom(newCottageState.getBedsByRoom());
        newCottageState.setImages(editCottage.getImages());

        if (editCottage.getAdditionalServices().size() != 0) {
            for (AdditionalService a :
                    newCottageState.getAdditionalServices()) {
                aditionalServicesService.deleteById(a.getId());
            }
        }
        newCottageState.setAdditionalServices(new HashSet<>());

        if (newServices.size() != 0) {
            for (AdditionalService newA : newServices) {
                //newA.setEntities(boatNewState);
                newCottageState.getAdditionalServices().add(newA);
            }
        }
        cottageRepository.save(newCottageState);
    }

    public CottageOwner findCottageOwner(int id){
        return cottageRepository.findCottageOwner(id);
    }
    
    public Integer getMaxPeople(int id) {
        Cottage cottage = this.findOne(id);
        return cottage.getRoomsNumber() * cottage.getBedsByRoom();
    }
}
