<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
	<jsp:include page="head.jsp">
		<jsp:param name="head" value="head" />
	</jsp:include>
</head>
<body>

<jsp:include page="nav.jsp">
	<jsp:param name="nav" value="nav" />
</jsp:include>

<div id="carouselExampleInterval" class="carousel slide" data-ride="carousel">
<div class="carousel-inner">

	<div class="carousel-item active" data-interval="7000">
		<a href="/mascotas">
			<img src="../img/banner_01.png" class="img-fluid w-100" alt="...">
		</a>
	</div>
	<div class="carousel-item" data-interval="7000">
		<a href="/refugios">
			<img src="../img/banner_02_nuevo.png" class="img-fluid w-100" alt="...">
		</a>
	</div>
	<div class="carousel-item" data-interval="7000">
		<a href="/cuidadores">
			<img src="../img/banner_03.png" class="img-fluid w-100" alt="...">
		</a>
	</div>
</div>
<a class="carousel-control-prev" href="#carouselExampleInterval" role="button" data-slide="prev">
	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
	<span class="sr-only">Previous</span>
</a>
<a class="carousel-control-next" href="#carouselExampleInterval" role="button" data-slide="next">
	<span class="carousel-control-next-icon" aria-hidden="true"></span>
	<span class="sr-only">Next</span>
</a>
</div>

<!--Slick Carousel Slider-->
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/kenwheeler/slick@1.8.1/slick/slick-theme.css"/>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<h1 style="text-align: center;"> Mascotas perdidas </h1>
<div class="items m-0">
	<c:forEach  var="mascotaPerdida" items="${carruseles.perdidos}">
	<div class="card">
		<img src="img/${mascotaPerdida.imagen}" class="card-img-top" alt="Mascota....">
		<div class="card-body">
			<h5 class="card-title">${mascotaPerdida.nombre}</h5>
			<form action="/mascota/detalle" method="GET">
				<input name="id" value=${mascotaPerdida.id} id=${mascotaPerdida.id} type="hidden" class="form-control"/>
				<button class="dog-paw-button">Detalle</button>
			</form>
		</div>
	</div>
</c:forEach>
</div>

<h1 style="text-align: center;"> Cuidadadores </h1>
<div class="items m-2">
	<c:forEach  var="cuidador" items="${carruseles.cuidadores}">
		<div class="card">
			<img src="img/${cuidador.imagen}" class="card-img-top" alt="Mascota....">
			<div class="card-body">
				<h5 class="card-title">${cuidador.nombre}</h5>
				<form action="/cuidador/detalle" method="GET">
					<input name="id" value=${cuidador.id} id=${cuidador.id} type="hidden" class="form-control"/>
					<button class="dog-paw-button">Detalle</button>
				</form>
			</div>
		</div>
	</c:forEach>
</div>
<h1 style="text-align: center;"> Refugios </h1>
<div class="items m-0">
	<c:forEach  var="refugio" items="${carruseles.refugios}">
		<div class="card">
			<img src="img/${refugio.imagen}" class="card-img-top" alt="Mascota....">
			<div class="card-body">
				<h5 class="card-title">${refugio.nombre}</h5>
				<form action="refugio/${refugio.id}" method="GET">
					<input name="id" value=${refugio.id} id=${refugio.id} type="hidden" class="form-control"/>
					<button class="dog-paw-button">Detalle</button>
				</form>
			</div>
		</div>
	</c:forEach>
</div>


<jsp:include page="foot.jsp">
	<jsp:param name="foot" value="foot" />
</jsp:include>
<link href="../css/mascotas.css" rel="stylesheet" >
<link href="../css/carruseles.css" rel="stylesheet" >
<script src="../js/carruseles.js"></script>
</body>
</html>