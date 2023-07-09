package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ServicioRegistracionGoogleImpl implements  ServicioRegistracion{
    @Override
    public Boolean esValido(String correo) {
        return null;
    }

    @Override
    public Boolean registrarUsuario(String correo, String clave) {
        return null;
    }

    @Override
    public String encriptarClave(String clave) {
        return null;
    }

    public Boolean registroUsuario(DatosRegistracion datosRegistracion){
        return true;
    }

    @Override
    public Boolean datosValidos(DatosRegistracion datosRegistracion) {
        return null;
    }

    @Override
    public String registrarImagen(MultipartFile img){
        return null;
    }
}
