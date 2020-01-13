<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<body class="text-gray-800 mt-20">
	<header
      class="container max-w-full inset-0 flex items-center justify-between px-6 h-20 border-b border-gray-400 fixed bg-white"
    >
    <div class="flex items-center w-1/3">
		<a href="{% url 'core:home' %}" class="mr-6">
		  <img class="w-8" src="{% static 'img/logo.png' %}" />
		</a>
    </div>
	<%@include file="nav.jsp" %>    	
	</header>