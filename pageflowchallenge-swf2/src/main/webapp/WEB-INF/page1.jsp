<%@ page language="java" session="false" contentType="text/html; charset=UTF-8" %>

<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Page 1</title>
	</head>
	<body>
		<h1>Page 1</h1>

		<!-- use POST for the form! -->
		<form name="page1Form" method="post" action="${flowExecutionUrl}">
			Input: <input type="text" name="page1Input" value="${page1Input}"/><br/>
			<input type="submit" name="_eventId_next" value="Next"/>
		</form>
	</body>
</html>
