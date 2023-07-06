package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public interface ServicioRegistracion {
    Boolean esValido(String correo);
    Boolean registroUsuario(DatosRegistracion datosRegistracion) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    Boolean registrarUsuario(String email, String password);

    String encriptarClave(String clave) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    Boolean datosValidos(DatosRegistracion datosRegistracion);
}
