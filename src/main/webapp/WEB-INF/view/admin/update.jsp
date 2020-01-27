<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="inc/common.jsp" %>
<%@include file="inc/edit_common.jsp" %>
<title>Update ${entity.name.origin}</title>
</head>
<body>

<c:set var="edit" value="UPDATE" />
<c:set var="lastNav" value="Update" />
<c:set var="action" value="/admin/entity/update?e=${entity.name.origin}&page=${param.page}&id=${id}" />
<c:set var="cta" value="Update" />
<%@include file="edit.jsp" %>

</body>
</html>
