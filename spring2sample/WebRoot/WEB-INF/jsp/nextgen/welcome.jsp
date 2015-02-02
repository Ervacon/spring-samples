<%@ include file="/WEB-INF/jspf/header.jspf"%>

<html>
	<head>
		<title>Spring 2 Sample</title>
	</head>
	<body>
		<h1>Welcome to nextgen!</h1>
		
		<ul>
			<li><a href="<c:url value="/nextgen/list"/>">Edit user list</a></li>
		</ul>
		
		<a href="<c:url value="/"/>">Back</a>
	</body>
</html>