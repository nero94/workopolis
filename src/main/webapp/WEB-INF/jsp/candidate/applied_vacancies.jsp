<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<!DOCTYPE html>
<html>
<head>
	<title>My Vacancies</title>
	<link rel="stylesheet" href="/resources/recruiter/filter-bar.css">
    <link rel="stylesheet" href="/resources/css/filter-bar-vacancies.css">
	<%@ include file="../../jspf/head.jspf"%>
	<link rel="stylesheet" href="/resources/candidate/css/Candidate's_profile.css">
</head>
<style>

div.pager span{
  padding:10px;
  font-size: 20px;
    font-family: arial, sans-serif;
    cursor: pointer;
}
@media (min-width: 100px){
div.container div.page-num div.pager span.page-number.clickable {
    padding: 10px;
    font-size: 20px;
    font-family: arial, sans-serif;
    cursor: pointer;
  }
}
  .pager {
    padding:10px!important;
    font-size: 20px!important;
      font-family: arial, sans-serif!important;
      cursor: pointer!important;
  }
	div.pager span.active {
	    background:#dddddd;
	}
</style>
<body>
<%@ include file="../../jspf/candidate_header.jspf"%>
<div class="container">
	<div class="page_num col-lg-offset-11 col-md-offset-11 col-sm-offset-11 col-xs-offset-10"></div>
<div class="paginated">
<c:forEach items="${candidateVacancies}" var="vacancy">
	<c:if test="${vacancy.state != 'CLOSED'}">
  <article class="search-result row">

     <div class="col-lg-3 col-xs-12 col-sm-12 col-md-3">
     <a href="/vacancy/${vacancy.id}"><img class="company-logo" src="/resources/employer/img/${vacancy.company.imgUrl}" alt="CompanyLogo" /></a>
     </div>
     <div id="vac-text" class="col-xs-12 col-sm-12 col-md-7 excerpet">
        <h3><a href="/vacancy/${vacancy.id}"> <c:out value="${vacancy.title}"/></a></h3>

				<c:if test="${fn:length(vacancy.description) > 300}">
					<p> <c:out value="${fn:substring(vacancy.description, 0, 300)}"/>...</p>
				</c:if>
				<c:if test="${fn:length(vacancy.description) <= 300}">
					<p> <c:out value="${vacancy.description}"/></p>
				</c:if>
				<br> 
        <br>

     </div>
     <div id="glyphicons" class="col-lg-2 col-xs-12 col-sm-12 col-md-2">
        <ul class="meta-search">
        <fmt:parseDate pattern="yyyy-MM-dd" value="${vacancy.postDate}" var="parsedPostDate" />
        <fmt:formatDate pattern="MM/dd/yyyy" value="${parsedPostDate}" var="formattedPostDate" />
           <li><i class="glyphicon glyphicon-time"></i> <span><c:out value="${formattedPostDate}"/></span></li>
           <li><i class="glyphicon glyphicon-usd"></i> <span><c:out value="${vacancy.salary}"/></span></li>
           <li><i class="glyphicon glyphicon-home"></i> <span><c:out value="${vacancy.company.address}"/></span></li>
        </ul>
      </div>


    <br>
      <!-- <input type="hidden" name="name" id="hiddenIdResult" value="${vacancy.id}"> -->
     <span class="clearfix border"></span>
  </article>
</c:if>
   </c:forEach>
</div>
</div>
<%@ include file="../../jspf/footer.jspf"%>
</body>
<script src="/resources/js/find-candidates.js"></script>
<script>
$('div.paginated').each(function() {
		var currentPage = 0;
		var numPerPage = 5;
		var $article = $(this);
		var a=$article.find('article').length;
		console.log(a);
		$article.bind('repaginate', function() {
				$article.find(' article').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
		});
		$article.trigger('repaginate');
		var numRows = $article.find('article').length;
		console.log(numRows);
		var numPages = Math.ceil(numRows / numPerPage);
		var $pager = $('<div class="pager"></div>');
		for (var page = 0; page < numPages; page++) {
				$('<span class="page-number"></span>').text(page + 1).bind('click', {
						newPage: page
				}, function(event) {
						currentPage = event.data['newPage'];
						$article.trigger('repaginate');
						$(this).addClass('active').siblings().removeClass('active');
				}).appendTo($pager).addClass('clickable');
		}

		$pager.appendTo('.page_num').find('span.page-number:first').addClass('active');

});
</script>
</html>
