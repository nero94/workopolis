<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
   <%@ include file="../jspf/head.jspf"%>
   <link rel="stylesheet" href="resources/css/registration.css">

   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
   <link rel="stylesheet" href="resources/css/popup.css">
</head>

<body>
<%@ include file="../jspf/header.jspf"%>

   <div class="container">
    <div id="logbox">
      <form:form method="POST" id="signup" action="j_spring_security_check">
        <h1 id="regi-head">Account login</h1>
        <input name="j_username" type="email" placeholder="enter your email" class="input pass regi-input"/>
        <input name="j_password" type="password" placeholder="enter your password" required="required" class="input pass regi-input"/>
        <input type="submit" value="Log me in!" class="inputButton"/>
        <div class="text-center">
           <a href="/register" id="">create an account</a> - <a href="/passwordRecoveryPage" id="">forgot password</a>
        </div>
      </form:form>
    </div>
    </div>

	<c:if test="${not empty msg}">
	<div id="dialog" title="${msg}">
		${msg}
	</div>
	</c:if>
	
	<c:if test="${not empty param.login_error}">
		<div id="dialog" title="">
			Incorrect login/password
		</div>
    </c:if>

<div>
 <%@ include file="../jspf/footer.jspf"%>
 </div>
</body>

</html>
