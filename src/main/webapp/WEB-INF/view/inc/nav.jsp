<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<ul class="flex items-center text-sm font-medium h-full">
	
	<sec:authorize access="isAuthenticated()">
		<li class="nav_link"><a href="/users/profile">Profile</a></li>
    	<li class="nav_link"><a href="/session_out">Log out</a></li>
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
	    <li class="nav_link"><a href="/login">Log in</a></li>
	    <li class="nav_link"><a href="/signup">Sign up</a></li>
	</sec:authorize>
	
</ul>
