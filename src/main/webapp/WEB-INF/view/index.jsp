<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="inc/common.jsp" %>
<title>Airbnb</title>
</head>

<%@include file="inc/top.jsp" %>

<div class="container mx-auto">

    <div class="container rounded-xl mx-auto bg-gray-800 h-50vh mt-32 mb-24 bg-cover bg-center" style="background-image:url(https://images.unsplash.com/photo-1536315238512-4c8cebdaaf93?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop)"></div>

    <div class="container flex flex-wrap">
        <c:forEach var="room" items="${rooms}">
        	<c:if test="${room.photos ne null and room.photos.size() ne 0}">
        		<%@ include file="inc/room_card.jsp" %>
        	</c:if>
        </c:forEach>
    </div>

    <c:if test="${page.totalPages > 1}" >
			
		<c:if test="${page.hasPrevious()}" >
			<a href="?page=${page.pageable.pageNumber - 1}">Previous</a>
		</c:if>
		
		Page ${page.pageable.pageNumber} of ${page.totalPages}
		
		<c:if test="${page.hasNext()}" >
			<a href="?page=${page.pageable.pageNumber + 1}">Next</a></li>
		</c:if>
			
	</c:if>

</div>

<%@include file="inc/bottom.jsp" %>

</html>