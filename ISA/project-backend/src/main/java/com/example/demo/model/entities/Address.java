package com.example.demo.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@SequenceGenerator(name = "addressSeqGen", sequenceName = "addressSeqGen", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSeqGen")
	@Column(name="id", unique=true, nullable=false)
	private int id;
	@Column(name = "country", nullable = false)
	private String country;
	@Column(name = "city", nullable = false)
	private String city;
	@Column(name = "street", nullable = false)
	private String street;
	@Column(name = "number", nullable = false)
	private String number;
	
	public Address() {}
	
	public Address(int id, String country, String city, String street, String number) {
		super();
		this.id = id;
		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
	}
	public Address( String country, String city, String street, String number) {

		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
