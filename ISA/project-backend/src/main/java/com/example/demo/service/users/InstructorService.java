package com.example.demo.service.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.enums.LoyalityType;
import com.example.demo.model.entities.Address;
import com.example.demo.model.users.Instructor;
import com.example.demo.enums.Role;
import com.example.demo.repository.users.InstructorRepository;
import com.example.demo.service.entities.AddressService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {
    private InstructorRepository instructorRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private AddressService addressService;

    public InstructorService(InstructorRepository instructorRepository, PasswordEncoder passwordEncoder,RoleService roleService,AddressService addressService) {
        this.instructorRepository=instructorRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.addressService = addressService;
    }

    public Instructor save(Instructor cottageOwner) {
        return instructorRepository.save(cottageOwner);
    }

    public void saveInstructor(UserRequest userRequest) {
        Instructor u = new Instructor();
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
        Role r = this.roleService.findByName("ROLE_INSTRUCTOR");
        if (r==null) {
            Role newRole = new Role(userRequest.getRole());
            this.roleService.save(newRole);
            u.setRole(newRole);
        }else {
            u.setRole(r);
        }

        u.setGrade(0);
        Address a = new Address(userRequest.getAddress().getCountry(),userRequest.getAddress().getCity(),userRequest.getAddress().getStreet(),userRequest.getAddress().getNumber());
        this.addressService.save(a);
        u.setAddress(a);
        System.out.print("USPELA SAM HEHE");

        this.instructorRepository.save(u);
    }

    public Instructor findByEmail(String email) {
        return this.instructorRepository.findByEmail(email);
    }
}
