<head>
    <title>Administrator Statistic</title>
    <%@ include file="../../jspf/head.jspf"%>
    <link rel="stylesheet" type="text/css" href="/resources/css/style_administrator.css">
    <link rel="stylesheet" href="/resources/recruiter/filter-bar.css">
    <link rel="stylesheet" href="/resources/candidate/css/Candidate's_profile.css">
    <script type="text/javascript" src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
  </head>
  <body>
  <%@ include file="../../jspf/administrator_header.jspf"%>
    <div class="interviewer_profile">
      <div class="container">
        <div class="col-lg-8 col-lg-offset-2">
         <div class="panel panel-default">
            <div class="panel-body">
                     <h2 style="text-align:center;" class="col-lg-8 col-lg-offset-2">${administrator.firstName} ${administrator.middleName} ${administrator.lastName}</h2>
                     <div class="col-md-12">
                        <table class="table table-user-information recruiter-info" >
                           <tbody>
                              <tr>
                                 <td>Email</td>
                                 <td>${administrator.email}</td>
                              </tr>

                           </tbody>
                        </table>
                     </div>
            </div>
         </div>
        </div>
      </div>
    </div>
    <div id="chartContainer" style="height: 300px; width: 100%;"></div>

  <%@ include file="../../jspf/footer.jspf"%>
</body>
<script type="text/javascript">
window.onload = function () {
  console.log(${candidates});
  console.log(${employers});
  var chart = new CanvasJS.Chart("chartContainer",
  {
    animationEnabled: true,
    animationDuration: 6000,
    title:{
      text: "Registered Users in 2016"
    },
    axisX:{
       title: "Months"
      },
      axisY:{
       title: "Number of Users"
      },
    data: [
    {

      type: "spline", //change type to bar, line, area, pie, etc
      showInLegend: true,
      name: "Candidates",
      dataPoints:
        ${candidates}
      },
      {

      type: "spline",
      showInLegend: true,
      name: "Employers",
      dataPoints:
        ${employers}
      }
    ],
    legend: {
      cursor: "pointer",
      itemclick: function (e) {
        if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
          e.dataSeries.visible = false;
        } else {
          e.dataSeries.visible = true;
      }
      chart.render();
      }
    }
  });

  chart.render();
}
</script>
