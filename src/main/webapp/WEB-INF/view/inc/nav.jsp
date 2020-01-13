<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<ul class="flex items-center text-sm font-medium h-full">
	
	<sec:authorize access="isAuthenticated()">
    	<li class="nav_link"><a href="/auth/session_out">Logout</a></li>
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
	    <li class="nav_link"><a href="/login">Login</a></li>
	    <li class="nav_link"><a href="/signup">Sign up</a></li>
	</sec:authorize>
	
</ul>
