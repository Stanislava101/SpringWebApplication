package com.sap.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Analyze")
public class Analyze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="numSold")
    private int numSold;
    
    @Column(name="profit")
    private double profit;
    
    @Column(name="turnover")
    private double turnover;
    
    public Analyze(int numSold) {
    	this.numSold=numSold;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumSold() {
		return numSold;
	}

	public void setNumSold(int numSold) {
		this.numSold = numSold;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover=turnover;
	}
	
    @Override
    public String toString() {
        return "Analyze [id=" + id + ", numSold" + numSold + 
                ", profit=" + profit +"turnover= " + turnover  + "]";
    }
}