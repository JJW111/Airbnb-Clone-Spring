<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../inc/common.jsp" %>
<title>Room | ${room.name}</title>
</head>

<c:set var="searchvar" value="true" />
<%@include file="../inc/top.jsp" %>

<div class="-mt-5 container max-w-full h-75vh flex mb-20">
    <div class="h-full w-1/2 bg-cover bg-center" style="background-image:url(${room.photos.get(0).path})"></div>
    <div class="h-full w-1/2 flex flex-wrap">
    	<c:forEach var="photo" items="${room.photos}" begin="1" end="4">
            <div class="w-1/2 h-auto bg-cover bg-center border-gray-500 border" style="background-image:url(${photo.path})"></div>
    	</c:forEach>
    </div>
</div>

<%@include file="../inc/bottom.jsp" %>

</html>
