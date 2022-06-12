package com.example.demo.service.entities;

import com.example.demo.dto.entities.AdventureHelpDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.enums.CancelationType;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.Adventure;
import com.example.demo.dto.business.ReservationSearchDTO;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;
import com.example.demo.repository.business.ReservationRepository;
import com.example.demo.repository.entities.AdventureRepository;
import com.example.demo.service.users.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class AdventureService {

    private AdventureRepository adventureRepository;
    private UserService userService;
    private ReservationRepository reservationRepository;
    private EntityService entityService;
    private AddressService addressService;

    public AdventureService(AddressService addressService,EntityService entityService,AdventureRepository adventureRepository, UserService userService,ReservationRepository reservationRepository){
        this.adventureRepository = adventureRepository;
        this.userService = userService;
        this.reservationRepository=reservationRepository;
        this.entityService=entityService;
        this.addressService = addressService;
    }

    public List<Adventure> findAll() {
        return adventureRepository.findAll();
    }

    public Adventure findOne(Integer id) {
        return adventureRepository.findById(id).orElseGet(null);
    }

    public Page<Adventure> findAll(Pageable page) {
        return adventureRepository.findAll( page);
    }

    public Adventure save(Adventure adventure) { return adventureRepository.save(adventure); }

    public void remove(Integer id) {
        adventureRepository.deleteById(id);
    }

    public List<Adventure> searchAdventures(SearchDTO searchParam) {
        List<Adventure> ret = new ArrayList<>();

        for (Adventure adventure : this.findAll()){
            if (adventure.getName().toLowerCase().contains(searchParam.getName().toLowerCase()) && adventure.getAddress().getCity().toLowerCase().contains(searchParam.getCity().toLowerCase()) && adventure.getAddress().getStreet().toLowerCase().contains(searchParam.getStreet().toLowerCase())){
                ret.add(adventure);
            }
        }

        return  ret;
    }

    public User fetchInstructorByAdventure(int id) {
        Adventure a = this.adventureRepository.fetchInstructor(id);
        return this.userService.findByEmail(a.getInstructor().getEmail());
    }


    public List<Adventure> searchAdventuresStartPage(SearchDTO searchParam) {
        List<Adventure> ret = new ArrayList<>();

        for (Adventure cottage : this.findAll()) {

            //prazno sve
            if (searchParam.getName().equals("") && searchParam.getStreet().equals("") && searchParam.getCity().equals("")) {
                ret.add(cottage);
            }
            //prazno ime
            else if (searchParam.getName().equals("") && !searchParam.getStreet().equals("") && !searchParam.getCity().equals("")) {
                if (cottage.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase()) && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())) {
                    ret.add(cottage);
                }
            }
            //prazno ime i ulica
            else if (searchParam.getName().equals("") && searchParam.getStreet().equals("") && !searchParam.getCity().equals("")) {
                if (cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())) {
                    ret.add(cottage);
                }
            }
            //prazno ime i grad
            else if (searchParam.getName().equals("") && !searchParam.getStreet().equals("") && searchParam.getCity().equals("")) {
                if (cottage.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase())) {
                    ret.add(cottage);
                }
            }
            //prazna ulica i grad
            else if (!searchParam.getName().equals("") && searchParam.getStreet().equals("") && searchParam.getCity().equals("")) {
                if (cottage.getName().toLowerCase().equals(searchParam.getName().toLowerCase())) {
                    ret.add(cottage);
                }
            }
            //prazno grad
            else if (!searchParam.getName().equals("") && !searchParam.getStreet().equals("") && searchParam.getCity().equals("")) {
                if (cottage.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) && cottage.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase())) {
                    ret.add(cottage);
                }
            }
            //prazna ulica
            else if (!searchParam.getName().equals("") && searchParam.getStreet().equals("") && !searchParam.getCity().equals("")) {
                if (cottage.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())) {
                    ret.add(cottage);
                }
            }
        }
        return ret;
    }
    
    public void deleteByNameOfAdventure(String namee) {
        Adventure a = this.adventureRepository.findByNameOfAdventure(namee);
        System.out.print("NAsla sam avanturu"+ a.getNameOfAdventure());
        this.adventureRepository.delete(a);
    }

    public boolean deleteAdventureByName(Adventure a) {
        List<Reservation> reservations = this.reservationRepository.findAll();
        System.out.print("Broj rezervacija je"+reservations.size());
        for (Reservation r : reservations) {
            EntityClass e = this.entityService.findById(r.getEntity().getId());
            System.out.print("NAZIVI SU"+ e.getName());
            if (e.getName().contains(a.getNameOfAdventure())) {
                System.out.print("HEJ TI");
                return false;
            }
        }
        this.adventureRepository.delete(a);
        return true;
    }

    public void update(AdventureHelpDTO adventureDTO) {
        Adventure a = this.adventureRepository.findByNameOfAdventure(adventureDTO.getRealName());
        a.setNameOfAdventure(adventureDTO.getNameOfAdventure());
        a.setName(adventureDTO.getNameOfAdventure());
        a.setMaxNumberOfPeople(adventureDTO.getMaxNumberOfPeople());
        a.setInstructorBiografy(adventureDTO.getInstructorBiografy());
        a.setPromoDescription(adventureDTO.getPromoDescription());
        a.setFishingEquipment(adventureDTO.getFishingEquipment());
        a.setRules(adventureDTO.getRules());
        a.setImages(adventureDTO.getImages());
        a.setDescription(adventureDTO.getPromoDescription());
        Address address = new Address(adventureDTO.getAddress().getCountry(),adventureDTO.getAddress().getCity(),adventureDTO.getAddress().getStreet(),adventureDTO.getAddress().getNumber());
        this.addressService.save(address);
        a.setAddress(address);
        if (adventureDTO.getCancelationType().equals("BESPLATNO")) {
            a.setCancelationType(CancelationType.BESPLATNO);
        }else {
            a.setCancelationType(CancelationType.SA_PROCENTOM);
        }
        this.adventureRepository.save(a);
    }

    public Boolean validate(String nameOfAdventure) {
        List<Reservation> reservations = this.reservationRepository.findAll();
        System.out.print("Broj rezervacija je"+reservations.size());
        for (Reservation r : reservations) {
            EntityClass e = this.entityService.findById(r.getEntity().getId());
            System.out.print("NAZIVI SU"+ e.getName());
            System.out.print("NAZIV je"+nameOfAdventure);
            if (e.getName().contains(nameOfAdventure)) {
                System.out.print("HEJ TI");
                return false;
            }else {
                return true;
            }
        }
        return true;
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
        calEnd.add(Calendar.HOUR_OF_DAY, searchParam.getNumber());

        Boolean isNotReserved = true;
        for (Adventure cottage : this.findAll()) {
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

            if (isNotReserved && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) && cottage.getAddress().getCountry().toLowerCase().equals(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() <= cottage.getMaxNumberOfPeople()  && searchParam.getPeople() > 0) {
                ret.add(cottage);
            }
            //country + people
            if (isNotReserved && searchParam.getCity().equals("") && cottage.getAddress().getCountry().toLowerCase().equals(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() <= cottage.getMaxNumberOfPeople()  && searchParam.getPeople() > 0) {
                ret.add(cottage);
            }
            //city + people
            if (isNotReserved && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) && searchParam.getCountry().equals("") && searchParam.getPeople() <= cottage.getMaxNumberOfPeople()  && searchParam.getPeople() > 0) {

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
            if (isNotReserved && searchParam.getCity().equals("") && searchParam.getCountry().equals("") && searchParam.getPeople() <= cottage.getMaxNumberOfPeople() && searchParam.getPeople() > 0) {
                ret.add(cottage);
            }
            isNotReserved = true;
        }
        return ret;
    }

    public Integer getMaxPeople(int id) {
        Adventure adventure = this.findOne(id);
        return adventure.getMaxNumberOfPeople();
    }

    public Adventure findById(int id) {
        return this.adventureRepository.findById(id);
    }
}
