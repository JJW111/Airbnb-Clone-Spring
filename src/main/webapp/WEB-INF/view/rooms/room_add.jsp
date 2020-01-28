<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../inc/common.jsp" %>
<title>Add Room | ${room.name}</title>
</head>

<%@include file="../inc/top.jsp" %>

<div class="container lg:w-5/12 md:w-1/2 xl:w-1/4 mx-auto my-10 flex flex-col items-center border p-6 border-gray-400">
       
	<form:form method="post" enctype="multipart/form-data" modelAttribute="room" class="w-full">
		<spring:bind path="name">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="name">Room Name</label>
				<form:input path="name" placeholder="Room Name" required="true" />
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
				<form:input path="address" placeholder="Address" required="true" />
				<form:errors path="address" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="city">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="city">City</label>
				<form:textarea path="city" placeholder="City" required="true" />
				<form:errors path="city" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="country">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="country">Country</label>
				<form:select path="country" required="true" >
					<form:option disabled="true" value="" label="----- Select Country -----" />
					<form:options items="${selectValues.countries()}" />
				</form:select>
				<form:errors path="country" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="price">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="price">Price($)</label>
				<form:input path="price" placeholder="~ 10,000($)" onKeydown="setInputFilter(this, integerFilter, 0, 10000)" required="true" />
				<form:errors path="price" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="guests">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="guests">Guests</label>
				<form:input path="guests" placeholder="~ 20(명)" onKeydown="setInputFilter(this, integerFilter, 0, 20)" required="true" />
				<form:errors path="guests" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="beds">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="beds">Beds</label>
				<form:input path="beds" placeholder="~ 8개" onKeydown="setInputFilter(this, integerFilter, 0, 8)" required="true" />
				<form:errors path="beds" class="error" />
			</div>
		</spring:bind>	
		
		<spring:bind path="bedrooms">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="bedrooms">Bedrooms</label>
				<form:input path="bedrooms" placeholder="~ 5개" onKeydown="setInputFilter(this, integerFilter, 0, 5)" required="true" />
				<form:errors path="bedrooms" class="error" />
			</div>
		</spring:bind>	
		
		<spring:bind path="baths">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="baths">Baths</label>
				<form:input path="baths" placeholder="~ 3개" onKeydown="setInputFilter(this, integerFilter, 0, 3)" required="true" />
				<form:errors path="baths" class="error" />
			</div>
		</spring:bind>	
		
		<spring:bind path="checkIn">
			<fmt:formatDate value="${room.checkIn}" var="formattedDateString" pattern="yyyy-MM-dd HH:mm:ss" />
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="checkIn">Check In</label>
				<form:input path="checkIn" placeholder="1999-01-01 24:00:00" value="${formattedDateString}" required="true" />
				<form:errors path="checkIn" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="checkOut">
			<fmt:formatDate value="${room.checkOut}" var="formattedDateString" pattern="yyyy-MM-dd HH:mm:ss" />
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="checkOut">Check Out</label>
				<form:input path="checkOut" placeholder="2000-01-01 24:00:00" value="${formattedDateString}" required="true" />
				<form:errors path="checkOut" class="error" />
			</div>
		</spring:bind>	
		
		<spring:bind path="instantBook">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="instantBook">Instant Book</label>
				<form:checkbox path="instantBook" />
				<form:errors path="instantBook" class="error" />
			</div>
		</spring:bind>	
		
		<spring:bind path="roomType">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="roomType">Room Type</label>
				<form:select path="roomType" required="true" >
					<form:option disabled="true" value="" label="--- Choose One ---" />
					<c:forEach var="option" items="${selectValues.roomTypes()}">
						<c:if test="${not empty room.roomType and empty optionSelected and option.equals(room.roomType)}">
							<c:set var="optionSelected" value="true" />
						</c:if>
						<form:option selected="${optionSelected}" value="${option.id}" label="${option.name}"/>
						<c:if test="${not empty optionSelected and optionSelected eq 'true'}">
							<c:set var="optionSelected" value="" />
						</c:if>
					</c:forEach>
				</form:select>
				<form:errors path="roomType" class="error" />
			</div>
			<c:remove var="optionSelected" />
		</spring:bind>
		
		<spring:bind path="amenities">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="amenities">Amenities</label>
				<form:select path="amenities" multiple="true" >
					<form:option value="" label="---Select More Than One---" />
					<form:options items="${selectValues.amenities()}" itemLabel="name" itemValue="id" />
				</form:select>
				<form:errors path="amenities" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="facilities">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="facilities">Facilities</label>
				<form:select path="facilities" multiple="true" >
					<form:option value="" label="---Select More Than One---" />
					<form:options items="${selectValues.facilities()}" itemLabel="name" itemValue="id" />
				</form:select>
				<form:errors path="facilities" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="houseRules">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="houseRules">House Rules</label>
				<form:select path="houseRules" multiple="true" >
					<form:option value="" label="---Select More Than One---" />
					<form:options items="${selectValues.houseRules()}" itemLabel="name" itemValue="id" />
				</form:select>
				<form:errors path="houseRules" class="error" />
			</div>
		</spring:bind>
		
		<spring:bind path="photos">
			<div class="input ${status.error ? 'has_error' : ''}">
				<label for="photos">Photos</label>
				<input type="file" name="photos" multiple required />
				<form:errors path="photos" class="error" />
			</div>
		</spring:bind>
		
		
		<button class="btn bg-red-500 text-white">Add Room</button>
		
	</form:form>
		
</div>

<%@include file="../inc/bottom.jsp" %>

</html>
