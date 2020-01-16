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

<div class="min-h-75vh">

	<div class="container lg:w-5/12 md:w-1/2 xl:w-1/4 mx-auto my-10 flex flex-col items-center border p-6 border-gray-400">
		
		<form:form method="post" modelAttribute="passwordChange" class="w-full">
			<spring:bind path="oldPassword">
				<div class="input ${status.error ? 'has_error' : ''}">
					<form:password path="oldPassword" required="true" placeholder="Old Password" />
					<form:errors path="oldPassword" class="error" />
				</div>
			</spring:bind>
							
			
			<spring:bind path="password">
				<div class="input ${status.error ? 'has_error' : ''}">
					<form:password path="password" required="true" placeholder="New Password" />
					<form:errors path="password" class="error" />
				</div>
			</spring:bind>
			
			<spring:bind path="retypePassword">
				<div class="input ${status.error ? 'has_error' : ''}">
					<form:password path="retypePassword" required="true" placeholder="Confirm Password" />
					<form:errors path="retypePassword" class="error" />
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
	
</div>

<%@include file="../inc/bottom.jsp" %>

</html>