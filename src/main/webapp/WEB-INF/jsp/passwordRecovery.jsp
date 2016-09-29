<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
   <%@ include file="../jspf/head.jspf"%>
   <link rel="stylesheet" href="resources/css/registration.css">

   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
   <link rel="stylesheet" href="resources/css/popup.css">
   
   	<script type="text/javascript">		
 		<%@ include file="/resources/js/registrationValidation.js"%>		
 	</script>
</head>

<body>
<%@ include file="../jspf/header.jspf"%>
   <div class="container">
    <div id="logbox">
      <form method="POST" action="/passwordRecoveryMsg" name="frm" onsubmit="return validateForm()">
        <h1 id="regi-head">Account password recovery</h1>
        <input name="email" type="email" placeholder="enter your email" class="input pass regi-input" />
        <input name="password1" pattern=".{6,}" type="password" placeholder="Enter new password" required="required" class="input pass regi-input" id="password1" />
        <input name="password2" pattern=".{6,}" type="password" placeholder="Confirm new password" required="required" class="input pass regi-input" id="password2" />
        <input type="submit" value="Reestablish" class="inputButton"/>
      </form>
    </div>
    </div>

	<c:if test="${not empty msg}">
	<div id="dialog" title="${msg}">
		${msg}
	</div>
	</c:if>
<div>
 <%@ include file="../jspf/footer.jspf"%>
 </div>
</body>

</html>
