<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="ml-4 mt-3 mb-3">
	<span>
		<a href="/admin" class="text-dark">Home</a>
	</span>
	<c:if test="${nav ne null}">
		<c:forEach var="item" items="${nav}">
			<span class="text-muted">-></span>
			<c:if test="${item.value ne ''}" >
				<span>
					<a href="${item.value}" class="text-dark">${item.key}</a>
				</span>
			</c:if>
			<c:if test="${item.value eq ''}" >
				<span class="text-muted">
					${item.key}
				</span>
			</c:if>
		</c:forEach>
	</c:if>
</div>