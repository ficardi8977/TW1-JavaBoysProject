package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;
import ar.edu.unlam.tallerweb1.domain.excepciones.*;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class ServicioRegistracionImpl implements ServicioRegistracion {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    public ServicioRegistracionImpl (RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public ServicioRegistracionImpl(){}

    @Override
    public Boolean datosValidos(DatosRegistracion datosRegistracion) {
        String email = datosRegistracion.getEmail();
        String password = datosRegistracion.getPassword();
        String telefono = datosRegistracion.getTelefono();
        Boolean esValido = false;
        Boolean emailValido = email.endsWith(".com") && email.contains("@");
        Boolean telefonoValido = !telefono.equals("") && telefono!=null;
        Boolean pwValida = password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$");
        // Que tenga al menos una mayúscula, al menos una minúscula, al menos un digito y
        // que sea de entre 4 y 8 caracteres de largo

        if(!emailValido)
            throw new EmailInvalido();
        if(!pwValida)
            throw new PasswordInvalida();
        if(!telefonoValido)
            throw new IngresarTelefono();

        if(this.repositorioUsuario.buscar(email)==null) {
            esValido = true;
        } else {
            throw new EmailYaRegistrado();
        }

        return esValido;
    }

    @Override
    public Boolean esValido(String correo) {
        return correo.endsWith(".com") && correo.contains("@");
    }

    @Override
    public String encriptarClave(String clave) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        byte[] bytesClave;
        // Se encripta la contraseña ingresada con el algoritmo MD5

        md = MessageDigest.getInstance("MD5");
        bytesClave = clave.getBytes("UTF-8");


        byte[] hashClave = md.digest(bytesClave);

        StringBuffer sb = new StringBuffer();
        for (final byte b : hashClave) {
            sb.append(String.format("%02x", b));
        }
        String passwordCifrada = sb.toString().toUpperCase();
        return passwordCifrada;
    }
    @Override
    public Boolean registrarUsuario(String email, String password) {
        return true;
    }

    public Boolean registroUsuario(DatosRegistracion datosRegistracion) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        datosValidos(datosRegistracion);
        String nuevaPassword = encriptarClave(datosRegistracion.getPassword());
        datosRegistracion.setPassword(nuevaPassword);

        return this.repositorioUsuario.registroUsuario(datosRegistracion);
    }

    /*
        @Override
    public String registrarImagen(MultipartFile img) throws IOException {
        String directorioImagenes = servletContext.getRealPath("/img/");
        String rutaImagen = directorioImagenes + img.getOriginalFilename();
        byte[] bytes = img.getBytes();
        Path path = Paths.get(rutaImagen);
        Files.write(path, bytes);

        return img.getOriginalFilename();
    }
     */

    @Override
    public String registrarImagen(MultipartFile img) throws IOException {
        String directorioImagenes = servletContext.getRealPath("/img/");
        String nombreOriginal = img.getOriginalFilename();
        String extension = obtenerExtension(nombreOriginal);
        String nombreUnico = generarNombreUnico(nombreOriginal, extension);
        String rutaImagen = directorioImagenes + nombreUnico;
        byte[] bytes = img.getBytes();
        Path path = Paths.get(rutaImagen);
        Files.write(path, bytes);

        return nombreUnico;
    }

    private String obtenerExtension(String nombreArchivo) {
        int ultimoPunto = nombreArchivo.lastIndexOf(".");
        if (ultimoPunto != -1) {
            return nombreArchivo.substring(ultimoPunto);
        }
        return "";
    }

    private String generarNombreUnico(String nombreOriginal, String extension) {
        String uuid = UUID.randomUUID().toString();
        return nombreOriginal + "_" + uuid + extension;
    }
}
