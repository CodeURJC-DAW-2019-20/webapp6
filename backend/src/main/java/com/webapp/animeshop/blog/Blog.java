package com.webapp.animeshop.blog;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Basic;
import com.webapp.animeshop.product.Product;
import com.webapp.animeshop.product.ProductService;

@Entity
@Table(name="blogentries")
public class Blog implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	@JsonView(Basic.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@JsonView(Basic.class)
	private String author;
	@JsonView(Basic.class)
	private String name;
	@JsonView(Basic.class)
	private String text;
	@JsonView(Basic.class)
	private String image;
	
	@JsonView(Basic.class)
	private String textfull;
	private int day;
	private int month;
	private int year;
	private long idproduct;
	
	@JsonView(Product.class)
	@OneToOne
	private Product product;
	
	public Blog() {};
	
	public Blog(String author, String name, String text, String image, String textfull, int queno) {
		this.author = author;
		this.name = name;
		this.text = text;
		this.image = image;
		this.textfull = textfull;
		
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		this.day = cal.get(Calendar.DAY_OF_MONTH);
		this.month = cal.get(Calendar.MONTH);
		this.year = cal.get(Calendar.YEAR);
	}

	public Blog(String author, String name, String text, String textfull) {
		this.author = author;
		this.name = name;
		this.text = text;
		this.image = "";
		this.textfull = textfull;
		
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		this.day = cal.get(Calendar.DAY_OF_MONTH);
		this.month = cal.get(Calendar.MONTH);
		this.year = cal.get(Calendar.YEAR);
	}
	
	public Blog(String author, String name, String text, String textfull, String idproduct) {
		this.author = author;
		this.name = name;
		this.text = text;
		this.image = "";
		this.textfull = textfull;
		
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		this.day = cal.get(Calendar.DAY_OF_MONTH);
		this.month = cal.get(Calendar.MONTH);
		this.year = cal.get(Calendar.YEAR);
		
		this.idproduct =  Long.parseLong(idproduct);
		
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public void setProduct(Product product) {
		this.product = product;
		//this.idproduct = this.product.getId();
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public void setIdProduct() {
		this.idproduct = this.getProduct().getId();
	}
	
}

