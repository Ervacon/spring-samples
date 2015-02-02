<%@ page language="java"
		 isErrorPage="true"
		 session="false"
		 contentType="text/html; charset=ISO-8859-1"
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Spring 2 Sample</title>
	</head>
	<body>
		<h1>An error occured</h1>
			
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
	</body>
</html>
