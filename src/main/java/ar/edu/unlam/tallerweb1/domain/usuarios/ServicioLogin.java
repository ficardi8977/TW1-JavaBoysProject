package ar.edu.unlam.tallerweb1.domain.usuarios;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioLogin {

	Usuario consultarUsuario(String email, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

	String encriptarClave(String clave) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
