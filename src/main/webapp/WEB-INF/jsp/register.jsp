<!DOCTYPE html>
<html>
<head>
   <%@ include file="../jspf/head.jspf"%>
</head>
<body>
<%@ include file="../jspf/header.jspf"%>

<link rel="stylesheet" href="resources/css/registration.css">
<div class="col-md-6 col-md-offset-3 register">
    <div id="logbox">
      <form id="signup" method="post" action="signup">
        <h1 id="regi-head">One more Step to do</h1>
        <h1 id="regi-head">Please choose your registration type below</h1>
        <a id="regi-option-button" href="cand_regi"> <input type="button" value="Register as a Candidate" class="inputButton" onclick="location.href='/cand_regi'"/></a>
        <a id="regi-option-button" href="emp_regi"> <input type="button" value="Register as an Employer" class="inputButton" onclick="location.href='/emp_regi'"/></a>
        <div class="text-center">
             <a href="/login" id="">Already registered? Click to login</a>
        </div>
      </form>
    </div>
    </div>
  </div>

<%@ include file="../jspf/footer.jspf"%>
</body>
</html>
