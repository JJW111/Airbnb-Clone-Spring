<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../inc/common.jsp" %>
<title>Log In</title>
</head>

<%@include file="../inc/top.jsp" %>

<div class="container lg:w-5/12 md:w-1/2 xl:w-1/4 mx-auto my-10 flex flex-col items-center border p-6 border-gray-400">
	
	<%@include file="inc/social_login.jsp" %>
	
  	<form action="/auth/session" method="post" class="w-full">
  	
  		<div class="input ${not empty error ? 'has_error' : ''}">
			<input type="text" name="username" placeholder="Email" value="${username}" />
		</div>
		
		<div class="input ${not empty error ? 'has_error' : ''}">
			<input type="password" name="password" placeholder="Password" />
		</div>
		
		<c:if test="${not empty error}">		
			<div class="input has_error">
				<span class="error"><small>아이디 혹은 패스워드가 일치하지 않습니다.</small></span>
			</div>
		</c:if>
		
   	 	<button class="btn bg-red-500 text-white">Log in</button>
  	</form>
  	
  	<div class="mt-3">
        <span class="mr-2">Dont't have an accouhnt?</span>
        <a href="/signup" class="text-teal-500 font-medium">Sign up</a>
    </div>

</div>

<%@include file="../inc/bottom.jsp" %>

</html>