<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../inc/common.jsp" %>
<title>${user.firstName}'s Profile</title>
</head>

<c:set var="searchvar" value="true" />
<%@include file="../inc/top.jsp" %>

<div class="min-h-75vh">
    
	<div class="container lg:w-5/12 md:w-1/2 xl:w-1/4 mx-auto my-10 flex flex-col items-center border p-6 border-gray-400">
		
		<%@include file="../mixins/user_avatar.jsp" %>
		
		<div class="flex items-center">
	        <span class="text-3xl mt-1">${user.firstName}</span>
	        <c:if test="${user.superhost}">
	            <i class="fas fa-check-circle text-teal-400 ml-2"></i>
	        </c:if>
	    </div>
	
	    <span class="text-lg mb-5">${user.bio}</span>
	
		<sec:authorize access="isAuthenticated()"> 
			<sec:authentication var="principal" property="principal" />
			<c:if test="${principal.username eq user.username}">
	    		<a href="/auth/update-profile" class="btn-link">Edit Profile</a>
	    	</c:if>
	    </sec:authorize>
		
	</div>
    <c:set var="rooms" value="${user.rooms}" />
	<c:if test="${not empty rooms and not rooms.isEmpty()}">
		<div class="container mx-auto pb-10 flex flex-col items-center">
		    <h3>${user.firstName}'s Rooms</h3>
		    <%@include file="../mixins/rooms.jsp" %>
		</div>
	</c:if>
	
</div>

<%@include file="../inc/bottom.jsp" %>

</html>