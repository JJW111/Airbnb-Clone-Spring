<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../inc/common.jsp" %>
<title>Sign Up</title>
</head>
<body>

<%@include file="../inc/top.jsp" %>

<div class="container lg:w-5/12 md:w-1/2 xl:w-1/4 mx-auto my-10 flex flex-col items-center border p-6 border-gray-400">

	<%@include file="inc/social_login.jsp" %>
	
	<form:form action="/signup" method="post" modelAttribute="user" enctype="multipart/form-data">
			
			<form:input path="firstName" required="true" placeholder="First Name" />
			<form:errors path="firstName" />
			
			<form:input path="lastName" required="true" placeholder="Last Name" />
			<form:errors path="lastName" />
			
			<form:input path="username" required="true" placeholder="airbnb_clone@gmail.com" />
			<form:errors path="username" />
			
			<form:password path="password" required="true" placeholder="Password" />
			<form:errors path="password" />
			
			<form:password path="retypePassword" required="true" placeholder="Retype Password" />
			<form:errors path="retypePassword" />
			
			<button class="btn bg-red-500 text-white">회원가입</button>
		
	</form:form>

</div>

<%@include file="../inc/bottom.jsp" %>

</body>
</html>