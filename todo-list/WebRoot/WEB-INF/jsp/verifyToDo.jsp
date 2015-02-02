<%@ page contentType="text/html" %>
<%@ page session="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Create TODO - Verify TODO</title>
	</head>
	<body>
		<h1>Create TODO - Verify TODO</h1>
		
		<form:form action="flows.html">
			Name: ${toDo.title}<br/>
			Deadline: <fmt:formatDate value="${toDo.deadline}"/><br/>
			Comments: <pre>${toDo.comments}</pre><br/>
			
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
			<input type="submit" name="_eventId_submit" value="Submit"/>
			<input type="submit" name="_eventId_cancel" value="Cancel"/>
		</form:form>
	</body>
</html>
