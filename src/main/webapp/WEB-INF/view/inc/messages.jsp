<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty messages}">
	<ul class="absolute top-0 mx-auto left-0 right-0 z-10 flex justify-center">
		<c:forEach var="message" items="${messages.messages}">
	   		<li class="font-medium bg-gray-700 rounded-full text-white py-4 px-6 ${message.tags.className}">${message.text}</li>
	   	</c:forEach>
	</ul>
</c:if>