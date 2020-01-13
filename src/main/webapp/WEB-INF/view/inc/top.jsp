<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body class="text-gray-800 mt-20">
	<header
      class="container max-w-full inset-0 flex items-center justify-between px-6 h-20 border-b border-gray-400 fixed bg-white"
    >
    <div class="flex items-center w-1/3">
		<a href="/" class="mr-6">
		  <img class="w-8" src="/image/logo.png" />
		</a>
		<form method="get" action="/search" class="w-9/12">
		  <input
		    class="search-box border px-5 py-3 text-sm font-medium placeholder-gray-600 rounded-sm w-64 shadow-md hover:shadow-lg w-full"
		    name="city"
		    placeholder="Search by City"
		  />
		</form>
    </div>
	<%@include file="nav.jsp" %>	
	</header>