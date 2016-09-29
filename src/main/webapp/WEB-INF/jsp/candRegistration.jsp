<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
   <%@ include file="../jspf/head.jspf"%>
   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
   <link rel="stylesheet" href="resources/css/popup.css">
   <script type="text/javascript">		
 		<%@ include file="/resources/js/registrationValidation.js"%>		
 	</script>
 	
  	<script type="text/javascript" src="https://ajax.microsoft.com/ajax/jQuery/jquery-1.4.2.min.js"></script>
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
      <form:form id="signup" method="post" action="candidate_registration" name="frm" onsubmit="return validateForm()">
        <h1 id="regi-head" >Create an account</h1>
        <input name="firstName" type="text" placeholder="What's your first name?" pattern="^[\w]{3,16}$" autofocus="autofocus" required="required" class="input pass regi-input"/>
        <input name="middleName" type="text" placeholder="What's your middle name?" pattern="^[\w]{3,16}$" autofocus="autofocus" class="input pass regi-input"/>
        <input name="lastName" type="text" placeholder="What's your last name?" pattern="^[\w]{3,16}$" autofocus="autofocus" required="required" class="input pass regi-input"/>
        <input name="email" id="email" type="email" placeholder="Email address" class="input pass regi-input"/><div id="result" style="color:red" align="center"></div>
        <input name="password" type="password" placeholder="Choose a password" required="required" class="input pass regi-input" id="password1"/>
        <input name="password2" type="password" placeholder="Confirm password" required="required" class="input pass regi-input" id="password2"/>
        <input type="submit" value="Register me!" class="inputButton"/>
        <div class="text-center">
            already have an account? <a href="#/login" id="login_id">login</a>
        </div>
      </form:form>
    </div>
   </div>
</div>
<div id="result"></div>
<%@ include file="../jspf/footer.jspf"%>
<c:if test="${not empty msg}">
	<div id="dialog" title="${msg}">
		${msg}
	</div>
</c:if>

</body>
</html>
