<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC Form Handling</title>
</head>
<body>

	<h2>Result</h2>
	${result}
	
	<hr />
	<form:form method="POST" action="echo">
		<table>
			<tr>
				<td><form:label path="item">Item</form:label></td>
				<td><form:input path="item" /></td>
			</tr>
			<tr>
				<td><form:label path="categorie">categorie</form:label></td>
				<td><form:input path="categorie" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>