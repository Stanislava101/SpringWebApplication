<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="${contextPath}/resources/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
</head>
<body style="background-image: url('${contextPath}/resources/img/signup-bg.jpg');">

    <div class="main">
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                           <form:form method="POST" modelAttribute="userForm2" id="signup-form" class="signup-form">
     <h2 class="form-title">Create account</h2>
     <div class="form-group">
                        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="email" class="form-input" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>
        </div>
                
               <div class="form-group">
                   <input type="submit" name="submit" id="submit" class="form-submit" value="Sign In"/>
               </div>
        
    </form:form>
                    <p class="loginhere">
                        Have already an account ? <a href="${contextPath}/login" class="loginhere-link">Login here</a>
                    </p>
                </div>
            </div>
        </section>
    </div>

    <!-- JS -->
    <script src="${contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
</body>
</html>