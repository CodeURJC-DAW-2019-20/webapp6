package com.webapp.animeshop.order;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Basic;
import com.webapp.animeshop.product.ProductAmount;
import com.webapp.animeshop.user.User;

@Entity
@Table(name="orders")
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonView(Basic.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonView(Basic.class)
	private String status;
	
	@JsonView(Basic.class)
	@OneToMany(cascade = CascadeType.ALL,mappedBy="order")
	private List<ProductAmount> productList;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	@JsonView(Basic.class)
	private double total;//Precio total del pedido
	
	@JsonView(Basic.class)
	private int day;
	@JsonView(Basic.class)
	private int month;
	@JsonView(Basic.class)
	private int year;
	
	public Order() {
		this.productList = new ArrayList<ProductAmount>();
		this.status = null;
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
		this.status = null;
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
	
	public void resetTotal() {
		this.total = 0;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		String order = "\nNúmero de pedido: " + Long.toString(this.getId()) + "\nFecha: " + 
						Integer.toString(this.day) + "/" + Integer.toString(this.month) + "/" + Integer.toString(this.year) + 
						" - "  + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + "'" + ":" + cal.get(Calendar.SECOND) + "''" 
						+ "\n";
		for(int i = 0;i<this.getProductList().size();i++) {
			order+=this.getProductList().get(i).toString() + "\n";
		}
		return order + "Precio total: " + this.getTotal() + "€";
	}
	

}
