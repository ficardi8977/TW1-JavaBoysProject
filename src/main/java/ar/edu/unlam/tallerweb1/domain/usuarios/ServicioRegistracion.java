package ar.edu.unlam.tallerweb1.domain.usuarios;

public interface ServicioRegistracion {
    Boolean esValido(String correo);
    Boolean registroUsuario(String nombre, String apellido, String email, String password, String telefono);

    Boolean registrarUsuario(String email, String password);

    Boolean datosValidos(String correo, String password);
}
