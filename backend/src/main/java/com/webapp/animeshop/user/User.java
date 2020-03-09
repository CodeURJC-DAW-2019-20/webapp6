package com.webapp.animeshop.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Basic;
import com.webapp.animeshop.order.Order;

@Entity
@Table(name="users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonView(Basic.class)
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@JsonView(Basic.class)
    private String name;

    @JsonView(Basic.class)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String passwordHash;
    
	@JsonView(Address.class)
    @Embedded
    private Address delivery;
    
	@JsonView(Order.class)
    @OneToMany(mappedBy="user")
    private List<Order> orderList = new LinkedList<>();
    
	@JsonView(Basic.class)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPassword(String passwordHash) {
		this.passwordHash = new BCryptPasswordEncoder().encode(passwordHash);
	}
 
	public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
	
/*	public String getBilling() {
		return billing;
	}


/*	public void setBilling(String billing) {
		this.billing = billing;
	}
	
/*	public int getPayment() {
		return this.payment;
	}
	
/**	public void setPayment(int payment) {
		String value = Integer.toString(payment);
		if(value.length() == 16) {
			this.payment = payment;
		}
	}*/
	
    public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public Address getDelivery() {
		return delivery;
	}


	public void setDelivery(Address delivery) {
		this.delivery = delivery;
	}

	public User() {
    }

	public User(String name, String password,Address address, String... roles) {
		this.name = name;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.delivery = address;
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}

	public User(String name, String password,Address address, List<String> roles) {
		this.name = name;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.delivery = address;
		this.roles = roles;
	}

	@Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password="
                + passwordHash + ", roles=" + roles + "]";
    }

}
