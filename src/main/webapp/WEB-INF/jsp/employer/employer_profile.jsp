<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
   <title>Employer profile</title>
   <%@ include file="../../jspf/head.jspf"%>
   <link rel="stylesheet" href="/resources/css/style_employer.css" charset="utf-8">
   <link rel="stylesheet" href="/resources/css/filter-bar.css" charset="utf-8">
   <link rel="stylesheet" href="/resources/css/popup.css">
</head>
<body>
<div id="employer_profile">
  <sec:authorize access="hasRole('ROLE_EMPLOYER')">
  <%@ include file="../../jspf/employer_header.jspf"%>
  </sec:authorize>
<sec:authorize access="hasRole('ROLE_MANAGER')">
<%@ include file="../../jspf/manager_header.jspf"%>
</sec:authorize>
<div class="container">
   <div class="">
      <div class="col-lg-8 col-lg-offset-2">
         <div class="panel panel-default">
            <div class="panel-body">
                  <div >
                     <h2>Company name: <span id="comp_name">${employer.company.name}</span></h2>
                     <div class="col-md-8">
                        <table class="table table-user-information recruiter-info" >
                           <tbody>
                              <tr>
                                 <td><label for="">Contact person:</label></td>
                                 <td><input type="text" name="name" value="${employer.firstName} ${employer.lastName}" readonly></td>
                              </tr>
                              <tr>
                              <tr>
                                 <td><label for="">Home Address:</label></td>
                                 <td><input type="text" name="name" value="${employer.company.address}" readonly></td>
                              </tr>
                              <tr>
                                 <td><label for="">Email:</label></td>
                                 <td><input type="text" name="name" value="${employer.email}" readonly></td>
                              </tr>
                              <td><label for="">Phone Number:</label></td>
                              <td><input type="text" name="name" value="123-4567-890" readonly></td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                     <div class="col-md-4">
         			<c:if test="${empty employer.company.imgUrl}">
         				<img src="/resources/employer/img/default-company-logo.jpg" class="col-lg-12">
    				</c:if>
    				<c:if test="${not empty employer.company.imgUrl != ''}">
    					<img src="/resources/employer/img/${employer.company.imgUrl}" alt="logo" class="col-md-12">
    				</c:if>


                       </div>
                  </div>

                  <sec:authorize access="hasRole('ROLE_EMPLOYER')">
                  <input type="button" name="name" value="Edit" class="btn btn-primary col-md-2 col-md-offset-4 col-lg-2 col-lg-offset-4 col-sm-offset-4 col-xs-offset-4" onclick="location.href='/employer/edit_profile'">
                  </sec:authorize>

            </div>
         </div>
      </div>
   </div>
</div>
<div class="container">
   <hgroup class="mb20">
      <h1>My recent vacancies</h1>
   </hgroup>

<div class="paginated">

<c:forEach items="${employer.createdVacancies}" var="vacancy">
  <c:if test="${vacancy.feedback == null}">
   <article class="search-result row">
   <div class="col-lg-3 col-xs-12 col-sm-12 col-md-3">
      <a href="/vacancy/${vacancy.id}" title="Logo" class="thumbnail"><img src="/resources/employer/img/${employer.company.imgUrl}" alt="CompanyLogo" /></a>
   </div>

   <div id="vac-text" class="col-lg-6 col-xs-12 col-sm-12 col-md-7 excerpet">
      <h3><a href="/vacancy/${vacancy.id}" title="">${vacancy.title}</a></h3>

      <c:if test="${fn:length(vacancy.description) > 300}">
        <p> <c:out value="${fn:substring(vacancy.description, 0, 300)}"/>...</p>
      </c:if>
      <c:if test="${fn:length(vacancy.description) <= 300}">
        <p> <c:out value="${vacancy.description}"/></p>
      </c:if>
      <br>
   </div>
   <div id="glyphicons" class="col-lg-3 col-xs-12 col-sm-12 col-md-5">
      <ul class="meta-search">
         <li><i class="glyphicon glyphicon-time"></i> <span>${vacancy.postDate}</span></li>
         <li><i class="glyphicon glyphicon-usd"></i> <span>${vacancy.salary}</span></li>
         <li><i class="glyphicon glyphicon-home"></i> <span>${vacancy.company.address}</span></li>
         <li><i class="glyphicon glyphicon-cog"></i> <span>${vacancy.state}</span></li>
         <li>

           <div class="manage-buttons">
             <sec:authorize access="hasRole('ROLE_EMPLOYER')">
             <div class="row">
                 <input type="button" onclick="location.href='/edit_vacancy?id=${vacancy.id}'" value="Edit" class="btn btn-primary">
                   <input onclick="location.href='/employer/delete_vacancy?id=${vacancy.id}'" type="button" name="name" value="Delete" class="btn btn-danger ">
                     <c:if test="${vacancy.state != 'CLOSED'}">
                     <input onclick="location.href='/close_vacancy?id=${vacancy.id}'" type="button" name="name" value="Close" class="btn btn-primary ">
                      </c:if>
                     <c:if test="${vacancy.state == 'CLOSED'}">
                      <input onclick="location.href='/feedback_about_vacancy?id=${vacancy.id}'" type="button" name="name" value="Leave Feedback" class="btn btn-primary ">
                        </c:if>
                 </div>
                  </sec:authorize>

                 <sec:authorize access="hasRole('ROLE_MANAGER')">
                   <div class="row">
                       <input onclick="location.href='/deactivate_vacancy?id=${vacancy.id}'" type="button" name="name" value="Deactivate" class="btn btn-primary ">
                         </div>
                   </sec:authorize>


           </div>
         </li>
      </ul>
    </div>
   <span class="clearfix border"></span>
</article>
</c:if>
</c:forEach>

</div>
</div>
</div>



</body>
<%@ include file="../../jspf/footer.jspf"%>
<script>


$( document ).ready(function() {
  function edit(){
  $("#employer_profile input").attr("readonly", null);
  $("#change-logo").css("display", "block");
  }

  function save(){
  $("#employer_profile input").attr("readonly", "");
  $("#change-logo").css("display", "none");
  }

  /////////////////////////////////////////////////////////

  $('.paginated','article').each(function(i) {
      $(this).text(i+1);
  });



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

      $pager.appendTo("h1").find('span.page-number:first').addClass('active');

  });
});
</script>

</html>
