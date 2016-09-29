<!DOCTYPE html>
<html>
<head>
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   <%@ include file="../jspf/head.jspf"%>
   <link href="https://s3-us-west-2.amazonaws.com/s.cdpn.io/123941/animate.min.css">
</head>
<body id="main-body" >
  <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
  <%@ include file="../jspf/header.jspf"%>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_CANDIDATE')">
  <%@ include file="../jspf/candidate_header.jspf"%>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_EMPLOYER')">
  <%@ include file="../jspf/employer_header.jspf"%>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_RECRUITER')">
  <%@ include file="../jspf/recruiter_header.jspf"%>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_MANAGER')">
  <%@ include file="../jspf/manager_header.jspf"%>
  </sec:authorize>
      <div id="dinamic" class="row">
         <div class="back-home col-lg-12">
  <div class="container">
	<div class="start-home">
	<div class="col-lg-12">
      <p class="mainSign_1 text-center ">LET'S FIND YOUR DREAM JOB</p>
   </div>
   <div class="col-lg-4 col-lg-offset-4 serch_form">
   <form action="/vacancies" method="GET">
      <div>
         <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <input type="search" class="form-control" id="inputsearch" name="keyword" placeholder="Job title,keywords" >
         </div>
         <div class="form-group col-lg-4 col-md-4 col-sm-4 col-xs-4">
            <input type="search" class="form-control" name="country" id="inputsearch" placeholder="Country" >
         </div>
         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
            <button id="main-find-btn" type="submit" class="btn btn-success">Find</button>
         </div>
      </div>
   </form>
   </div>
   </div>
  </div>
   <div class="start-employer">

   		<div id="employer-panel" >
     		<h2><a href="/for-employer"><p id="emp-question">Are you an Employer?</p></a></h2>
     	</div>

   		<header id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for Slides -->
        <div class="carousel-inner">
            <div class="item active">
                <div class="image-home">
                	<img class="fill" src="/home/business.jpg">
                	<h2 class="emp-home-text" >Manage your business.<br/> Leave the recruitment to US!</h2>
                </div>
            </div>
            <div class="item">
                <div class="image-home">
                	<img class="fill" src="/home/recruitment.jpg">
                	<h2 class="emp-home-text" >Get our recruitmet<br/> Solution!</h2>
                </div>
            </div>
            <div class="item">
                <div class="image-home">
                	<img class="fill" src="/home/publish.jpg">
                	<h2 class="emp-home-text" >Create and publish<br/>vacancies!</h2>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>

    </header>
   </div>

   <div class="container exp-advice">
	   <div class="mainSign_2 text-center ">
	     <p>Get expert advice 24/7</p>
	   </div>
	   <div class="col-lg-2 col-lg-offset-5 col-md-3 col-md-offset-5 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4 contact_button">
	      <button type="button" class="btn btn-success col-lg-12 col-md-12 col-sm-12 col-xs-12" onclick="location.href='/contact_form'">Contact us</button>
	   </div>
    </div>

	<div id="feedback-panel" >
     <h2><p id="emp-question">What people say about Workopolis?</p></h2>
    </div>
<!-- Second Carousel -->
        <div id="secondCarousel" class="row">
            <div class="span12">
                    <div id="secondCarousel" class="carousel fdi-Carousel slide">
                     <!-- Carousel items -->
                        <div class="carousel fdi-Carousel slide" id="eventCarousel" data-interval="0">
                            <div class="carousel-inner  onebyone-carosel">
                                <div class="item active">
                                    <div class="col-lg-3 col-md-4 col-lg-offset-1">
                                        <div class="image-home col-lg-3 col-md-4">
                							<img class=" img-responsive center-block" src="/home/person1.jpg">
                							<div class="carousel-caption container">
                							<h4 class="">Found my dream job!</h4>
                							</div>
               							 </div>

                                    </div>
                                </div>
                                <div class="item">
                                    <div class="col-lg-3 col-md-4 col-lg-offset-1">
                                        <div class="image-home col-lg-3 col-md-4 ">
                							<img class="img-responsive center-block" src="/home/person2.jpg">
                							<div class="carousel-caption container">
                							<h4 class="" >Great Service!</h4>
                							</div>
               							 </div>

                                    </div>
                                </div>
                                <div class="item">
                                    <div class="col-lg-3 col-md-4 col-lg-offset-1">
                                        <div class="image-home col-lg-3 col-md-4 ">
                							<img class="img-responsive center-block" src="/home/person8.jpg">
                							<div class="carousel-caption container">
                							<h4 class="" >So easy to use!</h4>
                							</div>
               							 </div>

                                    </div>
                                </div>
                                <div class="item">
                                    <div class="col-lg-3 col-md-4 col-lg-offset-1">
                                        <div class="image-home col-lg-3 col-md-4 ">
                							<img class="img-responsive center-block" src="/home/person3.jpg">
                							<div class="carousel-caption container">
                							<h4 class="" >Going to work tomorrow!</h4>
                							</div>
               							 </div>

                                    </div>
                                </div>
                                <div class="item">
                                    <div class="col-lg-3 col-md-4 col-lg-offset-1">
                                        <div class="image-home col-lg-3 col-md-4 ">
                							<img class="img-responsive center-block" src="/home/person7.jpg">
                							<div class="carousel-caption container">
                							<h4 class="" >I recommend Workopolis!</h4>
                							</div>
               							 </div>

                                    </div>
                                </div>
                                <div class="item">
                                    <div class="col-lg-3 col-md-4 col-lg-offset-1">
                                        <div class="image-home col-lg-3 col-md-4 ">
                							<img class="img-responsive center-block" src="/home/person5.png">
                							<div class="carousel-caption ">
                							<h4 class="" >Nice design!</h4>
                							</div>
               							 </div>

                                    </div>
                                </div>
                            </div>
                            <!-- <a class="left carousel-control" href="#eventCarousel" data-slide="prev"></a>
                            <a class="right carousel-control" href="#eventCarousel" data-slide="next"></a> -->
                        </div>
                        <!--/carousel-inner-->
                    </div><!--/myCarousel-->
            </div>
        </div>

<script>
$(document).ready(function () {
    $('#secondCarousel').carousel({
        interval: 10000
    })
    $('.fdi-Carousel .item').each(function () {
        var next = $(this).next();
        if (!next.length) {
            next = $(this).siblings(':first');
        }
        next.children(':first-child').clone().appendTo($(this));

        if (next.next().length > 0) {
            next.next().children(':first-child').clone().appendTo($(this));
        }
        else {
            $(this).siblings(':first').children(':first-child').clone().appendTo($(this));
        }
    });
});
</script>



<script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
</script>



</div>

      </div>
<%@ include file="../jspf/footer.jspf"%>
</body>

</html>
