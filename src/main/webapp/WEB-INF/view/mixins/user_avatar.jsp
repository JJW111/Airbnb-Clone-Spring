<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${not empty user.avatar}">
    <div class="h-20 w-20 rounded-full bg-cover" style="background-image: url(${user.avatar.path})"></div>
</c:if>
<c:if test="${empty user.avatar}">
    <div class="h-20 w-20 bg-gray-700 rounded-full text-white flex justify-center items-center overflow-hidden">
        <span class="text-3xl">${fn:substring(user.firstName,0,1)}</span>
	</div>
</c:if>