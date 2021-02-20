package com.sap.dbqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FetchDataClient {
	public ResultSet getClientData(String username) {
		ResultSet rs=null;
		try
		{
		Class.forName("org.h2.Driver").newInstance();
		Connection con=DriverManager.getConnection("jdbc:h2:mem:ecommerce","root","rootsa");
		Statement st=con.createStatement();
		String strQuery = "select * from client  where representative='" +username+"' ";
		rs = st.executeQuery(strQuery);
}
catch (Exception e){
e.printStackTrace();
}
		return rs;
	}
}
