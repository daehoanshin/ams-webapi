<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ABL EMAIL승인 :: 승인</title>

<%
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache");
 response.setDateHeader("Expires",0);
%>

<%@ include file="../commons/_header.jspf"%>

</head>
<body>
	
	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<h1>승인</h1>
				</div>
				
				<c:choose>
				<c:when test="${empty result}">
					<form:form cssClass="form-horizontal" action="/approval/${authenticate.wiid}" method="post">
					<button type="submit" class="btn btn-primary">승인처리</button>
				</form:form>
				</c:when>
				<c:otherwise>
					<div class="control-group">
						<label class="control-label" for="userId">사용자 아이디 : ${userId}</label>
						<div class="controls">
							<form:input path="userId" readonly="true" value="${wiid} : ${result}"/>
						</div>
					</div>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>