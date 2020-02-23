package com.webapp.animeshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="metrics")
public class OrderMetrics {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int totalOrders;
	private double average;
	private double totalMoney;
	private double lastMoney;
	private int toPrint;
	private int averageToPrint;
	//private List<Double> prices;
	
	public OrderMetrics() {
		this.totalOrders = 0;
		this.average = 0;
		this.totalMoney = 0;
		this.lastMoney = 0;
		//this.prices = new ArrayList();
	}
	
	public void newOrder(Order order) {
		this.totalOrders++;
		this.totalMoney += order.getTotal();
		this.average = this.totalMoney / this.totalOrders;
		this.lastMoney = order.getTotal();
		this.toPrint = (int) this.lastMoney;
	}

	public int getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	/*public List<Double> getPrices() {
		return prices;
	}*/

	/*public void setPrices(List<Double> prices) {
		this.prices = prices;
	}*/
	
	public double getLastMoney() {
		return lastMoney;
	}

	public void setLastMoney(double lastMoney) {
		this.lastMoney = lastMoney;
	}

	public int getToPrint() {
		return toPrint;
	}

	public void setToPrint(int toPrint) {
		this.toPrint = toPrint;
	}

	public int getAverageToPrint() {
		return averageToPrint;
	}

	public void setAverageToPrint(int averageToPrint) {
		this.averageToPrint = averageToPrint;
	}

	public OrderMetrics(OrderMetrics orderMetrics) {
		this.totalMoney = orderMetrics.totalMoney;
		this.totalOrders = orderMetrics.totalOrders;
		this.average = orderMetrics.average;
		
		//this.prices = orderMetrics.prices;
	}

}
