package com.webapp.animeshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="blogentries")
public class Blog implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String author;
	private String name;
	private String text;
	private String image;
	private String textfull;
	private int day;
	private int month;
	private int year;
	

	public Blog() {};
	
	public Blog(String author, String name, String text, String image, String textfull) {
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
	
	
}

