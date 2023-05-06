package ar.edu.unlam.tallerweb1.domain.usuarios;

public interface ServicioRegistracion {
    Boolean esValido(String correo);
    Boolean registrarUsuario(String correo, String clave);
}
