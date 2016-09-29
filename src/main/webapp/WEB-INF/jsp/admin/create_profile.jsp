<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create profile</title>
    <%@ include file="../../jspf/head.jspf"%>
    <link rel="stylesheet" type="text/css" href="/resources/css/style_administrator.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/popup.css">

    <script type="text/javascript">
  		<%@ include file="/resources/js/registrationValidation.js"%>
  	</script>

  	<script type="text/javascript" src="https://ajax.microsoft.com/ajax/jQuery/jquery-1.4.2.min.js"></script>
  	<script type="text/javascript">
		<%@ include file="/resources/js/checkEmailExist.js"%>
	</script>

    <script type="text/javascript">
    function validate()
    {
     var ddl = document.getElementById("role");
     var selectedValue = ddl.options[ddl.selectedIndex].value;
        if (selectedValue == "ROLE_EMPLOYER")
        {
        	document.getElementById("companyName").style.display = 'block';
        }

        if (selectedValue != "ROLE_EMPLOYER")
        {
        	document.getElementById("companyName").style.display = 'none';
        }

    }
  	</script>

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

  </head>
  <body>
  <%@ include file="../../jspf/administrator_header.jspf"%>
  <link rel="stylesheet" href="/resources/css/registration.css">
	 <div id="registraion" class="container">
	    <div class="col-md-6 col-md-offset-3 reg-form">
	    <div id="logbox">
	      <form:form id="signup" method="post" action="/admin/registration_by_admin" name="frm" onsubmit="return validateForm()">
	        <h1 id="regi-head" >Register new User</h1>
	        <input name="firstName" type="text" placeholder="First name" pattern="^[\w]{3,16}$" autofocus="autofocus" required="required" class="input pass regi-input"/>
	        <input name="middleName" type="text" placeholder="Middle name?" pattern="^[\w]{3,16}$" autofocus="autofocus" class="input pass regi-input"/>
	        <input name="lastName" type="text" placeholder="Last name?" pattern="^[\w]{3,16}$" autofocus="autofocus" required="required" class="input pass regi-input"/>
	        <input name="email" id="email" type="email" placeholder="Email address" class="input pass regi-input"/><div id="result" style="color:red" align="center"></div>
	        <input name="password" pattern=".{6,}" type="password" placeholder="Choose a password" required="required" class="input pass regi-input" id="password1"/>
	        <input name="password2" pattern=".{6,}" type="password" placeholder="Confirm password" required="required" class="input pass regi-input" id="password2"/>
	        <select name="role.name" id="role" class="input pass regi-input" onchange='validate();'>
    			<option value="ROLE_CANDIDATE">Candidate</option>
    			<option value="ROLE_RECRUITER">Recruiter</option>
    			<option value="ROLE_EMPLOYER">Employer</option>
    			<option value="ROLE_INTERVIEWER">Interviewer</option>
    			<option value="ROLE_MANAGER">Manager</option>
    		</select>

	        <input type="submit" value="Register!" class="inputButton"/>

	      </form:form>
	    </div>
	   </div>
</div>
<c:if test="${not empty param.msg}">
<div id="dialog" title="${param.msg}">
	${param.msg}
</div>
</c:if>
<%@ include file="../../jspf/footer.jspf"%>
</body>
</html>
