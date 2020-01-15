<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-1/4 mb-10 px-2 overflow-hidden">
	<a href="/detail?id=${room.id}">
		<c:if test="${not empty room.photos and not room.photos.isEmpty()}">
	    	<div class="w-full h-64 bg-cover bg-center rounded-lg mb-3" style="background-image: url(${room.photos.get(0).path});"></div>
	    </c:if>
	    <c:if test="${empty room.photos or room.photos.isEmpty()}">
	    	<div class="w-full h-64 bg-gray-400 rounded-lg mb-3"></div>
	    </c:if>
	    
	    <div class="flex justify-between mb-2 truncate">
	        <div class="w-4/5 overflow-hidden flex">
	
	            <c:if test="${room.host.superhost}">
	                <span class="mr-2 uppercase font-medium text-xs border border-black text-black rounded py-px px-1">superhost</span>
	            </c:if>
	            
				<span class="text-md -ml-1">&nbsp;</span>
	            <span class="text-sm text-gray-600 block truncate">${room.city},${room.countries().get(room.country)}</span>
	        </div>
	        <span class="text-sm flex items-center">
	            <i class="fas fa-star text-red-500 text-xs mr-1"></i> ${room.totalRating()}
	        </span>
	    </div>
	    <span class="text-black w-11/12 block truncate">${room.name}</span>
    </a>
</div>
