package com.example.demo.model.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.demo.model.business.Reservation;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.users.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="entities")
public class EntityClass {
	@Id
	@SequenceGenerator(name = "mySeqGenEntity", sequenceName = "mySeqEntity", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenEntity")
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="name", nullable=false)
	private String name;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE )
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Address address;
	
	@Column(name="promoDescription", unique=false, nullable=false, length = 10485760)
	private String promoDescription; //promotivni opis
	
//	@OneToMany(mappedBy = "entity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JsonIgnoreProperties("entity") //da ne pravi loop
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private Set<Image> image;

@ElementCollection(fetch = FetchType.EAGER)
@CollectionTable(name = "entity_images", joinColumns = @JoinColumn(name = "entity_id"))
	@Column(name="images", length = 10485760)
	private Set<String> images = new HashSet<>();
	
	@Column(name="rules", unique=false, nullable=false)
	private String rules;
	
	@Column(name="price", unique=false, nullable=false)
	private int price; //je l ovo dovoljno za cenovnik
	//slobodni termini sa akcijama za rezervaciju OVO NEMAMO

	@Column(name="grade", unique=false, nullable=false)
	private double grade;

	@OneToMany(mappedBy = "entity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Reservation> reservations;

	@ManyToMany(mappedBy = "subscribedEntities")
	@JsonIgnore
	private List<Client> subscribedClients;

	@OneToMany(mappedBy= "entity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("entity")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<AdditionalService> additionalServices = new HashSet<AdditionalService>();

	@OneToMany(mappedBy = "entity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ReservedTerm> reservedTerms;
	
	public EntityClass() {
		super();
	}

	public EntityClass(int id, String name, Address address, String promoDescription, Set<String> image,
			Set<AdditionalService> additionalServices, String rules, int price, double grade) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.promoDescription = promoDescription;
		this.images = image;
		this.additionalServices = additionalServices;
		this.rules = rules;
		this.price = price;
		this.grade = grade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPromoDescription() {
		return promoDescription;
	}

	public void setPromoDescription(String promoDescription) {
		this.promoDescription = promoDescription;
	}

	public Set<String> getImages() {
		return images;
	}

	public void setImages(Set<String> image) {
		this.images = image;
	}

	public Set<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public List<Client> getSubscribedClients() {
		return subscribedClients;
	}

	public void setSubscribedClients(List<Client> subscribedClients) {
		this.subscribedClients = subscribedClients;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<ReservedTerm> getReservedTerms() {
		return reservedTerms;
	}

	public void setReservedTerms(List<ReservedTerm> reservedTerms) {
		this.reservedTerms = reservedTerms;
	}
}
