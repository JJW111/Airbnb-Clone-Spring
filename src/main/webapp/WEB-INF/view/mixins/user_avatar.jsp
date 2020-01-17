<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${empty h_and_w}">
	<c:set var="h_and_w" value="h-20 w-20" />
</c:if>
<c:if test="${not empty user.avatar}">
    <div class="${h_and_w} rounded-full bg-cover" style="background-image: url(${user.avatar.path})"></div>
</c:if>
<c:if test="${empty user.avatar}">
	<c:if test="${empty text}">
		<c:set var="text" value="text-3xl" />
	</c:if>
    <div class="${h_and_w} bg-gray-700 rounded-full text-white flex justify-center items-center overflow-hidden">
        <span class="${text}">${fn:substring(user.firstName,0,1)}</span>
	</div>
</c:if>
<c:remove var="h_and_w" />
<c:remove var="text" />