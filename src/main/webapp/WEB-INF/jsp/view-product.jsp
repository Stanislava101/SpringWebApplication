<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE composition PUBLIC "-//W3C//DTD XHTML 1.0 Transitional/<!DOCTYPE composition PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
    xmlns:ui="http://java.sun.com/jsf/facelets"
    xmlns:h="http://java.sun.com/jsf/html"
    xmlns:sec="http://www.springframework.org/security/tags">
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="author" content="Sahil Kumar">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Web Admin Panel</title>
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <!-- jQuery library -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <!-- Popper JS -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <!-- Latest compiled JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</head>

<body>
<sql:setDataSource
        var="myDS"
        driver="org.h2.Driver"
        url="jdbc:h2:mem:ecommerce"
        user="root" password="rootsa"
    />
    
     
    <sql:query var="products"   dataSource="${myDS}">
        SELECT id FROM product;
    </sql:query>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6 mt-3 bg-info p-4 rounded">
        <h2 class="bg-light p-2 rounded text-center text-dark">ID : <c:out value="${product.id}" /></h2>
        <div class="text-center">
          <img src="${contextPath}/resources/img/iphone-12.png" width="300" class="img-thumbnail">
        </div>
        <h4 class="text-light">Type : <c:out value="${product.type}" /></h4>
        <h4 class="text-light">Model :<c:out value="${product.model}" /></h4>
        <h4 class="text-light">Price : <c:out value="${product.price}" /></h4>
<div id="share-buttons">
<div align="center">
    <!-- Email -->
    <a href="mailto:stanislava1505@gmail.com?Subject=TechBuy Products">
        <img src="https://simplesharebuttons.com/images/somacro/email.png" alt="Email" />
    </a>
 
    <!-- Facebook -->
    <a href="http://www.facebook.com/sharer.php?u=https://sapecommerce.000webhostapp.com/" target="_blank">
        <img src="https://simplesharebuttons.com/images/somacro/facebook.png" alt="Facebook" />
    </a>
       <!-- Twitter -->
    <a href="https://twitter.com/share?url=https://sapecommerce.000webhostapp.com/&amp;text=TechBuy the best eCommerce for electronic devices&amp;hashtags=TheBestPromotion"" target="_blank">
        <img src="https://simplesharebuttons.com/images/somacro/twitter.png" alt="Twitter" />
    </a>
    
    <!-- Print -->
    <a href="javascript:;" onclick="window.print()">
        <img src="https://simplesharebuttons.com/images/somacro/print.png" alt="Print" />
    </a>
</div></div></div>
</div>
  </div>
</body>

</html>