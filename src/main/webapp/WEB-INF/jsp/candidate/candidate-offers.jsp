<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<head>
  <title>My Offers</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <%@ include file="../../jspf/head.jspf"%>
  <link rel="stylesheet" href="/resources/css/create-offer.css" charset="utf-8">
  <link rel="stylesheet" href="/resources/css/add-vacancy.css" charset="utf-8">
  <link rel="stylesheet" href="/resources/css/interviewer-profile.css" charset="utf-8">
</head>
<body>
<%@ include file="../../jspf/candidate_header.jspf"%>
<div id="add-vac-container" class="container">
  <c:if test="${empty offers}">
    <h3 class="emty-page">You don't have offers.</h3>
  </c:if>
	<c:forEach items="${offers}" var="offer">
		<div class="interv-info well col-lg-12">
		<div id="int-info-head" class="well col-lg-12 row offer-title-well">
        	<h3 id="my-int" class="int-name">Job Offer</h3>
        </div>
        <table class="table table-striped table-responsive">
         <thead>
            <th><h4>Vacancy</h4></th>
            <th><h4>Candidate</h4></th>
            <th><h4>Offer Date</h4></th>
         </thead>
         <tbody id="offers-tbody">
         	<td><span>${offer.vacancy.title}</span></td>
         	<td><a href="/vacancy/${offer.vacancy.id}"><span>${offer.candidate.firstName} ${offer.candidate.lastName}. Email: <span class="cand-email">${offer.candidate.email}</span></span></a></td>
         	<td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${offer.offerDate}" /></td>
         </tbody>
        </table>
        <table class="table table-striped table-responsive">
         <thead>
        	<th><h3>Offer Comment</h3></th>
         </thead>
         <tbody>
         	<td><span>${offer.offerComment}</span></td>
         </tbody>
        </table>
    	<div class="well well-emp-offers col-lg-6 col-lg-offset-3 ">
    		<div class="glyphicon glyphicon-info-sign col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2">
    			<span>Status: </span>
    			<option id="dispayed-offer" style="display:inline" class=" dispayed-offer offer-state-option" value="${offer.state}" label="${offer.state}"></option>

	    		<select hidden id="offer-state" class="offer-state form control" >
	    			<option class="offer-state-option" value="ACCEPTED_BY_CANDIDATE" label="Accepted by Candidate"/>
	    			<option class="offer-state-option" value="REJECTED_BY_CANDIDATE" label="Rejected by Candidate"/>

	    		</select>
    		</div>
    	</div>
    	<input id="hidden-offer-id" hidden value="${offer.id}"/>

    	<input id="candidate-reject" type="button" onclick="RejectOffer(this)" value="Reject" class="btn btn-danger col-lg-2 col-lg-offset-5 col-md-offset-5 col-sm-offset-5  col-xs-offset-4 make-state-not-disabled"/>
    	<input id="candidate-accept" type="button" onclick="RespondToOffer(this)" value="Accept" class="btn btn-success col-lg-2  col-lg-offset-5 col-md-offset-5 col-sm-offset-5  col-xs-offset-4 submit-offer-state"/>
	</div>
	</c:forEach>
</div>
<%@ include file="../../jspf/footer.jspf"%>
</body>
<script src="/resources/js/offer-status.js"></script>
