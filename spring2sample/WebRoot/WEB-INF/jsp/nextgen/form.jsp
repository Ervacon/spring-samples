<%@ include file="/WEB-INF/jspf/header.jspf"%>

<html>
	<head>
		<title>Edit User: ${command.firstName} ${command.lastName}</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/sample.css"/>" />
	</head>

	<body>
		<h1>Edit User: ${command.firstName} ${command.lastName}</h1>
		<form:form>
			<form:errors path="*" cssClass="error" />
			<table>
				<%-- firstName is a String --%>
				<tr>
					<td>
						First Name:
					</td>
					<td>
						<form:input path="firstName" />
					</td>
					<td>
						<form:errors path="firstName" cssClass="error" />
					</td>
				</tr>

				<%-- lastName is a String --%>
				<tr>
					<td>
						Last Name:
					</td>
					<td>
						<form:input path="lastName" />
					</td>
					<td>
						<form:errors path="lastName" cssClass="error" />
					</td>
				</tr>

				<%-- country is a Country object --%>
				<tr>
					<td>
						Country:
					</td>
					<td>
						<form:select path="country">
							<form:option value="" label="&lt;Please Select&gt;" />
							<form:options items="${countryList}" itemValue="code" itemLabel="name" />
						</form:select>
					</td>
					<td></td>
				</tr>

				<%-- skills is a List<Skill> where Skill is an enum --%>
				<tr>
					<td>
						Skills:
					</td>
					<td>
						<form:select path="skills" items="${skills}" />
					</td>
					<td></td>
				</tr>

				<%-- notes is a String --%>
				<tr>
					<td>
						Notes:
					</td>
					<td>
						<form:textarea path="notes" rows="3" cols="20" />
					</td>
					<td></td>
				</tr>

				<%-- sex is a primitive char --%>
				<tr>
					<td>
						Sex:
					</td>
					<td>
						Male:
						<form:radiobutton path="sex" value="M" />
						<br />
						Female:
						<form:radiobutton path="sex" value="F" />
					</td>
					<td></td>
				</tr>

				<%-- house is a String --%>
				<tr>
					<td>
						House:
					</td>
					<td>
						<form:select path="house">
							<form:option value="Gryffindor" />
							<form:option value="Hufflepuff" />
							<form:option value="Ravenclaw" />
							<form:option value="Slytherin" />
						</form:select>
					</td>
				</tr>

				<%-- preferences is a Preferences object and preferences.receiveNewsletter is a primitive boolean --%>
				<tr>
					<td>
						Subscribe to newsletter?:
					</td>
					<td>
						<form:checkbox path="preferences.receiveNewsletter" />
					</td>
					<td></td>
				</tr>

				<%-- preferences is a Preferences object and preferences.interests is a String[] --%>
				<tr>
					<td>
						Interests:
					</td>
					<td>
						Quidditch:
						<form:checkbox path="preferences.interests" value="Quidditch" />
						Herbology:
						<form:checkbox path="preferences.interests" value="Herbology" />
						Defence Against the Dark Arts:
						<form:checkbox path="preferences.interests" value="Defence Against the Dark Arts" />
					</td>
					<td></td>
				</tr>

				<%-- favouriteColour is a Colour enum --%>
				<tr>
					<td>
						Favourite Colour:
					</td>
					<td>
						<form:select path="favouriteColour">
							<c:forEach items="${colours}" var="colour">
								<form:option value="${colour}" label="${colour}" />
							</c:forEach>
						</form:select>
					</td>
				</tr>

				<tr>
					<td colspan="3">
						<input type="submit" value="Save Changes" />
					</td>
				</tr>
			</table>
		</form:form>
		
		<a href="<c:url value="/nextgen/list"/>">Back</a>
	</body>
</html>
