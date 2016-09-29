<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>New Vacancies</title>

	<link rel="stylesheet" href="/resources/css/filter-bar-vacancies.css">
	<%@ include file="../../jspf/head.jspf"%>
	<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	</head>
<body>
<%@ include file="../../jspf/manager_header.jspf"%>

<div class="back-new-vacancies">
<div class="container" id="vacancies-container">
<div class="panel panel-default">
   <div id="panel-body" class="panel-body">
      <form class="row filter-form" role="form">
		<div class="form-group  col-lg-2 col-lg-offset-1">
            <label class="filter-col" for="pref-search">Search:</label>
            <input type="text" class="form-control" id="pref-search" name="keyword">
        </div>
        <div class="form-group form-group col-lg-2">
            <label  for="pref-orderby">By Direction:</label>
            <select id="pref-perpage" class="form-control" name="direction">
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
         <div class="form-group col-lg-2">
            <label   for="pref-perpage">By Company Name:</label>
            <select id="pref-perpage" class="form-control  col-lg-12" name="company">
            <option></option>
            	<c:forEach items="${companies}" var="company">
            		<option value="${company.name}">${company.name}</option>
            	</c:forEach>
            </select>
         </div>
         <div class="form-group col-lg-2">
            <label  for="pref-perpage">By Location:</label>
            <select id="pref-location" class="form-control col-lg-12" name="address">
            <option></option>
            	<c:forEach items="${countries}" var="country">
               		<option>${country.name}</option>
               	</c:forEach>
            </select>
         </div>

         <div class="form-group col-lg-2">
			<label></label><br>
            <button type="submit" class="btn btn-success filter-col col-lg-12 find">
            <span class="glyphicon glyphicon-search"></span> Find
            </button>
         </div>
      </form>
   </div>
</div>
</div>




<div class="container">

   <section class="col-lg-12 col-xs-12 col-sm-12 col-md-12" >

   <c:forEach items="${vacancies}" var="vacancy">
   <article class="search-result row">
        <div class="col-lg-3 col-xs-12 col-sm-12 col-md-3">
           <a href="/vacancy/${vacancy.id}" title="Logo" class="thumbnail"><img src="/resources/employer/img/${vacancy.company.imgUrl}" alt="CompanyLogo" /></a>
        </div>
        <div id="vac-text" class="col-xs-12 col-sm-12 col-md-7 excerpet">
           <h3><a href="/vacancy/${vacancy.id}" title="">${vacancy.title}</a></h3>

					 <c:if test="${fn:length(vacancy.description) > 300}">
						 <p> <c:out value="${fn:substring(vacancy.description, 0, 300)}"/>...</p>
					 </c:if>
					 <c:if test="${fn:length(vacancy.description) <= 300}">
						 <p> <c:out value="${vacancy.description}"/></p>
					 </c:if>
           <br>

        </div>
        <div id="glyphicons" class="col-lg-2 col-xs-12 col-sm-12 col-md-2">
           <ul class="meta-search">
           <fmt:parseDate pattern="yyyy-MM-dd" value="${vacancy.postDate}" var="parsedPostDate" />
        <fmt:formatDate pattern="MM/dd/yyyy" value="${parsedPostDate}" var="formattedPostDate" />
            <li><i class="glyphicon glyphicon-time"></i> <span><c:out value="${formattedPostDate}"/></span></li>
            <li><i class="glyphicon glyphicon-usd"></i> <span>${vacancy.salary}</span></li>
            <li><i class="glyphicon glyphicon-home"></i> <span>${vacancy.company.address}</span></li>
							<div class="row buttons">
	              <input onclick="location.href='/manager/approveVacancy?id=${vacancy.id}'" type="button" name="name" value="Approve" class="btn btn-success ">
								<input onclick="location.href='/delete_vacancy_as_manager?id=${vacancy.id}'" type="button" name="name" value="Delete" class="btn btn-danger ">
							<div>
           </ul>
         </div>
        <span class="clearfix border"></span>
     </article>
     </c:forEach>
      <div class="text-center">
      <tag:paginate max="5" offset="${offset}" count="${count}"
      uri="/new_vacancies?keyword=${keyword}&direction=${direction}&location=${location}" next="&raquo;" previous="&laquo;" />
      </div>
   </section>
</div>
</div>


</body>
<%@ include file="../../jspf/footer.jspf"%>


<script>
$( document ).ready(function() {

	var path = window.location.href;
	var current = path.substring(path.lastIndexOf('/')+1);
	console.log(path);
	console.log(current);
	if(current=="closed_vacancies" ){

	$('.buttons').hide();

}else if(current=="new_vacancies" ){
		$('.buttons').show();
	}

$.urlParam = function(name){
	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	return results[1] || 0;
}
if ($.urlParam("keyword") != 0) $("#pref-search").val($.urlParam("keyword"));

$("#pref-location option").each(function(){
	if (this.value == $.urlParam("address")) this.setAttribute("selected", "");
});

$("#pref-direction option").each(function(){
	if (this.value == $.urlParam("direction")) this.setAttribute("selected", "");
});


});



</script>
</html>
