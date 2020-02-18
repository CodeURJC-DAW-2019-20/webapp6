package com.webapp.animeshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	private int day;
	private int month;
	private int year;
	
	public Order() {
		this.productList = new ArrayList<ProductAmount>();
		this.user = null;
		this.total = 0;
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		this.day = cal.get(Calendar.DAY_OF_MONTH);
		this.month = cal.get(Calendar.MONTH);
		this.year = cal.get(Calendar.YEAR);
	};
	
	public Order(List<ProductAmount> productList, User user,int total) {
		if(!productList.isEmpty())
			this.productList = new ArrayList<ProductAmount>(productList);
		else
			this.productList = new ArrayList<ProductAmount>();
		this.user = user;
		this.total = total;
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		this.day = cal.get(Calendar.DAY_OF_MONTH);
		this.month = cal.get(Calendar.MONTH);
		this.year = cal.get(Calendar.YEAR);
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

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
