<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="inc/common.jsp" %>
<title>Group - ${group.name}</title>
</head>
<body>

	<%-- Nav Map 선언 --%>
	<c:set var="nav" value="<%=new java.util.LinkedHashMap<String, String>()%>" />
	<c:set target="${nav}" property="${group.name}" value=""/>
	
	<%@include file="inc/header.jsp" %>
		
	<div class="container-fluid">
		
		<div class="row">
			
			<div class="col-sm-8">
		
		
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th><span class="text-muted">${group.name}</span></th>
					</tr>
				</thead>
				<tbody>
					<c:set var="groupEndIndex" value="${group.endIndex()}" />
					<c:forEach var="i" begin="0" end="${groupEndIndex}">
						<c:set var="entityName" value="${group.get(i)}" />
						<tr>
							<td><a href="${admin_entity}?e=${entityName.origin}" class="text-dark">${entityName.alias}</a></td>
						</tr>
					</c:forEach>
				</tbody>
		    </table>
		    
		
	    </div>
			
			<div class="col-sm-4">
			</div>
			
		</div>
		
	</div>

	<%@include file="inc/footer.jsp" %>
	
</body>
</html>