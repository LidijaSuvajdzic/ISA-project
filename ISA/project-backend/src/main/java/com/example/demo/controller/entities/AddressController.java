package com.example.demo.controller.entities;

import com.example.demo.model.entities.Address;
import com.example.demo.service.entities.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/address")
public class AddressController {

    public AddressService addressService;

    public AddressController(AddressService addressService) { this.addressService=addressService;}

    public Address findById(int id) { return addressService.findById(id);}

}
