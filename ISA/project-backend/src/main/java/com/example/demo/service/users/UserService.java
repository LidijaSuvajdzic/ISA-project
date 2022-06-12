package com.example.demo.service.users;

import com.example.demo.dto.users.UpdateUserDTO;
import com.example.demo.dto.users.UserRequest;
import com.example.demo.enums.Role;

import com.example.demo.model.business.Complaint;
import com.example.demo.model.business.Evaluate;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.users.Administrator;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import com.example.demo.repository.entities.EntityRepository;
import com.example.demo.repository.users.ClientRepository;
import com.example.demo.repository.users.UserRepository;
import com.example.demo.service.business.ComplaintService;
import com.example.demo.service.business.EvaluateService;
import com.example.demo.service.business.ReservationService;
import com.example.demo.service.business.ReservedTermService;
import com.example.demo.service.entities.AddressService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService; 
    private AddressService addressService;
    private ComplaintService complaintService;
    private EvaluateService evaluateService;
    private ReservationService reservationService;
    private EntityRepository entityRepository;
    private ReservedTermService reservedTermService;
    private ClientRepository clientRepository;

    public UserService(ClientRepository clientRepository,UserRepository userRepository, PasswordEncoder passwordEncoder, AddressService addressService, RoleService roleService, ComplaintService complaintService,ReservationService reservationService,EvaluateService evaluateService, EntityRepository entityRepository,ReservedTermService reservedTermService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressService = addressService;
        this.roleService = roleService;
        this.complaintService = complaintService;
        this.evaluateService = evaluateService;
        this.reservationService = reservationService;
        this.entityRepository = entityRepository;
        this.reservedTermService = reservedTermService;
        this.clientRepository=clientRepository;
    }

    public User findById(int id) {
        return this.userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void update(UpdateUserDTO updatedUser) {
        User userToUpdate = userRepository.findById(updatedUser.getId());
        userToUpdate.setName(updatedUser.getFirstname());
        userToUpdate.setSurname(updatedUser.getLastname());
        userToUpdate.setAddress(updatedUser.getAddress());
        userToUpdate.setTelephone(updatedUser.getTelephone());
        userRepository.save(userToUpdate);
    }

    public void updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        user.setMust_change_password(false);
        userRepository.save(user);
    }

    public void updateEnableState(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void deleteById(User user){
        this.userRepository.delete(user);
    }

    public Boolean deleteUserById(int id){
        List<Reservation> reservations = reservationService.findAll();
        List<Complaint> complaints = complaintService.findAll();
        List<Evaluate> evaluates = evaluateService.findAll();

        for(Reservation r : reservations) {
            if (r.getClient().getId() == id) {
                //reservationService.deleteById(r.getId());
                return false;
            }
        }
        for(Complaint c : complaints) {
            if (c.getUserWhoSendsComplaint().getId() == id) {
                return false;
                // complaintService.deleteById(c.getId());
            }
            if (c.getUser().getId() == id) {
                return false;
                //complaintService.deleteById(c.getId());
            }
        }
        for(Evaluate e : evaluates) {
            if (e.getUser().getId() == id) {
                return false;
                // evaluateService.deleteById(e.getId());
            }
            if (e.getUserWhoSendsComplaint().getId() == id) {
               // evaluateService.deleteById(e.getId());
                return false;
            }
        }
        return true;
    }

    public boolean deleteEntityById(int id) {
        System.out.print("VAP");
        List<Complaint> complaints = complaintService.findAll();
        System.out.print("izlistala complaints");
        List<Evaluate> evaluates = evaluateService.findAll();
        System.out.print("izlistala evaluates");
        List<Reservation> reservations = reservationService.findAll();
        System.out.print("izlistala reservations");
        List<ReservedTerm> reservedTermList = reservedTermService.fidAll();
        System.out.print("izlistala reservedTermList");
        for(Complaint c : complaints) {
            if (c.getEntity().getId() == id) {
                return false;
//                System.out.print("Usla sam u complaints");
//                complaintService.deleteById(c.getId());

            }
        }
        for(Evaluate e : evaluates) {
            if (e.getEntity().getId() == id) {
                return false;
//                System.out.print("Usla sam u evaluate");
//                evaluateService.deleteById(e.getId());
            }
        }
        for (Reservation r: reservations) {
            if (r.getEntity().getId() == id) {
                return false;
//                System.out.print("Usla sam u reservations");
//                this.reservationService.delete(r);
            }
        }
        return true;
    }

    public User save(UserRequest userRequest){
        User u = new User();
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setName(userRequest.getFirstname());
       //u.setAddress(userRequest.getAddress());
        u.setSurname(userRequest.getLastname());
        u.setTelephone(userRequest.getTelephone());

        Role r = this.roleService.findByName("ROLE_ADMIN");
        if (r==null) {
            r= this.roleService.findByName("ROLE_PREDEF_ADMIN");
            if (r==null) {
                this.roleService.save(r);
                u.setRole(r);
            }
        }else {
            u.setRole(r);
        }

        u.setRole(roleService.save(new Role((userRequest.getRole())))); //ovo ce napraviti razlicite role
        u.setEmail(userRequest.getEmail());
        u.setEnabled(false);   // odmah odobreno
        u.setGrade(0);
        u.setAddress(addressService.save(userRequest.getAddress()));
        u.setReasonForRegistration(userRequest.getReasonForRegistration());
        this.userRepository.save(u);
        return u;
    }

    public User saveUser(User user){
        User exist = this.findByEmail(user.getEmail());
        if (exist == null) {

            User u = new User();
            u.setPassword(passwordEncoder.encode(user.getPassword()));
            u.setName(user.getName());
            u.setSurname(user.getSurname());
            u.setTelephone(user.getTelephone());
            u.setRole(user.getRole());
            u.setEmail(user.getEmail());
            u.setEnabled(true);
            u.setGrade(0);
            u.setAddress(addressService.save(user.getAddress()));
            this.userRepository.save(u);
            return u;
        } else {
            return this.userRepository.save(user);
        }
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> RequestUser() {
        List<User> users = new ArrayList<>();
        for (User user : this.findAll()){
            if (user.getEnabled()== false) {
                users.add(user);
            }
        }
        return  users;
    }

    @Transactional(readOnly = false)
    public Client saveClient(UserRequest userRequest){
        Client u = new Client();
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setName(userRequest.getFirstname());
        u.setSurname(userRequest.getLastname());
        u.setTelephone(userRequest.getTelephone());
        Role r = this.roleService.findByName("ROLE_CLIENT");
        if (r==null) {
            Role newRole = new Role(userRequest.getRole());
            this.roleService.save(newRole);
            u.setRole(newRole);
        }else {
            u.setRole(r);
        }
        u.setEmail(userRequest.getEmail());
        u.setEnabled(false);
        u.setGrade(0);
        u.setPenals(0);
        u.setPoents(0);
        u.setAddress(addressService.save(userRequest.getAddress()));
        System.out.print("burek");
        this.clientRepository.save(u);
        return u;
    }

    public void deleteByUserEmail(String userEmail) {
        User user = this.userRepository.findByEmail(userEmail);
        System.out.print("userov id je "+user.getId()+" CCC");
        this.deleteById(user);
    }


    public User saveAdmin(UserRequest userRequest) {
        Administrator a = new Administrator();
        a.setName(userRequest.getFirstname());
        a.setSurname(userRequest.getLastname());
        a.setEnabled(true);
        a.setMust_change_password(true);
        a.setEmail(userRequest.getEmail());
        a.setTelephone(userRequest.getTelephone());
        a.setAddress(userRequest.getAddress());
        a.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        Role r = this.roleService.findByName("ROLE_ADMIN");
        if (r==null) {
            r = this.roleService.findByName("ROLE_PREDEF_ADMIN");
            if (r==null) {
                Role newRole = new Role(userRequest.getRole());
                this.roleService.save(newRole);
                a.setRole(newRole);
            }
        }else {
            a.setRole(r);
        }
        return userRepository.save(a);
    }
}
