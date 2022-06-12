package com.example.demo.service.entities;

import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.Cottage;
import com.example.demo.repository.entities.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressService {

    private AddressRepository addressRepository;

    public  AddressService(AddressRepository repository){ this.addressRepository=repository;}

    public List<Address> findAll() { return addressRepository.findAll();}
    public Address findById(int id) {return addressRepository.findById(id);}

    public Address save(Address course) {
        return addressRepository.save(course);
    }

    public void remove(Integer id) {
        addressRepository.deleteById(id);
    }

    public Address findByStreet(String street) {
        return addressRepository.findByStreet(street);
    }
}
