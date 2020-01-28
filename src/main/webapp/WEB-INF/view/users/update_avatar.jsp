<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../inc/common.jsp" %>
<title>${user.firstName}'s Avatar</title>
</head>

<%@include file="../inc/top.jsp" %>

<div class="min-h-75vh">
	
	<div class="container lg:w-5/12 md:w-1/2 xl:w-1/4 mx-auto my-10 flex flex-col items-center border p-6 border-gray-400">
		
		<c:if test="${not empty user.avatar}">
			<div class="bg-no-repeat bg-contain bg-center w-64 h-64" style="background-image: url(${user.avatar.path});">
			</div>
			<a href="/auth/avatar/delete" class="btn bg-red-500 text-white mt-10">Delete Avatar</a>
		</c:if>
		<c:if test="${empty user.avatar}">
			<div class="bg-gray-500 w-64 h-64 flex items-center">
				<span class="flex-1 text-center text-3xl font-bold text-white">No Avatar</span>
			</div>
			<form action="/auth/avatar/upload" method="POST" enctype="multipart/form-data" class="mt-10">
				<input type="file" name="avatarFile">
				<button class="btn bg-teal-500 text-white mt-5">Update Avatar</button>
			</form>
		</c:if>
	
	</div>
	
</div>

<%@include file="../inc/bottom.jsp" %>

</html>
