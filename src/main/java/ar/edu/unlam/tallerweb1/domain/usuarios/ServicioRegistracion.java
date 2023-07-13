package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public interface ServicioRegistracion {
    Boolean esValido(String correo);
    Boolean registroUsuario(DatosRegistracion datosRegistracion) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    Boolean registrarUsuario(String email, String password);

    String encriptarClave(String clave) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    Boolean datosValidos(DatosRegistracion datosRegistracion);

    String registrarImagen(MultipartFile img) throws IOException;

}
