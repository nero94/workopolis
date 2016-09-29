
<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <title>Candidates</title>
    <%@ include file="../../jspf/head.jspf"%>
    <link rel="stylesheet" href="/resources/css/filter-bar-vacancies.css">
    <link rel="stylesheet" href="/resources/css/candidate's-search.css">
    <%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
</head>
<body>
<%@ include file="../../jspf/recruiter_header.jspf"%>
	<div class="container">
	<div class="panel panel-default">
	   <div class="panel-body" style="background-color:#e0ffa3;">
	      <form class="row " role="form">
			<div class=" form-group col-lg-3">
	            <label for="pref-direction">Direction:</label>
				<select id="pref-direction" name="interstedDirection" class="form-control">
					<option></option>
					<option value="ADMINISTRATION">Administration</option>
					<option value="BUSINESS_ANALYSIS">Business analysis</option>
					<option value="DESIGN">Design</option>
					<option value="DATABASES">Databases</option>
					<option value="PROJECT_MANAGEMENT">Project management</option>
					<option value="QUALITY_CONTROL">Quality control</option>
					<option value="SOFTWARE_DEVELOPMENT">Software development</option>
					<option value="SYSTEM_ARCHITECTURE">System architecture</option>
				</select>
	        </div>

	         <div class="form-group  col-lg-3">
	            <label class=""  for="">By Location:</label>
	            <select id="pref-location" class="form-control" name="country">
	            	<option></option>
	               <c:forEach items="${countries}" var = "country">
									 <option>${country.name}</option>
								 </c:forEach>
	            </select>
	         </div>

			<div class=" form-group col-lg-2">
	            <label class="" for="pref-search">Age from:</label>
	            <input type="number" class="form-control " id="" name="ageFrom">

	        </div>
	        <div class=" form-group col-lg-2">
	        	<label class="" for="pref-search">Age to: </label>
	        	<input type="number" class="form-control " id="" name="ageTo">
            </div>
	         <div class="form-group  col-lg-2 col-md-4 col-sm-4 col-xs-4">
	         <label class=""  for=""></label>
	            <button type="submit" class="btn btn-success form-control find">
	            <span class="glyphicon glyphicon-search"></span> Find </button>
	         </div>
	      </form>
	   </div>



</div>
</div>
<c:forEach items="${appliedCandidates}" var="appliedCandidate">
<div class="container">
   <article class="search-result row">
   	<div class="col-lg-3 col-xs-12 col-sm-12 col-md-12" style="margin-top: 1em;">
   	<c:if test="${not empty appliedCandidate.photoUrl}">
     <a href="/candidate/${appliedCandidate.id}"><img style="height: 300px; width: 250px;" class="company-logo" src="/resources/candidate/photos/${appliedCandidate.photoUrl}"/></a>
     </c:if>
     <c:if test="${empty appliedCandidate.photoUrl}">
     	<a href="/candidate/${appliedCandidate.id}"><img style="height: 250px; width: 250px;" class="company-logo" src="/resources/candidate/photos/default-avatar_0.png"/></a>
    </c:if>
</div>
   	<div id="vac-text" class=" col-lg-6 col-xs-12 col-sm-12 col-md-12 excerpet">
    <h3><a href="/candidate/${appliedCandidate.id}"> <c:out value="${appliedCandidate.firstName} ${appliedCandidate.middleName} ${appliedCandidate.lastName}"/></a></h3>
    <c:if test="${not empty appliedCandidate.candidateTechnicalSkills}">
    <label class="" for="">Technical skills: </label>
      	<div class="well skills">
      		<c:forEach items="${appliedCandidate.candidateTechnicalSkills}" var="candidateTechnicalSkill">
        		<div class="btn-group">
        			<button style="background-color: #d1f8d8;" type="button" class="btn btn-default">${candidateTechnicalSkill.name}</button>
        		</div>
        	</c:forEach>
        </div>
        </c:if>
        <c:if test="${not empty appliedCandidate.candidateCommunicationSkills}">
        <label class=""  for="">Communication skills: </label>
        <div class="well skills">
        	<c:forEach items="${appliedCandidate.candidateCommunicationSkills}" var="candidateCommunicationSkill">
        		<div class="btn-group">
        		<button style="background-color: #d1f8d8;" type="button" class="btn btn-default">${candidateCommunicationSkill.name}</button>
        		</div>
        	</c:forEach>
        </div>
        </c:if>
        <c:if test="${not empty appliedCandidate.additionalInformation}">
    		<label class=""  for="">Personal information: </label>
    		<div class="well skills">
    			<div style="font-size: 13pt">${appliedCandidate.additionalInformation}</div>
    		</div>
    	</c:if>
     </div>
     <div id="glyphicons" class="col-lg-3 col-xs-3 col-sm-3 col-md-3">
     <ul class="meta-search">
        <li><i class="glyphicon glyphicon-phone"></i> <span><c:out value="${appliedCandidate.telNumber}"/></span></li>
        <li><i class="fa fa-skype"></i> <span><c:out value="${appliedCandidate.skypeUrl}"/></span></li>
				<c:if test="${not empty appliedCandidate.facebookUrl}">
					<li><a href="${appliedCandidate.facebookUrl}"><i class="fa fa-facebook"></i></a></li>
				</c:if>
				<c:if test="${not empty appliedCandidate.linkedInUrl}">
        	<li><a href="${appliedCandidate.linkedInUrl}"><i class="fa fa-linkedin"></i></a></li>
        </c:if>
     </ul>
   </div>
    <br>

    </article>
    </div>
</c:forEach>

<div class="text-center">
<tag:paginate max="5" offset="${offset}" count="${count}"
uri="/recruiter/candidates?keyword=${keyword}" next="&raquo;" previous="&laquo;" />
</div>
<%@ include file="../../jspf/footer.jspf"%>

</body>

<script>
$.urlParam = function(name){
	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	if (results) return results[1] || 0;
	else return;
}

$("#pref-location option").each(function(){
	if (this.value == $.urlParam("address")) this.setAttribute("selected", "");
});
$("#pref-direction option").each(function(){
	if (this.value == $.urlParam("direction")) this.setAttribute("selected", "");
});
</script>

</html>
