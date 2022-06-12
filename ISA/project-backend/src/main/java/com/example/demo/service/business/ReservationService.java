package com.example.demo.service.business;

import com.example.demo.dto.business.*;
import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.enums.EntityType;
import com.example.demo.dto.users.ClientProfileDTO;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.business.ReservationServices;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import com.example.demo.repository.business.ReservationRepository;
import com.example.demo.repository.entities.EntityRepository;
import com.example.demo.repository.users.ClientRepository;
import com.example.demo.repository.users.UserRepository;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.entities.AdditionalServicesService;
import com.example.demo.service.entities.CottageService;
import com.example.demo.service.entities.EntityService;
import com.example.demo.service.users.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private EmailService emailService;
    private ClientService clientService;
    private EntityService entityService;
    private ReservedTermService reservedTermService;
    private AdditionalServicesService additionalService;
    private ReservationServicesService reservationServicesService;
    private UserRepository userRepository;
  //  private CottageService cottageService;
   private ClientRepository clientRepository;
    @Autowired
    private EntityRepository repo;

    public ReservationService (ClientRepository clientRepository,ReservationRepository reservationRepository, EmailService emailService, ClientService clientService, EntityService entityService, ReservedTermService reservedTermService, AdditionalServicesService additionalService, ReservationServicesService reservationServicesService, CottageService cottageService,UserRepository userRepo) {
        this.reservationRepository = reservationRepository;
        this.emailService = emailService;
        this.clientService = clientService;
        this.entityService = entityService;
        this.reservedTermService = reservedTermService;
        this.additionalService = additionalService;
        this.reservationServicesService = reservationServicesService;
       // this.cottageService = cottageService;
        this.userRepository = userRepo;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> fetchAllFutureReservationsForClient(int clientId) {
        return reservationRepository.fetchAllFutureReservationsForClient(clientId, new Date());
    }

    public List<Reservation> fetchAllHistoryReservationsForClientBoats(int clientId) {
        return reservationRepository.fetchAllHistoryReservationsForClientBoats(clientId, new Date());
    }

    public List<Reservation> fetchAllHistoryReservationsForClientCottages(int clientId) {
        return reservationRepository.fetchAllHistoryReservationsForClientCottages(clientId, new Date());
    }

    public List<Reservation> fetchAllHistoryReservationsForClientAdventures(int clientId) {
        return reservationRepository.fetchAllHistoryReservationsForClientAdventures(clientId, new Date());
    }

    public EntityClass findEntityByReservation(int id) {
        return this.reservationRepository.findEntityByReservation(id);
    }



    public Reservation findById(int id) {
        return this.reservationRepository.findById(id);
    }

    public Boolean checkDate(DateDTO searchParam){
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
        EntityClass cottage = this.entityService.findById(searchParam.getId());
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
            return  isNotReserved;
    }

    public Boolean checkPenals(User user){
        Client client = this.clientService.findById(user.getEmail());

        if (client.getPenals() >= 3){
            return false;
        }

        return  true;
    }

    public List<Reservation> getAllReservationForEntity(int id){
        return  this.reservationRepository.getReservationsForEntity(id);
    }

    @Transactional(readOnly = false)
    public Boolean save(ReservationNewDTO reservation, User user)  {
        Client client = this.clientService.findById(user.getEmail());
        EntityClass entity = new EntityClass();

       try{
        entity = this.entityService.findById(reservation.getEntityId());
        } catch (PessimisticLockingFailureException e){
            System.out.println("*************************************");
            System.out.println(e);
            System.out.println("*************************************");
        }

        boolean alreadyReserved = false;

        //getting start and end date
        String[] time = reservation.getTimeStart().split(":");
        String hour = time[0];
        String minutes = time[1];
        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(reservation.getDateStart());
        calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour) - 2);
        calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(calStart.getTime());
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));

        if (reservation.getType() == EntityType.ADVENTURE){
            calEnd.add(Calendar.HOUR_OF_DAY, reservation.getDuration());
        } else {
            calEnd.add(Calendar.DAY_OF_YEAR, reservation.getDuration());
        }

        Reservation newReservation = new Reservation();
        newReservation.setTerm(new ReservedTerm(calStart.getTime(), calEnd.getTime(), entity));
        newReservation.setPrice(reservation.getPrice());
        newReservation.setClient(client);
        newReservation.setEntity(entity);
        newReservation.setEntityType(reservation.getType());
        newReservation.setDuration(reservation.getDuration());

        ArrayList<ReservationServices> services = new ArrayList<>();

        for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
            ReservationServices a = new ReservationServices(as);
            services.add(a);
            this.reservationServicesService.save(a);
        }

        newReservation.setReservationServices(services);

        Reservation canceledReservation = this.reservationRepository.getCanceledReservation(client.getId(), entity.getId(), newReservation.getTerm().getDateStart(), newReservation.getTerm().getDateEnd());

        if (canceledReservation == null) {
            entity.getReservedTerms().add(newReservation.getTerm());
            this.reservationRepository.save(newReservation);

            for(ReservationServices as : services) {
                as.setReservation(newReservation);
                this.reservationServicesService.save(as);
            }

            try {
                this.emailService.sendEmailForReservation(user);
            } catch (Exception e){
                System.out.println(e);
            }
        } else {
            alreadyReserved = true;
        }

        return alreadyReserved;
    }

    public List<Reservation> getActionsForEntity(int id) {
        return this.reservationRepository.getActionsForEntity(id);
    }

    @Transactional(readOnly = false)
    public void actionReservation(int id, User user) {
        Reservation reservation = new Reservation();
        try {
            reservation = this.reservationRepository.findById(id);
        } catch (OptimisticLockingFailureException ex){
            System.out.print(ex);
        }
        Client client = this.clientRepository.findByEmail(user.getEmail());

        reservation.setClient(client);
        this.reservationRepository.save(reservation);
        emailService.sendEmailForReservationAction(user.getEmail());
    }

    //klijent otkazuje rezervaciju
    public String cancelReservation(int id, User user) {
        Reservation reservation = this.reservationRepository.findById(id);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, 3);

        if (cal.getTime().before(reservation.getTerm().getDateStart())) {
            reservation.setCanceled(true);
            Client client = this.clientService.findById(user.getEmail());
            int penals = client.getPenals();
            client.setPenals(penals + 1);
            this.clientService.save(client);
            Client c = this.clientService.findById(user.getEmail());

            ReservedTerm term = reservation.getTerm();
            term.setCanceled(true);

           this.reservedTermService.save(term);
           this.reservationRepository.save(reservation);
           return "Reservation is canceled!";
        }

        return "You can cancel your reservation no later than three days before the start!";
    }

    public Double getTotalPrice(PriceDTO price) {
        double ret = price.getEntityPrice();

        for(AdditionalServiceDTO as : price.getServices()) {
            ret = ret + as.getPrice();
        }

        return  ret;
    }

    public List<Reservation> getAllReservationsForCottageOwner(int id_owner) {
        List<Reservation> ret = new ArrayList<>();

        for (Reservation r: this.reservationRepository.findAllReservationsForCottageOwner(id_owner) ) {
            if(r.getAction() ){
                ret.add(r);
            }
        }
        return  ret;

    }

    public List<Reservation> getAllReservationsForCottageOwnerActions(int id_owner) {
        List<Reservation> ret = new ArrayList<>();
        for (Reservation r: this.reservationRepository.findAllReservationsForCottageOwner(id_owner) ) {
            if(r.getAction()){
                ret.add(r);
            }
        }
        return  ret;

    }

    public List<Reservation> getAllReservationsForBoatOwner(int id_owner) {
        return   this.reservationRepository.findAllReservationsForBoatOwner(id_owner);
    }


    public List<Reservation> getAllReservationsForInstructor(int id) {
        List<Reservation> res = new ArrayList<>();
        res= this.reservationRepository.findAllReservationsForInstructor(id);
        return res;
    }

    public Client findClientForReservation(int id){
        return this.reservationRepository.findClientFromReservation(id);
    }

    public Date getDateEnd(DateDTO date) {
        String[] time = date.getTime().split(":");
        String hour = time[0];
        String minutes = time[1];
        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(date.getDate());
        calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour) - 2);
        calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEnd.setTime(date.getDate());
        calEnd.add(Calendar.DAY_OF_YEAR, date.getNumber());
        return calEnd.getTime();
    }

    public void deleteById(int id) {
        Reservation r = this.reservationRepository.findById(id);
        this.reservationRepository.delete(r);
    }

    public void delete(Reservation r) {
        this.reservationRepository.delete(r);

    }

    public void saveReservation(Reservation r) {
        this.reservationRepository.save(r);
         }

    public boolean findFutureReservationsForEntity(int id) {
        List<Reservation> allReservations = this.reservationRepository.getReservationsForEntity(id);
        //Date today = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        List<Reservation> futureReservations = new ArrayList<>();
        if (allReservations.size() != 0) {
            for (Reservation r : allReservations) {
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(r.getTerm().getDateEnd());
                if (cal.before(cal2)) {
                    futureReservations.add(r);
                }
            }
        }
        if(futureReservations.size() != 0){
            return false;
        }else{
            return true;
        }
    }


    public boolean checkAvailability(ActionReservationDTO action) {
        EntityClass entity = this.entityService.findById(action.getEntityId()); //da bih prosla kroz reservedTerms
        String[] time = action.getTimeStart().split(":");
        String hour = time[0];
        String minutes = time[1];

        Calendar calStartAction = Calendar.getInstance();
        Calendar calEndAction = Calendar.getInstance();

        calStartAction.setTime(action.getDateStart());
        calStartAction.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calStartAction.add(Calendar.MINUTE, Integer.parseInt(minutes));

        calEndAction.setTime(calStartAction.getTime());
        calEndAction.add(Calendar.DAY_OF_YEAR, action.getDuration());

        Boolean isNotReserved = true;



        if(entity.getReservedTerms().size() != 0){
            for( ReservedTerm rt: entity.getReservedTerms()){
                Calendar calRStart = Calendar.getInstance();
                calRStart.setTime(rt.getDateStart());

                Calendar calREnd = Calendar.getInstance();
                calREnd.setTime(rt.getDateEnd());
                if(calRStart.getTime().after(calStartAction.getTime()) && calREnd.getTime().after(calEndAction.getTime())
                       && calStartAction.getTime().before(calRStart.getTime()) && calEndAction.getTime().before(calREnd.getTime())
                    && calEndAction.getTime().after(calRStart.getTime())){
                        isNotReserved = false;
                    }
                    else if(calRStart.getTime().before(calStartAction.getTime()) && calREnd.getTime().before(calEndAction.getTime())
                     && calStartAction.getTime().after(calRStart.getTime()) && calEndAction.getTime().after(calREnd.getTime())
                    && calStartAction.getTime().before(calREnd.getTime())     //ovo pokriva kompletan period nakon neke rezervacije
                    ){
                        isNotReserved = false;
                    }
                    else if(calRStart.getTime().before(calStartAction.getTime()) && calREnd.getTime().after(calEndAction.getTime())
                      && calStartAction.getTime().after(calRStart.getTime()) && calEndAction.getTime().before(calREnd.getTime())){
                        isNotReserved = false;
                    }
                    else if(calRStart.getTime().after(calStartAction.getTime()) && calREnd.getTime().before(calEndAction.getTime())
                    && calStartAction.getTime().before(calRStart.getTime()) && calEndAction.getTime().after(calREnd.getTime())){
                        isNotReserved = false;
                    }
            }
        }
        return isNotReserved;
    }


    public boolean checkReservations(UnavailablePeriodDTO unavailable){
        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(unavailable.getDateFrom());
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEnd.setTime(unavailable.getDateTo());

//        EntityClass entity = entityService.findEntityWithReservations(unavailable.getEntityId()); //ceo entity sa rezervacijama  NE RADI
//        List<Reservation> allReservations = this.reservationRepository.getReservationsForEntity(unavailable.getEntityId());

        EntityClass entity = entityService.findById(unavailable.getEntityId());

        Boolean isNotReserved = true;

        if(entity.getReservedTerms().size() != 0){
            for( ReservedTerm r: entity.getReservedTerms()){
                Calendar calRStart = Calendar.getInstance();
                calRStart.setTime(r.getDateStart());

                Calendar calREnd = Calendar.getInstance();
                calREnd.setTime(r.getDateEnd());
                if(calRStart.getTime().after(calStart.getTime()) && calREnd.getTime().after(calEnd.getTime())
                        && calStart.getTime().before(calRStart.getTime()) && calEnd.getTime().before(calREnd.getTime())
                        && calEnd.getTime().after(calRStart.getTime())){
                    isNotReserved = false;
                }
                else if(calRStart.getTime().before(calStart.getTime()) && calREnd.getTime().before(calEnd.getTime())
                        && calStart.getTime().after(calRStart.getTime()) && calEnd.getTime().after(calREnd.getTime())
                        && calStart.getTime().before(calREnd.getTime())     //ovo pokriva kompletan period nakon neke rezervacije
                ){
                    isNotReserved = false;
                }
                else if(calRStart.getTime().before(calStart.getTime()) && calREnd.getTime().after(calEnd.getTime())
                        && calStart.getTime().after(calRStart.getTime()) && calEnd.getTime().before(calREnd.getTime())){
                    isNotReserved = false;
                }
                else if(calRStart.getTime().after(calStart.getTime()) && calREnd.getTime().before(calEnd.getTime())
                        && calStart.getTime().before(calRStart.getTime()) && calEnd.getTime().after(calREnd.getTime())){
                    isNotReserved = false;
                }

            }
        }
        return isNotReserved;
    }

    public void saveUnavailablePeriod(UnavailablePeriodDTO unavailable, int type){
        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(unavailable.getDateFrom());
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEnd.setTime(unavailable.getDateTo());

        EntityClass entity = entityService.findById(unavailable.getEntityId()); //eager je, ovako je okej
        ReservedTerm newTerm = reservedTermService.saveNewTerm(new ReservedTerm(calStart.getTime(), calEnd.getTime(), entity, false));
        entity.getReservedTerms().add(newTerm);
        entityService.save(entity);
    }

    public void saveActionBoat(ActionReservationDTO reservation)  {

        EntityClass entity = this.entityService.findById(reservation.getEntityId()); //koji je entitet
        //getting start and end date, end action date nije ishendlan
        String[] time = reservation.getTimeStart().split(":");
        String hour = time[0];
        String minutes = time[1];
        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(reservation.getDateStart());
        calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour) - 2);
        calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEnd.setTime(calStart.getTime());
        calEnd.add(Calendar.DAY_OF_YEAR, reservation.getDuration());

        Calendar calEndAction = Calendar.getInstance();
        calEndAction.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEndAction.setTime(reservation.getDateEndAction());


        Reservation newReservation = new Reservation();
        ReservedTerm newTerm= reservedTermService.saveNewTerm(new ReservedTerm(calStart.getTime(), calEnd.getTime(), entity, false));
        newReservation.setTerm(newTerm);
        entity.getReservedTerms().add(newTerm);
        newReservation.setPrice(reservation.getPrice());
         newReservation.setValidFrom(new Date());
         newReservation.setValidTo(calEndAction.getTime());

        newReservation.setClient(null);
        newReservation.setAction(true);
        newReservation.setEntity(entity);
        newReservation.setEntityType(EntityType.BOAT);
        newReservation.setDuration(reservation.getDuration());

        for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
            ReservationServices a = new ReservationServices(as);

            a.setReservation(newReservation);
            newReservation.getReservationServices().add(a);
        }

        reservationRepository.save(newReservation);
        entityService.save(entity);

        for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
            ReservationServices a = new ReservationServices(as);
            a.setReservation(newReservation);
            this.reservationServicesService.save(a);
        }
        reservationRepository.save(newReservation);

        List<Client> subscribedClients = entityService.findSubscribedClients(entity.getId());

        for (Client c: subscribedClients ) {
            this.emailService.sendEmailForCreatedAction(c.getName(), entity.getName());
        }


    }

    public void saveActionCottage(ActionReservationDTO reservation)  {
        EntityClass entity = this.entityService.findById(reservation.getEntityId());
        String[] time = reservation.getTimeStart().split(":");
        String hour = time[0];
        String minutes = time[1];
        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(reservation.getDateStart());
        calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour) - 2);
        calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEnd.setTime(calStart.getTime());
        calEnd.add(Calendar.DAY_OF_YEAR, reservation.getDuration());
        Calendar calEndAction = Calendar.getInstance();
        calEndAction.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEndAction.setTime(reservation.getDateEndAction());

        Reservation newReservation = new Reservation();
        ReservedTerm newTerm = reservedTermService.saveNewTerm(new ReservedTerm(calStart.getTime(), calEnd.getTime(), entity, false));
        newReservation.setTerm(newTerm);
        newReservation.setPrice(reservation.getPrice());
        newReservation.setValidFrom(new Date());
        newReservation.setValidTo(calEndAction.getTime());
        newReservation.setClient(null);
        newReservation.setAction(true);
        newReservation.setEntity(entity);
        newReservation.setEntityType(EntityType.COTTAGE);
        newReservation.setDuration(reservation.getDuration());



        for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
            ReservationServices a = new ReservationServices(as);

            a.setReservation(newReservation);
            newReservation.getReservationServices().add(a);
        }
        reservationRepository.save(newReservation);
        entityService.save(entity);

        for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
            ReservationServices a = new ReservationServices(as);
            a.setReservation(newReservation);
            this.reservationServicesService.save(a);
        }
        reservationRepository.save(newReservation);

        List<Client> subscribedClients = entityService.findSubscribedClients(entity.getId());

        for (Client c: subscribedClients ) {
            this.emailService.sendEmailForCreatedAction(c.getName(), entity.getName());
        }
    }

    public List<Reservation> findAllReservationsForInstructor(int id) {
        return this.reservationRepository.findAllReservationsForInstructor(id);
    }

    public void sendMailToSubscribedClients(EntityClass e) {
        List<Client> clients = this.clientService.findClientWithSubscribedEntities(e.getId());
        System.out.print("brojj klijenata je"+clients.size());
        for (Client c: clients) {
            this.emailService.sendEmailForCreatedAction(c.getEmail(),e.getName());
        }

    }

    public ClientProfileDTO findCurrentClientForEntity(int id) {
        List<Reservation> allReservations = reservationRepository.getReservationsForEntity(id);
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        ClientProfileDTO client = new ClientProfileDTO();
        if(allReservations.size()!= 0) {
            for(Reservation r : allReservations){
                Calendar calRStart = Calendar.getInstance();
                calRStart.setTime(r.getTerm().getDateStart());

                Calendar calREnd = Calendar.getInstance();
                calREnd.setTime(r.getTerm().getDateEnd());
                if( today.getTime().after(calRStart.getTime()) && today.getTime().before(calREnd.getTime()) ){
                    Client c = findClientForReservation(r.getId());
                    client.setEmail(c.getEmail());
                    client.setName(c.getName());
                    client.setId(c.getId());
                    client.setSurname(c.getSurname());
                }
            }
        }
        return  client;
    }


    public  void saveReservationOwner(ReservationNewOwnerDTO reservation) throws Exception {
        EntityClass entity = this.entityService.findOne(reservation.getEntityId()); //koji je entitet
        Client client = this.clientService.find(reservation.getClientId());
        User user = this.userRepository.findById(reservation.getClientId());

        String[] time = reservation.getTimeStart().split(":");
        String hour = time[0];
        String minutes = time[1];
        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(reservation.getDateStart());
        calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour) - 2);
        calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEnd.setTime(calStart.getTime());
        calEnd.add(Calendar.DAY_OF_YEAR, reservation.getDuration());

        Reservation newReservation = new Reservation();
        ReservedTerm newTerm = reservedTermService.saveNewTerm(new ReservedTerm(calStart.getTime(), calEnd.getTime(), entity, false));
        newReservation.setTerm(newTerm);

        newReservation.setPrice(reservation.getPrice());
        newReservation.setValidFrom(null);
        newReservation.setValidTo(null);
        newReservation.setClient(client);
        newReservation.setAction(false);
        newReservation.setEntity(entity);
        if(reservation.getType() == EntityType.COTTAGE){
            newReservation.setEntityType(EntityType.COTTAGE);
        }else{
            newReservation.setEntityType(EntityType.BOAT);
        }
        newReservation.setDuration(reservation.getDuration());
        for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
            ReservationServices a = new ReservationServices(as);
            a.setReservation(newReservation);
            newReservation.getReservationServices().add(a);
        }
        reservationRepository.save(newReservation);
        entityService.save(entity);

        for(AdditionalServiceDTO as : reservation.getAdditionalServices()) {
            ReservationServices a = new ReservationServices(as);
            a.setReservation(newReservation);
            this.reservationServicesService.save(a);
        }
        reservationRepository.save(newReservation);

        this.emailService.sendEmailForReservation(user);


    }

    public void addReservationOfInstructor(ActionRequestDTO actionRequestDTO) {


    }
}
