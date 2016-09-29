<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>Vacancy description</title>
	<%@ include file="../jspf/head.jspf"%>
	<link rel="stylesheet" href="/resources/css/vacancy-description.css">


</head>
<body>
	<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
	<%@ include file="../jspf/header.jspf"%>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_CANDIDATE')">
	<%@ include file="../jspf/candidate_header.jspf"%>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_RECRUITER')">
  <%@ include file="../jspf/recruiter_header.jspf"%>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_EMPLOYER')">
	<%@ include file="../jspf/employer_header.jspf"%>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_INTERVIEWER')">
	<%@ include file="../jspf/interviewer_header.jspf"%>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_MANAGER')">
		<%@ include file="../jspf/manager_header.jspf"%>
	</sec:authorize>


<div class="back">
	<div id="vacancy" class="container">
		<div id="description-row" class="row">
	         	<h3 id="head-text" class="text-center">${vacancy.title}</h3>
	    </div>
	    <div id="info-row" class="row">
		    <div class="col-lg-4">
	            <a href="#" title="Logo" class="thumbnail"><img id="company-logo" src="/resources/employer/img/${vacancy.company.imgUrl}" alt="CompanyLogo"/></a>

	        	<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
	        		<button  onclick="location.href='/login'" id="apply "type="button" class="col-lg-10 col-lg-offset-1 col-md-2 col-md-offset-10 col-sm-4 col-sm-offset-4 col-xs-6 col-xs-offset-3 btn btn-success glyphicon glyphicon-ok">Apply</button>
	        	</sec:authorize>

	        <sec:authorize access="hasRole('ROLE_CANDIDATE')">
	            <sec:authentication var="principal" property="principal" />
	            <c:forEach items="${vacancy.appliedCandidates}" var="appliedCandidate">
	            <c:if test="${principal.username == appliedCandidate.email}">
	            	<c:set var="apply" value="applyed"/>
				</c:if>

	            </c:forEach>
	            	<c:if test="${apply != 'applyed'}">
	            		<button onclick="location.href='/candidate/apply?id=${vacancy.id}'" id="apply "type="button" class="col-lg-10 col-lg-offset-1 col-md-2 col-md-offset-10 col-sm-4 col-sm-offset-4 col-xs-6 col-xs-offset-3 btn btn-success glyphicon glyphicon-ok">Apply</button>
	            	</c:if>
	            	<c:if test="${apply == 'applyed'}">
	        		<div class="applied col-lg-offset-1 col-md-offset-4 col-sm-offset-4 col-xs-offset-3">
	        		<p>You've already applyed for this vacancy</p>
	    		</div>
            	</c:if>

	        </sec:authorize>
					<sec:authorize access="hasRole('ROLE_MANAGER')">
	            <sec:authentication var="principal" property="principal" />
	            	<c:if test="${vacancy.state == 'NOT_APPROVED'}">
	            		<button onclick="location.href='/manager/approveVacancy?id=${vacancy.id}'" id="apply "type="button" class="col-lg-10 col-lg-offset-1 col-md-2 col-md-offset-10 col-sm-4 col-sm-offset-4 col-xs-6 col-xs-offset-3 btn btn-success glyphicon glyphicon-ok">Approve</button>
	            	</c:if>
	        </sec:authorize>
					<sec:authorize access="hasRole('ROLE_RECRUITER')">
	            <sec:authentication var="principal" property="principal" />
	            	<c:if test="${vacancy.state == 'POSTED'}">
	            		<button onclick="location.href='/recruiter/assignVacancy?id=${vacancy.id}'" id="apply "type="button" class="col-lg-10 col-lg-offset-1 col-md-2 col-md-offset-10 col-sm-4 col-sm-offset-4 col-xs-6 col-xs-offset-3 btn btn-success glyphicon glyphicon-ok">Assign</button>
	            	</c:if>
	        </sec:authorize>
	        </div>
		     <div class="info col-lg-6 col-lg-offset-2 col-md-9 col-md-offset-2 col-sm-9 col-sm-offset-2 col-xs-11 col-xs-offset-2" style="margin:10px">
					 <div class="row">
						 <div class=" col-lg-4 col-lg-offset-2 col-md-4 col-md-offset-2 col-sm-4 col-sm-offset-2 col-xs-4 col-xs-offset-2"><span class="glyphicon glyphicon-home"></span>Company:</div>
		         <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6"><p class="text">${vacancy.company.name}</p></div>
					 </div>

					 <div class="row">
						 <div class=" col-lg-4 col-lg-offset-2 col-md-4 col-md-offset-2 col-sm-4 col-sm-offset-2 col-xs-4 col-xs-offset-2"><span class="glyphicon glyphicon-move"></span>Direction:</div>
						<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6"><p id="direction" class="text">${vacancy.direction}</p></div>
					 </div>
					 <div class="row">
						<div class=" col-lg-4 col-lg-offset-2 col-md-4 col-md-offset-2 col-sm-4 col-sm-offset-2 col-xs-4 col-xs-offset-2"><span class="glyphicon glyphicon-map-marker"></span>Location:</div>
						<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6"><p  class="text">${vacancy.company.address}</p></div>
					</div>
					<div class="row">
						<div class=" col-lg-4 col-lg-offset-2 col-md-4 col-md-offset-2 col-sm-4 col-sm-offset-2 col-xs-4 col-xs-offset-2"><span class="glyphicon glyphicon-usd"></span>Salary:</div>
						<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6"><p  class="text">${vacancy.salary}</p></div>
					</div>
					 <div class="row">
						 <div class=" col-lg-4 col-lg-offset-2 col-md-4 col-md-offset-2 col-sm-4 col-sm-offset-2 col-xs-4 col-xs-offset-2"><span class="glyphicon glyphicon-time"></span>Status:</div>
		         <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6"><p id="status" class="text">${vacancy.status}</p></div>
					 </div>
					 <div class="row">
						 <div class=" col-lg-4 col-lg-offset-2 col-md-4 col-md-offset-2 col-sm-4 col-sm-offset-2 col-xs-4 col-xs-offset-2"><span class="glyphicon glyphicon-hourglass"></span>Posted:</div>
		         <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6"><p class="text"><fmt:formatDate pattern="yyyy-MM-dd" value="${vacancy.postDate}" /></p></div>
					 </div>
	         </div>

		</div>

		<div id="vac-elements" class="container">
			<div id="start-text">
		    	<h3>General Description</h3>
		    </div>
			<div>
					<p class="lot_of_text">${vacancy.description}</p>
			</div>
		</div>
		<div id="vac-elements" class="container">
			<div id="start-text">
		    	<h3>Responsibilities</h3>
		    </div>
			<div>
					<p class="lot_of_text">${vacancy.responsibilities}</p>
			</div>
		</div>
		<div id="vac-elements" class="container">
			<div id="technical" class="row">
				<table class="technical-table">
					<thead>
				<div id="start-text"><h3>Technical skills</h3></div>
			</thead>
			<tbody>
				<tr>
					<div id="technologies" class="category">
						<td><h4>Technologies</h4></td>
						<td>
							<div id="skills">
							<c:forEach items="${vacancy.technicalSkills}" var="technicalSkill">
							<c:if test="${technicalSkill.category == 'TECHNOLOGIES'}">
								<div class="skill panel panel-success">
									<div class="panel-heading">${technicalSkill.name}</div>
									<div class="panel-body">Level:<span>${technicalSkill.level}</span></div>
									<div class="panel-footer">${technicalSkill.importance}</div>
								</div>
							</c:if>
							</c:forEach>
						</div>
					</td>
					</div>
				</tr>
				<tr>
					<div id="languages" class="category">
						<td><h4>Languages</h4></td>
						<td>
							<div id="skills">
							<c:forEach items="${vacancy.technicalSkills}" var="technicalSkill">
							<c:if test="${technicalSkill.category == 'LANGUAGES'}">
								<div class="skill panel panel-success">
									<div class="panel-heading">${technicalSkill.name}</div>
									<div class="panel-body">Level:<span>${technicalSkill.level}</span></div>
									<div class="panel-footer">${technicalSkill.importance}</div>
								</div>
							</c:if>
							</c:forEach>
						</div>
					</td>
					</div>
				</tr>
				<tr>
					<div id="frameworks" class="category">
						<td><h4>Frameworks</h4></td>
						<td>
							<div id="skills">
							<c:forEach items="${vacancy.technicalSkills}" var="technicalSkill">
							<c:if test="${technicalSkill.category == 'FRAMEWORKS'}">
								<div class="skill panel panel-success">
									<div class="panel-heading">${technicalSkill.name}</div>
									<div class="panel-body">Level:<span>${technicalSkill.level}</span></div>
									<div class="panel-footer">${technicalSkill.importance}</div>
								</div>
							</c:if>
							</c:forEach>
						</div>
					</td>
					</div>
				</tr>
				<tr>
					<div id="databases" class="category">
						<td><h4>Databases</h4></td>
						<td>
						<div id="skills">
							<c:forEach items="${vacancy.technicalSkills}" var="technicalSkill">
							<c:if test="${technicalSkill.category == 'DATABASES'}">
								<div class="skill panel panel-success">
									<div class="panel-heading">${technicalSkill.name}</div>
									<div class="panel-body">Level:<span>${technicalSkill.level}</span></div>
									<div class="panel-footer">${technicalSkill.importance}</div>
								</div>
							</c:if>
							</c:forEach>
						</div>
					</td>
					</div>
				</tr>
				<tr>
					<div id="tools" class="category">
						<td><h4>Tools</h4></td>
						<td>
						<div id="skills">
							<c:forEach items="${vacancy.technicalSkills}" var="technicalSkill">
							<c:if test="${technicalSkill.category == 'TOOLS'}">
								<div class="skill panel panel-success">
									<div class="panel-heading">${technicalSkill.name}</div>
									<div class="panel-body">Level:<span>${technicalSkill.level}</span></div>
									<div class="panel-footer">${technicalSkill.importance}</div>
								</div>
							</c:if>
							</c:forEach>
						</div>
					</td>
					</div>
				</tr>
				<tr>
					<div id="protocols" class="category">
						<td><h4>Protocols</h4></td>
						<td>
							<div id="skills">
							<c:forEach items="${vacancy.technicalSkills}" var="technicalSkill">
							<c:if test="${technicalSkill.category == 'PROTOCOLS'}">
								<div class="skill panel panel-success">
									<div class="panel-heading">${technicalSkill.name}</div>
									<div class="panel-body">Level:<span>${technicalSkill.level}</span></div>
									<div class="panel-footer">${technicalSkill.importance}</div>
								</div>
							</c:if>
							</c:forEach>
						</div>
					</td>
					</div>
				</tr>
				<tr>
					<div id="libraries" class="category">
						<td><h4>Libraries</h4></td>
						<td>
							<div id="skills">
							<c:forEach items="${vacancy.technicalSkills}" var="technicalSkill">
							<c:if test="${technicalSkill.category == 'LIBRARIES'}">
								<div class="skill panel panel-success">
									<div class="panel-heading">${technicalSkill.name}</div>
									<div class="panel-body">Level:<span>${technicalSkill.level}</span></div>
									<div class="panel-footer">${technicalSkill.importance}</div>
								</div>
							</c:if>
							</c:forEach>
						</div>
					</td>
					</div>
				</tr>
				<tr>
					<div id="techniques" class="category">
						<td><h4>Techniques</h4></td>
						<td>
							<div id="skills">
							<c:forEach items="${vacancy.technicalSkills}" var="technicalSkill">
							<c:if test="${technicalSkill.category == 'TECHNIQUES'}">
								<div class="skill panel panel-success">
									<div class="panel-heading">${technicalSkill.name}</div>
									<div class="panel-body">Level:<span>${technicalSkill.level}</span></div>
									<div class="panel-footer">${technicalSkill.importance}</div>
								</div>
							</c:if>
							</c:forEach>
						</div>
					</td>
					</div>
				</tr>
			</tbody>
				</table>

			</div>
			<div id="start-text">
		    	<h3>Communication skills</h3>
		    </div>
				<div class="category2">
				<div id="skills">
					<c:forEach items="${vacancy.communicationSkills}" var="communicationSkill">
						<div class="skill panel panel-info">
							<div class="panel-heading">${communicationSkill.name}</div>
							<div class="panel-body">Level:<span>${communicationSkill.level}</span></div>
							<div class="panel-footer">${communicationSkill.importance}</div>
						</div>
					</c:forEach>
				</div>
				</div>
			<div id="start-text">
		    	<h3>Personal skills</h3>
		    </div>
				<div class="category2">
				<div id="skills" class="category2">
					<c:forEach items="${vacancy.personalSkills}" var="personalSkill">
						<div class="skill panel panel-warning">
							<div class="panel-heading">${personalSkill.personalSkillName}</div>
							<div class="panel-body">Level:<span>${personalSkill.personalSkillLevel}</span></div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

		<div id="vac-elements" class="container">
			<div id="start-text">
		    	<h3>Education Requirements:</h3>
		    </div>
			<div>
				<div id="education_field">
					<h4>Education Field:</h4>
					<span id="education_field_val">${vacancy.educationField}</span>
				</div>
				<div id="education_degree">
					<h4>Education Degree:</h4>
					<span>${vacancy.educationDegree}</span>
				</div>
			</div>
		</div>
		<div id="vac-elements" class="container">
			<div id="start-text">
		    	<h3>We Offer</h3>
		    </div>
			<div>
					<p class="lot_of_text">${vacancy.employerOffer}</p>
			</div>
		</div>
		<c:if test="${vacancy.state == 'CLOSED'}">
			<c:if test="${vacancy.feedback != null}">
		<div id="vac-elements" class="container">
			<div id="start-text">
		    	<h3>Employer's Review</h3>
						<p class="lot_of_text">${vacancy.feedback}</p>
		    </div>
		</div>
</c:if>
	</c:if>
</div>
<%@ include file="../jspf/footer.jspf"%>
</body>

<script type="text/javascript">
$("#technical tr").each(function(){
	if(!$(this).find("#skills").has("div").length) $(this).css("display", "none");
});

var levelColors = {
	BASIC: "#8FBC8F",
	AVERAGE: "#FFA500",
	STRONG: "#DA70D6",
	EXPERT: "#FF4500",
	BEGINER: "#8FBC8F",
	ELEMENTARY: "#F4A460",
	INTERMEDIATE: "#FFA500",
	UPPER_INTERMEDIATE: "#FF1493",
	ADVANCED: "#DA70D6",
	PROFICIENT: "#FF4500",
	MINIMUM: "#8FBC8F",
	AVERAGE: "#DA70D6",
	MAXIMUM: "#FF4500"
};

var directions = {
	ADMINISTRATION: "Administration",
	BUSINESS_ANALYSIS: "Buisness Analysis",
	DESIGN: "Design",
	DATABASES: "Databases",
	PROJECT_MANAGEMENT: "Project Management",
	QUALITY_CONTROL: "Quality Control",
	SOFTWARE_DEVELOPMENT: "Software Development",
	SYSTEM_ARCHITECTURE: "System Architecture"
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

var statuses = {
	FULL_TIME: "Full Time",
	PART_TIME: "Part Time"
};
var a=$("#status").text();
$("#education_field_val").text(educationFields[$("#education_field_val").text()]);
$("#status").text(statuses[$("#status").text()]);
$("#direction").text(directions[$("#direction").text()]);

$("#technical tr .skill .panel-body span").each(function(){
	$(this).css("color", levelColors[$(this).text()]);
});

$(".category2 .skill .panel-body span").each(function(){
	$(this).css("color", levelColors[$(this).text()]);
});

$("#technical tr .skill").each(function(){
	if ($(this).find(".panel-footer").text() == "ADDITIONAL")
	$(this).parent().append($(this));
});

</script>

</html>
