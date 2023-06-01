<nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/mascotas">Mascotas App</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="/mascotas" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Mascotas
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" onclick="submenuSeleccionado(5)">Perdidos</a>
                            <a class="dropdown-item" onclick="submenuSeleccionado(2)">Adopción</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/mascotas">Todos</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/refugios">Refugios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="cuidadores">Cuidadores</a>
                    </li>
                </ul>
            </div>
        </div>
</nav>