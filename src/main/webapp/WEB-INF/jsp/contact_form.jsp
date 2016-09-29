<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   <title>Contact Form</title>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="stylesheet" href="/resources/contact-form/css/contact-form.css" type="text/css">
   <%@ include file="../jspf/head.jspf"%>
   
   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
   <link rel="stylesheet" href="resources/css/popup.css">
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
<div class="container" id="contact-form">
   <div class="row">
      <div class="col-lg-8 col-lg-offset-2">
         <h1>Contact us</h1>
         <form id="contact-form" method="post" action="/send_contact_form" >

            <div class="controls">

                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="form_name">Firstname *</label>
                        <input id="form_name" type="text" name="name" class="form-control" placeholder="Please enter your firstname *" required="required" data-error="Firstname is required.">
                        <div class="help-block with-errors"></div>
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="form_lastname">Lastname *</label>
                        <input id="form_lastname" type="text" name="surname" class="form-control" placeholder="Please enter your lastname *" required="required" data-error="Lastname is required.">
                        <div class="help-block with-errors"></div>
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="form_email">Email *</label>
                        <input id="form_email" type="email" name="email" class="form-control" placeholder="Please enter your email *" required="required" data-error="Valid email is required.">
                        <div class="help-block with-errors"></div>
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="form_phone">Phone</label>
                        <input id="form_phone" type="tel" name="phone" class="form-control" placeholder="Please enter your phone">
                        <div class="help-block with-errors"></div>
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-12">
                     <div class="form-group">
                        <label for="form_message">Message *</label>
                        <textarea id="form_message" name="message" class="form-control" placeholder="Message for me *" rows="4" required="required" data-error="Please,leave us a message."></textarea>
                        <div class="help-block with-errors"></div>
                     </div>
                  </div>
                  <div class="col-md-12">
                     <input type="submit" class="btn btn-success btn-send" value="Send message">
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-12">
                     <p class="text-muted"><strong>*</strong> These fields are required.</p>
                  </div>
               </div>
            </div>
         </form>
      </div>
      <!-- /.8 -->
   </div>
   <!-- /.row-->

</div>
<c:if test="${not empty msg}">
<div id="dialog" title="${msg}">
	${msg}
</div>
</c:if>
<%@ include file="../jspf/footer.jspf" %>
</body>
</html>
