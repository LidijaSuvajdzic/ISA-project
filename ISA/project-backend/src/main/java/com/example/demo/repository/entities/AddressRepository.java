package com.example.demo.repository.entities;

import com.example.demo.model.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findById(int id);

    Address findByStreet(String street);
}
