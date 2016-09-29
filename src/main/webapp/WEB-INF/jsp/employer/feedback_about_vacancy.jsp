<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<head>
    <title>Feadback</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <%@ include file="../../jspf/head.jspf"%>
    <link rel="stylesheet" href="/resources/css/add-vacancy.css" charset="utf-8">
    <link rel="stylesheet" href="/resources/css/interviewer-profile.css" charset="utf-8">
</head>
<body>
  <%@ include file="../../jspf/employer_header.jspf"%>
  <form:form action="/feedback_about_vacancy" method="post" modelAttribute="vacancy">
    <table class="table table-striped table-responsive">
      <thead>
        <th><h3>Leave a feedback</h3></th>
      </thead>
      <tbody>
        <td><form:textarea path="feedback" type="text" id="tworows" class="col-lg-12 col-md-12  col-sm-12 col-xs-12" rows="7"/></td>
        </tbody>
        <tbody>
          <td><input id="submit-result"  type="submit" value="Save and Post"></td>
        </tbody>
    </table>
  </form:form>
</body>
<%@ include file="../../jspf/footer.jspf"%>
