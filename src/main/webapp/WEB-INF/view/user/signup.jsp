<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../inc/common.jsp" %>
<title>Sign Up</title>
</head>
<body>

<%@include file="inc/top.jsp" %>

<%@include file="../inc/social_login.jsp" %>

<form:form action="/signup" method="post" modelAttribute="user" enctype="multipart/form-data">
	
	<div>
		<label>Username:</label>
		<form:input path="username" required="true"/>
		<form:errors path="username"/>
	</div>
	
	<div>
		<label>Password:</label>
		<form:password path="password" required="true"/>
		<form:errors path="password"/>
	</div>
	
	<div>
		<label>Retype Password:</label>
		<form:password path="retypePassword" required="true"/>
		<form:errors path="retypePassword"/>
	</div>
	
	<div>
		<label>First Name:</label>
		<form:input path="firstName" required="true"/>
		<form:errors path="firstName"/>
	</div>
	
	<div>
		<label>Last Name:</label>
		<form:input path="lastName" required="true"/>
		<form:errors path="lastName"/>
	</div>
	
	<div>
		<label>Avatar:</label>
		<input type="file" name="avatarFile"/>
		<form:errors path="avatarFile"/>
	</div>
	
	<div>
		<label>Bio:</label>
		<form:textarea path="bio"/>
		<form:errors path="bio"/>
	</div>
	
	<div>
		<label>Birthdate:</label>
		<form:input path="birthdate"/>
		<form:errors path="birthdate"/>
	</div>
	
	<div>
		<label>Gender:</label>
		<form:select path="gender" items="${genderValues}"/>
		<form:errors path="gender"/>
	</div>
	
	<div>
		<label>Language:</label>
		<form:select path="language" items="${languageValues}"/>
		<form:errors path="language"/>
	</div>
	
	<div>
		<label>Currency:</label>
		<form:select path="currency" items="${currencyValues}"/>
		<form:errors path="currency"/>
	</div>
	
	<div>
		<button>회원가입</button>
	</div>
	
</form:form>

<%@include file="inc/bottom.jsp" %>

</body>
</html>