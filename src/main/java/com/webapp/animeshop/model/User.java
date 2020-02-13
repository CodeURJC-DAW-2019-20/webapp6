package com.webapp.animeshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name="users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    //@JsonIgnore
    
    private String password;
    
    private String delivery;
    private String billing;
    
    private int payment;
    
    @ElementCollection(fetch = FetchType.LAZY)
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



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
 
	public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
/*    public String getDelivery() {
		return delivery;
	}


/*	public void setDelivery(String delivery) {
		this.delivery = delivery;
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
	
    public User() {
    }

	public User(String name, String password, String... roles) {
		this.name = name;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}

	public User(String name, String password, List<String> roles) {
		this.name = name;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.roles = roles;
	}

	@Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password="
                + password + ", roles=" + roles + "]";
    }

}
