<head>
<link rel="stylesheet" href="/resources/css/schedule.css" charset="utf-8">

	<%@ include file="../../jspf/head.jspf"%>
  <title>Schedule</title>
</head>
<body>
<%@ include file="../../jspf/recruiter_header.jspf"%>
<div id="for-background-img">


<div id="eventContent" title="Interview Details" class="modal fade">
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


<div class="container">
	<div class="container schedule-container">
			<div id='calendar'></div>
	</div>
</div>
<script>
	$(document).ready(function() {

    // page is now ready, initialize the calendar...

    $('#calendar').fullCalendar({
				titleRangeSeparator: ' - ',
        header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			defaultDate: '2016-09-01 00:00:00.0 ',
			businessHours: true,

			events:
      (${interviews}).interviews,
      eventRender: function (event, element) {
        element.click(function(){
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
        });
      }
    });

});
</script>
</div>
<%@ include file="../../jspf/footer.jspf"%>
</body>
