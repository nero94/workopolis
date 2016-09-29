<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/error.css">

   <%@ include file="../../jspf/head.jspf"%>
   <title>Happened some error</title>
   
</head>

<body>
<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
  <%@ include file="../../jspf/header.jspf"%>
 </sec:authorize>
 <sec:authorize access="hasRole('ROLE_CANDIDATE')">
  <%@ include file="../../jspf/candidate_header.jspf"%>
 </sec:authorize>
 <sec:authorize access="hasRole('ROLE_EMPLOYER')">
  <%@ include file="../../jspf/employer_header.jspf"%>
 </sec:authorize>
 <sec:authorize access="hasRole('ROLE_RECRUITER')">
  <%@ include file="../../jspf/recruiter_header.jspf"%>
 </sec:authorize>
 <sec:authorize access="hasRole('ROLE_MANAGER')">
  <%@ include file="../../jspf/manager_header.jspf"%>
 </sec:authorize>
 <sec:authorize access="hasRole('ROLE_INTERVIEWER')">
  <%@ include file="../../jspf/interviewer_header.jspf"%>
 </sec:authorize>
 <sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
  <%@ include file="../../jspf/administrator_header.jspf"%>
 </sec:authorize>

<div class="container error-window">
            
</div>




 <%@ include file="../../jspf/footer.jspf"%>

</body>

</html>
