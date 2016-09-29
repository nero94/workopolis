<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <title>Found candidates</title>
    <%@ include file="../../jspf/head.jspf"%>
    <link rel="stylesheet" href="/resources/css/filter-bar-vacancies.css">
    <link rel="stylesheet" href="/resources/css/candidate's-search.css">
    <%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
		<link rel="stylesheet" href="/resources/candidate/css/Candidate's_profile.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-tagsinput/1.3.6/jquery.tagsinput.min.css">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-tagsinput/1.3.6/jquery.tagsinput.min.js"></script>

</head>
<body id="for-back-found-cand">
<%@ include file="../../jspf/recruiter_header.jspf"%>
<div class="container">


    <c:if test="${size == 0}">
        <div class="container oops-window">

        </div>

    </c:if>


	<c:forEach items="${candidates}" var="candidate" varStatus="status">
		<article class="search-result row">
			<div class="col-lg-3 col-xs-12 col-sm-12 col-md-3 found-cand-photo row">

   				<c:if test="${not empty candidate.photoUrl}">
     				<a href="/candidate/${candidate.id}"><img style="height: 300px; width: 250px;" class="company-logo" src="/resources/candidate/photos/${candidate.photoUrl}"/></a>
     			</c:if>
     			<c:if test="${empty candidate.photoUrl}">
     				<a href="/candidate/${candidate.id}"><img style="height: 250px; width: 250px;" class="company-logo" src="/resources/candidate/photos/default-avatar_0.png"/></a>
    			</c:if>

			</div>
			<div id="vac-text" class="col-lg-6 col-xs-12 col-sm-12 col-md-12 excerpet">
    			<h3><a href="/candidate/${candidate.id}"> <c:out value="${candidate.firstName} ${candidate.middleName} ${candidate.lastName}"/></a>
    			</h3>
					<div class="">
						<h3><div class="glyphicon glyphicon-thumbs-up"></div> Match: <c:out value="${percentages[status.index]}"/></h3>
					</div>
    			<c:if test="${not empty candidate.candidateTechnicalSkills}">
    				<label class=""  for="">Technical skills: </label>
      				<div class=" skills ">
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
        		</c:if>
        		<c:if test="${not empty candidate.candidateCommunicationSkills}">
        			<label class="" style="margin-right:0;" for="">Communication skills: </label>
       				<div class=" skills">
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
       	 			</div>
        		</c:if>
                <c:if test="${not empty candidate.candidatePersonalSkills}">
                    <label class="" style="margin-right:0;" for="">Personal skills: </label>
                    <div class=" skills">
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
                    </div>
                </c:if>
        		<c:if test="${not empty candidate.additionalInformation}">

						<div id="dynamicInput_1" class="skills">
							<label  style="margin-right:0;" for="">Personal information: </label>
							 <div class=" row">
									<div >
										 <span>	${candidate.additionalInformation}</span>
									</div>
							 </div>

						</div>
    			</c:if>
					<br>
     		</div>
     		<br>
		</article>
		<br>
	</c:forEach>
	 <br>
</div>
<%@ include file="../../jspf/footer.jspf"%>
</body>
<script src="resources/js/found-candidates.js"></script>
</html>
