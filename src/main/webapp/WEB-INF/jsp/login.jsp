<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Log in</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="${contextPath}/resources/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
</head>
<body style="background-image: url('${contextPath}/resources/img/signin1.jpeg');">

    <div class="main">
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                <form:form method="POST" action="${contextPath}/login" id="signup-form" class="form-signin">
     <h2 class="form-title">Log in</h2>
     <div class="form-group">
     <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-input" placeholder="Username" autofocus="true"/>
            </div>
        </div>
                 <input name="password" type="password" class="form-input" placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
               <div class="form-group">
                   <input type="submit" name="submit" id="submit" class="form-submit" value="Sign In"/>
               </div>
          </form:form>
          <sec:authorize access="isAuthenticated()">
   <p>You are logged in <a href="${contextPath}/welcome" class="loginhere-link">Visit your account</a></p>
</sec:authorize>
<sec:authorize access="isAnonymous()">
   <p>You are not logged in</p>
</sec:authorize>
                    <p class="loginhere">
                        You don't have an account?<a href="${contextPath}/registration" class="loginhere-link"> Sign up</a>
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