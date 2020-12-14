<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE composition PUBLIC "-//W3C//DTD XHTML 1.0 Transitional/<!DOCTYPE composition PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
    xmlns:ui="http://java.sun.com/jsf/facelets"
    xmlns:h="http://java.sun.com/jsf/html"
    xmlns:sec="http://www.springframework.org/security/tags">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
<sec:authorize access="hasRole('ADMIN')">
    Manage Users
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    Welcome Back, <sec:authentication property="name"/>
     <form:form method="POST" modelAttribute="userForm" id="signup-form" class="signup-form">
<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>
</sec:authorize>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <sec:authorize access="hasRole('ADMIN')">
    Manage Users
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    Welcome Back, <sec:authentication property="name"/>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
  Login
</sec:authorize>
<sec:authorize access="isAuthenticated()">
  Logout
</sec:authorize>
        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
 <sec:authentication property="principal.authorities"/>
    </c:if>
<sec:authorize access="!isAuthenticated()">
  Login
</sec:authorize>
<sec:authorize access="isAuthenticated()">
  Logout
</sec:authorize>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>