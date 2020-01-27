<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="inc/common.jsp" %>
<%@include file="inc/edit_common.jsp" %>
<title>Add ${entity.name.origin}</title>
</head>
<body>

<c:set var="edit" value="ADD" />
<c:set var="lastNav" value="Add" />
<c:set var="action" value="/admin/entity/add?e=${entity.name.origin}&page=${param.page}" />
<c:set var="cta" value="Add" />
<%@include file="edit.jsp" %>

</body>
</html>
