<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<ul class="flex items-center text-sm font-medium h-full">
	<sec:authorize access="isAuthenticated()">
		<li class="nav_link">
			<a href="/auth/switch-hosting">
				<c:if test="${not empty sessionScope.is_hosting}">
		        	Stop hosting
		        </c:if>
		        <c:if test="${empty sessionScope.is_hosting}">
		        	Start hosting
		        </c:if>
		    </a>
		</li>
		<c:if test="${not empty sessionScope.is_hosting}">
			<li class="nav_link"><a href="/rooms/add">Upload Room</a></li>
		</c:if>
		<li class="nav_link"><a href="/users/profile">Profile</a></li>
    	<li class="nav_link"><a href="/session_out">Log out</a></li>
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
	    <li class="nav_link"><a href="/login">Log in</a></li>
	    <li class="nav_link"><a href="/signup">Sign up</a></li>
	</sec:authorize>
	
</ul>
