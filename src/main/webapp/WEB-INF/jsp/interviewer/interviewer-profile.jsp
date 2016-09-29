<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <title>Interviewer profile</title>
  <link rel="stylesheet" href="/resources/recruiter/filter-bar.css">
  <%@ include file="../../jspf/head.jspf"%>
  <link rel="stylesheet" href="/resources/candidate/css/Candidate's_profile.css">
</head>
<body>
<%@ include file="../../jspf/interviewer_header.jspf"%>
<div class="interviewer_profile">
<div class="container">
      <div class="col-lg-8 col-lg-offset-2">
         <div class="panel panel-default">
            <div class="panel-body">
                      <div>
                     <h2 class="col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1">${interviewer.firstName}  ${interviewer.middleName}  ${interviewer.lastName}</h2>
                     </div>
                     <div class="col-md-12">

                        <table class="table table-user-information recruiter-info" >
                           <tbody>
                              <tr>
                                 <td>Position:</td>
                                 <td>Interviewer</td>
                              </tr>
                              <tr>
                                 <td>Skype</td>
                                 <td>${interviewer.skype}</td>
                              </tr>
                              <tr>
                                 <td>Email</td>
                                 <td>${interviewer.email}</td>
                              </tr>
                              <tr>
                                  <td>Tel.Number</td>
                                  <td>${interviewer.telNumber}</td>
                              </tr>
                              <tr>
                                  <td>Knowledge Field</td>
                                  <td>${interviewer.knowledgeField}</td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                    <div class="col-md-offset-4 col-sm-offset-4 col-xs-offset-4">
               <input onclick="location.href='/interviewer/edit_profile'" type="button" name="name" value="Edit" class="btn btn-primary col-md-2 ">
             </div>
            </div>
         </div>
      </div>
    </div>

</div>
<%@ include file="../../jspf/footer.jspf"%>
</body>
</html>
