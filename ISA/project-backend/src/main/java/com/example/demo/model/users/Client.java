package com.example.demo.model.users;

import javax.persistence.*;

import com.example.demo.enums.LoyalityType;
import com.example.demo.enums.Role;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.entities.EntityClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
@Proxy(lazy = true)
@DiscriminatorValue("CC")
public class Client extends User{
	


	@Column(name = "penals", nullable = true)
	private int penals;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Reservation> reservations;

	@ManyToMany
	@JoinTable(name="subscription", joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "entity_id", referencedColumnName = "id"))
	@JsonIgnore
	private List<EntityClass> subscribedEntities;

	public Client() {
		super();
	}



	public int getPenals() {
		return penals;
	}

	public void setPenals(int penals) {
		this.penals = penals;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<EntityClass> getSubscribedEntities() {
		return subscribedEntities;
	}

	public void setSubscribedEntities(List<EntityClass> subscribedEntities) {
		this.subscribedEntities = subscribedEntities;
	}


	public Client(User user){
		this.setId(user.getId());
		this.setName(user.getName());
		this.setSurname(user.getSurname());
		this.setEmail(user.getEmail());
		this.setRole(new Role(user.getRole().getName()));
		this.setPassword(user.getPassword());
		this.setEnabled(true);
		this.setPenals(0);
		this.setTelephone(user.getTelephone());
		this.setAddress(user.getAddress());
	}
}
