package com.example.demo.controller.business;

import com.example.demo.dto.business.*;

import com.example.demo.dto.business.ReservationDTO;
import com.example.demo.dto.business.ReservationNewDTO;
import com.example.demo.dto.business.ReservationnDTO;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.enums.EntityType;
import com.example.demo.dto.users.ClientProfileDTO;
import com.example.demo.enums.LoyalityType;
import com.example.demo.model.business.FreeTerms;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.business.ReservationServices;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.entities.AdditionalService;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.Instructor;
import com.example.demo.model.users.User;
import com.example.demo.service.business.FreeTermsService;
import com.example.demo.service.business.ReservationService;
import com.example.demo.service.business.ReservationServicesService;
import com.example.demo.service.business.ReservedTermService;
import com.example.demo.service.email.EmailSenderService;
import com.example.demo.service.entities.AdditionalServicesService;
import com.example.demo.service.entities.EntityService;
import com.example.demo.service.users.CottageOwnerService;
import com.example.demo.service.users.ClientService;
import com.example.demo.service.users.InstructorService;
import com.example.demo.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


@CrossOrigin
@Controller
@RequestMapping(value = "api/reservation")
public class ReservationController {

    private ReservationService reservationService;
    private EntityService entityService;
    private ClientService  clientService;
    private ReservedTermService reservedTermService;
    private AdditionalServicesService additionalServicesService;
    private ReservationServicesService reservationServicesService;
    private FreeTermsService freeTermsService;
    private InstructorService instructorService;
    private UserService userService;

    @Autowired
    private EmailSenderService service;
    public ReservationController(UserService userService,InstructorService instructorService,FreeTermsService freeTermsService,ReservationServicesService reservationServicesService,AdditionalServicesService additionalServicesService,ReservationService reservationService,EntityService entityService, CottageOwnerService cottageOwnerService,ClientService clientService, ReservedTermService reservedTermService){
        this.reservationService = reservationService;
        this.entityService = entityService;
        this.clientService = clientService;
        this.reservedTermService=reservedTermService;
        this.additionalServicesService = additionalServicesService;
        this.reservationServicesService=reservationServicesService;
        this.freeTermsService =  freeTermsService;
        this.instructorService=instructorService;
        this.userService=userService;
    }

    @GetMapping("/scheduledReservations")
    public ResponseEntity<List<ReservationDTO>> getAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.fetchAllFutureReservationsForClient(user.getId());
        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            ret.add(new ReservationDTO(reservation));
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/historyReservationsAdventures")
    public ResponseEntity<List<ReservationDTO>> historyReservationsAdventures(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.fetchAllHistoryReservationsForClientAdventures(user.getId());
        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            ret.add(new ReservationDTO(reservation));
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/historyReservationsBoats")
    public ResponseEntity<List<ReservationDTO>> historyReservationsBoats(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.fetchAllHistoryReservationsForClientBoats(user.getId());
        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            ret.add(new ReservationDTO(reservation));
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }


    @GetMapping("/getUserFromReservation/{id}")
    public ResponseEntity<UserDTO> historyReservationsBoats(@PathVariable int id){
        System.out.print("id je"+id);

        List<Reservation> reservations = this.reservationService.findAll();
        System.out.print("duzina niza je" + reservations.size());
        UserDTO userDTO = new UserDTO();
        for(Reservation reservation : reservations ) {
            if (reservation.getClient() != null) {
                if(reservation.getClient().getId() == id) {
                    System.out.print("USLA SAM OVDE");
                    User user1 = this.userService.findById(reservation.getClient().getId());
                    userDTO.setFirstname(user1.getName());
                    userDTO.setLastname(user1.getSurname());
                    userDTO.setEmail(user1.getEmail());
                    System.out.print("first name je"+userDTO.getFirstname());
                }
            }
        }

        return  new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    @GetMapping("/getMyReservations")
    public ResponseEntity<List<ReservationnDTO>> getMyReservations(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        System.out.print("OVDE SAM");
        List<Reservation> scheduledReservations = reservationService.findAllReservationsForInstructor(user.getId());
        List<ReservationnDTO> ret = new ArrayList<>();
        System.out.print("OVDE SAM111");
        for(Reservation reservation : scheduledReservations ) {
            ReservationnDTO reservationDTO = new ReservationnDTO(reservation);
            System.out.print("OVDE SAM2222");
            EntityClass e = this.entityService.findById(reservation.getEntity().getId());
            EntityDTO entityDTO = new EntityDTO(e);
            reservationDTO.setEntity(entityDTO);
            ret.add(reservationDTO);
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }



    @GetMapping("/myRegularReservation")
    public ResponseEntity<List<ReservationnDTO>> getMyRegularReservation(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.findAllReservationsForInstructor(user.getId());
        System.out.print("BROJ RESERVACIJA JE" + scheduledReservations.size());
        List<ReservationnDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            if (!reservation.getAction()) {
                System.out.print("USLA:");
            ReservationnDTO reservationDTO = new ReservationnDTO();
            reservationDTO.setId(reservation.getId());
            reservationDTO.setCanceled(reservation.getCanceled());
            reservationDTO.setPrice(reservation.getPrice());
            reservationDTO.setDuration(reservation.getDuration());
            reservationDTO.setClientID(String.valueOf(reservation.getClient().getId()));
            reservationDTO.setDateStart1(reservation.getTerm().getDateStart());
            reservationDTO.setDateEnd1(reservation.getTerm().getDateEnd());
            System.out.print("PRVO:");
            ReservedTerm rt = this.reservedTermService.findById(reservation.getTerm().getId());
            ReservedTermDTO rtDTO = new ReservedTermDTO(rt);
                System.out.print("DRUGO:");
            reservationDTO.setTerm(rtDTO);
            reservationDTO.setDateEnd(rtDTO.getDateEnd().toString());
            reservationDTO.setDateStart(rtDTO.getDateStart().toString());
                System.out.print("TRECE:");
            EntityClass e = this.entityService.findById(reservation.getEntity().getId());
                System.out.print("CETVRTO:");
            EntityDTO entityDTO = new EntityDTO(e);
            reservationDTO.setEntity(entityDTO);
            ret.add(reservationDTO);
            }
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/myActionReservation")
    public ResponseEntity<List<ReservationnDTO>> myActionReservation(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.findAllReservationsForInstructor(user.getId());
        List<ReservationnDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            if (reservation.getAction()) {
                ReservationnDTO reservationDTO = new ReservationnDTO();
                reservationDTO.setId(reservation.getId());
                reservationDTO.setCanceled(reservation.getCanceled());
                reservationDTO.setPrice(reservation.getPrice());
                reservationDTO.setDuration(reservation.getDuration());

                ReservedTerm rt = this.reservedTermService.findById(reservation.getTerm().getId());
                ReservedTermDTO rtDTO = new ReservedTermDTO(rt);
                reservationDTO.setTerm(rtDTO);
                reservationDTO.setDateEnd(rtDTO.getDateEnd().toString());
                reservationDTO.setDateStart(rtDTO.getDateStart().toString());
                reservationDTO.setDateEnd1(reservation.getTerm().getDateEnd());
                reservationDTO.setDateStart1(reservation.getTerm().getDateStart());
                EntityClass e = this.entityService.findById(reservation.getEntity().getId());
                EntityDTO entityDTO = new EntityDTO(e);
                reservationDTO.setEntity(entityDTO);
                ret.add(reservationDTO);
            }
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }


    @GetMapping("/historyReservationsCottages")
    public ResponseEntity<List<ReservationDTO>> historyReservationsCottages(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> scheduledReservations = reservationService.fetchAllHistoryReservationsForClientCottages(user.getId());
        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation reservation : scheduledReservations ) {
            ret.add(new ReservationDTO(reservation));
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping(value = "/getSuccessfulBooking")
    public ResponseEntity<List<ReservationnDTO>> getSuccessfulBooking(){
        List<Reservation> allReservation = reservationService.findAll();
        List<ReservationnDTO> reservations = new ArrayList<>();
        for(Reservation r : allReservation ) {
            if (!r.getCanceled()) {
                ReservationnDTO reservationDTO = new ReservationnDTO(r);
                System.out.print("id je"+r.getEntity().getId());
                EntityClass e = this.entityService.findById(r.getEntity().getId());
                EntityDTO entityDTO = new EntityDTO(e);
                reservationDTO.setEntity(entityDTO);
                reservations.add(reservationDTO);
            }
        }
        return  new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(value = "/getSomeReservations/{dateFrom}/{dateTo}")
    public ResponseEntity<List<ReservationnDTO>> getSomeReservations(@PathVariable String dateFrom, @PathVariable String dateTo) throws ParseException {
        String[] parts1 = dateFrom.split("-");
        String year1 = parts1[0];
        String month1 = parts1[1];
        String day1 =parts1[2];
        String realDateFrom = day1+"/"+month1+"/"+year1;

        String[] parts2 = dateTo.split("-");
        String year2 = parts2[0];
        String month2 = parts2[1];
        String day2 =parts2[2];
        String realDateTo = day2+"/"+month2+"/"+year2;

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date dateStart = format.parse(realDateFrom);
        Date dateEnd = format.parse(realDateTo);

        List<Reservation> allReservation1 = reservationService.findAll();
        List<Reservation> allReservation = new ArrayList<>();
        for (Reservation r: allReservation1) {
            if(r.getCanceled() == false) {
                allReservation.add(r);
            }
        }
        System.out.print("duzina liste je" + allReservation.size());
        List<ReservationnDTO> reservationns = new ArrayList<>();
        for(Reservation r : allReservation ) {
            System.out.print(dateStart);
            System.out.print(dateEnd);

            System.out.print(r.getTerm().getDateStart().toString());
            System.out.print(r.getTerm().getDateEnd().toString());

            int y1 = r.getTerm().getDateStart().getYear();
            int m1 = r.getTerm().getDateStart().getMonth();
            int d1 = r.getTerm().getDateStart().getDay();

            int y2 = r.getTerm().getDateEnd().getYear();
            int m2 = r.getTerm().getDateEnd().getMonth();
            int d2 = r.getTerm().getDateEnd().getDay();

            if (r.getTerm().getDateStart().after(dateStart) || (y1 == dateStart.getYear() && m1 == dateStart.getMonth() && d1 == dateStart.getDay())) {
                if ((r.getTerm().getDateEnd().before(dateEnd)) || (y2 == dateEnd.getYear() && m2 == dateEnd.getMonth() && d2 == dateEnd.getDay())) {
                    ReservationnDTO reservationDTO = new ReservationnDTO(r);
                    EntityClass e = this.entityService.findById(r.getEntity().getId());
                    EntityDTO entityDTO = new EntityDTO(e);
                    reservationDTO.setEntity(entityDTO);
                    reservationns.add(reservationDTO);
                }
            }

        }
        return  new ResponseEntity<>(reservationns, HttpStatus.OK);
    }

    @GetMapping(value = "/getEarningsByDate/{dateFrom}/{dateTo}")
    public ResponseEntity<Double> getEarningsByDate(@PathVariable String dateFrom, @PathVariable String dateTo) throws ParseException {
        String[] parts1 = dateFrom.split("-");
        String year1 = parts1[0];
        String month1 = parts1[1];
        String day1 =parts1[2];
        String realDateFrom = day1+"/"+month1+"/"+year1;

        String[] parts2 = dateTo.split("-");
        String year2 = parts2[0];
        String month2 = parts2[1];
        String day2 =parts2[2];
        String realDateTo = day2+"/"+month2+"/"+year2;

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date dateStart = format.parse(realDateFrom);
        Date dateEnd = format.parse(realDateTo);

        List<Reservation> allReservation1 = reservationService.findAll();
        List<Reservation> allReservation = new ArrayList<>();
        for (Reservation r: allReservation1) {
            if(r.getCanceled() == false) {
                allReservation.add(r);
            }
        }
        System.out.print("duzina liste je" + allReservation.size());
        List<ReservationnDTO> reservationns = new ArrayList<>();
        for(Reservation r : allReservation ) {
            System.out.print(dateStart);
            System.out.print(dateEnd);

            System.out.print(r.getTerm().getDateStart().toString());
            System.out.print(r.getTerm().getDateEnd().toString());

            int y1 = r.getTerm().getDateStart().getYear();
            int m1 = r.getTerm().getDateStart().getMonth();
            int d1 = r.getTerm().getDateStart().getDay();

            int y2 = r.getTerm().getDateEnd().getYear();
            int m2 = r.getTerm().getDateEnd().getMonth();
            int d2 = r.getTerm().getDateEnd().getDay();

            if (r.getTerm().getDateStart().after(dateStart) || (y1 == dateStart.getYear() && m1 == dateStart.getMonth() && d1 == dateStart.getDay())) {
                if ((r.getTerm().getDateEnd().before(dateEnd)) || (y2 == dateEnd.getYear() && m2 == dateEnd.getMonth() && d2 == dateEnd.getDay())) {
                    ReservationnDTO reservationDTO = new ReservationnDTO(r);
                    EntityClass e = this.entityService.findById(r.getEntity().getId());
                    EntityDTO entityDTO = new EntityDTO(e);
                    reservationDTO.setEntity(entityDTO);
                    reservationns.add(reservationDTO);
                }
            }

        }

        double totalEarnings = 0.0;
        for (ReservationnDTO r : reservationns) {
            totalEarnings = totalEarnings+ r.getPrice();
        }
        return  new ResponseEntity<>(totalEarnings, HttpStatus.OK);
    }


    @GetMapping(value = "/getTotalEarnings")
    public ResponseEntity<Double> getTotalEarnings(){
        System.out.print("Cao1");
        Double totalEarnings = 0.0;
        List<Reservation> allReservation = reservationService.findAll();
        List<ReservationDTO> reservations = new ArrayList<>();
        for(Reservation r : allReservation ) {
            if (!r.getCanceled()) {
                ReservationDTO reservation = new ReservationDTO();
                reservation.setId(r.getId());
                //reservation.setDateStart(r.getTerm().getDateStart().toString());
                //reservation.setDateEnd(r.getTerm().getDateEnd().toString());
                reservation.setPrice(r.getPrice());
                EntityClass entity = new EntityClass();
                entity = entityService.findById(r.getEntity().getId());
                reservation.setEntity(entity);
                reservations.add(reservation);
            }
        }
        for( ReservationDTO reservationDTO: reservations) {
            totalEarnings += reservationDTO.getPrice();
        }
        return  new ResponseEntity<>(totalEarnings, HttpStatus.OK);
    }

    @GetMapping("/entity/{id}")
    public ResponseEntity<EntityDTO> findEntityByReservation(@PathVariable int id){

        Reservation reservation = this.reservationService.findById(id);
        EntityClass entity = this.reservationService.findEntityByReservation(id);

        return  new ResponseEntity<>(new EntityDTO(entity, reservation.getEntityType()), HttpStatus.OK);
    }

    @GetMapping("/actions/{id}")
    public ResponseEntity<List<ReservationDTO>> getActionsForEntity(@PathVariable int id){
        List<Reservation> reservations = reservationService.getActionsForEntity(id);
        List<ReservationDTO> ret = new ArrayList<>();
        for(Reservation reservation : reservations ) {
            ret.add(new ReservationDTO(reservation));
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/actionReservation/{id}")
    public ResponseEntity<HttpStatus> actionReservation(@PathVariable int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        try {
            reservationService.actionReservation(id, user);
        } catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody ReservationNewDTO reservation){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (!this.reservationService.checkPenals(user)){
            return new ResponseEntity<String>("Imate tri ili vise penala!Ne mozete vrsiti rezervisanje do prvog sledeceg u mesecu.", HttpStatus.OK);
        }

        boolean ret = false;
        try {
            ret = !this.reservationService.save(reservation, user);
        } catch (Exception e){
            System.out.print("*******************************************************************************************");
            System.out.print(e);
            System.out.print("*******************************************************************************************");
            return new ResponseEntity<String>("Ne mozete u ovom trenutku da izvrsite rezervaciju, neko je vec zakazauje!Pokusajte ponovo za koji minut!", HttpStatus.BAD_REQUEST);
        }

        if (ret){
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Vec ste jednom zapazali i otkazali ovu vikendicu u ovom periodu!Ne mozete ponovo!", HttpStatus.OK);
        }
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return new ResponseEntity<>(reservationService.cancelReservation(id, user), HttpStatus.OK);
    }

    @PostMapping("/totalPrice")
    public ResponseEntity<Double> getTotalPrice(@RequestBody PriceDTO price){
        return new ResponseEntity<>(this.reservationService.getTotalPrice(price), HttpStatus.OK);
    }

    //cottage owner
    @GetMapping("/allReservationsCottageOwner")
    public ResponseEntity<List<ReservationForOwnerDTO>> allReservationsCottageOwner()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
       // CottageOwner cottageOwner = cottageOwnerService.findById(user.getId());

        List<Reservation> allReservations = reservationService.getAllReservationsForCottageOwner(user.getId());
        List<ReservationForOwnerDTO> ret = new ArrayList<>();
        for(Reservation reservation : allReservations ) {
            if(reservationService.findClientForReservation(reservation.getId()) == null){
                ret.add(new ReservationForOwnerDTO(reservation.getId(), reservation.getAction(), new ReservedTermDTO(reservation.getTerm()), reservation.getPrice(),new EntityDTO(reservation.getEntity()), reservation.getCanceled(), reservation.getDuration(), new ClientProfileDTO("/", 0,"/","/") ));
            }else {
                Client c = reservationService.findClientForReservation(reservation.getId());
                ClientProfileDTO client = new ClientProfileDTO(c);
                ret.add(new ReservationForOwnerDTO(reservation,client));}

        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping("/checkDate")
    public ResponseEntity<Boolean> checkDate(@RequestBody DateDTO date){
        if (this.reservationService.checkDate(date)){
            return new ResponseEntity<>(true,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getDateEnd")
    public ResponseEntity<Date> getDateEnd(@RequestBody DateDTO date){
        return new ResponseEntity<>(this.reservationService.getDateEnd(date),HttpStatus.OK);
    }

    @PostMapping("/addAction")
    public ResponseEntity<HttpStatus> addAction(@RequestBody ActionRequestDTO actionRequestDTO) throws ParseException {
        System.out.print("naziv entiteta je"+actionRequestDTO.getName());
        System.out.print("vreme pocetka je" +actionRequestDTO.getTimeStart());
        ReservedTerm rt = new ReservedTerm();
        EntityClass e = this.entityService.findByName(actionRequestDTO.getName());
        rt.setEntity(e);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

        String[] parts1 = actionRequestDTO.getDateStart().split("-");
        String year1 = parts1[0];
        String month1 = parts1[1];
        String day1 =parts1[2];
        String realDateFrom = year1+"-"+month1+"-"+day1+" "+actionRequestDTO.getTimeStart();


        String[] parts2 = actionRequestDTO.getDateEnd().split("-");
        String year2 = parts2[0];
        String month2 = parts2[1];
        String day2 =parts2[2];
        String realDateTo = year2+"-"+month2+"-"+day2+" "+actionRequestDTO.getTimeEnd();

        Date dateStart = format.parse(realDateFrom);
        Date dateEnd = format.parse(realDateTo);
        rt.setDateStart(dateStart);
        rt.setDateEnd(dateEnd);
        System.out.print("DOVDE SAM");

        this.reservedTermService.saveNewTerm(rt);

        Reservation r = new Reservation();
        r.setTerm(rt);
        r.setPrice(actionRequestDTO.getPrice());
        r.setEntityType(EntityType.ADVENTURE);
        String[] parts3 = actionRequestDTO.getValidFrom().split("-");
        String year3 = parts3[0];
        String month3 = parts3[1];
        String day3 =parts3[2];
        String validFromString =year3+"-"+month3+"-"+day3+" "+"00:00";

        String[] parts4 =actionRequestDTO.getValidTo().split("-");
        String year4 = parts4[0];
        String month4 = parts4[1];
        String day4=parts4[2];
        String validToString = year4+"-"+month4+"-"+day4+" "+"00:00";

        Date validFrom = format.parse(validFromString);
        Date validTo = format.parse(validToString);

        r.setValidFrom(validFrom);
        r.setValidTo(validTo);
        r.setAction(true);
        r.setSuccessful(true);
        r.setCanceled(false);
        long diff = dateEnd.getTime() - dateStart.getTime();
        int duration = (int) TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
        r.setDuration(duration);
        r.setEntity(e);

        List<ReservedTerm> terms = this.reservedTermService.fidAll();
        for (ReservedTerm reservedTerm: terms) {
                if (reservedTerm.getId() == e.getId()) {
                    if (rt.getDateStart().getYear() == reservedTerm.getDateStart().getYear() && rt.getDateStart().getMonth() == reservedTerm.getDateStart().getMonth()  && rt.getDateStart().getDay() == reservedTerm.getDateStart().getDay()) {
                        System.out.print("U VALIDACIJI SAM");
                        if ((rt.getDateStart().getHours() <= reservedTerm.getDateStart().getHours() && rt.getDateEnd().getHours()>=reservedTerm.getDateStart().getHours() && rt.getDateEnd().getHours()<=reservedTerm.getDateEnd().getHours()) || ( rt.getDateStart().getHours()>=reservedTerm.getDateStart().getHours() && rt.getDateEnd().getHours()<=reservedTerm.getDateEnd().getHours()) || (
                                rt.getDateStart().getHours() <= reservedTerm.getDateEnd().getHours() && rt.getDateEnd().getHours() >= reservedTerm.getDateEnd().getHours()
                                )) {
                            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                        }

                    }
                }
        }

        this.reservationService.saveReservation(r);

        List<AdditionalServiceDTO> list = actionRequestDTO.getAdditionalServices();
        for (AdditionalServiceDTO a: list) {
            AdditionalService as = this.additionalServicesService.findByName(a.getName());
            ReservationServices rs = new ReservationServices();
            rs.setName(as.getName());
            rs.setReservation(r);
            rs.setPrice(as.getPrice());
            this.reservationServicesService.save(rs);
            System.out.print(a.getName());
        }

        this.reservationService.sendMailToSubscribedClients(e);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/addReservationOfInstructor")
    public ResponseEntity<HttpStatus> addReservationOfInstructor(@RequestBody ActionRequestDTO actionRequestDTO) throws ParseException, MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Instructor i = this.instructorService.findByEmail(user.getEmail());

        ReservedTerm rt = new ReservedTerm();
        EntityClass e = this.entityService.findByName(actionRequestDTO.getName());
        rt.setEntity(e);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

        String[] parts1 = actionRequestDTO.getDateStart().split("-");
        String year1 = parts1[0];
        String month1 = parts1[1];
        String day1 =parts1[2];
        String realDateFrom = year1+"-"+month1+"-"+day1+" "+actionRequestDTO.getTimeStart();


        String[] parts2 = actionRequestDTO.getDateEnd().split("-");
        String year2 = parts2[0];
        String month2 = parts2[1];
        String day2 =parts2[2];
        String realDateTo = year2+"-"+month2+"-"+day2+" "+actionRequestDTO.getTimeEnd();

        Date dateStart = format.parse(realDateFrom);
        Date dateEnd = format.parse(realDateTo);
        rt.setDateStart(dateStart);
        rt.setDateEnd(dateEnd);
        System.out.print("DOVDE SAM");



        Reservation r = new Reservation();
        r.setTerm(rt);
        r.setPrice(actionRequestDTO.getPrice());
        r.setEntityType(EntityType.ADVENTURE);

        r.setAction(false);
        r.setSuccessful(true);
        r.setCanceled(false);
        long diff = dateEnd.getTime() - dateStart.getTime();
        int duration = (int) TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
        r.setDuration(duration);
        r.setEntity(e);

        System.out.print("String je "+ actionRequestDTO.getSelectedClient());
        String string = actionRequestDTO.getSelectedClient();

        //CLIENT i INSTRUCTOR- LOYALTy

        Client c = this.clientService.findById(string);
        System.out.print("NASAO SAM KLIJENTA"+c.getName());
        r.setClient(c);
        double discount= 0;             //ZA REGULAR SNIZENJE NE POSTOJI KAO NI POVECANI PRIHODI
        double upIncomes = 0.0;
        if (c.getPoents() > 30) {                   //AKO IMA VISE OD 30 POENA,POSRAJE SILVER
            c.setLoyalityType(LoyalityType.SLIVER);  //ZA SILVER SNIZENJE I POVECANJE PRIHODA IZNOSI 15%
            discount = 0.15;
        }else if (c.getPoents() > 70) {                  //AKO IMA VISE OD 70 POENA,POSRAJE GOLD
            c.setLoyalityType(LoyalityType.GOLD);       //ZA GOLD SNIZENJE I POVECANJE PRIHODA IZNOSI 30%
            discount = 0.3;
            upIncomes= 0.3;
        }
        if(i.getPoents() >30) {
            i.setLoyalityType(LoyalityType.SLIVER);
            upIncomes = 0.15;
        }else if(i.getPoents() >70) {
            i.setLoyalityType(LoyalityType.GOLD);
            upIncomes = 0.3;
        }



        double price = r.getPrice();
        double priceWithDiscount = price - price*discount;
        r.setPrice(priceWithDiscount);

        List<AdditionalServiceDTO> additionalServices = actionRequestDTO.getAdditionalServices();
        for (AdditionalServiceDTO a: additionalServices) {
            System.out.print("caooooooo"+a.getName());
            r.setPrice(r.getPrice()+a.getPrice());
            System.out.print("Setovana nova cena je"+r.getPrice());
        }

        List<FreeTerms> freeTerms = this.freeTermsService.findAll();
        boolean is_in = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


        for(FreeTerms ft: freeTerms) {
            LocalDateTime dateFrom = LocalDateTime.parse(realDateFrom, formatter);
            LocalDateTime dateTo = LocalDateTime.parse(realDateTo, formatter);

            System.out.print("Validnost od"+realDateFrom);
            System.out.print("Validnost do"+ realDateTo);
            if( ( dateFrom.isAfter(ft.getFreeFrom()) || dateFrom.isEqual(ft.getFreeFrom()) ) && ( dateTo.isBefore(ft.getFreeTo()) || dateTo.isEqual(ft.getFreeTo())  )  )
                is_in = true;
            }
        if (!is_in) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        List<ReservedTerm> reservedTermList = this.reservedTermService.findAll();

        System.out.print("Upala si u Freee term");
        System.out.print("UId entiteta je "+ e.getId());
        for(ReservedTerm reservedTerm: reservedTermList) {
            if (reservedTerm.getEntity().getId() == e.getId()) {


            LocalDateTime dateFrom = LocalDateTime.parse(realDateFrom, formatter);
            LocalDateTime dateTo = LocalDateTime.parse(realDateTo, formatter);

            String str1 = reservedTerm.getDateStart().toString();
            String[] datee = str1.split(" ");
            String date = datee[0];

            String timee = datee[1];
            String[] separate = timee.split(":");
            String time = separate[0]+":"+separate[1];

            String datefrom = date+" "+time;

                String str2 = reservedTerm.getDateEnd().toString();
                String[] datee2 = str2.split(" ");
                String date2 = datee2[0];

                String timee2 = datee2[1];
                String[] separate2 = timee2.split(":");
                String time2 = separate2[0]+":"+separate2[1];

                String dateto = date2+" "+time2;


                System.out.print("datum 1 ukucan je "+datefrom);
                System.out.print("datum 2 ukucan je "+dateto);
             System.out.print("datum 1 iz baze je "+datefrom);
             System.out.print("datum 2 iz baze je "+dateto);
                LocalDateTime dateFromList = LocalDateTime.parse(datefrom, formatter);
            LocalDateTime dateToList = LocalDateTime.parse(dateto, formatter);
            is_in = false;
            if(
            ( ( dateFrom.isAfter(dateFromList) || dateFrom.isEqual(dateFromList) ) && ( dateTo.isBefore(dateToList) || dateTo.isEqual(dateToList) ) ) ||
            (dateFrom.isBefore(dateFromList) && (dateTo.isAfter(dateFromList) && dateTo.isBefore(dateToList)) ) ||
            (  dateFrom.isBefore(dateFromList) && dateTo.isEqual(dateFromList)) ||
                    (dateFrom.isAfter(dateFromList) && dateFrom.isBefore(dateToList) &&  dateTo.isAfter(dateToList)) ||
                    (  dateFrom.isEqual(dateToList) && dateTo.isAfter(dateToList) ) ||
                (dateFrom.isBefore(dateFromList) && dateTo.isAfter(dateToList)) ||
                    (dateFrom.isAfter(dateFromList) && dateTo.isBefore(dateToList)) ||
                    (dateFrom.isEqual(dateFromList) && dateTo.isEqual(dateToList)) ) {
                is_in = false;
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            }
        }


        i.setPoents(i.getPoents()+10);
        c.setPoents(c.getPoents()+10);

        if (c.getPoents() > 30) {                   //AKO IMA VISE OD 30 POENA,POSRAJE SILVER
            c.setLoyalityType(LoyalityType.SLIVER);  //ZA SILVER SNIZENJE I POVECANJE PRIHODA IZNOSI 15%
            discount = 0.15;
        }else if (c.getPoents() >70) {                  //AKO IMA VISE OD 70 POENA,POSRAJE GOLD
            c.setLoyalityType(LoyalityType.GOLD);       //ZA GOLD SNIZENJE I POVECANJE PRIHODA IZNOSI 30%
            discount = 0.3;
            upIncomes= 0.3;
        }
        if(i.getPoents() >30) {
            i.setLoyalityType(LoyalityType.SLIVER);
            upIncomes = 0.15;
            i.setIncome(i.getIncome()+r.getPrice()*upIncomes);
        }else if(i.getPoents() >70) {
            c.setLoyalityType(LoyalityType.GOLD);
            upIncomes = 0.3;
            i.setIncome(i.getIncome()+r.getPrice()*upIncomes);
        }

        this.instructorService.save(i);
        this.clientService.save(c);
        System.out.print("KLIJENTOV email JE"+ c.getEmail());
        this.service.sendEmailWithAttachment(c.getEmail(),
                "Vasa rezervacija entiteta je uspesna:",
                "Potvrda o rezervaciji");

        this.reservedTermService.saveNewTerm(rt);
        this.reservationService.saveReservation(r);

        List<AdditionalServiceDTO> list = actionRequestDTO.getAdditionalServices();
        for (AdditionalServiceDTO a: list) {
            AdditionalService as = this.additionalServicesService.findByName(a.getName());
            ReservationServices rs = new ReservationServices();
            rs.setName(as.getName());
            rs.setReservation(r);
            rs.setPrice(as.getPrice());
            this.reservationServicesService.save(rs);
            System.out.print(a.getName());
        }

        //this.reservationService.sendMailToSubscribedClients(e);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/allReservationsBoatOwner")
    public ResponseEntity<List<ReservationForOwnerDTO>> allReservationsBoatOwner()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Reservation> allReservations = reservationService.getAllReservationsForBoatOwner(user.getId());
        List<ReservationForOwnerDTO> ret = new ArrayList<>();
        for(Reservation reservation : allReservations ) {
            if(reservationService.findClientForReservation(reservation.getId()) == null){
                ret.add(new ReservationForOwnerDTO(reservation.getId(),reservation.getAction(), new ReservedTermDTO(reservation.getTerm()), reservation.getPrice(),new EntityDTO(reservation.getEntity()), reservation.getCanceled(), reservation.getDuration(), new ClientProfileDTO("/", 0,"/","/") ));
            }else {
                Client c = reservationService.findClientForReservation(reservation.getId());
                ClientProfileDTO client = new ClientProfileDTO(c);
                ret.add(new ReservationForOwnerDTO(reservation, client));
            }
        }

        return  new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<HttpStatus> checkIfFutureReservationExist(@PathVariable int id){
        if(this.reservationService.findFutureReservationsForEntity(id)){
            return  new ResponseEntity<>( HttpStatus.OK);
        }else{
            return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/checkAvailability")
    public ResponseEntity<HttpStatus> checkAvailability(@RequestBody ActionReservationDTO action){
            if( this.reservationService.checkAvailability(action)){
                return  new ResponseEntity<>( HttpStatus.OK);
            } else {
                return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
            }
    }

    @PostMapping("/actionCottage")
    public ResponseEntity<HttpStatus> createActionReservationCottage(@RequestBody ActionReservationDTO action)  {
        this.reservationService.saveActionCottage(action);

        return  new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping("/actionBoat")
    public ResponseEntity<HttpStatus> createActionReservationBoat(@RequestBody ActionReservationDTO action)  {
        this.reservationService.saveActionBoat(action);

        return  new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping("/checkIfReservationExist")
    public ResponseEntity<HttpStatus> checkIfReservationExist(@RequestBody UnavailablePeriodDTO action){
        if( this.reservationService.checkReservations(action)){
            return  new ResponseEntity<>( HttpStatus.OK);
        } else {
            return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/unavailablePeriodCottageOwner")
    public ResponseEntity<HttpStatus> unavailablePeriodDefineCO(@RequestBody UnavailablePeriodDTO action){

        this.reservationService.saveUnavailablePeriod(action, 1);
            return  new ResponseEntity<>( HttpStatus.OK);

    }

    @PostMapping("/unavailablePeriodBoatOwner")
    public ResponseEntity<HttpStatus> unavailablePeriodDefineBO(@RequestBody UnavailablePeriodDTO action){

        this.reservationService.saveUnavailablePeriod(action, 0);
        return  new ResponseEntity<>( HttpStatus.OK);

    }

    @GetMapping("/currentClient/{id}")
    public ResponseEntity<ClientProfileDTO> findCurrentClientForEntity(@PathVariable int id){
            ClientProfileDTO client = this.reservationService.findCurrentClientForEntity(id);
        return  new ResponseEntity<>( client, HttpStatus.OK);

    }

    @PostMapping("/makeReservationOwner")
    public  ResponseEntity<HttpStatus> makeReservationOwner(@RequestBody ReservationNewOwnerDTO res) throws Exception {
        this.reservationService.saveReservationOwner(res);
        return  new ResponseEntity<>( HttpStatus.OK);
    }


}
