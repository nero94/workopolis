<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<head>
  <title>Create Offer</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <%@ include file="../../jspf/head.jspf"%>
  <link rel="stylesheet" href="resources/css/create-offer.css" charset="utf-8">
  <link rel="stylesheet" href="resources/css/add-vacancy.css" charset="utf-8">
  <link rel="stylesheet" href="resources/css/interviewer-profile.css" charset="utf-8">
</head>
<body>
  <%@ include file="../../jspf/employer_header.jspf"%>
<div id="add-vac-container" class="container">
    <div class="interv-info well col-lg-12">
      <div id="int-info-head" class="well col-lg-12 row">
        <h3 id="my-int" class="int-name">Create a Job Offer</h3>
      </div>
      <form:form action="/create-offer" method="post" modelAttribute="offer">
        <table class="table table-striped table-responsive">
          <thead>
            <th>Select a Vacancy</th>
            <th>Select a Candidate</th>
            <th>Pick a Meeting Date</th>
          </thead>
          <tbody>
            <td>
              <select name = "vacancyId" id="vacancy-select" class="form-control" onchange = "selectVacancy()">
                  <option value="" label="Select:"/>
                <c:forEach var="vacancy" items="${vacancies}">
                  <option value="${vacancy.id}" label="${vacancy.title}" />
                </c:forEach>
              </select>
            </td>
            <td>
              <div id="candidate">

              </div>
            </td>
            <td>
              <div class="form-group">
                  <div class='input-group date' id='datetimepicker1'>
                      <form:input path="offerDate" class="form-control" />
                      <span class="input-group-addon">
                          <span class="glyphicon glyphicon-calendar"></span>
                      </span>
                  </div>
              </div>
            </td>
          </tbody>
        </table>
        <table class="table table-striped table-responsive">
          <thead>
            <th><h3>Please provide any additional information if needed below</h3></th>
          </thead>
          <tbody>
            <td><form:textarea path="offerComment" type="text" id="comment-lines" class="col-lg-12 col-md-12  col-sm-12 col-xs-12"/></td>
          </tbody>
        </table>
        <input id="submit-result" class="submit-result"  type="submit" value="Send Offer">
      </form:form>
    </div>
</div>
<%@ include file="../../jspf/footer.jspf"%>
</body>
<script src="resources/js/create-offer.js"></script>
