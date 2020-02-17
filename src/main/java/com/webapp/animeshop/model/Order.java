package com.webapp.animeshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="orders")
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="order")
	private List<ProductAmount> productList;
	
	@ManyToOne
	private User user;
	private double total;//Precio total del pedido
	private String status;
	
	
	
	public Order() {
		this.productList = new ArrayList<ProductAmount>();
		this.user = null;
		this.total = 0;
	};
	
	public Order(List<ProductAmount> productList, User user,int total) {
		if(!productList.isEmpty())
			this.productList = new ArrayList<ProductAmount>(productList);
		else
			this.productList = new ArrayList<ProductAmount>();
		this.user = user;
		this.total = total;
	}
	
	public List<ProductAmount> getProductList(){
		return productList;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public void setTotal() {
		double aux = 0;
		for(int i=0;i<this.getProductList().size();i++) 
			aux+=this.getProductList().get(i).getTotal();
		this.total = Math.round(aux*1e2)/1e2;
	}

	public void setProductList(List<ProductAmount> productList) {
		this.productList = productList;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
