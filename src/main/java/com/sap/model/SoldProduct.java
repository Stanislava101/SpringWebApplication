package com.sap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SoldProduct")
public class SoldProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="product")
    private String product;
    
    @Column(name="price")
    private double price;
    
    @Column(name="date")
    private String date;
    
    
    @Column(name="representativeName")
    private String representativeName;
    
    public SoldProduct() {
    }
    public SoldProduct(String product, double price, String date, String representativeName) {
    	this.product =product;
    	this.price = price;
    	this.date = date;
    	this.representativeName = representativeName;
    }
    
    public SoldProduct(String representativeName) {
    	this.representativeName = representativeName;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName=representativeName;
	}

    @Override
    public String toString() {
        return "EmployeeEntity [id=" + id + ", product=" + product +  "]";
    }
}