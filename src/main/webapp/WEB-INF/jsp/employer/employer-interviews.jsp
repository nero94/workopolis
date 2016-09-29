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
<%@ include file="../../jspf/employer_header.jspf"%>
	<div class="container">
		<div class="panel panel-default">
		   <div id="panel-body" class="panel-body ">
		      <form class="row filter-form " role="form">
		        <div class="form-group col-lg-2">
		            <label for="pref-direction">Vacancy:</label><br>
		            <select id="pref-direction" name="vacancyId" class="form-control">
		        		<option value=''>all</option>
								<c:forEach items="${vacancies}" var="vacancy">
									<option value="${vacancy.id}">${vacancy.title}</option>
								</c:forEach>
		             </select>
		         </div>
		         <div class=" form-group col-lg-2">
		           <br>
		            <button type="submit" class="btn btn-success col-lg-12 find">
		            <span class="glyphicon glyphicon-search "></span> Find
		            </button>
		         </div>
		      </form>
		   </div>
		</div>
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
</html>
