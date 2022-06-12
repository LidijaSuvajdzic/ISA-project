package com.example.demo.model.entities;

import com.example.demo.dto.entities.CottageDTO;
import com.example.demo.model.users.CottageOwner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="cottage")
public class Cottage extends EntityClass {

	@Column(name="roomsNumber", unique=false, nullable=true)
	private int roomsNumber;
	
	@Column(name="bedsByRoom", unique=false, nullable=true)
	private int bedsByRoom;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "owner_id") //ovako ce se zvati id polje u bazi
	@JsonIgnoreProperties("cottageOwner")
	public CottageOwner cottageOwner;

	public Cottage() {
		super();
	}

	public int getRoomsNumber() {
		return roomsNumber;
	}

	public void setRoomsNumber(int roomsNumber) {
		this.roomsNumber = roomsNumber;
	}

	public int getBedsByRoom() {
		return bedsByRoom;
	}

	public void setBedsByRoom(int bedsByRoom) {
		this.bedsByRoom = bedsByRoom;
	}

	public CottageOwner getCottageOwner() {
		return cottageOwner;
	}

	public void setCottageOwner(CottageOwner cottageOwner) {
		this.cottageOwner = cottageOwner;
	}

	//koristim za  cuvanje nove vikendice
	public Cottage(CottageDTO dto){
		this.setId(dto.getId());
		this.setName(dto.getName());
		this.setBedsByRoom(dto.getBedsByRoom());
		this.setRoomsNumber(dto.getRoomsNumber());
		this.setPromoDescription(dto.getPromoDescription());
		this.setImages(dto.getImages());
		this.setRules(dto.getRules());
		this.setPrice(dto.getPrice());
		this.setGrade(dto.getGrade());

	}

}
