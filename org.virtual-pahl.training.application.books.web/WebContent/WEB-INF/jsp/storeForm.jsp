<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC Form Handling</title>
</head>
<body>

	<h2>Input</h2>
	<form:form method="POST" action="store" modelAttribute="command">
		<table>
			<tr>
				<td><form:label path="item">item</form:label></td>
				<td><form:input path="item" /></td>
			</tr>
			<tr>
				<td><form:label path="categorie">categorie</form:label></td>
				<td><form:input path="categorie" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>