<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ attribute name="shirt" required="true" type="org.pimpmyshirt.domain.Shirt" %>

<table border="1">
	<tr style="font-weight: bold; font-family: 'Trebuchet MS'; background-image: url('<c:url value="/images/preview/${shirt.longSleeve ? 'longSleeve' : 'tShirt'}/${fn:toLowerCase(shirt.color)}.gif"/>');" height="300px">
		<td align="center" valign="middle" width="300px">
			<c:choose>
				<c:when test="${shirt.print.graphical}">
					<c:url value="/image.html" var="imgUrl">
						<c:if test="${!empty shirt.id}">
							<c:param name="id" value="${shirt.id}"/>
						</c:if>
						<c:if test="${!empty flowExecutionId}">
							<c:param name="_flowExecutionId" value="${flowExecutionId}"/>
						</c:if>
					</c:url>
					<img src="${imgUrl}"/>
				</c:when>
				<c:otherwise>
					${shirt.print.text}
				</c:otherwise>
			</c:choose>
			<br/><br/><br/><br/><br/>
		</td>
	</tr>
</table>
