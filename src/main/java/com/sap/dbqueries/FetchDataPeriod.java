package com.sap.dbqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FetchDataPeriod {

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

public ResultSet searchDay(String date) {
	ResultSet rs=null;
	try
	{
		Statement st = connectionDB();
		String strQuery = "select * from sales where date='" + date +"' ";
		rs = st.executeQuery(strQuery);
	}
	catch (Exception e){
	e.printStackTrace();
	}
	return rs;
}

public ResultSet searchPeriod(String date, String date2, String username) {
	ResultSet rs=null;
	 try
	 {
     Statement st = connectionDB();
	 String strQuery = "select * from sales where date >='" +date +"' AND date<='" + date2 +"' AND REPRESENTATIVE_NAME='" + username +"'";
	 rs = st.executeQuery(strQuery);
	 }
	 catch (Exception e){
	 e.printStackTrace();
	 }
	return rs;
}


public ResultSet searchPeriodAdmin(String date, String date2, String username) {
	ResultSet rs=null;
	 try
	 {
	 Statement st = connectionDB();
	 String strQuery = "select * from sales where date >='" +date +"' AND date<='" + date2 +"'";
	 rs = st.executeQuery(strQuery);
	 }
	 catch (Exception e){
	 e.printStackTrace();
	 }
	return rs;
}

public ResultSet searchByDayRepresentative(String date, String username) {
	ResultSet rs = null;
	try
	{
		Statement st = connectionDB();
		String strQuery = "select * from sales where date='" + date +"' AND REPRESENTATIVE_NAME='" + username +"'";
		rs = st.executeQuery(strQuery);
	}
	catch (Exception e){
	e.printStackTrace();
	}
	return rs;
}

}
