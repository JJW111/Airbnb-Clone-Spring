<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../inc/common.jsp" %>
<title>Reservation | ${reservation.id}</title>
</head>

<%@include file="../inc/top.jsp" %>

<sec:authorize access="isAuthenticated()">

<sec:authentication var="principal" property="principal" />

<div class="container mx-auto my-10 flex flex-col">

    <div class="border-t border-l border-r bg-cover bg-center h-56 rounded-t-lg" style="background-image: url(${empty reservation.room.photos or reservation.room.photos.isEmpty() ? '' : reservation.room.photos.get(0).path});"></div>

    <div class="flex flex-col items-start border-l border-r border-t border-b">
        <div class="font-medium border-b py-8 px-5 w-full">
            ${reservation.checkIn} - ${reservation.checkOut}
            <span class="ml-5 
            	<c:if test="${reservation.status == 'PENDING'}">text-yellow-500</c:if> 
            	<c:if test="${reservation.status == 'CANCELED'}">text-red-600</c:if>
            	<c:if test="${reservation.status == 'COMFIRMED'}">text-teal-600</c:if>">${reservation.status}</span>
        </div>

        <span class="text-2xl border-b p-5 mt-2 w-full">
            <a href="/rooms/detail/${reservation.room.id}">${reservation.room.name}</a>
        </span>

        <div class="flex p-5 border-b w-full">
            <div class="flex flex-col items-center">
            	<c:set var="user" value="${reservation.room.host}" />
            	<%@include file="../mixins/auth/user_avatar.jsp" %>
                <span class="mt-2 text-gray-500">${reservation.room.host.firstName}</span>
            </div>
            <c:if test="${principal.username ne reservation.room.host.username}">
	            <div class="ml-5 flex flex-col">
	                <span class="font-medium mb-px">Contact your Airbnb Host</span>
	                <a href="/messages/send" class="font-medium text-teal-500">Send a Message</a>
	            </div>
            </c:if>
        </div>

        <div class="py-10 px-5">
        	<c:if test="${reservation.status == 'PENDING'}">
                <a href="/reservations/cancel/${reservation.id}" class="btn-link block px-3 mb-5">Cancel Reservation</a>
	    		<c:if test="${principal.username eq reservation.room.host.username}">
                    <a href="/reservations/confirm/${reservation.id}" class="btn-link block px-5 bg-teal-600">Confirm Reservation</a>
                </c:if>
            </c:if>
        </div>
    </div>

</div>

</sec:authorize>

<%@include file="../inc/bottom.jsp" %>

</html>
