<!DOCTYPE html>
<html>
  <head>
    <title>Employer list</title>


    	<link rel="stylesheet" href="/resources/manager_profile/css/employer_list_filter_bar.css">
      	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>

        <%@ include file="../../jspf/head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!----------------------------------------------------------------------------->
	<link href="/resources/manager_profile/sorter_jquery/docs/css/jq.css" rel="stylesheet">

	<!-- jQuery: required (tablesorter works with jQuery 1.2.3+) -->
	<script src="/resources/manager_profile/sorter_jquery/docs/js/jquery-1.2.6.min.js"></script>

	<!-- Pick a theme, load the plugin & initialize plugin -->
	<link href="/resources/manager_profile/sorter_jquery/css/theme.default.css" rel="stylesheet">
	<script src="/resources/manager_profile/sorter_jquery/js/jquery.tablesorter.min.js"></script>
	<script src="/resources/manager_profile/sorter_jquery/js/jquery.tablesorter.widgets.min.js"></script>
  <!----------------------------------------------------------------------------->
  </head>

  <body>

    <%@ include file="../../jspf/manager_header.jspf"%>

    <div class="employer_list">
      <div class="container">

    <table id="employer_list" class="paginated table table-striped tabel-responsive">
      <thead>
  <tr id="table_head">
    <th data-type="Contact">Contact</th>
    <th data-type="Company">Company</th>
    <th>Country</th>
  </tr>
</thead>
  <tbody>
  <c:forEach items="${allEmployers}" var="employer">
  <tr>
    <td><a href="/manager/employer_profile?id=${employer.id}">${employer.firstName} ${employer.middleName} ${employer.lastName}</a></td>
    <td>${employer.company.name}</td>
    <td>${employer.company.address.country.name}</td>
  </tr>
  </c:forEach>
</tbody>
</table>
</div>
</div>
  </body>
  <script>
  $( document ).ready(function() {
  $('table.paginated').each(function() {
      var currentPage = 0;
      var numPerPage = 20;
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
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
