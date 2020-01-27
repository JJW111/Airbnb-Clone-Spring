<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="inc/common.jsp" %>
<title>Airbnb</title>
</head>

<c:set var="searchvar" value="true" />
<%@include file="inc/top.jsp" %>

<div class="container mx-auto pb-10">

    <div class="container rounded-xl mx-auto bg-gray-800 h-50vh mt-32 mb-24 bg-cover bg-center" style="background-image:url(https://images.unsplash.com/photo-1536315238512-4c8cebdaaf93?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop)"></div>
	
    <%@include file="mixins/room/rooms.jsp" %>
    <c:if test="${page.totalPages > 1}" >
		<div class="flex items-center justify-center">
				
			<c:if test="${page.hasPrevious()}" >
				<a href="?page=${page.pageable.pageNumber - 1}" class="text-teal-500">
					<i class="fas fa-arrow-left fa-lg"></i>
				</a>
			</c:if>
			
			<span class="mx-3 font-medium text-lg">${page.pageable.pageNumber + 1} of ${page.totalPages}</span>
			
			<c:if test="${page.hasNext()}" >
				<a href="?page=${page.pageable.pageNumber + 1}" class="text-teal-500">
					<i class="fas fa-arrow-right fa-lg"></i>
				</a>
			</c:if>
		</div>
	</c:if>

</div>

<%@include file="inc/bottom.jsp" %>

</html>