package com.webapp.animeshop.product;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapp.animeshop.order.Order;

@Entity
@Table(name="product_amount")
public class ProductAmount implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private Product product;
	private int amount;
	private double total;
	
	@JsonIgnore
	@ManyToOne
	private Order order;
	
	public ProductAmount() {}

	public ProductAmount(Product product, Order order, int amount) {
		this.product = product;
		this.order = order;
		this.amount = amount;
		this.setTotal(amount, product.getPrice());
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId() {
		this.id = this.getProduct().getId();
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(int amount, double price) {
		this.total =  Math.round((amount*price)*1e2)/1e2;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return this.getProduct().toString() + " X " + this.getAmount() + " :  " + this.getTotal() + "€";
	}
	
}
