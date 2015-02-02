<%@ include file="/WEB-INF/jspf/header.jspf"%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/pimpmyshirt.css"/>">
		
		<title><spring:message code="app.title"/></title>
	</head>
	<body>
		<div class="containerTop"></div>
		<div class="container">
			<h1><spring:message code="app.title"/></h1>
			
			<h2><spring:message code="composeShirt.step3"/></h2>
		
			<table class="mainTable">
				<form action="<c:url value="flow.html"/>" method="post">
					<input type="hidden" name="_eventId" value="ok"/>
					<input type="hidden" name="_flowExecutionId" value="${flowExecutionId}"/>
					<input type="hidden" name="_currentStateId" value="step3"/>
				
					<tr>
						<td width="25%"><spring:message code="composeShirt.preview"/></td>
						<td align="center"><ui:shirtPreview shirt="${shirtFbo.shirt}"/></td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input type="submit" value="<spring:message code="composeShirt.action.confirm"/>"/>
						</td>
					</tr>
				</form>
			</table>
	
			<a href="<c:url value="flow.html?_eventId=cancel&_flowExecutionId=${flowExecutionId}"/>">
				<spring:message code="composeShirt.action.cancel"/>
			</a>
		</div>
	</body>
</html>
