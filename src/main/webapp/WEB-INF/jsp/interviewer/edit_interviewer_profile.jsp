<%@page import="java.sql.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <title>Edit my profile</title>
      <%@include file="../../jspf/head.jspf"%>

      <link rel="stylesheet" href="/resources/employer/edit_profile.css" media="screen" title="no title" charset="utf-8">
      <link rel="stylesheet" href="/resources/css/popup.css">
      <link rel="stylesheet" href="/resources/candidate/css/Candidate's_profile.css">
      <link rel="stylesheet" href="/resources/css/edit-interviewer-profile.css">

      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
      <script type="text/javascript">
      	<%@ include file="/resources/employer/validation.js"%>
      	<%@ include file="/resources/interviewer/checkEmailExist.js"%>
      </script>
    </head>
    <body>
<%@ include file="../../jspf/interviewer_header.jspf"%>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
              <p> Change user info</p>
            </div> 
            <div class="panel-body">
               <form action="/interviewer/edit_interviewer_personal_info" method="POST" class="form-horizontal">
                  <div class="form-group">
                     <label for="name" class="col-sm-4 control-label">Name</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="name" name="firstName" placeholder="Name" value="${interviewer.firstName}">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="middlename" class="col-lg-4 control-label">Middle name</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="middlename" name="middleName" placeholder="Middle name" value="${interviewer.middleName}">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="lastname" class="col-lg-4 control-label">Lastname</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="lastname" name="lastName" placeholder="Lastname" value="${interviewer.lastName}">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="skype" class="col-lg-4 control-label">Tel.Number</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="number" name="number" placeholder="Tel.Number" value="${interviewer.telNumber}">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="skype" class="col-lg-4 control-label">Skype</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="skype" name="skype" placeholder="Skype" value="${interviewer.skype}">
                     </div>
                  </div>
                  <c:forEach items="${interviewer.knowledgeField}" var="field">
                     <div class="form-group">
                        <label for="knowledgField" class="col-lg-4 control-label">Knowledge Field</label>
                        <div class="col-lg-4">
                           <input type="text" class="form-control" id="submitedField" value = "${field}" readonly/>
                        </div>
                         <input id="add-int-knowledge" class="add-int-knowledge hidden-visibility" type="button" value="Add" onclick="AddKnowledgeField()">
                         <input id="delete-int-knowledge" class="delete-int-knowledge shown-visibility" type="button" value="Delete" onclick="DeleteKnowledgeField(this)">
                     </div>
                  </c:forEach>
                  <div id="copy-knowledge-field">
                     <div class="form-group">
                        <label for="knowledgField" class="col-lg-4 control-label">Add Knowledge Field</label>
                         <div class="col-lg-4">
                           <select id="knowledgeCatefory" class="form-control knowledgeSelect">
                              <option value="TECHNICAL" label="Technical" />
                              <option value="COMMUNICATION" label="Communication" />
                              <option value="PERSONAL" label="Personal" />
                           </select>
                         </div>
                         <input id="add-int-knowledge" class="add-int-knowledge shown-visibility" type="button" value="Add" onclick="AddKnowledgeField()">
                         <input id="delete-int-knowledge" class="delete-int-knowledge hidden-visibility" type="button" value="Delete" onclick="DeleteKnowledgeField(this)">
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
               <form class="form-horizontal"> <!-- action="/interviewer/change_interviewer_email" -->
                  <div class="form-group">
                     <label for="email" class="col-lg-4 control-label">Email</label>
                     <div class="col-lg-4">
                        <input type="email" class="form-control" placeholder="Email" value="${interviewer.email}" disabled>
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
               <form class="form-horizontal" action="/interviewer/change_password" method="POST" name="frm" onsubmit="return validateForm()">
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
<script src="/resources/js/edit-int-knowledge.js"></script>

