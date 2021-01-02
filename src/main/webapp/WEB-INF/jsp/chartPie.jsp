<!-- Supostavka prodadeni produkti cena -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en-US">
<body>

<h1>My Web Page</h1>
<sql:setDataSource
        var="myDS"
        driver="org.h2.Driver"
        url="jdbc:h2:mem:ecommerce"
        user="root" password="rootsa"
    />
     
     <!--  SELECT * FROM product -->
    <sql:query var="products"   dataSource="${myDS}">
        SELECT * FROM sold_product;
    </sql:query>
<div id="piechart"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
  google.load("visualization", "1", {packages:["corechart"]});
  google.setOnLoadCallback(drawBasic);
  function drawBasic() {

      var data = google.visualization.arrayToDataTable([
          ['quantity','price']
          <c:forEach var="product" items="${products.rows}">
              ,['${product.product}',${product.price}]
          </c:forEach>
      ]);         

      var options = {
              title: 'My Daily Activities',
              is3D: true,
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
            chart.draw(data, options);
  }
</script>

<body><div id="piechart_3d" style="width: 900px; height: 500px;"></body>
  <table class="table table-striped table-responsive-md">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Device</th>
                                <th>Model</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Promotion</th>
                                <th>View</th>
                            </tr>
                        </thead>
                        <tbody>
                           <c:forEach var="product" items="${products.rows}">
                                <td><c:out value="${product.id}" /></td>
                    <td><c:out value="${product.type}" /></td>
                    <td><c:out value="${product.model}" /></td>
                    <td><c:out value="${product.quantity}" /></td>
                    <td><c:out value="${product.price}" /></td>
                                 <td>
                                <a href="promotion/${product.id}">Promotion</a>
                                
                                </td>
                                <td>
                                <a href="view/${product.id}">View</a>
                                
                                </td>
                            </tr>
                        </tbody>
                           </c:forEach>
                    </table>
</body>
</html>
             