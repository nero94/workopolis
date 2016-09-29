<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<select name = "candidateId" id="vacancy-select" class="form-control">
<c:forEach var="candidate" items="${candidates}">
  <option value="${candidate.id}" label="${candidate.firstName} ${candidate.lastName}"/>
</c:forEach>
