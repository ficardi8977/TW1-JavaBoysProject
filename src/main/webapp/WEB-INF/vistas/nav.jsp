<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/home">
                <img class="small-logo" src="../img/logo_nav.png" alt="Logo">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon">
                <i class="fas fa-bars"></i>
              </span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="/home" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Mascotas
                        </a>
                        <div class="dropdown-menu" id="estadoSubmenu" aria-labelledby="navbarDropdown">
                            <!-- Opciones del submenu -->
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/refugios">Refugios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/cuidadores">Cuidadores</a>
                    </li>
                    <li class="nav-item dropdown">
                        <c:choose>
                            <c:when test="${empty sessionScope.NOMBRE}">
                                <a href="/login" class="btn btn-outline-secondary">Ingresar</a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link dropdown-toggle" href="/home" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Hola ${sessionScope.NOMBRE}
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="/mascotas/mis-mascotas?idUsuario=${sessionScope.IDUSUARIO}" >Mis Mascotas</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="/logout">Cerrar sesión</a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
        </div>
</nav>
