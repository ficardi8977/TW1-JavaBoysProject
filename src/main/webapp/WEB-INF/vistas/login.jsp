<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="head.jsp">
		<jsp:param name="head" value="head" />
	</jsp:include>
</head>
	<body>
	<section class="vh-100" style="background-color: #E6B9A0;">
		<div class="container py-5 h-100">
			<div class="row d-flex justify-content-center align-items-center h-100">
				<div class="col col-xl-10">
					<div class="card" style="border-radius: 1rem;">
						<div class="row g-0">
							<div class="col-md-6 col-lg-5 d-none d-md-block">
								<img src="../img/BannerLogin2.png"
									 alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
							</div>
							<div class="col-md-6 col-lg-7 d-flex align-items-center">
								<div class="card-body p-4 p-lg-5 text-black">

									<form action="/validar-login" method="post">

										<div class="d-flex align-items-center mb-3 pb-1">
											<img class="img-fluid img-thumbnail border-0 rounded mx-auto d-block" style="width: 50%; height: auto;" src="../img/AMIPETS2.PNG" alt="AmiPets">
										</div>

										<h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Iniciar sesión</h5>

										<div class="form-outline mb-4">
											<input type="email" name="email" id="email" class="form-control form-control-lg" />
											<label class="form-label" for="email">Email</label>
										</div>

										<div class="form-outline mb-4">
											<input type="password" name="password" id="password" class="form-control form-control-lg" />
											<label class="form-label" for="password">Contraseña</label>
										</div>
										<div>
											<c:if test="${not empty error}">
												<h5 class="text-error"><span>${error}</span></h5>
												<br>
											</c:if>
										</div>
										<div class="pt-1 mb-4">
											<input class="btn btn-dark btn-lg btn-block" type="submit">
										</div>
										<a class="small text-muted" href="#!">Olvidaste tu contraseña?</a>
										<p class="mb-5 pb-lg-2" style="color: #393f81;">No tienes cuenta?
											<a href="#!"style="color: #393f81;">Registrarte aqui</a></p>
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="foot.jsp">
		<jsp:param name="foot" value="foot" />
	</jsp:include>
	</body>
</html>
