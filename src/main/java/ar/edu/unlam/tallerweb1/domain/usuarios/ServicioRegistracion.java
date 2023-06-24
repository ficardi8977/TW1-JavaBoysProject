package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;

public interface ServicioRegistracion {
    Boolean esValido(String correo);
    Boolean registroUsuario(DatosRegistracion datosRegistracion);

    Boolean registrarUsuario(String email, String password);

    Boolean datosValidos(String correo, String password);
}
