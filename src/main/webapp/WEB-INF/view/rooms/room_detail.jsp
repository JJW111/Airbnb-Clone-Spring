<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../inc/common.jsp" %>
<title>Room | ${room.name}</title>
</head>

<c:set var="searchvar" value="true" />
<%@include file="../inc/top.jsp" %>

<div class="-mt-5 container max-w-full h-75vh flex mb-20">
	<c:set var="photosSize" value="${room.photos.size()}" />
	<c:if test="${photosSize > 0}">
		<div class="h-full w-1/2 bg-cover bg-center" style="background-image:url(${room.photos.get(0).path})"></div>
	</c:if>
	<c:if test="${photosSize == 0}">
		<div class="h-full w-1/2 bg-gray-400 items-center flex">
			<span class="flex-1 text-center text-3xl font-bold text-white">No Image</span>
		</div>
	</c:if>
    <div class="h-full w-1/2 flex flex-wrap">
    	<c:forEach var="i" begin="1" end="4">
    		<c:if test="${photosSize > i}">
            	<div class="w-1/2 h-auto bg-cover bg-center border-gray-500 border" style="background-image:url(${room.photos.get(i).path})"></div>
    		</c:if>
    		<c:if test="${photosSize <= i}">
            	<div class="w-1/2 h-auto bg-gray-400 border-gray-500 border items-center flex">
            		<span class="flex-1 text-center text-3xl font-bold text-white">No Image</span>		
            	</div>
    		</c:if>
    	</c:forEach>
    </div>
</div>

<div class="container mx-auto flex justify-around pb-56">
    <div class="w-1/2">
        <div class="flex justify-between">
            <div class="mb-5">
                <h4 class="text-3xl font-medium mb-px">${room.name}</h4>
                <span class="text-gray-700 font-light">${room.city}</span>
            </div>
            <c:set var="user" value="${room.host}" />
            <a href="/users/profile?id=${user.id}" class="flex flex-col items-center">
                <%@include file="../mixins/auth/user_avatar.jsp" %>
                <span class="mt-2 text-gray-500">${user.firstName}</span>
            </a>
        </div>
        <div class="flex border-b border-gray-400 pb-5">
            <span class="mr-5 font-light">${room.roomType.name}</span>
            <span class="mr-5 font-light">${room.beds} bed<c:if test="${room.beds > 1}">s</c:if></span>
            <span class="mr-5 font-light">${room.bedrooms} bedroom<c:if test="${room.bedrooms > 1}">s</c:if></span>
            <span class="mr-5 font-light">${room.baths} bath<c:if test="${room.baths > 1}">s</c:if></span>
            <span class="mr-5 font-light">${room.guests} guest<c:if test="${room.guests > 1}">s</c:if></span>
        </div>
        <p class="border-section">
            ${room.description}
        </p>
        <div class="border-section">
            <h4 class="font-medium text-lg mb-5">Amenities</h4>
            <ul>
            	<c:forEach var="a" items="${room.amenities}">
                    <li class="mb-2">${a.name}</li>
            	</c:forEach>
            </ul>
        </div>
        <div class="border-section">
            <h4 class="font-medium text-lg mb-5">Facilities</h4>
            <ul>
            	<c:forEach var="a" items="${room.facilities}">
                    <li class="mb-2">${a.name}</li>
            	</c:forEach>
            </ul>
        </div>
        <div class="border-section">
            <h4 class="font-medium text-lg mb-5">House Rules</h4>
            <ul>
            	<c:forEach var="a" items="${room.houseRules}">
                    <li class="mb-2">${a.name}</li>
            	</c:forEach>
            </ul>
        </div>
        <div class="mt-10">
            <h4 class="font-medium text-2xl mb-5">Reviews</h4>
            <div class="flex items-center">
                <div>
                    <i class="fas fa-star text-teal-500"></i>
                    <span class="font-bold text-xl">${room.totalRating()}</span>
                </div>
                <div class="h-4 w-px bg-gray-400 mx-5"></div>
                <div>
                    <span class="font-bold text-xl mr-1">${room.reviews.size()}</span>
                    <span>review<c:if test="${room.reviews.size() > 1}">s</c:if></span>
                </div>
            </div>
            <div class="mt-10">
            	<c:forEach var="review" items="${room.reviews}">
                    <div class="border-section">
                        <div class="mb-3 flex">
                            <div>
                            	<c:set var="h_and_w" value="w-10 h-10" />
                            	<c:set var="text" value="text-xl" />
                            	<c:set var="user" value="${review.user}" />
                            	<a href="/users/profile?id=${user.id}">
                            		<%@include file="../mixins/auth/user_avatar.jsp" %>
                            	</a>
                            </div>
                            <div class="flex flex-col ml-5">
                                <span class="font-medium">${review.user.firstName}</span>
                                <fmt:formatDate value="${review.created}" var="reviewCreated" pattern="MMMMM yyyy" />
                                <span class="text-sm text-gray-500">${reviewCreated}</span>
                            </div>
                        </div>
                        <p>${review.review}</p>
                    </div>
            	</c:forEach>
            </div>
        </div>
    </div>
    <div class="w-1/3">
    	<sec:authorize access="isAuthenticated()"> 
	    	<sec:authentication var="principal" property="principal" />
	    	<c:if test="${principal.username eq room.host.username}">
	            <a href="/rooms/${room.id}/edit" class="btn-link block">Edit Room</a>
	            <a href="/rooms/${room.id}/delete" class="btn-link block mt-10 bg-teal-500">Delete Room</a>
	            <c:set var="host_user" value="true" />
	        </c:if>
        </sec:authorize>
        
        <c:if test="${empty host_user}">
	        <c:if test="${empty sessionScope.is_hosting}">
	        	<c:forEach var="calendar" items="${calendars}">
                	<div class="mb-20">
                        <span class="text-center font-semibold text-lg block mb-8">${calendar.monthSymbol} / ${calendar.year}</span>
                        <div class="cal-grid font-medium mb-4">
                        	<c:forEach var="day" items="${calendar.header}">
                        		<span>${day}</span>
                        	</c:forEach>
                        </div>
                        <div class="calendar">
	                        <div class="cal-grid">
	                        	<c:forEach var="date" items="${calendar.dates}">
	                        		<c:if test="${not empty date}">
		                         		<c:if test="${date != 0}">
		                         			<c:choose>
		                         				<c:when test="${calendar.isPast(calendar.year, calendar.month, date)}">
		                         					<span class="rounded bg-gray-200 w-full text-center p-1 text-gray-300">${date}</span>
		                         				</c:when>
		                         				<c:when test="${room.isReserved(calendar.year, calendar.month, date)}">
		                         					<span class="cal-number bg-gray-200 text-gray-400 line-through">${date}</span>
		                         				</c:when>
		                         				<c:otherwise>
		                         					<span class="bg-gray-200 w-full text-center rounded text-gray-700 p-1 hover:bg-teal-400 hover:text-white hover:font-medium cursor-pointer">${date}</span>
		                         				</c:otherwise>
		                         			</c:choose>
		                          		</c:if>
		                         		<c:if test="${date == 0}">
		                                     <span></span>
		                         		</c:if>
	                        		</c:if>
	                        	</c:forEach>
	                        </div>
                        </div>
                    </div>
            	</c:forEach>
            	<sec:authorize access="isAuthenticated()"> 
	            	<form action="/reservations/check-in" method="post">
	            		<input type="hidden" name="room_id" value="${room.id}" />
	            		<input type="text" name="check_in" class="border-4 mb-3 border-blue-600" placeholder="Check In / 2000-01-01" />
	            		<input type="text" name="check_out"class="border-4 mb-3 border-blue-600" placeholder="Check Out / 2000-01-10" />
	            		<button class="btn-link">Check In</button>
	            	</form>
            	</sec:authorize>
	        </c:if>
        </c:if>
    </div>
</div>


<%@include file="../inc/bottom.jsp" %>

</html>
