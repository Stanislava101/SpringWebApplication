package com.sap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="representativeName")
    private String representativeName;
    
    @Column(name="clientName")
    private String clientName;
    
    @Column(name="product")
    private String product;
    
    @Column(name="price")
    private Double price;
    
    @Column(name="quantity")
    private int quantity;
    
    @Column(name="date")
    private String date;
    
    public Sales(String representativeName, String clientName, String product, Double price, int quantity, String date) {
    	this.representativeName=representativeName;
    	this.clientName=clientName;
    	this.product=product;
    	this.price=price;
    	this.quantity=quantity;
    	this.date = date;
    }
    
	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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
		this.price=price;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
	