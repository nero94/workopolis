<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>For employer</title>      
    <%@ include file="../jspf/head.jspf"%>
    <link href="resources/css/for_employer_style.css" rel="stylesheet" type="text/css" />
	 <script src="<c:url value='resources/js/jquery.carouFredSel-6.0.4-packed.js' />"/>
	

		<%@ include file="../jspf/head.jspf"%>
		<script type="text/javascript">
			$(function() {

				var $c = $('#carousel'),
					$w = $(window);

				$c.carouFredSel({
					align: false,
					items: 10,
					scroll: {
						items: 1,
						duration: 5000,
						timeoutDuration: 0,
						easing: 'linear',
						pauseOnHover: 'immediate'
					}
				});


				$w.bind('resize.example', function() {
					var nw = $w.width();
					if (nw < 990) {
						nw = 990;
					}

					$c.width(nw * 3);
					$c.parent().width(nw);

				}).trigger('resize.example');

			});
		</script>
		
		<style>
		.icon{
			margin: 6%;
		}
		</style>
</head>

<body>
<%@ include file="../jspf/header.jspf"%>


<div class="row icon" align="center">
<div class="col-sm-4">
    <img src="resources/img/22_03.png" style="width:100px; height:100px;">
    <div>Large base of professionals</div>
</div>
<div class="col-sm-4">
 	<img src="resources/img/3_03.png" style="width:100px; height:100px;">
 	<div>Own search technology</div>
</div>
	<div class="col-sm-4">
		<img src="resources/img/4_03.png" style="width:100px; height:100px;">
		<div>Payment at the end of the trial period</div>
		</div>
	</div>
	<div align="center">
		<input onclick="location.href='/emp_regi'" type="button" class="btn btn-success btn-send btn-lg" value="Get Started - Register today">
	</div>
	<div class="icon">
		<h2 class="page-header join_us" align="center">Join multiple companies that rely on Workopolis!</h2>
		</div>

<div id="wrapper">
<div id="carousel">
	<div>
		<img src="resources/img/logo-amazon.png" width="100" height="45" />
	</div>
	<div>
		<img src="resources/img/logo-android.png" width="100" height="45" />
	</div>
	<div>
		<img src="resources/img/logo-ebay.png" width="100" height="45" />
	</div>
	<div>
		<img src="resources/img/logo-microsoft.png" width="100" height="45" />
	</div>
	<div>
		<img src="resources/img/acerlogo.png" width="100" height="45" />
	</div>
	<div>
		<img src="resources/img/Intel.png" width="100" height="45" />
	</div>
	<div>
		<img src="resources/img/facebook-logo.png" width="100" height="45" />
	</div>
	<div>
		<img src="resources/img/Logo_Google.png" width="100" height="45" />
	</div>
	<div>
		<img src="resources/img/logo-youtube.png" width="100" height="45" />
	</div>
	<div>
		<img src="resources/img/logo-hp.png" width="100" height="45" />
	</div>
	<div>
		<img src="resources/img/samsung-logo.png" width="100" height="45" />
	</div>
	<div>
		<img src="resources/img/Philips_logo_new.png" width="100" height="45" />
	</div>
</div>
</div>
  
    

    <%@ include file="../jspf/footer.jspf"%>
</body>
</html>
