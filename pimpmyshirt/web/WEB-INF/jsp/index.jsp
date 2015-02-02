<%@ include file="/WEB-INF/jspf/header.jspf"%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/pimpmyshirt.css"/>">
		
		<title><spring:message code="app.title"/></title>
		
		<script type="text/javascript">
			function confirmDelete(shirtId) {
				if (confirm('<spring:message code="index.action.deleteShirt.confrom"/>')) {
					window.location = '<c:url value="/index.html?op=handleDelete&id="/>' + shirtId;
				}
			}
		</script>
	</head>
	<body>
		<div class="containerTop"></div>
		<div class="container">
			<h1><spring:message code="app.title"/></h1>
	
			<spring:hasBindErrors name="shirtRatings">
				<DIV class="error">
					<spring:message message="${errors.globalError}"/>
				</DIV>
			</spring:hasBindErrors>
			
			<h2><spring:message code="index.shirtList"/></h2>
	
			<table class="mainTable">
				<c:choose>
					<c:when test="${empty shirtRatings}">
						<tr>
							<td>
								<spring:message code="index.noShirts"/>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<form action="<c:url value="/index.html"/>" method="post">
							<input type="hidden" name="op" value="handleRate"/>
							<tr>
								<th><spring:message code="index.column.shirt"/></th>
								<th><spring:message code="index.column.rating"/></th>
							</tr>
							<c:forEach items="${shirtRatings}" var="shirtRating">
								<tr>
									<td align="center">
										<ui:shirtPreview shirt="${shirtRating.shirt}"/>
										
										<a href="javascript:confirmDelete('${shirtRating.shirt.id}')">
											<spring:message code="index.action.deleteShirt"/>
										</a>
									</td>
									<td align="center">
										${shirtRating.totalNumberOfVotes} <spring:message code="index.votes"/>
										(
										${shirtRating.numberOfLowVotes} <spring:message code="index.votes.low"/>;
										${shirtRating.numberOfMediumVotes} <spring:message code="index.votes.medium"/>;
										${shirtRating.numberOfHighVotes} <spring:message code="index.votes.high"/>
										)
										<br/>
										<spring:message code="index.rate"/>
										<select name="id_${shirtRating.shirt.id}">
											<option value="">&lt;<spring:message code="index.rate.select"/>&gt;</option>
											<c:forEach items="${ratings}" var="rating">
												<option value="${rating}">
													<spring:message code="index.rate.${fn:toLowerCase(rating)}"/>
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td>&nbsp;</td>
								<td align="center">
									<input type="submit" value="<spring:message code="index.action.rateShirt"/>"/>
								</td>
							</tr>
						</form>
					</c:otherwise>
				</c:choose>
			</table>
			
			<a href="<c:url value="flow.html?_flowId=composeShirt"/>">
				<spring:message code="index.action.addShirt"/>
			</a>
		</div>
	</body>
</html>
