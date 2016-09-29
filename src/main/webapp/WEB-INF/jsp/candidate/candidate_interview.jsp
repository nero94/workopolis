<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<%@ include file="../../jspf/candidate_header.jspf"%>
<div class="container">
	<c:if test="${empty candidateInterviews}">
		<h3 class="emty-page">You don't have scheduled interviews.</h3>
	</c:if>
  <c:forEach items="${candidateInterviews}" var="interview">
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
            <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${interview.date}" /></td>
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
              <th><h3>Interview Result</h3></th>
            </thead>
            <tbody>
              <thead>
                <th>Identified Skills</th>
                <th>Interviewer Comment</th>
              </thead>
              <tbody>
                <td><c:out value="${interview.result.identifiedSkills}"/></td>
                <td><c:out value="${interview.result.interviewerComment}"/></td>
              </tbody>
            </tbody>
          </table>
        </table>

      </div>

  </c:forEach>
</div>


<%@ include file="../../jspf/footer.jspf"%>
</body>
<script src="/resources/js/schedule-interview.js"></script>
</html>
