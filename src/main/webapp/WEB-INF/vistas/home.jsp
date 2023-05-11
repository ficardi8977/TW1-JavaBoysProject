<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
	<jsp:include page="head.jsp">
		<jsp:param name="head" value="head" />
	</jsp:include>
</head>
	<body>

		<jsp:include page="nav.jsp">
			<jsp:param name="nav" value="nav" />
		</jsp:include>

		<div class = "container">
			<h1>Bienvenidos a Taller Web 1</h1>
		</div>


	<jsp:include page="foot.jsp">
		<jsp:param name="foot" value="foot" />
	</jsp:include>

	</body>
</html>