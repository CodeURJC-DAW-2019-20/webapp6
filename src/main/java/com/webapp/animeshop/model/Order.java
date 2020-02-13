package com.webapp.animeshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="orders")
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private ArrayList<Product> productList;
	private User user;
	private int total;//Precio total del pedido
	
	
	
	public Order() {};
	
	public Order(ArrayList<Product> productList, User user,int total) {
		this.productList = productList;
		this.user = user;
		this.total = total;
	}
	
	public ArrayList<Product> getProductList(){
		return this.productList;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public int getTotal() {
		return this.total;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
