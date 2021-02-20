package com.sap.dbqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FetchData {
	
	public Statement connectionDB() {
		Statement st = null;
		try
		{
			Class.forName("org.h2.Driver").newInstance();
			Connection con=DriverManager.getConnection("jdbc:h2:mem:ecommerce","root","rootsa");
			st=con.createStatement();
		}
		catch (Exception e){
		e.printStackTrace();
		}
		return st;
	}

	
	public String financialData(String username) {
		String Countrow="";
		try
		{
		Statement st = connectionDB();
		String strQuery = "SELECT COUNT(*) FROM sales where REPRESENTATIVE_NAME ='" + username +"'";
		ResultSet rs = st.executeQuery(strQuery);
		while(rs.next()){
	Countrow = rs.getString(1);
		}
		}
		catch (Exception e){
		e.printStackTrace();
		}
		return Countrow;
	}
	public double getProfits(String username) {
		double roundProfit =0.0;
		try
		{
		Statement st = connectionDB();
		String strQuery = "SELECT cast(SUM(price) as DOUBLE) FROM sales where REPRESENTATIVE_NAME ='" + username +"'";
		ResultSet rs = st.executeQuery(strQuery);
		double Countrun=0;
		while(rs.next()){
		Countrun = rs.getDouble(1);
		double profit = Countrun- (0.2+0.3); //dds + sebestoinost
		roundProfit = (double) Math.round(profit * 100) / 100;
		double roundTurnover = (double) Math.round(Countrun * 100) / 100;
		}
		}
		catch (Exception e){
		e.printStackTrace();
		}
		return roundProfit;
	}
	
	public double getTurnover(String username) {
		double roundTurnover =0.0;
		try
		{
			Statement st = connectionDB();
		String strQuery = "SELECT cast(SUM(price) as DOUBLE) FROM sales where REPRESENTATIVE_NAME ='" + username +"'";


		ResultSet rs = st.executeQuery(strQuery);
		double Countrun=0;
		while(rs.next()){
		Countrun = rs.getDouble(1);
		double profit = Countrun- (0.2+0.3); //dds + sebestoinost
		roundTurnover = (double) Math.round(Countrun * 100) / 100;
		}
		}
		catch (Exception e){
		e.printStackTrace();
		}
		return roundTurnover;
	}
	
	public String getSProductNumAdmin() {
		String Countrow="";
		try
		{
		Statement st = connectionDB();
		String strQuery = "SELECT COUNT(*) FROM sales";
		ResultSet rs = st.executeQuery(strQuery);
		while(rs.next()){
		Countrow = rs.getString(1);
		}
		}
		catch (Exception e){
		e.printStackTrace();
		}
		return Countrow;
		
	}
	public double getProfitsAll() {
		double roundProfit=0.0;
		try
		{
		Statement st = connectionDB();
		String strQuery = "SELECT cast(SUM(price) as DOUBLE) FROM sales";
		ResultSet rs = st.executeQuery(strQuery);
		double Countrun=0;
		while(rs.next()){
		Countrun = rs.getDouble(1);
		double profit = Countrun- (0.2+0.3); //dds + sebestoinost
	    roundProfit = (double) Math.round(profit * 100) / 100;
		}
		}
		catch (Exception e){
		e.printStackTrace();
		}
		return roundProfit;
	}

public double getTurnoversAll() {
	double roundTurnover=0.0;
	try
	{
	Statement st = connectionDB();
	String strQuery = "SELECT cast(SUM(price) as DOUBLE) FROM sales";
	ResultSet rs = st.executeQuery(strQuery);
	double Countrun=0;
	while(rs.next()){
	Countrun = rs.getDouble(1);
	roundTurnover = (double) Math.round(Countrun * 100) / 100;
	}
	}
	catch (Exception e){
	e.printStackTrace();
	}
	return roundTurnover;
}

public ResultSet searchByRepresentative(String representative) {
	ResultSet rs = null;
	try
	{
	Statement st = connectionDB();
	String strQuery = "select * from sales where REPRESENTATIVE_NAME ='" + representative +"'";
	rs = st.executeQuery(strQuery);
	}
	catch (Exception e){
	e.printStackTrace();
	}
	return rs;
}
}
