<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>My Interviews</title>
	<link rel="stylesheet" href="/resources/recruiter/filter-bar.css">
    <link rel="stylesheet" href="/resources/css/filter-bar-vacancies.css">
	<%@ include file="../../jspf/head.jspf"%>
	<link rel="stylesheet" href="/resources/candidate/css/Candidate's_profile.css">
  <link rel="stylesheet" href="/resources/css/add-vacancy.css" charset="utf-8">
  <link rel="stylesheet" href="/resources/css/interviewer-profile.css" charset="utf-8">
</head>
<body>
<%@ include file="../../jspf/interviewer_header.jspf"%>
<div class="container">
	<c:forEach items="${interviews}" var="interview">
		<div class="interv-info well col-lg-12">
			<table class="table table-striped table-responsive">
				<thead>
            <th>Interview Type</th>
            <th>Communication Type</th>
            <th>Interview Date</th>
          </thead>
          <tbody>
            <td><c:out value="${interview.interviewType}"/></td>
            <td><c:out value="${interview.communicationType}"/></td>
            <td><c:out value="${interview.date}"/></td>
          </tbody>
          <thead>
            <th>Candidate</th>
            <th>Vacancy</th>
            <th>Interviewer</th>
          </thead>
          <tbody>
            <td><a href="/candidate/${interview.candidate.id}"><c:out value="${interview.candidate.firstName} ${interview.candidate.lastName}"/></a></td>
            <td><a href="/vacancy/${interview.vacancy.id}"><c:out value="${interview.vacancy.title}, ${interview.vacancy.company.name}"/></a></td>
            <td><c:out value="${interview.interviewer.firstName} ${interview.interviewer.lastName}"/></td>
          </tbody>
					<table class="table table-striped table-responsive">
						<thead>
							<th><h3>Recruiter Comment</h3></th>
						</thead>
						<tbody>
							<td><c:out value="${interview.comment}"/></td>
						</tbody>
					</table>
          <table class="table table-striped table-responsive">
            <thead>
              <th colspan="2"><h3>Interview Result</h3></th>
            </thead>
            <tbody>
              <thead>
                <th>Identified Skills</th>
                <th>Interviewer Comment</th>
              </thead>
              <tbody>
                <td><textarea rows="5" id="identifiedSkills" class="col-lg-12 identifiedSkills" disabled>${interview.result.identifiedSkills}</textarea></td>
                <td><textarea rows="5" id="interviewerComment" class="col-lg-12 ResultComment" disabled>${interview.result.interviewerComment}</textarea></td>
              </tbody>
            </tbody>
          </table>
		</table>
		<input type="hidden" name="name" id="hiddenIdResult" value="${interview.id}">
			<c:if test="${interview.result == null}">
		<input id="edit-interview-result" type="button" onclick="editResult(this)" name="name" value="Edit Result" class="btn btn-primary col-md-12 new-vac-btn col-lg-2 col-lg-offset-5 edit-interview-result"/>
		<input onclick="SendInterviewResult(this)" id="delete-interview" type="button" name="name" value="Save Result" class="btn btn-success col-md-12 new-vac-btn col-lg-2 col-lg-offset-5 delete-interview-result"/>
			</c:if>
		</div>
	</c:forEach>
</div>
<%@ include file="../../jspf/footer.jspf"%>
</body>
<script src="/resources/js/interviewer.js"></script>
