package com.example.demo.service.entities;

import com.example.demo.dto.business.ReservationSearchDTO;
import com.example.demo.dto.entities.BoatDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.entities.AdditionalService;

import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.BoatOwner;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;
import com.example.demo.repository.entities.AdditionalServicesRepository;
import com.example.demo.repository.entities.BoatRepository;
import com.example.demo.service.users.BoatOwnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoatService {

    private BoatRepository boatRepository;
    private AdditionalServicesRepository additionalServicesRepository;
    private BoatOwnerService boatOwnerService;

    public BoatService(BoatRepository boatRepository,BoatOwnerService ownerService, AdditionalServicesRepository additionalServicesRepository){
        this.boatRepository = boatRepository;
        this.additionalServicesRepository =additionalServicesRepository;
        this.boatOwnerService = ownerService;
    }

    public List<Boat> findAll() {
        return boatRepository.findAll();
    }

    public Boat findOne(Integer id) {
        return boatRepository.findById(id).orElseGet(null);
    }

    public Page<Boat> findAll(Pageable page) {
        return boatRepository.findAll( page);
    }

    public Boat save(Boat boat) {
        return boatRepository.save(boat);
    }

    public void remove(Integer id) {
        boatRepository.deleteById(id);
    }

    public List<Boat> searchBoatsStartPage(SearchDTO searchParam) {
        List<Boat> ret = new ArrayList<>();

        for (Boat cottage : this.findAll()) {

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

    public List<Boat> searchBoatsOwner(SearchDTO searchParam) {
        List<Boat> ret = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        if(user.getRole().getName().equals("ROLE_BOAT_OWNER")){
            List<Boat> ownersCottages = this.findBoatsForBoatOwner(user.getId());
            for (Boat c : ownersCottages ) {
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

    public List<Boat> findBoatsForBoatOwner(int id){
        return  boatRepository.findBoatsForBoatOwner(id);
    }

    public void update(BoatDTO updatedBoat, Set<AdditionalService> services){
        Boat boatNewState = this.boatRepository.findBoatWithServices(updatedBoat.getId());
        boatNewState.setName(updatedBoat.getName());
        boatNewState.setAddress(updatedBoat.getAddress());
        boatNewState.setPromoDescription(updatedBoat.getPromoDescription());
        boatNewState.setRules(updatedBoat.getRules());
        boatNewState.setPrice(updatedBoat.getPrice());
        boatNewState.setBoatType(updatedBoat.getBoatType());
        boatNewState.setLenght(updatedBoat.getLenght());
        boatNewState.setMotorNumber(updatedBoat.getMotorNumber());
        boatNewState.setPower(updatedBoat.getPower());
        boatNewState.setMaxSpeed(boatNewState.getMaxSpeed());
        boatNewState.setNavigationEquipment(updatedBoat.getNavigationEquipment());
        boatNewState.setFishingEquipment(updatedBoat.getFishingEquipment());
        boatNewState.setQuantity(boatNewState.getQuantity());
        boatNewState.setCancelationType(updatedBoat.getCancelationType());
        boatNewState.setImages(updatedBoat.getImages());
        //List<AdditionalService> current = additionalServicesRepository.findAdditionalServicesForCottage(updatedBoat.getId());
     if(updatedBoat.getAdditionalServices().size() != 0) {
         for (AdditionalService a :
                 boatNewState.getAdditionalServices()) {
             additionalServicesRepository.deleteById(a.getId());
             //boatNewState.getAdditionalServices().remove(a);
         }

     }
        boatNewState.setAdditionalServices(new HashSet<>());
         if(services.size() != 0) {
             for (AdditionalService newA : services ) {
                 //newA.setEntities(boatNewState);
                 boatNewState.getAdditionalServices().add(newA);
             }
         }
          boatRepository.save(boatNewState);

    }

    public BoatOwner findOwnerForBoat(int id) {
        return boatRepository.findBoatOwner(id);
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
        for (Boat cottage : this.findAll()) {
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

            if (isNotReserved && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) && cottage.getAddress().getCountry().toLowerCase().equals(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() <= cottage.getQuantity()  && searchParam.getPeople() > 0) {
                ret.add(cottage);
            }
            //country + people
            if (isNotReserved && searchParam.getCity().equals("") && cottage.getAddress().getCountry().toLowerCase().equals(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() <= cottage.getQuantity()  && searchParam.getPeople() > 0) {
                ret.add(cottage);
            }
            //city + people
            if (isNotReserved && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) && searchParam.getCountry().equals("") && searchParam.getPeople() <= cottage.getQuantity()  && searchParam.getPeople() > 0) {

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
            if (isNotReserved && searchParam.getCity().equals("") && searchParam.getCountry().equals("") && searchParam.getPeople() <= cottage.getQuantity() && searchParam.getPeople() > 0) {
                ret.add(cottage);
            }
            isNotReserved = true;
        }
        return ret;
    }

    public Integer getMaxPeople(int id) {
        Boat boat = this.findOne(id);
        return boat.getQuantity();
    }

    public void deleteById(int id) {
        Boat boat = this.boatRepository.findBoatWithServices(id);
        BoatOwner owner = this.findOwnerForBoat(id);
        BoatOwner ownerWithBoats = boatOwnerService.getOwnerWithBoats(owner.getId()); //zasto vrati samo 1


        for(Boat c : ownerWithBoats.getBoatList()){
            if(c.getId() == id) {
                ownerWithBoats.getBoatList().remove(c);
                boatOwnerService.save(ownerWithBoats);
                break;
            }
        }
        //brisem sve servise u kom je boat id strani kljuc
        for(AdditionalService a: boat.getAdditionalServices()){
            if(a.getEntities().getId() == id ){
                additionalServicesRepository.deleteById(a.getId());
            }
        }

        this.boatRepository.delete(boat);
    }
}
