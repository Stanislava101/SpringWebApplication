package com.sap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="email")
    private String email;
    
    @Column(name="phoneNumber")
    private String phoneNumber;
    
    
    @Column(name="representative")
    private String representative;
    
    @OneToOne(mappedBy = "husband")
    private Sales wife;
    
    public Client(){}
    
    public Client(String name,String phoneNumber, String email, String representative){
      this.name = name;
      this.phoneNumber=phoneNumber;
      this.email = email;
      this.representative=representative;
    }
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	
	 public void setWife(Sales wife){
		    this.wife = wife;
		  }
		  
		  public Sales getWife(){
		    return this.wife;
		  }


    @Override
    public String toString() {
        return "Client [id=" + id + "name = " + name + ", email=" + email + 
                ", phoneNumber=" + phoneNumber + "]";
    }
}
