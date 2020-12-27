<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

    <%
try
{
Class.forName("org.h2.Driver").newInstance();
Connection con=DriverManager.getConnection("jdbc:h2:mem:ecommerce","root","rootsa");
Statement st=con.createStatement();
String date=request.getParameter("date");
System.out.println(date);
String strQuery = "select * from sold_product where date='" + date +"' ";
//String strQuery = "select * from sold_product where id=1";
ResultSet rs = st.executeQuery(strQuery);
String Countrow="";

while(rs.next()){
Countrow = rs.getString(1);
out.println("Number of sold products: " +Countrow);
%>
<tr>
<td><%=rs.getString("id") %></td>
<td><%=rs.getString("product") %></td>
<td><%=rs.getString("date") %></td>
</tr>
<%
}
}
catch (Exception e){
e.printStackTrace();
}
%>

<!DOCTYPE html>
<html>
<body>
<h1>Search Data</h1>
<table border="1">
<tr>
<td>Name</td>
<td>Email Id</td>
<td>Roll No</td>
</tr>
</table>
</body>
</html>