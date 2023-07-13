<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <jsp:include page="head.jsp">
        <jsp:param name="head" value="head" />
    </jsp:include>
    <link rel="stylesheet" href="../css/lista-todos.css">
</head>
<body>

<jsp:include page="nav.jsp">
    <jsp:param name="nav" value="nav" />
</jsp:include>

<div class="banners-pagina-cuidadores">
    <div class="titulos-todos">
        <div>
            <h1>Cuidadores</h1>
        </div>
        <div>
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/home">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Cuidadores</li>
                </ol>
            </nav>
        </div>
    </div>
</div>

<div id="contenido-home">
    <c:if test="${not empty cuidadores}">
        <div class="cards-container">
            <c:forEach  var="cuidado" items="${cuidadores}">
                <div class="card">
                    <img src="img/${cuidado.imagen}" class="card-img" alt="Cuidador....">
                    <div class="card-body">
                        <h5 class="card-title" style="white-space: nowrap">${cuidado.nombre}</h5>
                        <form:form action="cuidador/detalle" method="GET">
                            <input name="id" value=${cuidado.id} id=${cuidado.id} type="hidden" class="form-control"/>
                            <button class="dog-paw-button">Detalles</button>
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
</body>
</html>
