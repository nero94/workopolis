<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
  <title>Schedule Interview</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <%@ include file="../../jspf/head.jspf"%>
  <link rel="stylesheet" href="/resources/css/add-vacancy.css" charset="utf-8">
  <link rel="stylesheet" href="/resources/css/interviewer-profile.css" charset="utf-8">
  <link rel="stylesheet" href="/resources/css/schedule-interview.css" charset="utf-8">


  	<script type="text/javascript">
  	$(document).ready(function () {

      var createInt = function() {

        var email = $('#email').val();

        function foo(){
          var xhttp = new XMLHttpRequest();
          var url = "/check-candidate?email="+email;
          console.log(url);
          xhttp.open('GET', url, true);
          xhttp.send();
          }

          foo();

				$.ajax({
					type: "POST",
		            url : '/recruiter/check_candidate_email_exist',
		           data : "email=" + email,
		            success : function(data) {
						$('#result').html(data);
						if(data=="Enter valid email!") {
							document.getElementById("submit-result").style.visibility = 'hidden';
						} else {
							document.getElementById("submit-result").style.visibility = 'visible';
						}
		            }
		        });

			}

  		$("#email").blur(createInt);
		});
	</script>
</head>
<body>
  <%@ include file="../../jspf/recruiter_header.jspf"%>
  <div id="add-vac-container" class="container">
    <div class="interv-info well col-lg-12">
      <div id="int-info-head" class="well col-lg-12 row">
        <h3 id="my-int" class="int-name">Schedule an Interview</h3>
      </div>
      <div class="col-lg-12 intervs well">
        <form:form action="/schedule-interview" method="post" modelAttribute="interview">
          <table class="table table-striped table-responsive">
            <thead>
              <th>Interview Type</th>
              <th>Communication Type</th>
              <th>Interview Date</th>
            </thead>
            <tbody>
              <td><form:select path="interviewType" class="form-control" >
                    <form:option value="TECHNICAL" label="Technical"/>
                    <form:option value="COMMUNICATION" label="Communication"/>
                    <form:option value="PERSONAL" label="Personal"/>
                  </form:select></td>
              <td><form:select path="communicationType" class="form-control" >
                    <form:option value="ONSITE" label="Onsite"/>
                    <form:option value="SKYPE" label="Skype"/>
                    <form:option value="PHONE" label="Phone"/>
                  </form:select></td>
              <td>
                     <div class="form-group">
                      <div class='input-group date' id='datetimepicker1'>
                        <form:input path="date" class="form-control" />
                        <span class="input-group-addon">
                          <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                      </div>
                     </div>
              </td>
            </tbody>
            <thead>
              <th>Enter a Candidate Email</th>
              <th>Select a Vacancy</th>
              <th>Select an Interviewer</th>
            </thead>
            <tbody>
              <td>
                <div class="row">
                	<form class="form-signin">
                  		<input id="email" name="email" class="candEmail"/>
                  	</form>
                </div>
                <div id="result" style="color:red" align="center"></div>
              </td>
              <td>
                <select name="vacancyId" class="form-control">
                    <c:forEach var="vacancy" items="${vacancies}">
                      <option value="${vacancy.id}" label="${vacancy.title}, ${vacancy.company.name}"/>
                    </c:forEach>
                </select>
               </td>
              <td>
                <select name="interviewerId" class="form-control">
                    <c:forEach var="interviewer" items="${interviewers}">
                        <option value="${interviewer.id}" label="${interviewer.firstName} ${interviewer.middleName} ${interviewer.lastName} - ${interviewer.knowledgeField} "/>
                    </c:forEach>
                </select>
              </td>
            </tbody>
          </table>
          <table class="table table-striped table-responsive">
            <thead>
              <th><h3>Please provide any additional information if needed below</h3></th>
            </thead>
            <tbody>
              <td><form:textarea path="comment" type="text" id="comment-lines" class="col-lg-12 col-md-12  col-sm-12 col-xs-12"/></td>
            </tbody>
          </table>
          <input id="submit-result" class="submit-result"  type="submit" value="Save">
        </form:form>

      </div>
    </div>
  </div>
  <%@ include file="../../jspf/footer.jspf"%>
</body>
<script src="/resources/js/schedule-interview.js"></script>
<script type="text/javascript">
  $.urlParam = function(name){
	 var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	 if (results) return results[1];
   else return;
  }
  if ($.urlParam('candidate')){
    $("#email").val($.urlParam('candidate'));
    $("#submit-result").css("visibility", "visible");
  }
</script>
