package com.example.demo.service.users;

import com.example.demo.enums.LoyalityType;
import com.example.demo.enums.Role;
import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.entities.EntityRepository;
import com.example.demo.repository.users.CottageOwnerRepository;
import com.example.demo.service.entities.AddressService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CottageOwnerService {

    private CottageOwnerRepository cottageOwnerRepository;
    private UserService userService;
    private EntityRepository entityRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private AddressService addressService;

    public CottageOwnerService(AddressService addressService,CottageOwnerRepository cottageOwnerRepository, PasswordEncoder passwordEncoder, RoleService roleService,EntityRepository entityRepository, UserService userService){
        this.cottageOwnerRepository = cottageOwnerRepository;
        this.entityRepository= entityRepository;
        this.userService = userService;
        this.entityRepository = entityRepository;
        this.passwordEncoder=passwordEncoder;
        this.roleService=roleService;
        this.addressService=addressService;
    }

    public CottageOwner save(CottageOwner cottageOwner) {
        //cottageOwner.setCottageList(new ArrayList<>());
        return cottageOwnerRepository.save(cottageOwner);

    }
    public  CottageOwner findById(int id) {
        return cottageOwnerRepository.getById(id);
    }

    public CottageOwner findByEmail(String id) {
        return this.cottageOwnerRepository.findByEmail(id);
    }

    public void remove(Integer id) {
        cottageOwnerRepository.deleteById(id);
    }

    public List<CottageOwner> findAll() {
        return cottageOwnerRepository.findAll();
    }

    public CottageOwner findOne(Integer id) {
        return cottageOwnerRepository.findById(id).orElseGet(null);
    }

    public User fetchCottageOwnerByCottage(int idCottage){
        Cottage cottage = this.cottageOwnerRepository.fetchCottageOwnerByCottage(idCottage);
        return this.userService.findByEmail(cottage.getCottageOwner().getEmail());
    }

    public void saveCottageOwner(UserRequest userRequest) {
        CottageOwner u = new CottageOwner();
        u.setEmail(userRequest.getEmail());
        u.setEnabled(false);
        u.setMust_change_password(false);
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setName(userRequest.getFirstname());
        u.setReasonForRegistration(userRequest.getReasonForRegistration());
        u.setSurname(userRequest.getLastname());
        u.setTelephone(userRequest.getTelephone());
        u.setLoyalityType(LoyalityType.REGULAR);
        u.setPoents(0);
        u.setIncome(0.0);
        Role r = this.roleService.findByName("ROLE_COTTAGE_OWNER");
        if (r==null) {
            Role newRole = new Role(userRequest.getRole());
            this.roleService.save(newRole);
            u.setRole(newRole);
        }else {
            u.setRole(r);
        }
        System.out.print("USPELA");
        u.setGrade(0);
        Address a = new Address(userRequest.getAddress().getCountry(),userRequest.getAddress().getCity(),userRequest.getAddress().getStreet(),userRequest.getAddress().getNumber());
        this.addressService.save(a);
        System.out.print("USPELA SAM ");
        u.setAddress(a);
        System.out.print("USPELA SAM HEHE");

        this.cottageOwnerRepository.save(u);



    }
}
