<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../jspf/head.jspf"%>
	<title>Recruiters</title>
	<link href="/resources/manager_profile/css/recruiters-information.css" rel="stylesheet">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="/resources/manager_profile/rating_stars/ewsRatingStars.jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/manager_profile/rating_stars/ewsRatingStars.jquery.css">

	<script type="text/javascript" src="/resources/manager_profile/js/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="/resources/manager_profile/js/sortScript.js"></script>
	<script type="text/javascript" src="/resources/manager_profile/js/starsScript.js"></script>
</head>
<body >
<%@ include file="../../jspf/manager_header.jspf"%>
<div align="center" class='container'>
<table cellpadding="10" class="sortable" class="click-table recruiters_table table table-striped tabel-responsive">
	<thead>
		<tr>
			<th>Name</th>
			<th>Assigned Vacancies</th>
			<th>Processing</th>
			<th>Closed</th>
			<th>Progress</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${recruiters}" var="recruiter" varStatus="status">
		<tr onclick="myFunction(this)">
			<td>${recruiter.firstName} ${recruiter.lastName}</td>
			<td>${fn:length(recruiter.assignedVacancies)}</td>
			<td>${countProcessing[status.index]}</td>
			<td>${countClosed[status.index]}</td>
			<td><progress max="${fn:length(recruiter.assignedVacancies)}" value="${countClosed[status.index]}"></progress></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<%@ include file="../../jspf/footer.jspf"%>
</body>
</html>
