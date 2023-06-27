<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="head.jsp">
        <jsp:param name="head" value="head" />
    </jsp:include>
</head>

<body>
<!-- NAV -->
<jsp:include page="nav.jsp">
    <jsp:param name="nav" value="nav" />
</jsp:include>
<!-- NAV -->

Error: ${msg}!!!

<!-- FOOTER -->
<jsp:include page="foot.jsp">
    <jsp:param name="foot" value="foot" />
</jsp:include>
<!-- FOOTER -->
    </div>
  </body>
</html>