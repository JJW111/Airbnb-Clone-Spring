<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="inc/common.jsp" %>
<title>Delete ${entityName.origin}</title>
</head>
<body>

	<%-- Nav Map 선언 --%>
	<c:set var="nav" value="<%=new java.util.LinkedHashMap<String, String>()%>" />
	<c:set target="${nav}" property="${groupName}" value="${admin_group}?g=${groupName}"/>
	<c:set target="${nav}" property="${entityName.alias}" value="${admin_entity}?e=${entityName.origin}"/>
	<c:set target="${nav}" property="Delete" value=""/>
	
	<%@include file="inc/header.jsp" %>

	<div class="container-fluid">
		
		<div class="mb-1 clearfix">
			<a class="float-right" href="${admin_entity}?e=${entityName.origin}&page=${page}">Go Back</a>
		</div>
		
		Do you want to delete?
		<a href="javascript:this.f.submit();">Yes, I want to delete.</a>
		
		<form action="${admin_entity}/delete?e=${entityName.origin}&page=${page}" name="f" method="post">
			<input type="hidden" name="id" value="${id}" />
		</form>
	</div> <!-- End Container -->

	<%@include file="inc/footer.jsp" %>
	
</body>
</html>