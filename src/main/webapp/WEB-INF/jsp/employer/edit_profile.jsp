<%@page import="java.sql.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <title>Edit my profile</title>
      <%@include file="../../jspf/head.jspf"%>

      <link rel="stylesheet" href="/resources/employer/edit_profile.css" media="screen" title="no title" charset="utf-8">

      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
      <link rel="stylesheet" href="/resources/css/popup.css">

      <script type="text/javascript" src="https://ajax.microsoft.com/ajax/jQuery/jquery-1.4.2.min.js"></script>
      <script type="text/javascript">
  	  	<%@ include file="/resources/employer/validation.js"%>

		 <%@ include file="/resources/employer/checkEmailExist.js"%>
	   </script>
    </head>
   <body >

       <%@ include file="../../jspf/employer_header.jspf"%>
       <div class="container">
         <div class="panel panel-default">
            <div class="panel-heading">
              <p> Change user info</p>
            </div>
            <div class="panel-body">
               <form action="/employer/edit_user_info" method="POST" class="form-horizontal">
                  <div class="form-group">
                     <label for="name" class="col-sm-4 control-label">Name</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${employer.firstName}">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="middlename" class="col-lg-4 control-label">Middle name</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="middlename" name="middleName" placeholder="Middle name" value="${employer.middleName}">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="lastname" class="col-lg-4 control-label">Lastname</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Lastname" value="${employer.lastName}">
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
              <p> Change company info</p>
            </div>
            <div class="panel-body">
               <form action="/employer/edit_company_info" id="candidateAddressBody" method="POST" class="form-horizontal">
                  <div class="col-lg-7">
                     <div class="form-group">
                        <label for="company-name" class="col-lg-4 control-label">Company name</label>
                        <div class="col-lg-8">
                           <input type="text" name="name" class="form-control" id="company-name" placeholder="Company name" value="${employer.company.name}">
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="countrySelect" class="col-lg-4 control-label">Country</label>
                        <div id="country" class="col-sm-8">
                           <select class='form-control' id='countrySelect' name='country'>
                           <option value='${employer.company.address.country.id}'>${employer.company.address.country.name}</option>

                           </select>
                           <input type="hidden" id="countrySelectName" value="${employer.company.address.country.name}">
                           <input type="hidden" id="countrySelectId" value="${employer.company.address.country.id}">
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="stateSelect" class="col-lg-4 control-label">State</label>
                        <div id="state" class="col-lg-8">
                           <select class="form-control" id="stateSelect" name="state" disabled>
                           <option value='${employer.company.address.state.id}'>${employer.company.address.state.name}</option>

                           </select>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="citySelect" class="col-lg-4 control-label">City</label>
                        <div id="city" class="col-lg-8">
                           <select class="form-control" id="citySelect" name="city" disabled>
                           <option value='${employer.company.address.city.id}'>${employer.company.address.city.name}</option>

                           </select>
                        </div>
                     </div>
                     <button type="submit" class="btn btn-success">Change</button>
                  </div>
                </form>

                <form action="/employer/change_emp_photo" method="post" enctype="multipart/form-data">
                  <div id="edit_photo_block" class="col-lg-5">

          			<c:if test="${empty employer.company.imgUrl}">
          				<img src="/resources/employer/img/default-company-logo.jpg" class="col-lg-12">
          			</c:if>
          			<c:if test="${not empty employer.company.imgUrl != ''}">
          				<img src="/resources/employer/img/${employer.company.imgUrl}" alt="logo" class="col-md-12">
					</c:if>
                     <input type="button" id="change-logo" onclick="location.href='#win2'" class="btn btn-warning" style="margin-bottom:10px" value="Change logo">
                     <a href="#x" class="overlay" id="win2"></a>
               <div class="popup">
                  <label>File to upload:</label> <input type="file" name="file"><br>
                  <br>
                  <input type="submit" value="Upload"/> <label>Press here to upload the file!</label>
                <a class="close" title="Close" href="#close"></a>
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
               <form class="form-horizontal"><!-- action="/employer/change_email" -->
                  <div class="form-group">
                     <label for="email" class="col-lg-4 control-label">Email</label>
                     <div class="col-lg-4">
                        <input type="email" class="form-control" placeholder="Email" value="${employer.email}" disabled>
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="new-email" class="col-sm-4 control-label">New email</label>
                     <div class="col-lg-4">
                        <input type="text" class="form-control" id="email" name="newEmail" placeholder="New email">
                        <div id="result" align="center"></div>
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
               <form class="form-horizontal" action="/employer/change_password" name="frm" onsubmit="return validateForm()" method="POST">
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
   <script src="/resources/js/location-controller.js"></script>
   <script>
   showCountry("candidateAddressBody", document.getElementById("countrySelectId").value, document.getElementById("countrySelectName").value);
   </script>
