<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container mx-auto pb-10">

    <div class="container rounded-xl mx-auto bg-gray-800 h-50vh mt-32 mb-24 bg-cover bg-center" style="background-image:url(https://images.unsplash.com/photo-1536315238512-4c8cebdaaf93?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop)"></div>

    <%@include file="../mixins/room/rooms.jsp" %>
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