<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../inc/common.jsp" %>
<title>Update Profile</title>
</head>

<%@include file="../inc/top.jsp" %>

<div class="container lg:w-5/12 md:w-1/2 xl:w-1/4 mx-auto my-10 flex flex-col items-center border p-6 border-gray-400">
	
	<form:form method="post" modelAttribute="user" enctype="multipart/form-data" class="w-full">
		<form:hidden path="id" />
							
		<spring:bind path="firstName">
			<div class="input ${status.error ? 'has_error' : ''}">
				<form:input path="firstName" required="true" placeholder="First Name" />
				<form:errors path="firstName" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="lastName">
			<div class="input ${status.error ? 'has_error' : ''}">
				<form:input path="lastName" required="true" placeholder="Last Name" />
				<form:errors path="lastName" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="gender">
			<div class="input ${status.error ? 'has_error' : ''}">
				<form:select path="gender" items="${user.gender.values()}" />
				<form:errors path="gender" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="bio">
			<div class="input ${status.error ? 'has_error' : ''}">
				<form:textarea path="bio" placeholder="Comment Bio" />
				<form:errors path="bio" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="birthdate">
			<div class="input ${status.error ? 'has_error' : ''}">
				<form:input path="birthdate" placeholder="2000-01-01" />
				<form:errors path="birthdate" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="language">
			<div class="input ${status.error ? 'has_error' : ''}">
				<form:select path="language" items="${user.language.values()}" />
				<form:errors path="language" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="currency">
			<div class="input ${status.error ? 'has_error' : ''}">
				<form:select path="currency" items="${user.currency.values()}" />
				<form:errors path="currency" class="error" />
			</div>
		</spring:bind>
		
		<button class="btn bg-red-500 text-white">Update profile</button>
		
	</form:form>
		
	<c:if test="${user.loginMethod eq 'EMAIL'}">
	  	<div class="mt-3">
	        <a href="/auth/change-password" class="text-teal-500 font-medium">Change Password</a>
	    </div>
    </c:if>

</div>

<%@include file="../inc/bottom.jsp" %>

</html>