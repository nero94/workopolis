<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All profiles</title>
    <%@ include file="../../jspf/head.jspf"%>
    	<link href="/resources/manager_profile/sorter_jquery/docs/css/jq.css" rel="stylesheet">
    	<script src="/resources/manager_profile/sorter_jquery/docs/js/jquery-1.2.6.min.js"></script>
    	<link href="/resources/manager_profile/sorter_jquery/css/theme.default.css" rel="stylesheet">
    	<script src="/resources/manager_profile/sorter_jquery/js/jquery.tablesorter.min.js"></script>
    	<script src="/resources/manager_profile/sorter_jquery/js/jquery.tablesorter.widgets.min.js"></script>
      </head>

    <link rel="stylesheet" href="/resources/css/admin_all_profiles.css">

  </head>
  <body>
  <%@ include file="../../jspf/administrator_header.jspf"%>
  <div class="container all_profiles">
  <table align="center" class="paginated table table-striped tabel-responsive">
    <thead>
  <tr id="table_head">
  	
    <th data-type="Contact" width="120">First Name</th>
    <th data-type="Contact" width="120">Middle Name</th>
    <th data-type="Contact" width="120">Last Name</th>
    <th data-type="Contact" width="120">Email</th>
    <th data-type="Contact" width="120">Role</th>
    <th data-type="Contact" width="60">Active</th>

    <th data-type="Contact" width="60">Delete</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${users}" var="user">
      <tr>
      
        <td><c:out value="${user.firstName}"/> </td>
        <td><c:out value="${user.middleName}"/> </td>
        <td><c:out value="${user.lastName}"/> </td>
        <c:if test="${user.role.name == 'ROLE_CANDIDATE'}">
          <td><a href="/candidate/${user.id}"><c:out value="${user.email}"/></a></td>
        </c:if>
        <c:if test="${user.role.name != 'ROLE_CANDIDATE'}">
          <td><c:out value="${user.email}"/> </td>
        </c:if>
        <td><c:out value="${user.role.name}"/> </td>
        <td>
      		<a href="<c:url value='isActive?id=${user.id}' />" > <c:out value="${user.isActive}"/></a>
      	</td>

        <td><a href="<c:url value='/admin/remove?id=${user.id}' />" >Delete</a></td>
      </tr>
  </c:forEach>
</tbody>
  </table>
</div>
<%@ include file="../../jspf/footer.jspf"%>
</body>
<script>
$( document ).ready(function() {
$('table.paginated').each(function() {
    var currentPage = 0;
    var numPerPage = 15;
    var $table = $(this);
    $table.bind('repaginate', function() {
        $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
    });
    $table.trigger('repaginate');
    var numRows = $table.find('tbody tr').length;
    var numPages = Math.ceil(numRows / numPerPage);
    var $pager = $('<div class="pager"></div>');
    for (var page = 0; page < numPages; page++) {
        $('<span class="page-number"></span>').text(page + 1).bind('click', {
            newPage: page
        }, function(event) {
            currentPage = event.data['newPage'];
            $table.trigger('repaginate');
            $(this).addClass('active').siblings().removeClass('active');
        }).appendTo($pager).addClass('clickable');
    }
    $pager.insertBefore($table).find('span.page-number:first').addClass('active');
});

$(function(){
  $('table').tablesorter({
    widgets        : ['columns'],
    usNumberFormat : true,
    sortReset      : true,
    sortRestart    : true
  });
});
});
</script>
</html>
