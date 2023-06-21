package ar.edu.unlam.tallerweb1.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioRegistracionImpl implements ServicioRegistracion {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioRegistracionImpl (RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public ServicioRegistracionImpl(){}

    @Override
    public Boolean datosValidos(String correo, String password) {
        return this.repositorioUsuario.validarDatos(correo, password);
    }

    @Override
    public Boolean esValido(String correo) {
        return correo.endsWith(".com") && correo.contains("@");
    }

    @Override
    public Boolean registrarUsuario(String email, String password) {
        return true;
    }
    public Boolean registroUsuario(String nombre, String apellido, String email, String password, String telefono){
        return this.repositorioUsuario.registroUsuario(nombre, apellido, email, password, telefono);
    }
}
