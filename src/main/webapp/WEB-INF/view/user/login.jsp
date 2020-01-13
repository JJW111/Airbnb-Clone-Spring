<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../inc/common.jsp" %>
<title>Log In</title>
</head>

<%@include file="inc/top.jsp" %>

<%@include file="../inc/social_login.jsp" %>
	
<div id="login-form">
	
  	<form action="/auth/session" method="post">
  		<div class="form-group">
			<input type="text" name="username" value="${username}" class="form-control" placeholder="airbnb@airbnb_clone.com" />
		</div>
  		<div class="form-group">
			<input type="password" name="password" class="form-control" placeholder="password" />
		</div>
		<c:if test="${error ne null}">
			<span class="error mb-1" style="display: inline-block;"><small>아이디 혹은 패스워드가 일치하지 않습니다.</small></span>
		</c:if>
   	 	<button type="submit" class="btn btn-primary btn-block">Login</button>
  	</form>

</div>

<%@include file="inc/bottom.jsp" %>

</html>