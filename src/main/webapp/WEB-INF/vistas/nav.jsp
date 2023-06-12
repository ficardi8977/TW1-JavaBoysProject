<%@ page contentType="text/html; charset=UTF-8" %>
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
                        <a class="nav-link" href="/cuidadores">Cuidadores</a>
                    </li>
                </ul>
                <div class="dropleft">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="material-symbols-outlined">menu</span>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="mascotas/mis-mascotas?idUsuario=1">Mis Mascotas</a>
                    </div>
                </div>
            </div>
        </div>
</nav>
<style>
    .btn, .btn:hover, .btn:active, .show>.btn-secondary.dropdown-toggle{
        color: black;
        background-color: white;
        border: none;
    }
    .dropleft .dropdown-toggle::before {
        display: none;
    }
    .btn-secondary:focus, .show>.btn-secondary.dropdown-toggle:focus {
        box-shadow: none;
    }
    .show>.btn-secondary.dropdown-toggle {
        color: black;
        background-color: white;
        border: none;
    }
    .btn:focus {
        outline: none;
    }
    .btn-secondary{
        --bs-btn-active-bg: none;
    }
    .btn-secondary:not(:disabled):not(.disabled).active, .btn-secondary:not(:disabled):not(.disabled):active, .show>.btn-secondary.dropdown-toggle{
        color: black;
        background-color: white;
        border: none;
    }
    .btn-secondary:not(:disabled):not(.disabled).active:focus, .btn-secondary:not(:disabled):not(.disabled):active:focus, .show>.btn-secondary.dropdown-toggle:focus {
        box-shadow: none;
    }
</style>