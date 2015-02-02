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
			
			<h2><spring:message code="composeShirt.step1"/></h2>
			
			<table class="mainTable">
				<form action="<c:url value="flow.html"/>" method="post">
					<input type="hidden" name="_eventId" value="ok"/>
					<input type="hidden" name="_flowExecutionId" value="${flowExecutionId}"/>
					<input type="hidden" name="_currentStateId" value="step1"/>
				
					<tr>
						<td width="25%">
							<spring:message code="composeShirt.type"/>
						</td>
						<td>
							<spring:bind path="shirtFbo.shirt.longSleeve">
								<select name="${status.expression}">
									<option value="false" <c:if test="${!status.value}">selected</c:if>>
										<spring:message code="composeShirt.type.tShirt"/>
									</option>
									<option value="true" <c:if test="${status.value}">selected</c:if>>
										<spring:message code="composeShirt.type.longSleeve"/>
									</option>
								</select>
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
						<td width="25%">
							<spring:message code="composeShirt.color"/>
						</td>
						<td>
							<spring:bind path="shirtFbo.shirt.color">
								<select name="${status.expression}">
									<option value="">&lt;<spring:message code="composeShirt.color.select"/>&gt;</option>
									<c:forEach items="${colors}" var="color">
										<spring:transform value="${color}" var="colorCode"/>
										<option value="${colorCode}" <c:if test="${status.value == colorCode}">selected</c:if>>
											<spring:message code="composeShirt.color.${fn:toLowerCase(colorCode)}"/>
										</option>
									</c:forEach>
								</select>
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
						<td width="25%">
							<spring:message code="composeShirt.graphical"/>
						</td>
						<td>
							<spring:bind path="shirtFbo.graphical">
								<input type="hidden" name="_${status.expression}" value="visible"/>
								<input type="checkbox" name="${status.expression}" value="true" <c:if test="${status.value}">checked</c:if>>
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
