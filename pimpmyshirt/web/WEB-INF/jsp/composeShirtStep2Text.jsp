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
			
			<h2><spring:message code="composeShirt.step2"/></h2>
		
			<table class="mainTable">
				<form action="<c:url value="flow.html"/>" method="post">
					<input type="hidden" name="_eventId" value="ok"/>
					<input type="hidden" name="_flowExecutionId" value="${flowExecutionId}"/>
					<input type="hidden" name="_currentStateId" value="step2Text"/>
				
					<tr>
						<td width="25%"><spring:message code="composeShirt.type"/></td>
						<td><spring:message code="${shirtFbo.shirt.longSleeve ? 'composeShirt.type.longSleeve' : 'composeShirt.type.tShirt'}"/></td>
					</tr>
					<tr>
						<td width="25%"><spring:message code="composeShirt.color"/></td>
						<td><spring:message code="composeShirt.color.${fn:toLowerCase(shirtFbo.shirt.color)}"/></td>
					</tr>
					<tr>
						<td width="25%"><spring:message code="composeShirt.graphical"/></td>
						<td>${shirtFbo.graphical}</td>
					</tr>
					<tr>
						<td width="25%"><spring:message code="composeShirt.text"/></td>
						<td>
							<spring:bind path="shirtFbo.shirt.print">
								<TEXTAREA name="${status.expression}" cols="50" rows="10"><c:out value="${status.value}" escapeXml="true"/></TEXTAREA>
								<c:if test="${status.error}">
									<DIV class="error">
						                <c:forEach items="${status.errorMessages}" var="error">
						                	${error}
						                </c:forEach>								
									</DIV>
								</c:if>
							</spring:bind>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input type="submit" value="<spring:message code="composeShirt.action.next"/>"/>
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
