package com.example.demo.model.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.enums.LoyalityType;
import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.Cottage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "boatOwner")
@Proxy(lazy = true)
@DiscriminatorValue("BO")
public class BoatOwner extends User {

	@OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("boatOwner")
	private List<Boat> boatList;

	public BoatOwner() {
		
	}

	public BoatOwner(String name, String surname, String email, String password, String telephone, Address address, List<Boat> boatList) {
		super(name, surname, email, password, telephone, address);
		this.boatList = boatList;
	}

	public BoatOwner(UserRequest user){
		super(user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getTelephone(), user.getAddress(), user.getRole());
	}
	//PROVERI KOD REGISTRACIJE DA L NIJE PROBLEM SA ROLOM !!!!
//	public BoatOwner(User user){
//		super(user.getName(), user.getSurname(), user.getEmail(),user.getPassword(), user.getTelephone(), user.getAddress(),true, user.getRole(), user.getLastPasswordResetDate(),user.getReasonForRegistration());
//	}
	public List<Boat> getBoatList() {
		return boatList;
	}

	public void setBoatList(List<Boat> boatList) {
		this.boatList = boatList;
	}
}
