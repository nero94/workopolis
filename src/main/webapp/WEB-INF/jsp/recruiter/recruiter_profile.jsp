<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Recruiter profile</title>
	<link rel="stylesheet" href="resources/recruiter/filter-bar.css">
	<%@ include file="../../jspf/head.jspf"%>
	<link rel="stylesheet" href="/resources/candidate/css/Candidate's_profile.css">
		<link rel="stylesheet" href="/resources/recruiter/filter-bar.css">
</head>
<body>
<%@ include file="../../jspf/recruiter_header.jspf"%>
<div class="recruiter_profile">
<div class="container">
      <div class="col-lg-8 col-lg-offset-2">
         <div class="panel panel-default">
            <div class="panel-body">

                     <h2 class="col-lg-10 col-lg-offset-2 ">${recruiter.firstName} ${recruiter.middleName} ${recruiter.lastName}</h2>
                     <div class="col-md-12">
                        <table class="table table-user-information recruiter-info" >
                           <tbody>
                              <tr>
                                 <td>Position:</td>
                                 <td>Recruiter</td>
                              </tr>
                              <tr>
                                 <td>Skype</td>
                                 <td>vasyl.dmytriv</td>
                              </tr>
                              <tr>
                                 <td>Email</td>
                                 <td><a href="mailto:info@support.com">${recruiter.email}</a></td>
                              </tr>
                              <tr>
                              <td>Phone Number:</td>
                              <td>123-4567-890(Landline)<br><br>555-4567-890(Mobile)
                              </td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                    <div class="col-md-offset-4">
               <input onclick="location.href='/recruiter/edit_profile'" type="button" name="name" value="Edit" class="btn btn-primary col-md-2 ">
             </div>
            </div>
         </div>
      </div>
    </div>

</div>
<%@ include file="../../jspf/footer.jspf"%>
</body>
</html>
