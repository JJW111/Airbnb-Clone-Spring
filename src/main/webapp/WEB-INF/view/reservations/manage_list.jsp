<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../inc/common.jsp" %>
<title>Manage Reservations</title>
</head>

<%@include file="../inc/top.jsp" %>

<sec:authorize access="isAuthenticated()">

<sec:authentication var="principal" property="principal" />

<div class="container mx-auto my-10 flex flex-col min-h-75vh">
	<c:if test="${empty reservations or reservations.isEmpty()}">
		<div class="py-8 w-full text-center">
			<span class="font-medium text-2xl">No Reservations</span>
		</div>
	</c:if>
	<c:if test="${not empty reservations and not reservations.isEmpty()}">
	 	<c:forEach var="reservation" items="${reservations}">
			<div class="mb-3 font-medium border-b py-8 px-5 w-full flex">
				<a href="/reservations/detail/${reservation.id}">
					<span>${reservation.room.name} | ${reservation.checkIn} - ${reservation.checkOut}</span>
				</a>
				<span class="ml-auto
		            	<c:if test="${reservation.status == 'PENDING'}">text-yellow-500</c:if> 
		            	<c:if test="${reservation.status == 'CANCELED'}">text-red-600</c:if>
		            	<c:if test="${reservation.status == 'CONFIRMED'}">text-teal-600</c:if>">${reservation.status}</span>
			</div>
		</c:forEach>
	</c:if>
</div>

</sec:authorize>

<%@include file="../inc/bottom.jsp" %>

</html>
