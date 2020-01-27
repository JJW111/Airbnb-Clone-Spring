<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../inc/common.jsp" %>
<title>Photo Upload</title>
</head>
<body>

<%@include file="../inc/top.jsp" %>

<div class="min-h-75vh">

	<div class="container lg:w-5/12 md:w-1/2 xl:w-1/4 mx-auto my-10 flex flex-col items-center border p-6 border-gray-400">
	    
		<form action="/rooms/photo/add?room_id=${param.room_id}" enctype="multipart/form-data" method="post" class="w-full">
			
			<input type="file" name="photoFile" class="mb-3" required />
			
			<button class="btn bg-red-500 text-white">Upload Photo</button>
		
		</form>
		
	</div>

</div>

<%@include file="../inc/bottom.jsp" %>

</html>