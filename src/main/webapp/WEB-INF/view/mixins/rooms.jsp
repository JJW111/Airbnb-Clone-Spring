<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container flex flex-wrap mb-10">
	<c:forEach var="room" items="${rooms}">
    	<%@ include file="room_card.jsp" %>
	</c:forEach>
</div>