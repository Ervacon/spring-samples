<%@ page contentType="text/html" %>
<%@ page session="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>TODO List</title>
	</head>
	<body>
		<h1>TODO List</h1>
		
		<table border="1" width="100%">
			<tr>
				<th>Title</th>
				<th>Deadline</th>
				<th>Comments</th>
			</tr>
			<c:forEach items="${toDos}" var="toDo">
				<tr>
					<td><c:out value="${toDo.title}"/></td>
					<td align="center"><fmt:formatDate value="${toDo.deadline}"/></td>
					<td><pre><c:out value="${toDo.comments}" default=" "/></pre></td>
				</tr>
			</c:forEach>
		</table>
		
		<a href="<c:url value="/flows.html?_flowId=create-todo-flow"/>">
			Create TODO
		</a>
	</body>
</html>