<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-1/4 mb-10 px-2">
    <div class="w-full h-64 bg-cover bg-center" style="background-image: url(${room.photos.get(0).path});"></div>
    <div class="flex justify-between">
    	<div>
			
			<c:if test="${room.host.superhost}">
				<span class="uppercase font-medium text-xs border border-black text-black rounded py-px px-1">superhost</span>
			</c:if>

            <span class="text-sm text-gray-600">${room.city}</span>
        </div>
        <span class="text-sm flex items-center">
            <i class="fas fa-star text-red-500 text-xs mr-1"></i> ${room.totalRating()}
        </span>
    </div>
</div>
