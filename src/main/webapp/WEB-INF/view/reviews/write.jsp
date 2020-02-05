<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../inc/common.jsp" %>
<title>Write Your Review</title>
</head>

<%@include file="../inc/top.jsp" %>

<div class="container lg:w-5/12 md:w-1/2 xl:w-1/4 mx-auto my-10 flex flex-col items-center border p-6 border-gray-400">
    
	<form:form method="post" modelAttribute="review" class="w-full">
		<spring:bind path="review">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="review">Review</label>
				<form:input path="review" placeholder="리뷰 작성 100자까지" required="true" maxlength="100" />
				<form:errors path="review" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="accuracy">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="accuracy">Accuracy</label>
				<form:input path="accuracy" placeholder="0 ~ 5" onKeydown="setInputFilter(this, integerFilter, 0, 5)"/>
				<form:errors path="accuracy" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="communication">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="communication">Communication</label>
				<form:input path="communication" placeholder="0 ~ 5" onKeydown="setInputFilter(this, integerFilter, 0, 5)"/>
				<form:errors path="communication" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="cleaniness">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="cleaniness">Cleaniness</label>
				<form:input path="cleaniness" placeholder="0 ~ 5" onKeydown="setInputFilter(this, integerFilter, 0, 5)"/>
				<form:errors path="cleaniness" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="location">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="location">Location</label>
				<form:input path="location" placeholder="0 ~ 5" onKeydown="setInputFilter(this, integerFilter, 0, 5)"/>
				<form:errors path="location" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="checkIn">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="checkIn">Check In</label>
				<form:input path="checkIn" placeholder="0 ~ 5" onKeydown="setInputFilter(this, integerFilter, 0, 5)"/>
				<form:errors path="checkIn" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="value">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="value">Value</label>
				<form:input path="value" placeholder="0 ~ 5" onKeydown="setInputFilter(this, integerFilter, 0, 5)"/>
				<form:errors path="value" class="error" />
			</div>
		</spring:bind>
		
		<button class="btn bg-red-500 text-white">Write Review</button>
		
	</form:form>
		
</div>

<%@include file="../inc/bottom.jsp" %>

</html>
