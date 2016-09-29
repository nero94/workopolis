<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
   <%@ include file="../jspf/head.jspf"%>
   <script type="text/javascript">		
 		<%@ include file="/resources/js/registrationValidation.js"%>		
 	</script>
 	
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
  	<script type="text/javascript">		
		<%@ include file="/resources/js/checkEmailExist.js"%>		
	</script>
</head>
<body>
<%@ include file="../jspf/header.jspf"%>
<link rel="stylesheet" href="resources/css/registration.css">
<div id="registraion" class="container">
    <div class="col-md-6 col-md-offset-3">
	    <div id="logbox">
	      <form id="signup" method="post" action="employer_registration" name="frm" onsubmit="return validateForm()">
	        <h1 id="regi-head" >Register new Company</h1>
	        <input name="company.name" type="text" placeholder="What's your company name?" pattern="^[\w]{3,16}$" autofocus="autofocus" required="required" class="input pass regi-input"/>
	        <input name="firstName" type="text" placeholder="What's your First name?" pattern="^[\w]{3,16}$" autofocus="autofocus" required="required" class="input pass regi-input"/>
	        <input name="middleName" type="text" placeholder="What's your Middle name?" pattern="^[\w]{3,16}$" autofocus="autofocus" required="required" class="input pass regi-input"/>
	        <input name="lastName" type="text" placeholder="What's your Last name?" pattern="^[\w]{3,16}$" autofocus="autofocus" required="required" class="input pass regi-input"/>
	        <input name="email" id="email" type="email" placeholder="Email address" class="input pass regi-input"/><div id="result" style="color:red" align="center"></div>
	        <input name="password" type="password" placeholder="Choose a password" required="required" class="input pass regi-input" id="password1"/>
	        <input name="password2" type="password" placeholder="Confirm password" required="required" class="input pass regi-input" id="password2"/>
	        <input type="submit" value="Register Me!" class="inputButton"/>
	        <div class="text-center">
	            already have an account? <a href="#/login" id="login_id">login</a>
	        </div>
	      </form>
	    </div>
	</div>
</div>
<%@ include file="../jspf/footer.jspf"%>
<c:if test="${not empty msg}">
<div id="dialog" title="${msg}">
	${msg}
</div>
</c:if>
</body>
</html>
