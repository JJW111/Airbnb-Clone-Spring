<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%-- Nav Map 선언 --%>
<c:set var="nav" value="<%=new java.util.LinkedHashMap<String, String>()%>" />
<c:set target="${nav}" property="${groupName}" value="${admin_group}?g=${groupName}"/>
<c:set target="${nav}" property="${entity.name.alias}" value="${admin_entity}?e=${entity.name.origin}"/>
<c:set target="${nav}" property="${lastNav}" value=""/>

<%@include file="inc/header.jsp" %>
	
<div class="container-fluid">
	
	<div class="mb-1 clearfix">
		<a class="float-right" href="${admin_entity}?e=${entity.name.origin}&page=${param.page}">Go Back</a>
	</div>
	
	<div class="mb-5 clearfix">
		
		<c:if test="${entity.columns.hasFileUploadForm() eq true}">
			<c:set var="enctype" value="multipart/form-data" />
		</c:if>
		<c:if test="${entity.columns.hasFileUploadForm() eq false}">
			<c:set var="enctype" value="application/x-www-form-urlencoded" />
		</c:if>
		
		<form:form action="${action}" modelAttribute="entityObj" enctype="${enctype}" method="POST">

			<c:forEach var="setName" items="${fieldSet.keySet()}" >
				<c:set var="fieldList" value="${fieldSet.get(setName)}" />
				
				<table class="table table-text-center">
					<thead class="thead-dark">
						<tr>
							<th colspan="2">${setName}</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="key" items="${fieldList.keySet()}" >
							<c:if test="${entity.columns.containsKey(key)}">
								<c:set var="alias" value="${fieldList.get(key)}" />
								<c:set var="column" value="${entity.columns.get(key)}" />
								<c:set var="formType" value="${column.formType}" />
								<c:set var="fieldType" value="${formType.fieldType}" />
								<c:set var="fieldTypeName" value="${fieldType.name()}" />
								<c:set var="formInfo" value="${formType.formInfo}" />
								<c:set var="value" value="${reflectionInvocator.get(entityObj, key)}" />
								
								<c:if test="${formInfo.blank eq false}">
									<c:set var="required" value="true" />
									<c:set var="requiredHtml" value="required" />
									<c:set var="defaultOptionDisabled" value="true" />
								</c:if>
								<c:if test="${formInfo.blank eq true}">
									<c:set var="required" value="" />
									<c:set var="requiredHtml" value="" />
									<c:set var="defaultOptionDisabled" value="" />
								</c:if>
								
								<tr>
									
									<th style="width: 25%; vertical-align: middle;"><h6>${alias}</h6></th>
									<td class="text-sm-left" style=" vertical-align: middle;">
									
										<c:if test="${not empty formType}">
										
	
											<%-- 필드 타입에 따라 다른 폼 출력 --%>
											<c:choose>
												
												
												
												<%-- 필드 타입이 CHECK_BOX 일 경우 체크박스 --%>
												<c:when test="${fieldTypeName eq 'CHECK_BOX' }">
													<div class="custom-control custom-checkbox">
													    <form:checkbox path="${key}" class="custom-control-input" id="${key}"/>
													    <label class="custom-control-label" for="${key}"></label>
													</div>
												</c:when>
												
												
												
												<%-- 필드 타입이 USERNAME 일 경우 텍스트 input 폼 --%>
												<c:when test="${fieldTypeName eq 'USERNAME' }">
													<c:if test="${edit eq 'ADD'}">
													<div class="form-group">
														<form:input path="${key}" class="form-control" maxlength="${formInfo.maxlength}" placeholder="${formInfo.placeholder}" required="true" />
													</div>
													</c:if>
													
													<c:if test="${edit eq 'UPDATE'}">
														<form:hidden path="${key}" />
														${value}
													</c:if>
												</c:when>
												
												
												
												<%-- 필드 타입이 PASSWORD 일 경우 패스워드 폼 --%>
												<c:when test="${fieldTypeName eq 'PASSWORD' }">
													<c:if test="${edit eq 'ADD'}">
														<div class="form-group mb-2">
															<form:password path="${key}" class="form-control" maxlength="${formInfo.maxlength}" placeholder="${formInfo.placeholder}" required="true" />
														</div>
													</c:if>
													
													<c:if test="${edit eq 'UPDATE'}">
														<form:hidden path="${key}" />
														<p>Encoded: ${value}</p>
														<p><a href="#">Chage Password</a></p>
													</c:if>
												</c:when>
												
												
												
												<%-- 필드 타입이 TEXT입일 경우 텍스트 input 폼 --%>
												<c:when test="${fieldTypeName eq 'TEXT' }">
													<div class="form-group">
														<form:input path="${key}" class="form-control" maxlength="${formInfo.maxlength}" placeholder="${formInfo.placeholder}" required="${required}" />
													</div>
												</c:when>
												
												
												
												<%-- 필드 타입이 TEXT_AREA 일 경우 텍스트 에어리어 폼 --%>
												<c:when test="${fieldTypeName eq 'TEXT_AREA' }">
													<div class="form-group">
														<form:textarea path="${key}" class="form-control" maxlength="${formInfo.maxlength}" rows="${formInfo.rows}" placeholder="${formInfo.placeholder}" required="${required}" />
													</div>
												</c:when>
												
												
												<%-- 필드 타입이  DATE 일 경우 --%>
												<c:when test="${fieldTypeName eq 'DATE' }">
													<fmt:formatDate value="${value}" var="dateString" pattern="yyyy-MM-dd" />
													<div class="form-group">
										                <div class="input-group date" id="datetimepicker-${key}" data-target-input="nearest">
										                    <form:input path="${key}" value="${dateString}" class="form-control datetimepicker-input" data-target="#datetimepicker-${key}" style="max-width: 200px;" required="${required}" />
										                    <div class="input-group-append" data-target="#datetimepicker-${key}" data-toggle="datetimepicker">
										                        <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
										                    </div>
										                </div>
										            </div>
										            <script type="text/javascript">
											            $(function () {
											                $('#datetimepicker-${key}').datetimepicker({
											                    format: 'YYYY-MM-DD',
											                    buttons: {
											                        showToday: true
											                    }
											                });
											                $('#${key}').val('${dateString}')
											            });
											        </script>
											        <c:remove var="dateString" />
												</c:when>		
														
														
																			
												<%-- 필드 타입이  DATETIME 일 경우 --%>
												<c:when test="${fieldTypeName eq 'DATETIME' }">
													<fmt:formatDate value="${value}" var="dateString" pattern="yyyy-MM-dd HH:mm:ss" />
													<div class="form-group">
										                <div class="input-group date" id="datetimepicker-${key}" data-target-input="nearest">
										                    <form:input path="${key}" value="${dateString}" class="form-control datetimepicker-input" data-target="#datetimepicker-${key}" style="max-width: 200px;" required="${required}" />
										                    <div class="input-group-append" data-target="#datetimepicker-${key}" data-toggle="datetimepicker">
										                        <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
										                    </div>
										                </div>
										            </div>
										            <script type="text/javascript">
											            $(function () {
											                $('#datetimepicker-${key}').datetimepicker({
												                format: 'YYYY-MM-DD HH:MM:SS',
											                	buttons: {
											                        showToday: true
											                    }
											                });
											                $('#${key}').val('${dateString}')
											            });
											        </script>
											        <c:remove var="dateString" />
												</c:when>		
															
															
																			
												<%-- 필드 타입이  TIME 일 경우 --%>
												<c:when test="${fieldTypeName eq 'TIME' }">
													<fmt:formatDate value="${value}" var="dateString" pattern="HH:mm:ss" />
													<div class="form-group">
										                <div class="input-group date" id="datetimepicker-${key}" data-target-input="nearest">
										                    <form:input path="${key}" value="${dateString}" class="form-control datetimepicker-input" data-target="#datetimepicker-${key}" style="max-width: 200px;" required="${required}" />
										                    <div class="input-group-append" data-target="#datetimepicker-${key}" data-toggle="datetimepicker">
										                        <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
										                    </div>
										                </div>
										                <form:errors path="${key}" class="error" />
										            </div>
										            <script type="text/javascript">
											            $(function () {
											                $('#datetimepicker-${key}').datetimepicker({
												                format: 'HH:MM:SS',
											                	buttons: {
											                        showToday: true
											                    }
											                });
											                $('#${key}').val('${dateString}')
											            });
											        </script>
											        <c:remove var="dateString" />
												</c:when>
												
												
												
												
	
												<%-- 필드 타입이  단일 파일 업로드 폼일 경우 --%>
												<c:when test="${fieldTypeName eq 'FILE_UPLOAD_FORM' or fieldTypeName eq 'IMAGE_UPLOAD_FORM'}">
													<c:if test="${not empty value and not empty value.originalFilename}">
														<c:set var="fileId" value="${key}-hidden-${value.id}" />
														<form:hidden path="${key}.id" 					file-id="${fileId}" />
														<form:hidden path="${key}.originalFilename" 	file-id="${fileId}" />
														<form:hidden path="${key}.path" 				file-id="${fileId}" />
														<form:hidden path="${key}.uploadPath" 			file-id="${fileId}" />
													</c:if>
													<%-- 단일 업로드 폼 --%>
													<div class="input-group">
														<div class="custom-file">
													      <input type="file" id="${formInfo.fileFormName}" name="${formInfo.fileFormName}" class="custom-file-input" accept="${formInfo.accept}" onChange="customFileInputOnChange(this); removeFileHidden('${fileId}');" ${requiredHtml} >
													      <label class="custom-file-label">
													      	<c:if test="${value ne null}">
													      		${value.originalFilename}
													      	</c:if>
													      	<c:if test="${value eq null}">
													      		${formInfo.labelText}
													      	</c:if>
													      </label>
													    </div>
													    <div class="input-group-append">
														      <span class="input-group-text" onclick="setFileToNull('${formInfo.fileFormName}', '${formInfo.labelText}'); removeFileHidden('${fileId}');"><i class="fas fa-times"></i></span>
														</div>
												    </div>
												    <c:remove var="fileId" />
												</c:when>			
												
												
												
												
												
												<%-- 필드 타입이  다중 파일 업로드 폼일 경우 --%>
												<c:when test="${fieldTypeName eq 'MULTIPLE_FILE_UPLOAD_FORM' or fieldTypeName eq 'MULTIPLE_IMAGE_UPLOAD_FORM' }">
													<%-- 멀티플 업로드 폼 --%>
													<div class="multiple-file-upload" form-name="${formInfo.fileFormName}" style="display: none;">
														<div class="input-group mb-2 file-upload">
															<div class="custom-file">
														      <input type="file" class="custom-file-input" accept="${formInfo.accept}" onChange="customFileInputOnChange(this)" ${requiredHtml} >
														      <label class="custom-file-label">${formInfo.labelText}</label>
														    </div>
														    <div class="input-group-append">
																<span class="input-group-text" onclick="deleteFileInput(this);"><i class="fas fa-times"></i></span>
															</div>
														</div>
												    </div>
													<c:if test="${not empty value and !value.isEmpty()}">
														<c:forEach var="item" varStatus="status" items="${value}">
															<c:if test="${not empty item.originalFilename}">
																<c:set var="fileId" value="${key}-hidden-${item.id}" />
																<form:hidden path="${key}[${status.index}].id" 					file-id="${fileId}" />
																<form:hidden path="${key}[${status.index}].originalFilename" 	file-id="${fileId}" />
																<form:hidden path="${key}[${status.index}].path" 				file-id="${fileId}" />
																<form:hidden path="${key}[${status.index}].uploadPath" 			file-id="${fileId}" />
																
																<div class="input-group mb-2 file-upload">
																	<div class="custom-file">
																      <input type="file" class="custom-file-input" accept="${formInfo.accept}" onChange="customFileInputOnChange(this)" ${requiredHtml} >
																      <label class="custom-file-label">${item.originalFilename}</label>
																    </div>
																    <div class="input-group-append">
																		<span class="input-group-text" onclick="deleteFileInput(this); removeFileHidden('${fileId}')"><i class="fas fa-times"></i></span>
																	</div>
																</div>
																<c:remove var="fileId" />
															</c:if>
														</c:forEach>
													</c:if>
												    <button type="button" class="btn btn-primary btn-block" multiple-file-upload-add>Add</button>
												</c:when>			
												
												
												
												
												<%-- 필드 타입이 INTEGER 타입일 경우 --%>
												<c:when test="${fieldTypeName eq 'INTEGER' }">
													<div class="form-group">
														<form:input path="${key}" class="form-control" id="${key}" onKeydown="setInputFilter(this, integerFilter)" required="${required}" />
													</div>
												</c:when>
												
												
												
												<%-- 필드 타입이 FLOAT 타입일 경우 --%>
												<c:when test="${fieldTypeName eq 'FLOAT' }">
													<div class="form-group">
														<form:input path="${key}" class="form-control" id="${key}" onKeydown="setInputFilter(this, floatFilter)" maxlength="${formInfo.maxlength}" placeholder="${formInfo.placeholder}" required="${required}" />
													</div>
												</c:when>
												
												
												<%-- 필드 타입이 SELECT_BOX 일 경우 select box 출력 --%>
												<c:when test="${fieldTypeName eq 'SELECT_BOX'}">
													<form:select path="${key}" class="custom-select" required="${required}" >
														<form:option disabled="${defaultOptionDisabled}" value="" label="${formInfo.defaultOption}" />
														<form:options items="${value.values()}" />
													</form:select>
												</c:when>
											
												
												<%-- 필드 타입이 MAP_SELECT_BOX 일 경우 select box 출력 --%>
												<c:when test="${fieldTypeName eq 'MAP_SELECT_BOX'}">
													<form:select path="${key}" class="custom-select" required="${required}" >
														<form:option disabled="${defaultOptionDisabled}" value="" label="${formInfo.defaultOption}" />
														<form:options items="${reflectionInvocator.invoke(selectValues, formInfo.method)}" />
													</form:select>
												</c:when>
												
												
												<%-- 필드 타입이 JOIN_ONE_TEXT 타입일 경우 텍스트 input 폼 --%>
												<c:when test="${fieldTypeName eq 'JOIN_ONE_TEXT' }">
													<c:if test="${not empty value}" >
														<c:set var="joinValue" value="${reflectionInvocator.get(value, formInfo.field)}" />
													</c:if>
													<div class="form-group">
														<form:input path="${key}" class="form-control" value="${joinValue}" maxlength="${formInfo.maxlength}" placeholder="${formInfo.placeholder}" required="${required}" />
													</div>
													<c:remove var="joinValue" />
												</c:when>
												
												
												<%-- 필드 타입이 JOIN_ONE 일 경우 select box 출력 --%>
												<c:when test="${fieldTypeName eq 'JOIN_ONE'}">
													<form:select path="${key}" class="custom-select" required="${required}" >
														<form:option disabled="${defaultOptionDisabled}" value="" label="${formInfo.defaultOption}" />
														<c:forEach var="option" items="${reflectionInvocator.invoke(selectValues, formInfo.method)}">
															<c:if test="${not empty value and empty optionSelected and option.equals(value)}">
																<c:set var="optionSelected" value="true" />
															</c:if>
															<form:option selected="${optionSelected}" value="${reflectionInvocator.get(option, formInfo.itemValue)}" label="${reflectionInvocator.get(option, formInfo.itemLabel)}"/>
															<c:if test="${not empty optionSelected and optionSelected eq 'true'}">
																<c:set var="optionSelected" value="" />
															</c:if>
														</c:forEach>
													</form:select>
												</c:when>
												
												
												<%-- 필드 타입이 JOIN_MANY 일 경우 select box 출력 --%>
												<c:when test="${fieldTypeName eq 'JOIN_MANY'}">
													<form:select path="${key}" class="custom-select" multiple="true" required="${required}" >
														<form:option disabled="${defaultOptionDisabled}" value="" label="${formInfo.defaultOption}" />
														<form:options items="${reflectionInvocator.invoke(selectValues, formInfo.method)}" itemLabel="${formInfo.itemLabel}" itemValue="${formInfo.itemValue}" />
													</form:select>
												</c:when>
												
											</c:choose>
											
											<form:errors path="${key}" class="error" />
											
										</c:if>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				
			</c:forEach>
			
			<button type="submit" class="btn btn-success float-right">${cta}</button>
			
		</form:form>
  
  		</div>
  
</div>

<script>
function setFileToNull(id, labelText) {
	var input = document.getElementById(id);
	$(input).val('');
	$(input).siblings(".custom-file-label").addClass("selected").html(labelText);
}


function customFileInputOnChange(input) {
	var fileName = $(input).val().split("\\").pop();
	$(input).siblings(".custom-file-label").addClass("selected").html(fileName);
}


function deleteFileInput(e) {
	$(e).closest('.file-upload').remove();
}


function removeFileHidden(hiddenId) {
	if (hiddenId) {
		var hidden = $("[file-id=" + hiddenId + "]");
		if (hidden != null) {
			$(hidden).remove();
		}
	}
}


$('button[multiple-file-upload-add]').on("click", function() {
	var multipleFileUpload = $(this).siblings(".multiple-file-upload");
	var content = $(multipleFileUpload).html();
	var formName = $(multipleFileUpload).attr('form-name');
	$(this).before(content);
	$(this).prev().find('input[type=file]').attr('name', formName);
}).trigger("click");
</script>

<%@include file="inc/footer.jsp" %>
	
