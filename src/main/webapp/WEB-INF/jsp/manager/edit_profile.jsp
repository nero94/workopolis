<%@page import="java.sql.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
      <meta charset="utf-8">
      <title>Edit my profile</title>
      <%@include file="../../jspf/head.jspf"%>

      <link rel="stylesheet" href="/resources/employer/edit_profile.css" media="screen" title="no title" charset="utf-8">

      <link rel="stylesheet" href="/resources/candidate/css/Candidate's_profile.css">

      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/popup.css">
    
    <script type="text/javascript">		
  		<%@ include file="/resources/manager_profile/js/passwordValidation.js"%>		
  	</script>
  	
  	<script type="text/javascript" src="https://ajax.microsoft.com/ajax/jQuery/jquery-1.4.2.min.js"></script>
    <script type="text/javascript">
		 <%@ include file="/resources/manager_profile/js/checkEmailExist.js"%>
	</script>
</head>
<body>
<%@ include file="../../jspf/manager_header.jspf"%>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
              <p> Change user info</p>
            </div>
            <div class="panel-body">
               <form action="/manager/edit_personal_info" method="POST" class="form-horizontal">
                  <div class="form-group">
                     <label for="name" class="col-sm-4 control-label">Name</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="name" name="firstName" placeholder="Name" value="${manager.firstName}">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="middlename" class="col-lg-4 control-label">Middle name</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="middlename" name="middleName" placeholder="Middle name" value="${manager.middleName}">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="lastname" class="col-lg-4 control-label">Lastname</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="lastname" name="lastName" placeholder="Lastname" value="${manager.lastName}">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="skype" class="col-lg-4 control-label">Skype</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="skype" name="skype" placeholder="Skype" value="${manager.skypeUrl}">
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="col-lg-2 col-lg-offset-5">
                        <button type="submit" action class="btn btn-success">Change</button>
                     </div>
                  </div>
               </form>
			</div>
		</div>
		<div class="panel panel-default">
            <div class="panel-heading">
              <p> Change email</p>
            </div>
            <div class="panel-body">
               <form class="form-horizontal"><!-- action="/manager/change_email" -->
                  <div class="form-group">
                     <label for="email" class="col-lg-4 control-label">Email</label>
                     <div class="col-lg-4">
                        <input type="email" class="form-control" placeholder="Email" value="${manager.email}" disabled>
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="new-email" class="col-sm-4 control-label">New email</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="email" name="newEmail" placeholder="New email">
                     </div>
                  </div>
                  <div id="result" align="center"></div>
               </form>
               <div class="form-group">
               <div class="col-lg-2 col-lg-offset-5">
                  <button type="submit" class="btn btn-success" id="target">Change</button>
               </div>
            </div>
            </div>
         </div>
         <div class="panel panel-default">
            <div class="panel-heading">
               <p>Change password</p>
            </div>
            <div class="panel-body">
               <form class="form-horizontal" action="/change_password_manager" method="POST" name="frm" onsubmit="return validateForm()">
            
                  <div class="form-group">
                     <label for="new-password" class="col-sm-4 control-label">New password</label>
                     <div class="col-lg-4">
                        <input type="password" class="form-control" id="password1" placeholder="New password" name="newPassword1">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="repeat-new-password" class="col-sm-4 control-label">Repeat new password</label>
                     <div class="col-lg-4">
                        <input type="password" class="form-control" id="password2" placeholder="Repeat new password" name="newPassword2">
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="col-lg-2 col-lg-offset-5">
                        <button type="submit" class="btn btn-success">Change</button>
                     </div>
                  </div>
               </form>
            </div>
         </div>

	</div>
	<c:if test="${not empty msg}">
	<div id="dialog" title="${msg}">
		${msg}
	</div>
	</c:if>
<%@ include file="../../jspf/footer.jspf"%>
</body>
</html>