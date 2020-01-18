<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../inc/common.jsp" %>
<title>Room | ${room.name}</title>
</head>

<%@include file="../inc/top.jsp" %>

<div class="container mx-auto my-10 flex flex-col">

    <c:forEach var="photo" items="${room.photos}">
        <div class="mb-5 border p-6  border-gray-400 flex justify-between">
            <div class="flex items-start w-4/5">
                <img src="${photo.path}" class="w-32 h-32" />
                <span class="ml-5 text-xl">${photo.originalFilename}</span>
            </div>
            <div class="flex flex-col w-1/5">
                <a class="btn-link mb-5 bg-teal-500" href="">Edit</a>
                <a class="btn-link bg-red-600" href="">Delete</a>
            </div>
        </div>
    </c:forEach>

</div>

<%@include file="../inc/bottom.jsp" %>

</html>
