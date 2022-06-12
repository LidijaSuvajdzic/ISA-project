package com.example.demo.service.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.enums.LoyalityType;
import com.example.demo.enums.Role;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import com.example.demo.repository.entities.EntityRepository;
import com.example.demo.repository.users.ClientRepository;
import com.example.demo.service.entities.AddressService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private EntityRepository entityRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private AddressService addressService;

    public ClientService (RoleService roleService,AddressService addressService, PasswordEncoder passwordEncoder,ClientRepository clientRepository, EntityRepository entityRepository){
        this.addressService=addressService;
        this.roleService = roleService;
        this.clientRepository = clientRepository;
        this.entityRepository = entityRepository;
        this.passwordEncoder =passwordEncoder;
    }

    @Transactional
    public Client findById(String id){
        return this.clientRepository.findByEmail(id);
    }
    public Client find(int id){
        return this.clientRepository.findById(id);
    }

    public Client findClientById(int id){
        return this.clientRepository.getById(id);
    }

    public List<EntityClass> findSubscribedEnities(int idClient){
        return this.clientRepository.findSubscribedEnities(idClient);
    }

    public void cancelSubsrciption(int idEntity, Client client){
        EntityClass entity = this.entityRepository.findById(idEntity);
        List<EntityClass> entities = this.findSubscribedEnities(client.getId());

        if (entities.size() > 0) {
            for (EntityClass e : entities
            ) {
                if (entities.size() > 0) {
                    if (e.getId() == idEntity) {
                        entities.remove(e);
                        client.setSubscribedEntities(entities);
                        this.clientRepository.save(client);
                        break;
                    }
                }
            }
        }

        List<Client> clients = this.clientRepository.findClientWithSubscribedEntities(idEntity);
        if (clients.size() > 0) {
            for (Client c : clients
            ) {
                if (clients.size() > 0) {
                    if (c.getId() == client.getId()) {
                        clients.remove(c);
                        entity.setSubscribedClients(clients);
                        this.entityRepository.save(entity);
                        break;
                    }
                }
            }
        }

    }

    public List<Client> findClientWithSubscribedEntities(int id) {
        return this.clientRepository.findClientWithSubscribedEntities(id);
    }


    public void addSubsrciptions(int idEntity, Client client){
        EntityClass entity = this.entityRepository.findById(idEntity);
        System.out.print("ajajaj"+entity.getName());
        List<EntityClass> entities = this.findSubscribedEnities(client.getId());
        if (entities == null) {
            entities = new ArrayList<>();
        }
        entities.add(entity);
        client.setSubscribedEntities(entities);
        this.clientRepository.save(client);

        List<Client> clients = this.clientRepository.findClientWithSubscribedEntities(idEntity);
        if (clients == null){
            clients = new ArrayList<>();
        }
        clients.add(client);
        entity.setSubscribedClients(clients);
        this.entityRepository.save(entity);
    }

    public Client save(User user) {
        return this.clientRepository.save(new Client(user));
    }

    public Client save(Client client){
        return this.clientRepository.save(client);
    }

    @Scheduled(cron = "${greeting.cron}")
    public void deletePenals(){
        List<Client> clients = this.clientRepository.findAll();
        for (Client c : clients){
            c.setPenals(0);
            this.clientRepository.save(c);
        }
    }


    public void saveClient(UserRequest userRequest) {
        System.out.print("burek1");
        Client u = new Client();
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setName(userRequest.getFirstname());
        u.setSurname(userRequest.getLastname());
        u.setTelephone(userRequest.getTelephone());
        u.setLoyalityType(LoyalityType.REGULAR);
        u.setPoents(0);
        u.setIncome(0.0);
        System.out.print("burek2");
        //Role r = this.roleService.findByName("ROLE_CLIENT");
       // System.out.print("rola je"+r.getName());
        //if (r==null) {
//            Role newRole = new Role(userRequest.getRole());
//            this.roleService.save(newRole);
//            u.setRole(newRole);
        //}else {
        //    u.setRole(r);
       // }
        Role r = this.roleService.findByName("ROLE_CLIENT");
        if (r==null) {
            Role newRole = new Role(userRequest.getRole());
            this.roleService.save(newRole);
            u.setRole(newRole);
        }else {
            u.setRole(r);
        }

        System.out.print("burek3");
        u.setEmail(userRequest.getEmail());
        u.setEnabled(false);
        u.setGrade(0);
        u.setPenals(0);
        u.setPoents(0);
        System.out.print("burek4");
        u.setAddress(addressService.save(userRequest.getAddress()));
        System.out.print("burek5");
        this.clientRepository.save(u);

    }

    public List<Client> getAll() {
        return this.clientRepository.findAll();
    }
}
