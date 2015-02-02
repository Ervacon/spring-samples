<%@ page language="java"
		 isErrorPage="true"
		 session="false"
		 contentType="text/html; charset=ISO-8859-1"
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/pimpmyshirt.css"/>">
		
		<title><spring:message code="app.title"/></title>
	</head>
	<body>
		<div class="containerTop"></div>
		<div class="container">
			<h1><spring:message code="app.title"/></h1>
			
			<c:if test="${exception!=null}">
				<h2>${exception.message}</h2>
				<pre>
<%
	exception.printStackTrace(new java.io.PrintWriter(out, true));
%>
				</pre>
			</c:if>
										
			<c:if test="${requestScope['javax.servlet.error.exception']!=null }">
				<h2>${requestScope['javax.servlet.error.exception'].message}</h2>
				<pre>
<%
	Throwable servletThrowable=(Throwable)request.getAttribute("javax.servlet.error.exception");
	servletThrowable.printStackTrace(new java.io.PrintWriter(out, true));
%>
				</pre>
			</c:if>
		</div>
	</body>
</html>
