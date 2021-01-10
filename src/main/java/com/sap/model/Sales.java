package com.sap.model;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="Sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="representativeName")
    private String representativeName;
    
    @Column(name="product")
    private String product;
    
    @Column(name="price")
    private Double price;
    
    @Column(name="quantity")
    private int quantity;
    
    @Column(name="date")
    private String date;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "clientID")
  private Client client;
    
    public Sales() {}
    
    public Sales(Client client) {
    	
    	this.client=client;
    }

    public Sales(String representativeName, String product, Double price, int quantity, String date, Client client) {
    	this.representativeName=representativeName;
    	this.product=product;
    	this.price=price;
    	this.quantity=quantity;
    	this.date = date;
    	this.client=client;
    }
    
    public Sales(String representativeName, Client client) {
    	this.representativeName=representativeName;
    	this.product=product;
    	this.price=price;
    	this.quantity=quantity;
    	this.date = date;
    	this.client=client;
    }
    
    
	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
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
	  public void setClient(Client client){
		    this.client = client;
		  }
		  
		  public Client getClient(){
		    return this.client;
		  }
}
	