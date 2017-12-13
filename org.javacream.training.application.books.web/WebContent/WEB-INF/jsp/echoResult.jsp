<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC Form Handling</title>
</head>
<body>

	<h2>Echo Result</h2>
	${echoMessage}
	
	<hr />
	<form:form method="POST" action="echo">
		<table>
			<tr>
				<td><form:label path="message">Message</form:label></td>
				<td><form:input path="message" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Echo again" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>