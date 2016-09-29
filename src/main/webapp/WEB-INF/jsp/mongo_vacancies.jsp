<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  <title>Vacancy Search</title>
  <link rel="stylesheet" href="/resources/css/filter-bar-vacancies.css">
   <%@ include file="../jspf/head.jspf"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
</head>

<body>
<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
<%@ include file="../jspf/header.jspf"%>
</sec:authorize> 
<sec:authorize access="hasRole('ROLE_CANDIDATE')">
<%@ include file="../jspf/candidate_header.jspf"%>
</sec:authorize> 
<sec:authorize access="hasRole('ROLE_EMPLOYER')">
<%@ include file="../jspf/employer_header.jspf"%>
</sec:authorize>
<div class="back" id="vcancies_search">
<div class="container" id="vacancies-container">
<div class="panel panel-default">
   <div id="panel-body" class="panel-body ">
      <form class="row filter-form " role="form">
		<div class="form-group col-lg-2 col-lg-offset-1">
            <label   for="pref-search">Search:</label><br>
            <input type="text" value="${keyword}" class="form-control " id="pref-search" name="keyword">
        </div>
        <div class="form-group col-lg-2">
           <label   for="pref-salary">By Salary:</label><br>
           <div class="input-group">
  				<span class="input-group-addon">$</span>
           <input type="number" value="${salary}" class="form-control " name="salary" id="pref-salary">
           </div>
        </div>
        <div class="form-group col-lg-2">
            <label for="pref-direction">By Direction:</label><br>
            <select id="pref-direction" name="direction" class="form-control">
        		<option></option>
            	<option value="ADMINISTRATION">Administration</option>
            	<option value="BUSINESS_ANALYSIS">Buisness Analysis</option>
            	<option value="DESIGN">Design</option>
            	<option value="PROJECT_MANAGEMENT">Project Management</option>
            	<option value="QUALITY_CONTROL">Quality Control</option>
            	<option value="SOFTWARE_DEVELOPMENT">Software Development</option>
            	<option value="SYSTEM_ARCHTECTURE">System Architecture</option>
             </select>
         </div>
         <div class="form-group col-lg-2" >
            <label  for="pref-location">By Location:</label><br>
            <select id="pref-location" class="form-control col-lg-12" name="location">
               <option></option>
               <option>Ukraine</option>
               <option>USA</option>
               <option>Germany</option>
               <option>Egypt</option>
               <option>Israel</option>
               <option>Poland</option>
               <option>Brazil</option>
            </select>
         </div>
         <div class=" form-group col-lg-2">
           <label></label><br>
            <button type="submit" class="btn btn-success col-lg-12">
            <span class="glyphicon glyphicon-search"></span> Find
            </button>
         </div>
      </form>
   </div>
</div>
</div>




<div class="container">
   <hgroup class="mb20">
      <h1>Search Results</h1>
      <h2 class="lead"><strong class="text-danger">${vacancyCount}</strong> results were found.</h2>
   </hgroup>
   <section class="col-md-12">


   <c:forEach items="${vacancies}" var="vacancy">
     <article class="search-result row">
        <div class="col-lg-3 col-xs-12 col-sm-12 col-md-3">
        <a href="#"><img class="company-logo" src="resources/img/abstract-logo.gif" alt="CompanyLogo" /></a>
        </div>
        <div id="vac-text" class="col-xs-12 col-sm-12 col-md-7 excerpet">
           <h3><a href="#"> <c:out value="${vacancy.title}"/></a></h3>

           <p> <c:out value="${vacancy.description}"/></p>
           <br>

        </div>
        <div id="glyphicons" class="col-lg-2 col-xs-12 col-sm-12 col-md-2">
           <ul class="meta-search">
              <li><i class="glyphicon glyphicon-time"></i> <span><c:out value="${vacancy.postDate}"/></span></li>
              <li><i class="glyphicon glyphicon-usd"></i> <span><c:out value="${vacancy.salary}"/></span></li>
              <li><i class="glyphicon glyphicon-home"></i> <span><c:out value="${vacancy.location}"/></span></li>
           </ul>
         </div>

       <br>

        <span class="clearfix border"></span>
     </article>
      </c:forEach>


      <div class="text-center">
      <tag:paginate max="15" offset="${offset}" count="${count}"
      uri="/vacancies" next="&raquo;" previous="&laquo;" />
      </div>
   </section>
</div>
</div>
<%@ include file="../jspf/footer.jspf"%>
</body>
<script>
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
	}
	if ($.urlParam("keyword") != 0) $("#pref-search").val($.urlParam("keyword"));
	if ($.urlParam("salary") != 0) $("#pref-salary").val($.urlParam("salary"));
	$("#pref-location option").each(function(){
		if (this.value == $.urlParam("location")) this.setAttribute("selected", "");
	});
	$("#pref-direction option").each(function(){
		if (this.value == $.urlParam("direction")) this.setAttribute("selected", "");
	});
</script>
</html>
