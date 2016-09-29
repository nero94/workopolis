<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
   <title>Employer profile</title>
   <%@ include file="../../jspf/head.jspf"%>
   <link rel="stylesheet" href="/resources/css/style_employer.css" charset="utf-8">
   <link rel="stylesheet" href="/resources/css/filter-bar.css" charset="utf-8">
   <link rel="stylesheet" href="/resources/css/popup.css">
</head>

<div id="employer_profile">
<%@ include file="../../jspf/manager_header.jspf"%>
<div class="container">
   <div class="">
      <div class="col-lg-8 col-lg-offset-2">
         <div class="panel panel-default">
            <div class="panel-body">
                  <div >
                     <h2>Company name: <span id="comp_name">${employer.company.name}</span></h2>
                     <div class="col-md-8">
                        <table class="table table-user-information recruiter-info" >
                           <tbody>
                              <tr>
                                 <td><label for="">Contact person:</label></td>
                                 <td><input type="text" name="name" value="${employer.firstName} ${employer.lastName}" readonly></td>
                              </tr>
                              <tr>
                              <tr>
                                 <td><label for="">Home Address:</label></td>
                                 <td><input type="text" name="name" value="${employer.company.address}" readonly></td>
                              </tr>
                              <tr>
                                 <td><label for="">Email:</label></td>
                                 <td><input type="text" name="name" value="${employer.email}" readonly></td>
                              </tr>
                              <td><label for="">Phone Number:</label></td>
                              <td><input type="text" name="name" value="123-4567-890" readonly></td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                     <div class="col-md-4">
         			<c:if test="${empty employer.company.imgUrl}">
         				<img src="/resources/employer/img/default-company-logo.jpg" class="col-lg-12">
    				</c:if>
    				<c:if test="${not empty employer.company.imgUrl != ''}">
    					<img src="/resources/employer/img/${employer.company.imgUrl}" alt="logo" class="col-md-12">
    				</c:if>


                       </div>
                  </div>

            </div>
         </div>
      </div>
   </div>
</div>
