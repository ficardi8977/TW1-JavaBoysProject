<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <h1>Nuestros Cuidadores</h1>
</div>

<div id="contenido-home">
    <c:if test="${not empty cuidadores}">
        <div class="cards-container">
            <c:forEach  var="cuidado" items="${cuidadores}">
                <div class="card">
                    <img src="img/${cuidado.imagen}" class="card-img-top" alt="Cuidador....">
                    <div class="card-body">
                        <h5 class="card-title">${cuidado.nombre}</h5>
                        <form:form action="cuidador/detalle" method="GET">
                            <input name="id" value=${cuidado.id} id=${cuidado.id} type="hidden" class="form-control"/>
                            <button class="dog-paw-button">Detalle</button>
                        </form:form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

<c:if test="${empty cuidadores}">
    <h3><span>No hay cuidadores</span></h3>
    <br>
</c:if>

<jsp:include page="foot.jsp">
    <jsp:param name="foot" value="foot" />
</jsp:include>
<link href="../css/mascotas.css" rel="stylesheet" >
</body>
</html>