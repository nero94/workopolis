<head>
<%@ include file="../../jspf/head.jspf"%>

    <link rel="stylesheet" href="../resources/manager_profile/css/manager-filter-bar.css">
    <script type="text/javascript" src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
  <title>Manager profile </title>
</head>
<body>
<%@ include file="../../jspf/manager_header.jspf"%>


<div class="manager_profile">
<div class="container">
      <div class="col-lg-8 col-lg-offset-2">
         <div class="panel panel-default">
            <div class="panel-body">

                     <h2 class="col-lg-offset-2 ">${manager.firstName} ${manager.middleName} ${manager.lastName}</h2>
                     <div class="col-md-12">
                        <table class="table table-user-information recruiter-info" >
                           <tbody>
                              <tr>
                                 <td>Position:</td>
                                 <td>Manager</td>
                              </tr>
                              <tr>
                                 <td>Skype</td>
                                 <td>${manager.skypeUrl}</td>
                              </tr>
                              <tr>
                                 <td>Email</td>
                                 <td><a href="mailto:info@support.com">${manager.email}</a></td>
                              </tr>
                              <tr>
                              <td>Phone Number:</td>
                              <td>${manager.telNumber}<br>
                              </td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                    <div class="col-md-offset-4">
              <input onclick="location.href='/manager/edit_profile'" type="button" name="name" value="Edit" class="btn btn-primary col-md-2 ">
             </div>
            </div>
         </div>
      </div>
    </div>
    <div class="container">
    <div id="chartContainer" style="height: 500px; width: 100%;"></div>
    </div>
</div>
<%@ include file="../../jspf/footer.jspf"%>
</body>
<script type="text/javascript">
  window.onload = function () {
    console.log(${vacancies});
  // var chartData = JSON.parse(${vacancies});
  var chart = new CanvasJS.Chart("chartContainer", {
    theme: "theme2",//theme1
    title:{
      text: "Vacancies by Directions"              
    },
    animationEnabled: false,   // change to true
    data: [              
    {
      // Change type to "bar", "area", "spline", "pie",etc.
      type: "column",
      dataPoints: 
      ${vacancies}
        // chartData
    }
    ]
  });
  chart.render();
}
</script>
<!-- <script src="/resources/js/admin_statistic.js"></script> -->
