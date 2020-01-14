<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty messages}">
	<ul class="messages">
		<c:forEach var="message" items="${messages.messages}">
	   		<li class="message ${message.tags.className}">${message.text}</li>
	   	</c:forEach>
	</ul>
</c:if>