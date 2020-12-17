<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>List of Products</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>

<body>

        <sec:authorize access="hasRole('ADMIN')">
    	<h1>Manage products</h1>
        <sql:setDataSource
        var="myDS"
        driver="org.h2.Driver"
        url="jdbc:h2:mem:ecommerce"
        user="root" password="rootsa"
    />
    
     
    <sql:query var="employees"   dataSource="${myDS}">
        SELECT * FROM product;
    </sql:query>
         </div>
    <div align="center">
       
    

    <div class="card" style="padding-top: 50px;">
    <div class="card-body">
    
            <p class="my-5">
                <a href="/edit" class="btn btn-primary">
                <i class="fas fa-user-plus ml-2"> Add Product </i></a>
            </p>
            
           
            <div class="col-md-10">
                    <table class="table table-striped table-responsive-md">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Device</th>
                                <th>Model</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                           <c:forEach var="employee" items="${employees.rows}">
                                <td><c:out value="${employee.id}" /></td>
                    <td><c:out value="${employee.type}" /></td>
                    <td><c:out value="${employee.model}" /></td>
                    <td><c:out value="${employee.quantity}" /></td>
                    <td><c:out value="${employee.price}" /></td>
                                <td>
                                 <a href="edit/${employee.id}">Edit</a>
                                    
                                </td>
                                <td>
                                <a href="delete/${employee.id}">Del</a>
                                
                                </td>
                            </tr>
                        </tbody>
                           </c:forEach>
                    </table>
                    </sec:authorize>
                </div>
    </div>
            </div>
        </div>
    </div>
    </div>
</body>

</html>