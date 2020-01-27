<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="inc/common.jsp" %>
<title>Change ${entity.name.origin}</title>
</head>
<body>

	<%-- Nav Map 선언 --%>
	<c:set var="nav" value="<%=new java.util.LinkedHashMap<String, String>()%>" />
	<c:set target="${nav}" property="${groupName}" value="${admin_group}?g=${groupName}"/>
	<c:set target="${nav}" property="${entity.name.alias}" value=""/>
	
	<%@include file="inc/header.jsp" %>

	<c:set var="pageOffset" value="${page.pageable.pageNumber }" />
	<c:set var="pageNumber" value="${pageOffset + 1 }"/>		
	<div class="container-fluid">
		
		<div class="row">
			
			<div class="col-sm-10">
				
				<div class="mb-1 clearfix">
					<small>${pageNumber} / ${page.totalPages} 페이지</small>
					<small class="font-weight-light">&nbsp;(총 ${page.totalElements}행 검색됨)</small>
					<button class="btn btn-success float-right" onClick="location.href='${admin_entity}/add?e=${entity.name.origin}&page=${pageOffset}'">Add</button>
				</div>
			
				<table class="table table-text-center">
					<thead class="thead-light">
						<tr>
							<c:forEach var="alias" items="${fieldList.values()}" >
								<th>${alias}</th>
							</c:forEach>
							<th style="width: 70px;">#</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="row" items="${page.content}">
							<c:set var="id" value="${reflectionInvocator.get(row, 'id')}"/>
							<tr>
								<c:forEach var="key" items="${fieldList.keySet()}" >
									<td style="vertical-align: middle;">
									
										<c:choose>
											
											<%-- 필드일 경우 --%>
											<c:when test="${key == 'id' or entity.columns.containsKey(key)}">
												<c:set var="value" value="${reflectionInvocator.get(row, key)}" />
												<c:set var="column" value="${entity.columns.get(key)}" />
												<c:set var="formType" value="${column.formType}" />
												<c:set var="fieldType" value="${formType.fieldType}" />
												<c:set var="fieldTypeName" value="${fieldType.name()}" />
												<c:set var="formInfo" value="${formType.formInfo}" />
												
												<c:if test="${value ne null}">
													<c:choose>
														<%-- 컬림타입이 BOOLEAN 타입일 경우 true 면 체크표시, false 면 X표시 아이콘을 출력한다. --%>
														<c:when test="${fieldTypeName eq 'CHECK_BOX' }">
															<c:if test="${value eq true}">
																<i class="fas fa-check"></i>
															</c:if>
															<c:if test="${value eq false}">
																<i class="fas fa-times"></i>
															</c:if>
														</c:when>
														
														
														<%-- 컬럼타입이 IMAGE_UPLOAD_FORM 일 경우 이미지 출력 --%>
														<c:when test="${fieldTypeName eq 'IMAGE_UPLOAD_FORM' }">
															<img src="${value.path}" alt="${value.originalFilename}" title="${value.originalFilename}" class="entity-list-image"/>
														</c:when>
														
														<%-- 컬럼타입이 MULTIPLE_IMAGE_UPLOAD_FORM 일 경우 첫 번째 이미지 출력 --%>
														<c:when test="${fieldTypeName eq 'MULTIPLE_IMAGE_UPLOAD_FORM' }">
															<c:if test="${not value.isEmpty()}">
																<c:set var="first" value="${value.get(0)}" />
																<img src="${first.path}" alt="${first.originalFilename}" title="${first.originalFilename}" class="entity-list-image"/>
															</c:if>
														</c:when>										
														
														<%-- 컬럼타입이 SELECT_BOX 일 경우 Enum value 출력 --%>
														<c:when test="${fieldTypeName eq 'SELECT_BOX'}">
															${value.value}
														</c:when>
														
														<%-- 컬럼타입이 MAP_SELECT_BOX 일 경우 --%>
														<c:when test="${fieldTypeName eq 'MAP_SELECT_BOX'}">
															<c:set var="map" value="${reflectionInvocator.invoke(selectValues, formInfo.method)}" />
															${map.get(value)}
															<c:remove var="map" />
														</c:when>														
														
														<%-- 컬럼타입이 JOIN_ONE_TEXT 일 경우 field value 출력 --%>
														<c:when test="${fieldTypeName eq 'JOIN_ONE_TEXT'}">
															${reflectionInvocator.get(value, formInfo.field)}
														</c:when>
														
														
														<%-- 컬럼타입이 JOIN_ONE_TEXT 일 경우 field value 출력 --%>
														<c:when test="${fieldTypeName eq 'JOIN_ONE'}">
															${reflectionInvocator.get(value, formInfo.itemLabel)}
														</c:when>
														
														
														<%-- 컬럼타입이 JOIN_MANY 일 경우 field values 출력 --%>
														<c:when test="${fieldTypeName eq 'JOIN_MANY'}">
															<c:if test="${not value.isEmpty()}">
																<c:forEach var="item" items="${value}">
																	<c:set var="fieldValue" value="${reflectionInvocator.get(item, formInfo.field)}" />
																	<c:if test="${not empty itemsStr}">
																		<c:set var="itemsStr" value="${itemsStr}, ${fieldValue}" />
																	</c:if>
																	<c:if test="${empty itemsStr}">
																		<c:set var="itemsStr" value="${fieldValue}" />
																	</c:if>
																</c:forEach>
																${itemsStr}
																<c:remove var="itemsStr"/>
															</c:if>
														</c:when>
														
														
														<c:otherwise>
														
															<c:if test="${fieldTypeName eq 'NONE'}">
																<c:choose>
																	
																	<c:when test="${formInfo.typeName eq 'Boolean'}">
																		<c:if test="${value eq true}">
																			<i class="fas fa-check"></i>
																		</c:if>
																		<c:if test="${value eq false}">
																			<i class="fas fa-times"></i>
																		</c:if>
																	</c:when>
																	
																	<c:otherwise>
																		${value}
																	</c:otherwise>
																
																</c:choose>
															</c:if>
															
															<c:if test="${fieldTypeName ne 'NONE'}">
																${value}
															</c:if>
														</c:otherwise>
													</c:choose>
												</c:if>
											</c:when>
											
											<%-- 메소드일 경우 --%>
											<c:when test="${entity.methods.contains(key)}">
												<c:out value="${reflectionInvocator.invoke(row, key)}"/>
											</c:when>
											
										</c:choose>
										
										
									</td>
									
								</c:forEach>
								
								<!-- 수정, 삭제 버튼 -->
								<td style="vertical-align: middle;">
									<span class="edit"><a href="javascript:entityEdit(${id});"><i class="fas fa-edit"></i></a></span>
									<span class="delete ml-2"><a href="javascript:entityDelete(${id});"><i class="fas fa-trash"></i></a></span>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
		    
			</div>
			
			<c:if test="${page.hasContent() }">
				<!-- 수정, 삭제 스크립트 -->
				<script>

				function entityEdit(id) {
					location.href="${admin_entity}/update?e=${entity.name.origin}&page=${pageOffset}&id=" + id;
				}


				
				function entityDelete(id) {
					location.href="${admin_entity}/delete?e=${entity.name.origin}&page=${pageOffset}&id=" + id;
				}
				
				</script>
			</c:if>
			
		</div> <!-- end row -->
		
		
		
		
		<%-- Pagination --%>		
		<c:if test="${page.totalPages > 1}" >
			<ul class="pagination justify-content-center">
						
				<c:if test="${!page.first}" >
					<li class="page-item"><a class="page-link" href="${admin_entity}?e=${entity.name.origin}&page=0">&lt;&lt;</a></li>
				</c:if>
				
				<c:if test="${page.hasPrevious()}" >
					<li class="page-item"><a class="page-link" href="${admin_entity}?e=${entity.name.origin}&page=${pageOffset - 1}">&lt;</a></li>
				</c:if>
				
				<c:forEach var="i" begin="${pageBlock.first}" end="${pageBlock.last}">
					<c:if test="${i eq pageNumber }">
						<li class="page-item active"><a class="page-link" href="javascript:void(0);">${i}</a></li>
					</c:if>
					<c:if test="${i ne pageNumber }">
						<li class="page-item"><a class="page-link" href="${admin_entity}?e=${entity.name.origin}&page=${i-1}">${i}</a></li>
					</c:if>
				</c:forEach>
				
				<c:if test="${page.hasNext()}" >
					<li class="page-item"><a class="page-link" href="${admin_entity}?e=${entity.name.origin}&page=${pageOffset + 1}">&gt;</a></li>
				</c:if>
				
				<c:if test="${!page.last}" >
					<li class="page-item"><a class="page-link" href="${admin_entity}?e=${entity.name.origin}&page=${page.totalPages - 1}">&gt;&gt;</a></li>
				</c:if>
				
			</ul>
		</c:if>
		
		

	</div> <!-- End Container -->

	<%@include file="inc/footer.jsp" %>
	
</body>
</html>