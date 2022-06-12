package com.example.demo.service.users;

import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.users.CottageOwnerRepository;
import com.example.demo.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", username));
        } else {
            return user;
        }
    }

    /*@Service
    public static class CottageOwnerService {

        @Autowired
        private CottageOwnerRepository cottageOwnerRepository;
        @Autowired
        private UserService userService;

        public List<CottageOwner> findAll() {
            return cottageOwnerRepository.findAll();
        }

        public CottageOwner findOne(Integer id) {
            return cottageOwnerRepository.findById(id).orElseGet(null);
        }

        public Page<CottageOwner> findAll(Pageable page) {
            return cottageOwnerRepository.findAll( page);
        }

        public CottageOwner save(CottageOwner cottageOwner) {
            return cottageOwnerRepository.save(cottageOwner);

        }

        public void remove(Integer id) {
            cottageOwnerRepository.deleteById(id);
        }

        public User fetchCottageOwnerByCottage(int idCottage){
            Cottage cottage = this.cottageOwnerRepository.fetchCottageOwnerByCottage(idCottage);
            return this.userService.findByEmail(cottage.getCottageOwner().getEmail());
        }
    }*/
}
