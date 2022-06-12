package com.example.demo.model.entities;


import javax.persistence.*;

import com.example.demo.dto.entities.BoatDTO;
import com.example.demo.enums.BoatType;
import com.example.demo.enums.CancelationType;
import com.example.demo.model.users.BoatOwner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="boat")
public class Boat extends EntityClass{
	
	@Column(name="boatType", unique=false, nullable=true)
	private BoatType boatType;
	
	@Column(name="lenght", unique=false, nullable=true)
	private int lenght;
	
	@Column(name="motorNumber", unique=false, nullable=true)
	private int motorNumber;
	
	@Column(name="power", unique=false, nullable=true)
	private int power;
	
	@Column(name="maxSpeed", unique=false, nullable=true)
	private int maxSpeed;
	
	@Column(name="navigationEquipment", unique=false, nullable=true)
	private String navigationEquipment;
	
	@Column(name="fishingEquipment", unique=false, nullable=true)
	private String fishingEquipment;
	
	@Column(name="quantity", unique=false, nullable=true)
	private int quantity;
	
	@Column(name="cancelationType", unique=false, nullable=true)
	private CancelationType cancelationType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id") //ovako ce se zvati id polje u bazi
	@JsonIgnoreProperties("boatOwner")
	public BoatOwner boatOwner;

	public Boat() {
		
	}

	public Boat(BoatType boatType, int lenght, int motorNumber, int power, int maxSpeed,
		String navigationEquipment, String fishingEquipment, int quantity, CancelationType cancelationType) {
		super();
		this.boatType = boatType;
		this.lenght = lenght;
		this.motorNumber = motorNumber;
		this.power = power;
		this.maxSpeed = maxSpeed;
		this.navigationEquipment = navigationEquipment;
		this.fishingEquipment = fishingEquipment;
		this.quantity = quantity;
		this.cancelationType = cancelationType;
	}

	public  Boat(BoatDTO dto){
		this.setId( dto.getId());
		this.setName(dto.getName());
		this.setAddress(dto.getAddress());
		this.setPromoDescription(dto.getPromoDescription());
		this.setImages(dto.getImages());
		this.setRules(dto.getRules());
		this.setPrice(dto.getPrice());
		this.setGrade(dto.getGrade());
		this.setBoatType(dto.getBoatType());
		this.setLenght(dto.getLenght());
		this.setMotorNumber(dto.getMotorNumber());
		this.setPower(dto.getPower());
		this.setMaxSpeed(dto.getMaxSpeed());
		this.setNavigationEquipment(dto.getNavigationEquipment());
		this.setFishingEquipment(dto.getFishingEquipment());
		this.setQuantity(dto.getQuantity());
		this.setCancelationType(dto.getCancelationType());
		//fale additional services


	}
	public BoatOwner getBoatOwner() {
		return boatOwner;
	}

	public void setBoatOwner(BoatOwner boatOwner) {
		this.boatOwner = boatOwner;
	}

	public BoatType getBoatType() {
		return boatType;
	}

	public void setBoatType(BoatType boatType) {
		this.boatType = boatType;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public int getMotorNumber() {
		return motorNumber;
	}

	public void setMotorNumber(int motorNumber) {
		this.motorNumber = motorNumber;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getNavigationEquipment() {
		return navigationEquipment;
	}

	public void setNavigationEquipment(String navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}

	public String getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(String fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CancelationType getCancelationType() {
		return cancelationType;
	}

	public void setCancelationType(CancelationType cancelationType) {
		this.cancelationType = cancelationType;
	}
	
	
}
