<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>About Us</title>
    <%@ include file="../jspf/head.jspf"%>

</head>

<body>
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
    <!-- Navigation -->
    <!-- Page Content -->
    <div class="container">

        <!-- Introduction Row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">About Us
                    <small>It's Nice to Meet You!</small>
                </h1>
                <p>Not all recruitment companies are created equal; let us show you what makes us a cut above.

Here at Workopolis we simplify recruitment. We reward and recognise success. We are a recruitment company with an award-winning CSR initiative and Best Companies 2-star status which represents outstanding levels of staff engagement as voted for by our people.</p>
            </div>
        </div>

        <!-- Team Members Row -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Our Team</h2>
            </div>
            <div class="col-lg-4 col-sm-6 text-center">
                <img class="img-circle img-responsive img-center" src="http://i50.photobucket.com/albums/f303/Maxpower111999/Shpak_zpsh7dshf8y.jpg" alt="">
                <h3>Max Shpak
                    <small>Manager</small>
                </h3>
                <p>Has more than 10 years managing recruitment businesses all around the world. Has brought Workopolis to lead position in recent time!</p>
            </div>
            <div class="col-lg-4 col-sm-6 text-center">
                <img class="img-circle img-responsive img-center" src="http://i50.photobucket.com/albums/f303/Maxpower111999/Dzundza_zpsetr2o1bp.jpg" alt="">
                <h3>Anastasia Dzundza
                    <small>Recruiter</small>
                </h3>
                <p>Great Professional! Leads the recruitment department of Workopolis company. Has more than 8 years of recruitment in different industries.</p>
            </div>
            <div class="col-lg-4 col-sm-6 text-center">
                <img class="img-circle img-responsive img-center" src="http://i50.photobucket.com/albums/f303/Maxpower111999/Kozlov_zpskk01it8w.jpg" alt="">
                <h3>Slavik Kozlov
                    <small>Administrator</small>
                </h3>
                <p>Slavik is a computer Genius! Runs all Workopolis website processes, performs checks and validation. Great Web Developer!</p>
            </div>
            <div class="col-lg-4 col-sm-6 text-center">
                <img class="img-circle img-responsive img-center" src="http://i50.photobucket.com/albums/f303/Maxpower111999/Dmytriv_zpsaubthsrc.jpg" alt="">
                <h3>Vasyl Dmytriv
                    <small>Manager</small>
                </h3>
                <p>Manages all relations with foreign customers. Knows 6 foreign languages. Graduated from well known Lviv Politechnic University. Knows how to handle business efficiently!</p>
            </div>
            <div class="col-lg-8 col-sm-12 text-center">
                <img class="img-responsive img-center" src="http://i50.photobucket.com/albums/f303/Maxpower111999/employees_zpsfsk9pvfj.jpg" alt="">
                <h3>Workopolis
                    <small>Team</small>
                </h3>
                <p>This is our Workopolis Family! They make everything to satisfy our customers and bring the best results at the right time!</p>
            </div>
        </div>

        <hr>

        <!-- Footer -->
    </div>
    <!-- /.container -->

    <!-- jQuery -->

    <%@ include file="../jspf/footer.jspf"%>
</body>

</html>
