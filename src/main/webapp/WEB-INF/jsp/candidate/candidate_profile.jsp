<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
   <head>
      <title>Candidate</title>
      <%@ include file="../../jspf/head.jspf"%>
      <link rel="stylesheet" type="text/css"   href="https://fonts.googleapis.com/css?family=Tangerine">
      <link rel="stylesheet" href="/resources/candidate/css/Candidate's_profile.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-tagsinput/1.3.6/jquery.tagsinput.min.css">
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-tagsinput/1.3.6/jquery.tagsinput.min.js"></script>

      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
      <link rel="stylesheet" href="/resources/css/popup.css">
   </head>
   <body>
<sec:authorize access="hasRole('ROLE_CANDIDATE')">
  <%@ include file="../../jspf/candidate_header.jspf"%>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_RECRUITER')">
  <%@ include file="../../jspf/recruiter_header.jspf"%>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_EMPLOYER')">
  <%@ include file="../../jspf/employer_header.jspf"%>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
  <%@ include file="../../jspf/administrator_header.jspf"%>
</sec:authorize>
      <div class="candidate_profile ">
         <div class="container well" >
            <div class="col-lg-12 col-md-12 col-sm-12">
               <div class=" profile_picture col-lg-3 col-md-4 col-sm-4 col-xs-4">
                  <c:if test="${empty candidate.photoUrl}">
                     <img src="/resources/candidate/photos/default-avatar_0.png" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                  </c:if>
                  <c:if test="${not empty candidate.photoUrl != ''}">
                     <img src="/resources/candidate/photos/${candidate.photoUrl}" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                  </c:if>
   <sec:authorize access="hasRole('ROLE_CANDIDATE')">
                  <a href="#win2" id="default_button" class="btn btn-default col-lg-10 col-md-10 col-sm-10 col-lg-offset-1 col-md-offset-1 col-sm-offset-1  col-xs-10 col-xs-offset-1">Change photo</a>

                  <a href="#x" class="overlay" id="win2"></a>
                  <div class="popup">
                     <form method="POST" action="candidate/change_photo" enctype="multipart/form-data">
                        File to upload: <input type="file" name="file"><br />
                        <br />
                        <input type="submit" value="Upload"/> Press here to upload the file!
                     </form>
                     <a class="close"title="Close" href="#close"></a>
                  </div>
                  <a id="default_button" href="/candidate/edit_profile?id=${candidate.id}" class="btn btn-default col-lg-10 col-md-10 col-sm-10 col-xs-10 col-lg-offset-1 col-sm-offset-1 col-md-offset-1  col-xs-offset-1 edit" >Edit profile</a>
                  <c:if test="${empty candidate.CVUrl}">
                     <a href="#win1" id="default_button" class="btn btn-default col-lg-10 col-md-10 col-sm-10 col-xs-10 col-lg-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-offset-1">Upload CV</a>
                     <a href="#x" class="overlay" id="win1"></a>
                     <div class="popup">
                        <form method="POST" action="candidate/upload_cv" enctype="multipart/form-data">
                           File to upload: <input type="file" name="file"><br />
                           <br />
                           <input type="submit" value="Upload"/> Press here to upload the file!
                        </form>
                        <a class="close"title="Close" href="#close"></a>
                     </div>
                  </c:if>
                  <c:if test="${not empty candidate.CVUrl}">
                     <a id="default_button" href="/candidate/delete_cv" class="btn btn-default col-lg-10 col-sm-10 col-md-10 col-xs-10 col-lg-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-offset-1">Delete CV</a>
                     <a id="default_button" href="<c:url value='/candidate/download' />" class="btn btn-defaultcol-lg-10 col-sm-10 col-md-10 col-xs-10 col-lg-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-offset-1 edit" >Download CV</a>
                     <a href="<c:url value='/candidate/download' />" id="default_button" class="fa fa-file-pdf-o col-lg-offset-5 col-sm-offset-5 col-md-offset-5 col-xs-offset-5"></a>

                     <p class="col-lg-10 col-md-10 col-sm-10 col-lg-offset-3 col-md-offset-3 col-sm-offset-3 col-xs-offset-2"><b>CV is uploaded</b></p>
                  </c:if>
   </sec:authorize>
   <sec:authorize access="hasRole('ROLE_RECRUITER')">
   <a  href="<c:url value='/schedule-interview?candidate=${candidate.email}' />" class="btn btn-success col-lg-10 col-lg-offset-1 edit" >Schedule interview</a>
   </sec:authorize>
   <sec:authorize access="hasRole('ROLE_EMPLOYER')">
   <a  href="<c:url value='/schedule-interview?candidate=${candidate.email}' />" class="btn btn-success col-lg-10 col-lg-offset-1 edit" >Create Offer</a>
   </sec:authorize>
   <sec:authorize access="hasAnyRole('ROLE_RECRUITER' , 'ROLE_EMPLOYER')">
      <c:if test="${not empty candidate.CVUrl}">
             <a id="default_button" href="<c:url value='/candidate/download' />" class="btn btn-default col-lg-10 col-lg-offset-1 edit" >Download CV</a>
             <a href="<c:url value='/candidate/download' />" id="default_button" class="fa fa-file-pdf-o col-lg-offset-5"></a>
             <p class="col-lg-10 col-lg-offset-3"><b>CV is uploaded</b></p>
      </c:if>
  </sec:authorize>
    </div>
          <div class="col-lg-9 name1">
                  <h2 >${candidate.firstName} ${candidate.middleName} ${candidate.lastName}</h2>
               </div>
               <div class="col-lg-9 col-md-8 col-sm-8 col-xs-8 ">
                 <div class="row ">
                   <h3>
                <div class="col-lg-4 col-md-12  col-sm-12 col-xs-12 interested">Interested in:</div><div class="col-lg-8 col-md-12  col-sm-12 col-xs-12" id="interestedIn" >${candidate.interstedDirection}</div></h3>
              </div>
                  <div class="personal_inf well">
                     <div class="form-group">
                        <p>${candidate.additionalInformation}</p>
                     </div>
            </div>
            <div>
               <div class="contacts well">
                  <div class="row">
                      <div class="col-lg-7 col-md-11 col-sm-11 col-xs-11">
                         <div class="row form-group">
                              <label for="email" class="col-lg-3 col-md-3 col-sm-3 col-xs-3">email:</label>
                              <input class="col-lg-5 col-md-5 col-sm-5 col-xs-5" type="email" name="e_mail" value="${candidate.email}" readonly>
                          </div>
                          <div class="row form-group">
                              <label for="telnum" class="col-lg-3 col-md-3 col-sm-3 col-xs-3">m-phone:</label>
                              <input class="col-lg-5 col-md-5 col-sm-5 col-xs-5" type="telnum" name="telnum" value="${candidate.telNumber!=0?candidate.telNumber:''}" size="15" readonly>
                          </div>
                              <div class="row form-group">
                                 <label for="skype" class="col-lg-3 col-md-3 col-sm-3 col-xs-3">Skype:</label>
                                 <input class="col-lg-5 col-md-5 col-sm-5 col-xs-5" type="text" name="skype" value="${candidate.skypeUrl}" readonly>
                              </div>
                           </div>
                           <div class="col-lg-5 col-md-11 col-sm-11 col-xs-11">
                              <div class="row form-group">
                                 <label for="Country" class="col-lg-3 col-md-3 col-sm-3 col-xs-3">Country:</label>
                                 <input class="col-lg-5 col-md-5 col-sm-5 col-xs-5" type="text" name="country" value="${candidate.address.country.name}" readonly>
                              </div>
                              <div class="row form-group">
                                 <label for="City" class="col-lg-3 col-md-3 col-sm-3 col-xs-3">City:</label>
                                 <input class="col-lg-5 col-md-5 col-sm-5 col-xs-5" type="text" name="city" value="${candidate.address.city.name}" readonly>
                              </div>
                              <div class="row form-group">
                                 <label for="State" class="col-lg-3 col-md-3 col-sm-3 col-xs-3">State:</label>
                                 <input class="col-lg-5 col-md-5 col-sm-5 col-xs-5" type="text" name="state" value="${candidate.address.state.name}" readonly>
                              </div>
                              <div class="row form-group">
                                 <label for="Zipcode" class="col-lg- col-md-3 col-sm-3 col-xs-3">Zipcode:</label>
                                 <input class="col-lg-5 col-md-5 col-sm-5 col-xs-5" type="text" name="zipcode" value="${candidate.address.zip!=0?candidate.address.zip:''}" readonly>
                              </div>
                           </div>
                        </div>
                        <div class="row">
                           <a href="${candidate.facebookUrl}" class="fa fa-facebook-square col-lg-offset-4 col-lg-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2"></a>
                           <a href="${candidate.linkedInUrl}" class="fa fa-linkedin-square col-lg-2"></a>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <h3>Skills:</h3>
            <div class="well skills ">
               <h4>Personal:</h4>
               <c:forEach items="${candidate.candidatePersonalSkills}" var="personalSkill">
                  <div id="dynamicInput_1">
                     <div class="row" >
                        <div  class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                           <span>${personalSkill.personalSkillName}</span>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                           <span>${personalSkill.personalSkillLevel}</span>
                        </div>
                     </div>
                  </div>
               </c:forEach>
               <h4>Communication:</h4>
               <c:forEach items="${candidate.candidateCommunicationSkills}" var="communicationSkill">
                  <div id="dynamicInput_2">
                     <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                           <span>${communicationSkill.name}</span>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                           <span>${communicationSkill.level}</span>
                        </div>
                     </div>
                  </div>
               </c:forEach>
               <h4>Technical:</h4>
               <c:forEach items="${candidate.candidateTechnicalSkills}" var="technicalSkill">
                  <div id="dynamicInput_3">
                     <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                           <span>${technicalSkill.name}</span>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                           <span>${technicalSkill.category}</span>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                           <span>${technicalSkill.level}</span>
                        </div>
                     </div>
                  </div>
               </c:forEach>
            </div>
            <h3>Education:</h3>
            <div class="education well ">
               <div class="school ">
                  <div id="dynamicInput_5">
                    <c:forEach items="${candidate.educations}" var="education">
                     <div class="block">
                        <table class="">
                          <tr>
                            <td>
                              <div class="form-group">
                                 <label for="department" class="">University name: </label>
                                 <select id="university-name" class="education_school" disabled>
                                    <option value="" >${education.university.name}</option>
                                 </select>
                              </div>
                            </td>
                            <td>
                              <div class="form-group">
                                 <label for="department" class="">Address: </label>
                                 <select class="education_school" disabled>
                                    <option value="">${education.university.address}</option>
                                 </select>
                              </div>
                            </td>
                          </tr>
                          <tr>
                            <td>
                              <div class="form-group">
                                 <label for="department" class="">Education field: </label>
                                 <select class="education_school" disabled>
                                    <option id="education-field" value="${education.educationField}"></option>
                                 </select>
                              </div>
                              <td>
                                <div class="form-group">
                                   <label for="Degree" class="">Degree: </label>
                                   <select class="education_school" disabled>
                                      <option id="education-degree" value="">${education.educationDegree}</option>
                                   </select>
                                </div>
                              </td>
                            </td>
                          </tr>
                          <tr>
                            <td>
                              <div class="form-group"><label for="StartDate" class="">Start date: </label><fmt:formatDate pattern="yyyy-MM-dd" value="${education.university.startDate}" /></div>
                            </td>
                            <td>
                              <div class="form-group"><label for="FinishDate" class="">Finish date: </label><fmt:formatDate pattern="yyyy-MM-dd" value="${education.university.finishDate}" /></div>
                            </td>
                          </tr>
                        </table>
                     </div>
                   </c:forEach>
                  </div>
               </div>
            </div>
            <h3>Work experiance:</h3>
            <div class="work_experiance well">
              <c:forEach items="${candidate.experiances}" var="experiance">
               <div id="dynamicInput_7">
                  <div class="block">
                    <div class="row">
                      <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                     <div class="form-group"><label for="department">Position: </label><input name="position-name" class="experiance" type ="text" value="${experiance.position}" size="15" readonly></div>
                     <div class="form-group"><label for="department">Company: </label><input name="company-name" class="experiance" type="text" value="${experiance.companyName}" readonly></div>
                     <div class="form-group"><label for="department">Responsibilities: </label><input name="responsibilities"  class="experiance" type="text" value="${experiance.responsibilities}" readonly></div>
                     </div>
                     <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                     <div class="form-group"><label for="department">Start date: </label><fmt:formatDate pattern="yyyy-MM-dd" value="${experiance.startDate}" /></div>
                     <div class="form-group"><label for="department">Finish date: </label><fmt:formatDate pattern="yyyy-MM-dd" value="${experiance.finishdate}" /></div>
                   </div>
                  </div>
               </div>
             </c:forEach>

            </div>
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
   <script>

   function FormatDate(date) {
               var formatted_birth = $.datepicker.formatDate('mm-dd-yy', new Date(date));
              return formatted_birth;
            };

   var educationFields = {
   	AUTOMATION_AND_MACHINE_CONSTRUCTING : "Automation and machine constructing",
   	ELECTRONICS_AND_TELECOMMUNICATIONS : "Electronics and Telecommunications",
   	SYSTEM_ANALYSIS : "System Analysis",
   	COMPUTER_SCIENCE : "Computer Science",
   	SOFTWARE_ENGINEERING : "Software Engineering",
   	SYSTEM_ARCHITECTURE : "System Archotecture",
   	PROJECT_MANAGEMENT : "Project Management",
   	SYSTEM_PROGRAMMING : "System Programming",
   	METROLOGY_AND_MEASURING_EQUIPMENT : "Metrology and Measuring Equipment",
   	INFORMATION_TECHNOLOGY : "Information Technology",
   	INFORMATION_AND_COMMUNICATION_SYSTEMS_SECURITY : "Information and Communication Systems Security",
   	INFORMATION_SECURITY_MANAGEMENT : "Information Security Management",
   	QUALITY_STANDARTIZATION_AND_CERTIFICATION : "Quallity Standartization and Certification",
   	APPLIED_MATHEMATICS : "Applied Mathematics",
   	APPLIED_PHYSICS : "Applied Physics",
   	MATHEMATICAL_AND_COMPUTER_MODELING : "Mathematical and Computer Modeling"
   };

   var interestedIn = {
    ADMINISTRATION : "Administration",
    BUSINESS_ANALYSIS : "Business Analysis",
    DESIGN : "Design",
    DATABASES : "Databases",
    PROJECT_MANAGEMENT : "Project Management",
    QUALITY_CONTROL : "Quality Control",
    SOFTWARE_DEVELOPMENT : "Software Development",
    SYSTEM_ARCHITECTURE : "System Architecrute"
   };

   $("#education-field").html(educationFields[$("#education-field").val()]);
   $("#interestedIn").text(interestedIn[$("#interestedIn").text()]);

   </script>
</html>
