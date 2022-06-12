package com.example.demo.model.users;

import com.example.demo.enums.LoyalityType;
import com.example.demo.enums.Role;
import com.example.demo.model.entities.Address;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Proxy(lazy = false)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public class User implements UserDetails {

	@Id
	@SequenceGenerator(name = "userSeqGen", sequenceName = "userSeqGen", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeqGen")
	@Column(name="id", unique=true, nullable=false)

	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "surname", nullable = false)
	private String surname;
	@Column(name = "email", nullable = false, unique=false)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "grade")
	private double grade;
	@Column(name = "telephone")
	private String telephone;
	@Column(name = "income")
	private double income;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE ) //PRILIKOM REGISTRACIJE NIJE CUVAO USERA, PA SAM PROMENILA CASCADETYPE
	private Address address;
	@Column(name = "reasonForRegistration")
	private String reasonForRegistration;
	@Column(name = "poents")
	private int poents;
	@Column(name = "loyalityType")
	private LoyalityType loyalityType;
	@Column(name = "enabled")
	private boolean enabled;
	@Column(name = "must_change_password")
	private boolean must_change_password;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "role_id")
	private Role role;
	@Column(name = "last_password_reset_date", nullable = true)
	private Timestamp lastPasswordResetDate;
	
	public User() {}


	//koristim pri registraciji
	public User(String name, String surname, String email, String password, String telephone, Address address,boolean enabled, Role role, Timestamp lastPasswordResetDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.address = address;
		this.enabled = enabled;
		this.role = role;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.grade = 0;
	}

	public User(String name, String surname, String email, String password, String telephone, Address address) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.address = address;
	}

	//za pravljenje odredjenog usera  -> ne koristim
	public User(String name, String surname, String email, String password, String telephone, Address address, String role) {
		super();
		this.role = new Role(role); //poslace se u stringu rola
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.enabled = true;
		this.address = address;
	}

	public boolean isMust_change_password() {
		return must_change_password;
	}

	public int getPoents() {
		return poents;
	}

	public void setPoents(int poents) {
		this.poents = poents;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public LoyalityType getLoyalityType() {
		return loyalityType;
	}

	public void setLoyalityType(LoyalityType loyalityType) {
		this.loyalityType = loyalityType;
	}
	public void setMust_change_password(boolean must_change_password) {
		this.must_change_password = must_change_password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReasonForRegistration() {
		return reasonForRegistration;
	}
	public void setReasonForRegistration(String reasonForRegistration) {
		this.reasonForRegistration = reasonForRegistration;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> collection = new ArrayList<Role>();
		collection.add(this.role);
		return collection;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setPassword(String password) {
		Timestamp now = new Timestamp(new Date().getTime());
		this.setLastPasswordResetDate(now);
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean getEnabled() {return enabled;}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
}
