<%@ include file="/WEB-INF/jspf/header.jspf"%>

<html>
	<head>
		<title>Edit User: ${command.firstName} ${command.lastName}</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/sample.css"/>" />
	</head>

	<body>
		<h1>Edit User: ${command.firstName} ${command.lastName}</h1>
		<form method="POST">
			<spring:hasBindErrors name="command">
				<span class="error">
					<c:forEach items="${errors.allErrors}" var="error">
						${error.defaultMessage}<br/>
					</c:forEach>
				</span>
			</spring:hasBindErrors>
			<table>
				<%-- firstName is a String --%>
				<tr>
					<td>
						First Name:
					</td>
					<spring:bind path="command.firstName">
						<td>
							<input name="${status.expression}" type="text" value="${status.value}"/>
						</td>
						<td>
							<c:if test="${status.error}">
								<span class="error">
									<c:forEach items="${status.errorMessages}" var="error">
										${error}<br/>
									</c:forEach>
								</span>
							</c:if>
						</td>
					</spring:bind>
				</tr>

				<%-- lastName is a String --%>
				<tr>
					<td>
						Last Name:
					</td>
					<spring:bind path="command.lastName">
						<td>
							<input name="${status.expression}" type="text" value="${status.value}"/>
						</td>
						<td>
							<c:if test="${status.error}">
								<span class="error">
									<c:forEach items="${status.errorMessages}" var="error">
										${error}<br/>
									</c:forEach>
								</span>
							</c:if>
						</td>
					</spring:bind>
				</tr>

				<%-- country is a Country object --%>
				<tr>
					<td>
						Country:
					</td>
					<td>
						<spring:bind path="command.country">
							<select name="${status.expression}">
								<option value="">&lt;Please Select&gt;</option>
								<c:forEach items="${countryList}" var="country">
									<option value="${country.code}" ${status.value==country.code ? 'selected' : ''}>${country.name}</option>
								</c:forEach>
							</select>
						</spring:bind>
					</td>
					<td></td>
				</tr>

				<%-- skills is a List<Skill> where Skill is an enum --%>
				<tr>
					<td>
						Skills:
					</td>
					<td>
						<spring:bind path="command.skills">
							<select name="${status.expression}" multiple="true">
								<c:forEach items="${skills}" var="skill">
									<option value="${skill}" ${fn:contains(status.value, skill) ? 'selected' : ''}>${skill}</option>
								</c:forEach>
							</select>
						</spring:bind>
					</td>
					<td></td>
				</tr>
				
				<%-- notes is a String --%>
				<tr>
					<td>
						Notes:
					</td>
					<td>
						<spring:bind path="command.notes">
							<textarea name="${status.expression}" rows="3" cols="20">${status.value}</textarea>
						</spring:bind>
					</td>
					<td></td>
				</tr>

				<%-- sex is a primitive char --%>
				<tr>
					<td>
						Sex:
					</td>
					<td>
						<spring:bind path="command.sex">
							<spring:transform value="${status.value}" var="sexStr"/>
							Male:
							<input name="${status.expression}" type="radio" value="M" ${sexStr=='M' ? 'checked' : ''}/>
							<br />
							Female:
							<input name="${status.expression}" type="radio" value="F" ${sexStr=='F' ? 'checked' : ''}/>
						</spring:bind>
					</td>
					<td></td>
				</tr>

				<%-- house is a String --%>
				<tr>
					<td>
						House:
					</td>
					<td>
						<spring:bind path="command.house">
							<select name="${status.expression}">
								<option value="Gryffindor" ${status.value=='Gryffindor' ? 'selected' : ''}>Gryffindor</option>
								<option value="Hufflepuff" ${status.value=='Hufflepuff' ? 'selected' : ''}>Hufflepuff</option>
								<option value="Ravenclaw" ${status.value=='Ravenclaw' ? 'selected' : ''}>Ravenclaw</option>
								<option value="Slytherin" ${status.value=='Slytherin' ? 'selected' : ''}>Slytherin</option>
							</select>
						</spring:bind>
					</td>
				</tr>

				<%-- preferences is a Preferences object and preferences.receiveNewsletter is a primitive boolean --%>
				<tr>
					<td>
						Subscribe to newsletter?:
					</td>
					<td>
						<spring:bind path="command.preferences.receiveNewsletter">
							<input name="${status.expression}" type="checkbox" value="true" ${status.value ? 'checked' : ''}/>
							<input name="_${status.expression}" type="hidden" value="1"/>
						</spring:bind>
					</td>
					<td></td>
				</tr>

				<%-- preferences is a Preferences object and preferences.interests is a String[] --%>
				<tr>
					<td>
						Interests:
					</td>
					<td>
						<spring:bind path="command.preferences.interests">
							Quidditch:
							<input name="${status.expression}" type="checkbox" value="Quidditch" 
								${fn:contains(fn:join(status.value, ';'), 'Quidditch') ? 'checked' : ''}/>
							Herbology:
							<input name="${status.expression}" type="checkbox" value="Herbology"
								${fn:contains(fn:join(status.value, ';'), 'Herbology') ? 'checked' : ''}/>
							Defence Against the Dark Arts:
							<input name="${status.expression}" type="checkbox" value="Defence Against the Dark Arts"
								${fn:contains(fn:join(status.value, ';'), 'Defence Against the Dark Arts') ? 'checked' : ''}/>
						</spring:bind>
					</td>
					<td></td>
				</tr>

				<%-- favouriteColour is a Colour enum --%>
				<tr>
					<td>
						Favourite Colour:
					</td>
					<td>
						<spring:bind path="command.favouriteColour">
							<select name="${status.expression}">
								<c:forEach items="${colours}" var="colour">
									<option value="${colour}" ${status.value==colour ? 'selected' : ''}>${colour}</option>
								</c:forEach>
							</select>
						</spring:bind>
					</td>
				</tr>

				<tr>
					<td colspan="3">
						<input type="submit" value="Save Changes" />
					</td>
				</tr>
			</table>
		</form>
		
		<a href="<c:url value="/classic/list"/>">Back</a>
	</body>
</html>
