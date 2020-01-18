<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../inc/common.jsp" %>
<title>Room | ${room.name}</title>
</head>

<%@include file="../inc/top.jsp" %>

<div class="container lg:w-5/12 md:w-1/2 xl:w-1/4 mx-auto my-10 flex flex-col items-center border p-6 border-gray-400">
       
	<form:form method="post" modelAttribute="room" class="w-full">
		<form:hidden path="id" />
							
		<spring:bind path="name">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="name">Room Name</label>
				<form:input path="name" required="true" placeholder="Room Name" />
				<form:errors path="name" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="description">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="description">Description</label>
				<form:textarea path="description" placeholder="Description" />
				<form:errors path="description" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="address">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="address">Address</label>
				<form:input path="address" placeholder="Address" />
				<form:errors path="address" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="city">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="city">City</label>
				<form:textarea path="city" placeholder="City" />
				<form:errors path="city" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="country">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="country">Country</label>
				<form:select path="country" items="${room.countries()}" />
				<form:errors path="country" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="price">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="price">Price($)</label>
				<form:input path="price" placeholder="0 ~ 10,000,000($)" onKeydown="setInputFilter(this, integerFilter, 0, 10000000)" />
				<form:errors path="price" class="error" />
			</div>
		</spring:bind>		
		
		<button class="btn bg-red-500 text-white">Update Room</button>
		
	</form:form>
		
	<div class="mt-3">
       	<a href="/rooms/photos?id=${room.id}" class="text-teal-500 font-medium">Edit Photos</a>
  		</div>
	
</div>

<%@include file="../inc/bottom.jsp" %>

</html>
