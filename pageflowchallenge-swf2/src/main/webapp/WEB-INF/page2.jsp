<%@ page language="java" session="false" contentType="text/html; charset=UTF-8" %>

<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Page 2</title>
	</head>
	<body>
		<h1>Page 2</h1>

		<p>
			Page 1 input was: ${page1Input}
		</p>

		<!-- use POST for the form! -->
		<form method="post" action="${flowExecutionUrl}">
			Input: <input type="text" name="page2Input" value="${page2Input}"/><br/>
			<input type="submit" name="_eventId_next" value="Next"/>
		</form>

		<p>
			<a href="${flowExecutionUrl}&_eventId=back">Back</a>
		</p>
	</body>
</html>
