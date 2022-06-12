package com.example.demo.service.users;

import com.example.demo.enums.Role;
import com.example.demo.repository.users.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository=roleRepository;
    }

    public Role save(Role role) { return this.roleRepository.save(role);}

    public void saveRole(Role role) {  this.roleRepository.save(role);}

    public void deleteById(int id) {
        this.roleRepository.deleteById(id);
    }

    public void delete(Role role) {
        this.roleRepository.delete(role);
    }

    public Role findById(int id){ return this.roleRepository.findById(id);}

    public Role findByName(String name){ return this.roleRepository.findByName(name);}

}
