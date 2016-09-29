<head>
<link rel="stylesheet" href="/resources/css/schedule.css" charset="utf-8">

  <%@ include file="../../jspf/head.jspf"%>
  <title>Schedule</title>
</head>
<body>
<%@ include file="../../jspf/candidate_header.jspf"%>
<div id="for-background-img" class="for-background-img">



            <!-- For Interview Modal -->
  <div id="eventContent" title="Interview Details" class="modal fade interview-modal-div">
      <div class="modal-dialog">
        <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
                <span aria-hidden="true">X</span>
            </button>
            <span id="interview-type-modal"></span>
            <h4 id="vacancyTitleModal" class="modal-title"></h4>
           </div>

          <p class="interview-modal-dialog">Date: <span id="dateTime" class="modal-title"></span></p>

          <p class="interview-modal-dialog">Candidate:
                  <span id="candidateFirstName"></span>
                  <span> </span>
                  <span id="candidateLastName"></span>
                  <span> </span>
                  <span id="candidateEmail"></span>
                  <span> </span>
                  <span id="candidateTel"></span>

          </p>
          <p class="interview-modal-dialog">Interviewer:
                    <span id="interviewerFirstName"></span>
                    <span> </span>
                    <span id="interviewerLastName"></span>
                    <span> </span>
                    <span id="interviewerEmail"></span>
                    <span> </span>
                    <span id="interviewerTel"></span>
          </p>
          <p class="interview-modal-dialog">Interview Type:
                  <span id="interviewType"></span>
          </p>
          <p class="interview-modal-dialog">Communication Type:
                  <span id="communicationType"></span>
          </p>

        </div>
      </div>
  </div>
            <!-- For Offer Modal -->
  <div id="eventContent" title="Offer Details" class="modal fade offer-modal-div">
      <div class="modal-dialog">
        <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
                <span aria-hidden="true">X</span>
            </button>
            <span id="offer-type-modal"></span>
            <h4 id="vacancyTitleModal" class="modal-title"></h4>
           </div>

          <p class="interview-modal-dialog">Date: <span id="dateTime" class="modal-title"></span></p>

          <p class="interview-modal-dialog">Candidate:
                  <span id="candidateFirstName"></span>
                  <span> </span>
                  <span id="candidateLastName"></span>
                  <span> </span>
                  <span id="candidateEmail"></span>
                  <span> </span>
                  <span id="candidateTel"></span>

          </p>

          <p class="interview-modal-dialog">Offer Status:
                  <span id="offerStatus"></span>
          </p>
          <p class="interview-modal-dialog">Comment:
                  <span id="offerComment"></span>
          </p>

        </div>
      </div>
  </div>

<div class="container">
  <div class="col-lg-12">
    <h3 class="col-lg-5 col-lg-offset-1 col-md-5 col-md-offset-1 col-sm-5 col-sm-offset-1 col-xs-5 col-lxs-offset-1"><span class="col-lg-5 col-md-5 col-sm-5 col-xs-5" id="interview-example">Interview - </span><div class="interv-exampl well col-lg-5  col-md-5 col-sm-5 col-xs-5"></div></h3>
    <h3 class="col-lg-5 col-md-5 col-sm-5 col-xs-5"><span class="col-lg-4 col-md-4 col-sm-4 col-xs-4" id="offer-example">Offer - </span><div class="offer-exampl well col-lg-5 col-md-5 col-sm-5 col-xs-5"></h3>
  </div>
</div>
<div class="container">
  <div class="container schedule-container">
      <div id='calendar'></div>
  </div>
</div>
<script>
  var d = new Date(); var t = d.getDay();
  $(document).ready(function() {
console.log($.merge( (${interviews}).interviews, (${offers}).offers))
    // page is now ready, initialize the calendar...

    $('#calendar').fullCalendar({
        titleRangeSeparator: ' - ',
        header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay'
      },
      firstDay: t,

      businessHours: true,

      events: $.merge( (${interviews}).interviews, (${offers}).offers),



      eventRender: function (event, element) {
        element.click(function(){

          if (event.color == "#27a0c9") {
            console.log("Start Interview");
            $(".offer-modal-div").remove();
          $("#dateTime").html(moment(event.date).format('MMM Do h:mm A'));
          $("#interview-type-modal").html(event.interview);
          $('#vacancyTitleModal').html(event.title);
          $('#candidateFirstName').html(event.candidateFirstName);
          $('#candidateLastName').html(event.candidateLastName);
          $('#candidateEmail').html(event.candidateEmail);
          $('#candidateTel').html(event.candidateTel);
          $('#interviewerFirstName').html(event.interviewerFirstName);
          $('#interviewerLastName').html(event.interviewerLastName);
          $('#interviewerEmail').html(event.interviewerEmail);
          $('#interviewerTel').html(event.interviewerTel);
          $('#interviewType').html(event.interviewType);
          $('#communicationType').html(event.communicationType);
          $('#modal-content').modal();
          $('#eventContent').modal();
            console.log("Finish Interview");

            $(".for-background-img").append('<div id="eventContent" title="Offer Details" class="modal fade offer-modal-div">' +
      '<div class="modal-dialog">'+
       '<div class="modal-content">'+
         '<div class="modal-header">'+
            '<button type="button" class="close" data-dismiss="modal">'+
                '<span aria-hidden="true">X</span>'+
            '</button>'+
            '<span id="offer-type-modal"></span>'+
            '<h4 id="vacancyTitleModal" class="modal-title"></h4>'+
           '</div>'+

          '<p class="interview-modal-dialog">Date: <span id="dateTime" class="modal-title"></span></p>'+

          '<p class="interview-modal-dialog">Candidate:'+
                  '<span id="candidateFirstName"></span>'+
                  '<span> </span>'+
                  '<span id="candidateLastName"></span>'+
                  '<span> </span>'+
                  '<span id="candidateEmail"></span>'+
                  '<span> </span>'+
                  '<span id="candidateTel"></span>'+

          '</p>'+

          '<p class="interview-modal-dialog">Offer Status:'+
                  '<span id="offerStatus"></span>'+
          '</p>'+
          '<p class="interview-modal-dialog">Comment:'+
                  '<span id="offerComment"></span>'+
          '</p>'+

        '</div>'+
      '</div>'+
  '</div>');
            console.log("Append finished");
      } else {
            console.log("Start Offer");
             $(".interview-modal-div").remove();
          $('#vacancyTitleModal').html(event.title);
          $('#vacancyTitleModal').html(event.title);
          $('#candidateFirstName').html(event.candidateFirstName);
          $('#candidateLastName').html(event.candidateLastName);
          $('#candidateEmail').html(event.candidateEmail);
          $('#candidateTel').html(event.candateTel);
          $("#dateTime").html(moment(event.date).format('MMM Do h:mm A'));
          $('#offer-type-modal').html(event.offer);
          $('#offerStatus').html(event.offerState);
          $('#offerComment').html(event.offerComment);
          $('#eventContent').modal();
          $('#modal-content').modal();
            console.log("Finish Offer");

            $(".for-background-img").append('<div id="eventContent" title="Interview Details" class="modal fade interview-modal-div">'+
      '<div class="modal-dialog">'+
        '<div class="modal-content">'+
         '<div class="modal-header">'+
            '<button type="button" class="close" data-dismiss="modal">'+
                '<span aria-hidden="true">X</span>'+
            '</button>'+
            '<span id="interview-type-modal"></span>'+
            '<h4 id="vacancyTitleModal" class="modal-title"></h4>'+
           '</div>'+

          '<p class="interview-modal-dialog">Date: <span id="dateTime" class="modal-title"></span></p>'+

          '<p class="interview-modal-dialog">Candidate:'+
                  '<span id="candidateFirstName"></span>'+
                  '<span> </span>'+
                  '<span id="candidateLastName"></span>'+
                  '<span> </span>'+
                  '<span id="candidateEmail"></span>'+
                  '<span> </span>'+
                  '<span id="candidateTel"></span>'+

          '</p>'+
          '<p class="interview-modal-dialog">Interviewer:'+
                    '<span id="interviewerFirstName"></span>'+
                    '<span> </span>'+
                    '<span id="interviewerLastName"></span>'+
                    '<span> </span>'+
                    '<span id="interviewerEmail"></span>'+
                    '<span> </span>'+
                    '<span id="interviewerTel"></span>'+
          '</p>'+
          '<p class="interview-modal-dialog">Interview Type:'+
                  '<span id="interviewType"></span>'+
          '</p>'+
          '<p class="interview-modal-dialog">Communication Type:'+
                  '<span id="communicationType"></span>'+
          '</p>'+

        '</div>'+
      '</div>'+
  '</div>');
            console.log("Append finished");
            }
        });
      }

    });

});
</script>
</div>
<%@ include file="../../jspf/footer.jspf"%>
</body>
